package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.ShuffleBoardConfig;
import frc.robot.commands.cargo.SuckySuckCargo;
import frc.robot.utils.LimitSwitch;
import frc.robot.utils.XboxWrapper;;

/**
 * The cargo subsystem sets up all hardware related to the physical subsystem.
 * Creates methods to control arm and intake. Runs most functions through
 * in-class state machine. Calls default command to control intake.
 * 
 * @author Muaad and Reece
 */
public class Cargo extends Subsystem {

    /* CONSTANTS */

    // Joystick value scalars
    private static final double SPIN_SCALAR = 1;

    // Current limits
    private static final int ARM_MAX_AMPS = 10;
    private static final int ARM_CLIMB_AMPS = 25;
    private static final int INTAKE_MAX_AMPS = 15;

    // Arm position constants
    private static final int MAX_ARM_POS = 8150;
    public static int armFloor = -(MAX_ARM_POS);
    public static final int ARM_ROCKET = -4577; // -4466
    public static final int ARM_CARGOSHIP = -(MAX_ARM_POS - 6300);
    public static final int ARM_UP = -100;

    // Arm PID constants
    private static double P = 1023.0 / MAX_ARM_POS * 4;
    private static double I = 0;
    private static double D = 0;
    private static double F = 0;
    private static final int MAX_VEL = 750;
    private static final int MAX_ACCEL = 350;

    // Zero arm speed
    private final double UP_SPEED = 0.35;
    private final double DOWN_SPEED = -0.15;

    // Max holding change in error
    private final double MAX_HOLD_ERROR_CHANGE = 0.1;

    // Maximum error derivative in arm PID loop
    double maxErrorDerivative = 0;

    /* INSTANCE VARIABLES */

    // Speed controller Talons
    private WPI_TalonSRX leftArm = new WPI_TalonSRX(RobotMap.LEFT_ARM_FOLLOWER);
    private WPI_TalonSRX rightArm = new WPI_TalonSRX(RobotMap.RIGHT_ARM_MASTER);

    // Basic intake Talon
    private WPI_TalonSRX intake = new WPI_TalonSRX(RobotMap.INTAKE);

    // Limit switches
    private LimitSwitch lowerLimit = new LimitSwitch(RobotMap.CARGO_ARM_LOWER_LIMIT_SWITCH, true);
    private LimitSwitch upperLimit = new LimitSwitch(RobotMap.CARGO_ARM_UPPER_LIMIT_SWITCH, true);

    /* STATE MACHINE MEMBERS */

    // Subsystem state
    private CargoDesiredState desiredState = CargoDesiredState.UPPER_LIMIT;
    private CargoSystemState systemState = CargoSystemState.NOT_ZEROED;

    // Climbing piston
    private DoubleSolenoid climbPiston = new DoubleSolenoid(RobotMap.CLIMB_PISTON_FORWARD,
            RobotMap.CLIMB_PISTON_REVERSE);

    /* SHUFFLEBOARD ENTRIES */

    // Shuffleboard diagnostic values
    private final NetworkTableEntry encoderEntry = ShuffleBoardConfig.cargo.add("Arm Encoder", 0).getEntry();
    private final NetworkTableEntry desiredStateEntry = ShuffleBoardConfig.cargo
            .add("Cargo Desired State", desiredState.toString()).getEntry();
    private final NetworkTableEntry systemStateEntry = ShuffleBoardConfig.cargo
            .add("Cargo System State", systemState.toString()).getEntry();
    private final NetworkTableEntry errorDerivativeEntry = ShuffleBoardConfig.cargo.add("PID Error Derivative", 0)
            .getEntry();

    /* SUBSYSTEM CONSTRUCTOR */

    /**
     * Cargo subsystem constructor configures all the Talons that are part of this
     * subsystem as described by comments below.
     */
    public Cargo() {
        // Arm Talon inversions
        leftArm.setInverted(false);
        rightArm.setInverted(false);

        // Don't invert intake Talon
        intake.setInverted(false);

        // Set arm Talons to break when no power
        leftArm.setNeutralMode(NeutralMode.Brake);
        rightArm.setNeutralMode(NeutralMode.Brake);

        // Set intake Talon to coast
        intake.setNeutralMode(NeutralMode.Coast);

        // Set right arm Talon to follow left arm Talon
        leftArm.follow(rightArm);

        rightArm.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
        rightArm.setSelectedSensorPosition(0, 0, 0);
        rightArm.setSensorPhase(true);

        ShuffleBoardConfig.cargo.add("Arm", rightArm);
        ShuffleBoardConfig.cargo.add("Intake", intake);

        ShuffleBoardConfig.cargo.add("Upper Limit", upperLimit).withSize(2, 1).withPosition(1, 0);
        ShuffleBoardConfig.cargo.add("Lower Limit", lowerLimit).withSize(2, 1).withPosition(3, 0);

        leftArm.enableCurrentLimit(true);
        rightArm.enableCurrentLimit(true);
        intake.enableCurrentLimit(true);

        leftArm.configContinuousCurrentLimit(ARM_MAX_AMPS);
        rightArm.configContinuousCurrentLimit(ARM_MAX_AMPS);
        intake.configContinuousCurrentLimit(INTAKE_MAX_AMPS);

        rightArm.config_kP(0, P);
        rightArm.config_kI(0, I);
        rightArm.config_kD(0, D);
        rightArm.config_kF(0, F);

        rightArm.configMotionAcceleration(MAX_ACCEL);
        rightArm.configMotionCruiseVelocity(MAX_VEL);

        ShuffleBoardConfig.cargo.add(this).withPosition(4, 1);

        setClimber(Value.kForward);
    }

    /* CONTROL METHODS */

    public void setClimber(Value val) {
        climbPiston.set(val);
    }

    @Override
    public void periodic() {
        encoderEntry.setNumber(rightArm.getSelectedSensorPosition(0));
        desiredStateEntry.setString(desiredState.toString());
        systemStateEntry.setString(systemState.toString());

        double errorDerivative = Math.abs(rightArm.getErrorDerivative());
        if (errorDerivative > maxErrorDerivative) {
            maxErrorDerivative = errorDerivative;
        }
        errorDerivativeEntry.setDouble(errorDerivative);

        if (systemState != CargoSystemState.MANUAL) {
            leftArm.configContinuousCurrentLimit(ARM_MAX_AMPS);
            rightArm.configContinuousCurrentLimit(ARM_MAX_AMPS);
        }

        switch (systemState) {
        case MANUAL:
            double power = Robot.oi.manipulator.getY(Hand.kRight);
            rightArm.set(power * 0.6);
            if (power > 0) {
                leftArm.configContinuousCurrentLimit(ARM_CLIMB_AMPS);
                rightArm.configContinuousCurrentLimit(ARM_CLIMB_AMPS);
            } else {
                leftArm.configContinuousCurrentLimit(ARM_MAX_AMPS);
                rightArm.configContinuousCurrentLimit(ARM_MAX_AMPS);
            }
            break;

        case UP_PID:
            if (upperLimit.get()) {
                transitionSystemState(CargoSystemState.UP_LIMIT);
            } else if (errorDerivative < maxErrorDerivative && errorDerivative <= MAX_HOLD_ERROR_CHANGE) {
                transitionSystemState(CargoSystemState.UP_PO);
            }
            break;

        case NOT_ZEROED:
            // Fall through
        case UP_PO:
            if (upperLimit.get()) {
                transitionSystemState(CargoSystemState.UP_LIMIT);
            } else {
                rightArm.set(UP_SPEED);
            }
            break;

        case UP_LIMIT:
            if (upperLimit.get()) {
                rightArm.set(ControlMode.Position, 0);
            } else {
                transitionSystemState(CargoSystemState.UP_PO);
            }
            break;

        case DOWN_PID:
            if (lowerLimit.get()) {
                transitionSystemState(CargoSystemState.DOWN_LIMIT);
            } else if (errorDerivative < maxErrorDerivative && errorDerivative <= MAX_HOLD_ERROR_CHANGE) {
                transitionSystemState(CargoSystemState.DOWN_PO);
            }
            break;

        case DOWN_PO:
            if (lowerLimit.get()) {
                transitionSystemState(CargoSystemState.DOWN_LIMIT);
            } else {
                rightArm.set(DOWN_SPEED);
            }
            break;

        case DOWN_LIMIT:
            if (lowerLimit.get()) {
                rightArm.set(ControlMode.Position, armFloor);
            } else {
                transitionSystemState(CargoSystemState.DOWN_PO);
            }

        default:
            break;
        }
    }

    private void transitionSystemState(CargoSystemState state) {
        if (state == systemState)
            return;
        switch (state) {
        case UP_PID:
            maxErrorDerivative = 0;
            rightArm.set(ControlMode.Position, ARM_UP);
            break;

        case DOWN_PID:
            maxErrorDerivative = 0;
            rightArm.set(ControlMode.Position, armFloor);
            break;

        case ROCKET_PID:
            rightArm.set(ControlMode.Position, ARM_ROCKET);
            break;

        case CARGO_SHIP_PID:
            rightArm.set(ControlMode.Position, ARM_CARGOSHIP);
            break;

        case UP_LIMIT:
            rightArm.setSelectedSensorPosition(0);
            Robot.oi.manipulator.rumbleFor(XboxWrapper.RUMBLE_TIME);
            break;

        case DOWN_LIMIT:
            armFloor = rightArm.getSelectedSensorPosition();
            Robot.oi.manipulator.rumbleFor(XboxWrapper.RUMBLE_TIME);
            break;

        default:
            break;
        }
        systemState = state;
    }

    public void setArmState(CargoDesiredState state) {
        if (state == desiredState || (systemState == CargoSystemState.NOT_ZEROED && state != CargoDesiredState.MANUAL))
            return;
        switch (state) {
        case UPPER_LIMIT:
            transitionSystemState(CargoSystemState.UP_PID);
            break;

        case LOWER_LIMIT:
            transitionSystemState(CargoSystemState.DOWN_PID);
            break;

        case ROCKET:
            transitionSystemState(CargoSystemState.ROCKET_PID);
            break;

        case CARGO_SHIP:
            transitionSystemState(CargoSystemState.CARGO_SHIP_PID);
            break;

        case MANUAL:
            rightArm.set(0);
            transitionSystemState(CargoSystemState.MANUAL);
            break;
        }
        desiredState = state;
    }

    /**
     * Spins intake at given speed.
     * 
     * @param speed The speed at which to spin the intake, range [-1, 1]
     */
    public void spinIntake(double speed) {
        intake.set(speed * SPIN_SCALAR);
    }

    public void setNeutral(NeutralMode neutralMode) {
        leftArm.setNeutralMode(neutralMode);
        rightArm.setNeutralMode(neutralMode);
    }

    public boolean isNotZeroed() {
        return systemState == CargoSystemState.NOT_ZEROED;
    }

    public boolean isManualMode() {
        return systemState == CargoSystemState.MANUAL;
    }

    public enum CargoDesiredState {
        MANUAL, UPPER_LIMIT, CARGO_SHIP, ROCKET, LOWER_LIMIT;
    }

    private enum CargoSystemState {
        MANUAL, NOT_ZEROED, ROCKET_PID, CARGO_SHIP_PID, UP_PID, DOWN_PID, UP_PO, DOWN_PO, UP_LIMIT, DOWN_LIMIT;
    }

    /* IMPLEMENTED METHODS */

    /**
     * Calls default command to run subsystem Talons.
     */
    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new SuckySuckCargo());
    }
}