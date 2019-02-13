package frc.robot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.robot.Robot;

public class TurnToAngle extends PIDCommand {

    private double targetAngle;
    private PIDController pidController;

    public TurnToAngle(double targetAngle) {
        super(0.0145, 0, 0.0376);

        requires(Robot.navX);
        requires(Robot.drivetrain);

        this.targetAngle = targetAngle;
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
    }

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
        pidController.disable();
        System.out.println("Command is finished.");
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}