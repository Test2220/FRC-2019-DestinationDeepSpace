package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.RobotMap;
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
    private static final double SPIN_SCALAR = 3 / 4;
    private static final double MOVE_SCALAR = 3 / 10;

    /* INSTANCE VARIABLES */

    // Speed controller Talons
    private WPI_TalonSRX leftArm = new WPI_TalonSRX(RobotMap.LEFT_ARM);
    private WPI_TalonSRX rightArm = new WPI_TalonSRX(RobotMap.RIGHT_ARM);

    // Basic intake Talon
    private TalonSRX intake = new TalonSRX(RobotMap.INTAKE);

    /* SUBSYSTEM CONSTRUCTOR */

    /**
     * Cargo subsystem constructor configures all the Talons that are part of this
     * subsystem as described by comments below.
     */
    public Cargo() {
        // Arm Talon inversions
        leftArm.setInverted(false);
        rightArm.setInverted(true);

        // Don't invert intake Talon
        intake.setInverted(false);

        // Set arm Talons to break when no power
        leftArm.setNeutralMode(NeutralMode.Brake);
        rightArm.setNeutralMode(NeutralMode.Brake);

        // Set intake Talon to coast
        intake.setNeutralMode(NeutralMode.Coast);

        // Set right arm Talon to follow left arm Talon
        rightArm.follow(leftArm);
    }

    /* CONTROL METHODS */

    /**
     * Moves arm by setting power to Talons.
     * 
     * @param power The power at which to move the Talons, range [-1, 1]
     */
    public void moveArm(double power) {
        power *= MOVE_SCALAR;
        leftArm.set(power);
    }

    /**
     * Spins intake at given speed.
     * 
     * @param speed The speed at which to spin the intake, range [-1, 1]
     */
    public void spinIntake(double speed) {
        speed *= SPIN_SCALAR;
        intake.set(ControlMode.PercentOutput, speed);
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