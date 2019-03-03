package frc.robot.commands.limelight;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DriveToVisionTarget extends Command {

    private PIDController pidControllerDrive;
    private PIDSource pidSourceDrive;
    private PIDOutput pidOutputDrive;

    private PIDController pidControllerTurn;
    private PIDSource pidSourceTurn;
    private PIDOutput pidOutputTurn;

    private double drive = 0;
    private double turn = 0;

    private final double TARGET_AREA = 13.0;
    private final double DRIVE_SCALAR = 0.26;
    private final double TURN_SCALAR = 0.25;

    private final double P_DRIVE = 0.024;
    private final double I_DRIVE = 0;
    private final double D_DRIVE = 0.02;

    private final double P_TURN = 0.024;
    private final double I_TURN = 0;
    private final double D_TURN = 0.02;
    

    public DriveToVisionTarget() {
        requires(Robot.drivetrain);
        requires(Robot.limelight);

        pidSourceDrive = new PIDSource() {
        
            private PIDSourceType pidSource = PIDSourceType.kDisplacement;

            @Override
            public void setPIDSourceType(PIDSourceType pidSource) {
                this.pidSource = pidSource;
            }
        
            @Override
            public double pidGet() {
                return (TARGET_AREA - Robot.limelight.getTargetSize());
            }
        
            @Override
            public PIDSourceType getPIDSourceType() {
                return pidSource;
            }
        };

        pidOutputDrive = new PIDOutput() {
            @Override
            public void pidWrite(double output) {
                drive = output * DRIVE_SCALAR;
            }
        };

        pidSourceTurn = new PIDSource() {

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

        pidOutputTurn = new PIDOutput() {

            @Override
            public void pidWrite(double output) {
                turn = -output * TURN_SCALAR;
            }

        };

        pidControllerDrive = new PIDController(P_DRIVE, I_DRIVE, D_DRIVE, pidSourceDrive, pidOutputDrive);
        pidControllerTurn = new PIDController(P_TURN, I_TURN, D_TURN, pidSourceTurn, pidOutputTurn);
    }

    @Override
    protected void initialize() {
        pidControllerDrive.enable();
        pidControllerTurn.enable();
    }

    @Override
    protected void execute() {
        Robot.drivetrain.drive(drive, turn);
    }

    @Override
    protected void end() {
        pidControllerDrive.disable();
        pidControllerTurn.disable();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}