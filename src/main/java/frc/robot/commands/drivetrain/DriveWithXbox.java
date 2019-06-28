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

    // Acceleration limiting
    private double lastPower = 0;
    private double lastTurn = 0;
    private final double MAX_POWER_CHANGE = 0.125;
    private final double MAX_TURN_CHANGE = 0.125; // TODO: Consider raising this as split second turning is valuable to drivers

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

        // Acceleration limiting
        double changePower = (power - lastPower);
        double changeTurn = (turn - lastTurn);

        // If change in power || turn is > max change limit change to the max change
        if (Math.abs(changePower) > MAX_POWER_CHANGE) {
            power = lastPower + Math.signum(changePower) * MAX_POWER_CHANGE;
        }

        if (Math.abs(changeTurn) > MAX_TURN_CHANGE) {
            turn = lastTurn + Math.signum(changeTurn) * MAX_TURN_CHANGE;
        }

        // Set current power & turn to last power & turn for next iteration
        lastPower = power;
        lastTurn = turn;

        Robot.drivetrain.drive(power, turn * TURN_MULTIPLIER);
    }

    /**
     * Drive with xbox command never finishes.
     */
    @Override
    protected boolean isFinished() {
        return false;
    }
}