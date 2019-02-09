package frc.robot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class TurnToAngle extends Command {

    private double targetAngle;
    private PIDController pidController;

    public TurnToAngle(double targetAngle) {
        requires(Robot.navX);
        requires(Robot.drivetrain);
        this.targetAngle = targetAngle;

        PIDSource pidSource = new PIDSource() {

            private PIDSourceType pidSource = PIDSourceType.kDisplacement;

            @Override
            public void setPIDSourceType(PIDSourceType pidSource) {
                this.pidSource = pidSource;
            }

            @Override
            public double pidGet() {
                return Robot.navX.getAngle();
            }

            @Override
            public PIDSourceType getPIDSourceType() {
                return pidSource;
            }
        };

        PIDOutput pidOutput = new PIDOutput() {
            @Override
            public void pidWrite(double output) {
                double turn = output;
                Robot.drivetrain.curvatureDrive(0, turn);
            }
        };

        pidController = new PIDController(0.0145, 0, 0.0376, pidSource, pidOutput);
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