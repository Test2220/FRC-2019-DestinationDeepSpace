package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.OI;

/**
 * Drive with xbox command references driver controller xbox controller defined
 * in OI in loop to control drivetrain via differential drive's curvature drive
 * method.
 * 
 * @author Muaad, Reece
 */
public class DriveWithXbox extends Command {

    /* CONSTANTS */

    // Power to raise driving and turning to (handles all powers)
    private static final double EXP_DRIVE_POWER = 1.4; // TODO -> Choose final value for driving and turning exp power
    private static final double EXP_TURN_POWER = 1.6;

    /* COMMAND CONSTRUCTOR */

    /**
     * Drive with xbox command constructor, nothing necessary here besides requiring
     * drivetrain subsystem.
     */
    public DriveWithXbox() {
        super(Robot.drivetrain);
    }

    /* IMPLEMENTED METHODS */

    /**
     * Executes code to run drivetrain in loop, uses exponential drive with
     * specified power above.
     */
    @Override
    protected void execute() {
        // Grab joystick values used for curvature drive calculation
        double power = -Robot.oi.driver.getY(Hand.kLeft);
        double turn = Robot.oi.driver.getX(Hand.kRight);
        // Exponential drive for forward and backward movement
        power = 0.25 * Math.pow(Math.abs(power), EXP_DRIVE_POWER) * Math.signum(power);
        // Exponential turning for more control or speed
        turn = 0.25 * Math.pow(Math.abs(turn), EXP_TURN_POWER) * Math.signum(turn);
        // Run curvature drive off of drivetrain subsystem
        Robot.drivetrain.drive(power, turn);
    }

    /**
     * Drive with xbox command never finishes.
     */
    @Override
    protected boolean isFinished() {
        return false;
    }
}