package frc.robot.commands.limelight.unfinished;

import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.robot.Robot;

/**
 * NOT DONE --- Corrects the position of the robot using the gyro and limelight in conjunction with each other
 * 
 * @author Dhruv
 */
public class AlignToVisionTargetGyro extends PIDCommand {

    private double targetAngle;

    public AlignToVisionTargetGyro() {
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
        Robot.drivetrain.drive(0, turn);
    }

    /**
     * The initialize method is called the first time this Command is run after
     * being started. Enables the PID controller.
     */
    @Override
    public void initialize() {
        Robot.navX.zeroAngle();
        getPIDController().setSetpoint(targetAngle);
        getPIDController().setAbsoluteTolerance(5);
    }

    @Override
    protected void execute() {
        // System.out.println("Current Gyro val: " + Robot.navX.getAngle());
        // System.out.println("PID Get: " + getPIDController().get());

        double setpoint = Robot.navX.getAngle() + Robot.limelight.getHOffset();

        setSetpoint(setpoint);
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