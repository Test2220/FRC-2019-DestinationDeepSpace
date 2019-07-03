package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.utils.XboxWrapper.Button;

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
    private static final double MAX_CHANGE = 0.125;

    private double lastDrivePower = 0;
    private double lastTurnPower = 0;

    // private final double MAX_CHANGE = 0.001;

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

        Robot.oi.childActive = Robot.oi.childMode && (Robot.oi.driver.getTrigger(Hand.kLeft) > 0.5);

        double drivePower = Robot.oi.childActive ? -Robot.oi.manipulator.getY(Hand.kLeft) : -Robot.oi.driver.getY(Hand.kLeft);
        double turnPower = Robot.oi.childActive ? Robot.oi.manipulator.getX(Hand.kRight) : Robot.oi.driver.getX(Hand.kRight);
        turnPower *= TURN_MULTIPLIER;

        // Limit acceleration by limiting the power differential from the previous iteration 
        double dpForward = (drivePower - lastDrivePower);
        double dpTurn = (turnPower - lastTurnPower);

        if (Math.abs(dpForward) > MAX_CHANGE) {
            drivePower = lastDrivePower + Math.signum(dpForward) * MAX_CHANGE;
        }

        if (Math.abs(dpTurn) > MAX_CHANGE) {
            turnPower = lastTurnPower + Math.signum(dpTurn) * MAX_CHANGE;
        }

        lastDrivePower = drivePower;
        lastTurnPower = turnPower;

        //Slow mode for turning only
        if (Robot.oi.driver.getControllerObject().getTriggerAxis(Hand.kLeft) >= 0.25) {
            turnPower *= 0.5;
        }
        
        //reverse mode
        if (Robot.oi.driver.getControllerObject().getTriggerAxis(Hand.kRight) >= 0.25) {
            drivePower *= -1;
        }
        
        Robot.drivetrain.drive(drivePower, turnPower);
    }

    /**
     * Drive with xbox command never finishes.
     */
    @Override
    protected boolean isFinished() {
        return false;
    }
}