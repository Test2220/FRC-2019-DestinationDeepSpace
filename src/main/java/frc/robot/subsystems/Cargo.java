package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.command.Subsystem;

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

    /* INSTANCE VARIABLES */

    // Speed controller Talons
    private WPI_TalonSRX leftArm = new WPI_TalonSRX(RobotMap.LEFT_ARM_FOLLOWER);
    private WPI_TalonSRX rightArm = new WPI_TalonSRX(RobotMap.RIGHT_ARM_MASTER);

    // Basic intake Talon
    private WPI_TalonSRX intake = new WPI_TalonSRX(RobotMap.INTAKE);

    private final NetworkTableEntry encoderEntry = ShuffleBoardConfig.diagnosticsTab.add("Arm Encoder", 0).getEntry();

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
    }

    /**
     * Spins intake at given speed.
     * 
     * @param speed The speed at which to spin the intake, range [-1, 1]
     */
    public void spinIntake(double speed) {
        intake.set(ControlMode.PercentOutput, speed * SPIN_SCALAR);
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