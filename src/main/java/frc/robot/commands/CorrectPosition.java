package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.GenericHID.Hand;


public class CorrectPosition extends Command {
    
    private PIDController pidController;

    public CorrectPosition() {

        requires(Robot.limelight);
        requires(Robot.drivetrain);
        
        PIDSource limelightPIDSource = new PIDSource() {
        
            private PIDSourceType pidSource = PIDSourceType.kDisplacement;

            @Override
            public void setPIDSourceType(PIDSourceType pidSource) {
                this.pidSource = pidSource;
            }

            @Override
            public double pidGet() {
                return Robot.limelight.getHOffset();
            }
        
            @Override
            public PIDSourceType getPIDSourceType() {
                return pidSource;
            }
        };

        PIDOutput limelightPIDOutput = new PIDOutput() {
            @Override
            public void pidWrite(double output) {
                double move = (Robot.oi.getDriver().getY(Hand.kLeft)) * 0.25;
                double turn = -output;
                Robot.drivetrain.curvatureDrive(move, turn);
            }
        };

        pidController = new PIDController(0.025 * 0.65, 0.00023 * 0.65, 0, limelightPIDSource, limelightPIDOutput);  
    }

    @Override
    public void initialize() {
        pidController.enable();
    }

    @Override
    public void end() {
        pidController.disable();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}