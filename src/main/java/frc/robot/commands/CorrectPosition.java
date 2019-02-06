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
    private PIDSource limelightPIDSource;
    private PIDOutput limelightPIDOutput;

    public CorrectPosition() {

        requires(Robot.limelight);
        requires(Robot.drivetrain);
        //pidController = new PIDController(1, 1, 1, 1);
        limelightPIDSource = new PIDSource() {
        
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

        limelightPIDOutput = new PIDOutput() {
            @Override
            public void pidWrite(double output) {
                double move = (Robot.oi.getDriver().getY(Hand.kLeft)) * 0.25;
                double turn = -output;
                if (Math.abs(turn) > 1) turn = 0;
                Robot.drivetrain.curvatureDrive(move, turn);
            }
        };

        double Tu = 5.0/14.0, Ku = 0.024, kP = 0.6*Ku, kI = 1.2*Ku/Tu, kD = 3*Ku*Tu/40;

        System.out.printf("Tu: %.5f; Ku: %f; kP: %.5f; kI: %.5fkI; kD: %.5fkD\n", Tu, Ku, kP, kI, kD);

        pidController = new PIDController(0.025 * 0.65, 0.00023 * 0.65, 0, limelightPIDSource, limelightPIDOutput);
    }

    @Override
    public void initialize() {
        pidController.enable();
    }

    @Override
    public void execute() {
        System.out.println("ts: " + Robot.limelight.getTargetSkew());
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