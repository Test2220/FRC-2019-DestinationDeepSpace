package frc.robot.commands;

import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.robot.Robot;

/**
 * Turns the robot to a certain angle specified when this command is called.
 */
public class TurnToAngle extends PIDCommand {

    private double targetAngle;

    /**
     * Constructor that sets up the PID controller and initializes the targetAngle.
     * 
     * @param targetAngle
     */
    public TurnToAngle(double targetAngle) {
        super(0.0145, 0, 0.0376);

        requires(Robot.navX);
        requires(Robot.drivetrain);

        this.targetAngle = targetAngle;
    }

    /* INSTANCE METHODS */

    /**
     * Essentially a getter for the pidInput for the PIDController class.
     * 
     * @return double the PID output for the controller to use.
     */
    @Override
    protected double returnPIDInput() {
        return Robot.navX.getAngle();
    }

    /**
     * Writes the PID output by setting power to the motors.
     * 
     * @param output the output given by the PID controller.
     */
    @Override
    protected void usePIDOutput(double output) {
        double turn = output;
        Robot.drivetrain.drive(0, turn);
    }

    /**
     * The initialize method is called the first time this Command is run after
     * being started. Zeroes the NavX and sets the setpoint to the target angle.
     */
    @Override
    public void initialize() {
        Robot.navX.zeroAngle();
        setSetpoint(targetAngle);
    }

    /**
     * The execute method is called repeatedly until this Command either finishes or is canceled.
     * Prints value to console to know when the command is running..
     */
    @Override
    protected void execute() {
        System.out.println("Current Gyro val: " + Robot.navX.getAngle());
        System.out.println("PID Get: " + getPIDController().get());
    }

    /**
     * Prints a message when this command is interrupted for debugging purposes.
     */
    @Override
    protected void interrupted() {
        System.out.println("Interrupted");
        super.interrupted();
    }

    /**
     * Called when the command ended peacefully. Disables the PI controller.
     */
    @Override
    public void end() {
        System.out.println("Command is finished.");
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}