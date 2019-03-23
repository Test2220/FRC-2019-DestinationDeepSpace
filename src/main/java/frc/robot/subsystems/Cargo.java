package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.ShuffleBoardConfig;
import frc.robot.commands.cargo.ManipulateCargo;
import frc.robot.utils.LimitSwitch;
import frc.robot.utils.XboxWrapper;;

/**
 * The cargo subsystem sets up all hardware related to the physical subsystem.
 * Creates methods to control arm and intake. Calls default command to control
 * subsystem.
 * 
 * @author Muaad
 */
public class Cargo extends Subsystem {

    /* CONSTANTS */

    // Joystick value scalars
    private static final double SPIN_SCALAR = 1;

    // Current limits
    private static final int ARM_MAX_AMPS = 2;
    private static final int INTAKE_MAX_AMPS = 10;

    // Arm position constants
    private static final int MAX_ARM_POS = 7908;
    public static final int ARM_FLOOR = -(MAX_ARM_POS);
    public static final int ARM_ROCKET = -(MAX_ARM_POS - 3600);
    public static final int ARM_CARGOSHIP = -(MAX_ARM_POS - 6000);
    public static final int ARM_UP = -(MAX_ARM_POS - 7800);

    // Arm PID constants
    private static double P = 1023.0 / MAX_ARM_POS * 4;
    private static double I = 0;
    private static double D = 0;
    private static double F = 0;
    private static final int MAX_VEL = 750;
    private static final int MAX_ACCEL = 350;

    // Zero arm speed
    private final double ZERO_SPEED = 0.3;

    /* INSTANCE VARIABLES */

    // Speed controller Talons
    private WPI_TalonSRX leftArm = new WPI_TalonSRX(RobotMap.LEFT_ARM_FOLLOWER);
    private WPI_TalonSRX rightArm = new WPI_TalonSRX(RobotMap.RIGHT_ARM_MASTER);

    // Basic intake Talon
    private WPI_TalonSRX intake = new WPI_TalonSRX(RobotMap.INTAKE);

    // Limit switches
    private LimitSwitch lowerLimit = new LimitSwitch(RobotMap.LOWER_CARGO_LIMIT, true);
    private LimitSwitch upperLimit = new LimitSwitch(RobotMap.UPPER_CARGO_LIMIT, true);

    /* STATE MACHINE MEMBERS */

    // Subsystem state
    private CargoState cargoState = CargoState.NOT_ZEROED;

    // Requested position
    private int requestedPosition = 0;

    // Processed requested position
    private boolean processedRequestedPosition = true;

    // Last position
    private int lastPosition = 0;

    private final NetworkTableEntry encoderEntry = ShuffleBoardConfig.cargo.add("Arm Encoder", 0).getEntry();
    private final NetworkTableEntry stateEntry = ShuffleBoardConfig.cargo.add("Cargo State", cargoState.toString()).getEntry();

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

    }

    /* CONTROL METHODS */

    @Override
    public void periodic() {
        encoderEntry.setNumber(rightArm.getSelectedSensorPosition(0));
        stateEntry.setString(cargoState.toString());

        // State machine
        switch (cargoState) {
            case NOT_ZEROED:
                rightArm.set(ZERO_SPEED);
                if (upperLimit.get()) {
                    rightArm.setSelectedSensorPosition(0);
                    rightArm.set(ControlMode.Position, -50);
                    rightArm.setSelectedSensorPosition(0);
                    setState(CargoState.UPPER_LIMIT);
                }
                break;

            case UPPER_LIMIT:
                if (requestedPosition < lastPosition) {
                    setArmPosition();
                }

                if (!upperLimit.get()) {
                    setState(CargoState.FREE);
                }
                break;

            case LOWER_LIMIT:
                if (requestedPosition > lastPosition) {
                    setArmPosition();
                }

                if (!lowerLimit.get()) {
                    setState(CargoState.FREE);
                }

                break;

            case FREE:
                setArmPosition();
                Robot.oi.manipulator.stopRumble();

                if (upperLimit.get()) {
                    setState(CargoState.UPPER_LIMIT);
                    rightArm.set(ControlMode.Position, rightArm.getSelectedSensorPosition());
                } else if (lowerLimit.get()) {
                    setState(CargoState.LOWER_LIMIT);
                    rightArm.set(ControlMode.Position, rightArm.getSelectedSensorPosition());
                } 
                break; 
        }
    }

    private void setState(CargoState state) {
        if (cargoState != state) {
            System.out.println(cargoState.toString() + " -> " + state.toString());
            if (state == CargoState.UPPER_LIMIT || state == CargoState.LOWER_LIMIT) {
                Robot.oi.manipulator.rumbleFor(XboxWrapper.RUMBLE_TIME);
            }
        }
        cargoState = state;
    }

    public void reZeroArm() {
        lastPosition = 0;
        requestedPosition = 0;
        processedRequestedPosition = true;
        setState(CargoState.NOT_ZEROED);
    }

    /**
     * Spins intake at given speed.
     * 
     * @param speed The speed at which to spin the intake, range [-1, 1]
     */
    public void spinIntake(double speed) {
        intake.set(speed * SPIN_SCALAR);
    }

    public void requestArmPosition(int pos) {
        if (requestedPosition != pos) {
            requestedPosition = pos;
            processedRequestedPosition = false;
        }
    }

    private void setArmPosition() { //TODO: remove parameter?
        if (!processedRequestedPosition) {
            rightArm.set(ControlMode.Position, requestedPosition);
            lastPosition = requestedPosition;
            processedRequestedPosition = true;
        }
    }

    public void setNeutral(NeutralMode neutralMode) {
        leftArm.setNeutralMode(neutralMode);
        rightArm.setNeutralMode(neutralMode);
    }

    // private void zeroArmEncoder() {
    //     // Rotate arm down maximum amount of encoder ticks possible TODO: Check MAX_ARM_POS works
    //     rightArm.set(ControlMode.Position, -ZERO_TICK_AMOUNT);
    //     if (lowerLimit.get()) {
    //         rightArm.setSelectedSensorPosition(0);
    //         setArmPosition(0);
    //         cargoState = CargoState.LOWER_LIMIT;
    //     }
    // }

    public enum CargoState {
        NOT_ZEROED, LOWER_LIMIT, UPPER_LIMIT, FREE;
    }

    /* IMPLEMENTED METHODS */

    /**
     * Calls default command to run subsystem Talons.
     */
    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new ManipulateCargo());
    }
}