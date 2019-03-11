package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import frc.robot.RobotMap;
import frc.robot.ShuffleBoardConfig;
import frc.robot.commands.cargo.ManipulateCargo;;

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
    private static final double SPIN_SCALAR = 0.75;
    private static final double MOVE_SCALAR = 0.3;

    // Current limits
    private static final int ARM_MAX_AMPS = 2;
    private static final int INTAKE_MAX_AMPS = 10;

    // Arm position constants
    public static final int ARM_FLOOR = 59;
    public static final int ARM_ROCKET = 3600;
    public static final int ARM_CARGOSHIP = 6000;
    public static final int ARM_UP = 7700;

    // Arm PID constants
    private static double P = 1023.0 / 7908 * 4;
    private static double I = 0;
    private static double D = 0;
    private static double F = 0;
    private static final int MAX_VEL = 750;
    private static final int MAX_ACCEL = 350;

    /* INSTANCE VARIABLES */

    // Speed controller Talons
    private WPI_TalonSRX leftArm = new WPI_TalonSRX(RobotMap.LEFT_ARM_FOLLOWER);
    private WPI_TalonSRX rightArm = new WPI_TalonSRX(RobotMap.RIGHT_ARM_MASTER);

    // Basic intake Talon
    private WPI_TalonSRX intake = new WPI_TalonSRX(RobotMap.INTAKE);

    private final NetworkTableEntry encoderEntry = ShuffleBoardConfig.diagnosticsTab.add("Arm Encoder", 0).withWidget(BuiltInWidgets.kGraph).getEntry();
    // private final NetworkTableEntry pEntry = ShuffleBoardConfig.diagnosticsTab.add("P", P).getEntry();
    // private final NetworkTableEntry iEntry = ShuffleBoardConfig.diagnosticsTab.add("I", 0).getEntry();
    // private final NetworkTableEntry dEntry = ShuffleBoardConfig.diagnosticsTab.add("D", 0).getEntry();

    /* SUBSYSTEM CONSTRUCTOR */

    /**
     * Cargo subsystem constructor configures all the Talons that are part of this
     * subsystem as described by comments below.
     */
    public Cargo() {
        
        // Arm Talon inversions
        leftArm.setInverted(true);
        rightArm.setInverted(true);

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

        ShuffleBoardConfig.cargoLayout.add("Arm", rightArm);
        ShuffleBoardConfig.cargoLayout.add("Intake", intake);

        leftArm.enableCurrentLimit(true);
        rightArm.enableCurrentLimit(true);
        intake.enableCurrentLimit(true);

        leftArm.configContinuousCurrentLimit(ARM_MAX_AMPS);
        rightArm.configContinuousCurrentLimit(ARM_MAX_AMPS);
        intake.configContinuousCurrentLimit(INTAKE_MAX_AMPS);

        rightArm.config_kP(0, P);
        rightArm.config_kI(0, I);
        rightArm.config_kD(0, D);

        rightArm.configMotionAcceleration(MAX_ACCEL);
        rightArm.configMotionCruiseVelocity(MAX_VEL);
    }

    /* CONTROL METHODS */

    /**
     * Moves arm by setting power to Talons.
     * 
     * @param power The power at which to move the Talons, range [-1, 1]
     */
    public void moveArm(double power) {
        leftArm.set(power * MOVE_SCALAR);
    }

    @Override
    public void periodic() {
        encoderEntry.setNumber(rightArm.getSelectedSensorPosition(0));
        // F = (rightArm.getSelectedSensorVelocity() < 0) ? -Math.cos((rightArm.getSelectedSensorPosition() / 7908) * (Math.PI / 2)) : 0;
        // F = -Math.cos((rightArm.getSelectedSensorPosition() / 7908) * (Math.PI / 2));
        // F = (rightArm.getSelectedSensorPosition() / 7908) * (Math.PI / 2);
        // F = (rightArm.getSelectedSensorVelocity() >= 0) ? Math.cos(F) : Math.sin(F);
        // P = pEntry.getDouble(P);
        // I = iEntry.getDouble(I);
        // D = dEntry.getDouble(D);

        // rightArm.config_kP(0, P);
        // rightArm.config_kI(0, I);
        // rightArm.config_kD(0, D);
        // rightArm.config_kF(0, F);
    }

    /**
     * Spins intake at given speed.
     * 
     * @param speed The speed at which to spin the intake, range [-1, 1]
     */
    public void spinIntake(double speed) {
        intake.set(speed * SPIN_SCALAR);
    }

    public void setArmPosition(int pos) {
        rightArm.set(ControlMode.Position, pos);
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