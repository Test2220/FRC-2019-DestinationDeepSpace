package frc.robot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.PIDBase.Tolerance;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class TurnToAngle extends Command {

    private double angle;
    private PIDController pidController;

    public TurnToAngle(double angle) {
        requires(Robot.navX);
        requires(Robot.drivetrain);
        this.angle = angle;

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
                double turn = output * 0.5;
                Robot.drivetrain.curvatureDrive(0, turn);
            }
        };

        pidController = new PIDController(0.01, 0, 0, pidSource, pidOutput);
        pidController.setSetpoint(angle);
        pidController.setAbsoluteTolerance(5);
    }

    /**
     * The initialize method is called the first time this Command is run after
     * being started. Enables the PID controller.
     */
    @Override
    public void initialize() {
        Robot.navX.zeroAngle();
        pidController.enable();
    }

    @Override
    protected void execute() {
        System.out.println("Command is still running, turn speed is " + Robot.drivetrain.getTurnSpeed());
    }

    /**
     * Called when the command ended peacefully. Disables the PI controller.
     */
    @Override
    public void end() {
        pidController.disable();
    }

    @Override
    protected boolean isFinished() {
        return (pidController.onTarget()) 
        && (Math.abs(Robot.drivetrain.getTurnSpeed()) - 0 <= 0.01);
    }
}