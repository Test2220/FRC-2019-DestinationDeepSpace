package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * Drive with xbox command references driver controller xbox controller defined
 * in OI in loop to control drivetrain via differential drive's curvature drive
 * method.
 * 
 * @author Muaad, Reece
 */
public class DriveWithXbox extends Command {

    /* CONSTANTS */

    // Turning multiplier
    private static final double TURN_MULTIPLIER = 0.9;

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
        double turn = Robot.oi.driver.getX(Hand.kRight) * TURN_MULTIPLIER;

        //Slow mode for turning only
        // if (Robot.oi.driver.getTrigger(Hand.kLeft) >= 0.25) {
        //     turn *= 0.5;
        // }
        
        //reverse mode
        // if (Robot.oi.driver.getTrigger(Hand.kRight) >= 0.25) {
        //     power *= -1;
        // }

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