package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * Drive with xbox command references driver controller xbox controller defined
 * in OI in loop to control drivetrain via differential drive's curvature drive
 * method
 * 
 * @author Muaad, Reece
 */

public class DriveXbox extends Command {

    // Power to raise forward and backward movement to (handles all powers)
    private static final double EXP_DRIVE_POWER = 1.4;

    /**
     * Drive with xbox command constructor, nothing necessary here besides requiring
     * drivetrain subsystem
     */
    public DriveXbox() {
        requires(Robot.drivetrain);
    }

    /**
     * Executes code to run drivetrain in loop, uses exponential drive with
     * specified power above
     */
    @Override
    public void execute() {
        // Grab joystick values used for curvature drive calculation
        double move = -Robot.oi.getDC().getY(Hand.kLeft);
        double turn = Robot.oi.getDC().getX(Hand.kRight);
        // Exponential drive for forward and backward movement
        move = Math.pow(Math.abs(move), EXP_DRIVE_POWER) * Math.signum(move);
        // Run curvature drive off of drivetrain subsystem
        Robot.drivetrain.curvatureDrive(move, turn);
    }

    /**
     * Drive with xbox command never finishes
     */
    @Override
    public boolean isFinished() {
        return false;
    }
}