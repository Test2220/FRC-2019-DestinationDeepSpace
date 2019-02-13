package frc.robot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.robot.Robot;

/**
 * NOT DONE --- Corrects the position of the robot using the gyro and limelight in conjunction with each other
 * 
 * @author Dhruv
 */
public class CorrectPositionGyro extends PIDCommand {

    private double targetAngle;
    private PIDController pidController;

    public CorrectPositionGyro() {
        super(0.0145, 0, 0.0376);

        requires(Robot.navX);
        requires(Robot.limelight);
        requires(Robot.drivetrain);
    }

    /* INSTANCE METHODS */

    @Override
    protected double returnPIDInput() {
        return Robot.navX.getAngle();
    }

    @Override
    protected void usePIDOutput(double output) {
        double turn = output;
        Robot.drivetrain.curvatureDrive(0, turn);
    }

    /**
     * The initialize method is called the first time this Command is run after
     * being started. Enables the PID controller.
     */
    @Override
    public void initialize() {
        Robot.navX.zeroAngle();
        pidController.setSetpoint(targetAngle);
        pidController.setAbsoluteTolerance(5);
        pidController.enable();
    }

    @Override
    protected void execute() {
        System.out.println("Current Gyro val: " + Robot.navX.getAngle());
        System.out.println("PID Get: " + pidController.get());

        double setpoint = Robot.navX.getAngle() + Robot.limelight.getHOffset();

        pidController.setSetpoint(setpoint);
    }

    /**
     * Called when the command ended peacefully. Disables the PI controller.
     */
    @Override
    public void end() {
        pidController.disable();
        System.out.println("Command is finished.");
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}