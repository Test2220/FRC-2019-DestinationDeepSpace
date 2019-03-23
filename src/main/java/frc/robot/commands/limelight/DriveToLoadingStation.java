
package frc.robot.commands.limelight;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import frc.robot.Robot;
import frc.robot.ShuffleBoardConfig;
import frc.robot.subsystems.Shield.LimitSwitchCombination;

public class DriveToLoadingStation extends Command {

    private static final NetworkTableEntry targetAreaEntry = ShuffleBoardConfig.pidTuningTab.add("Target Area", 0)
            .withWidget(BuiltInWidgets.kGraph).getEntry();
    private static final NetworkTableEntry targetOffsetEntry = ShuffleBoardConfig.pidTuningTab.add("Target Offset", 0)
            .withWidget(BuiltInWidgets.kGraph).getEntry();

    private PIDController pidControllerDrive;
    private PIDSource pidSourceDrive;
    private PIDOutput pidOutputDrive;

    private PIDController pidControllerTurn;
    private PIDSource pidSourceTurn;
    private PIDOutput pidOutputTurn;

    private double drive = 0;
    private double turn = 0;

    private final double TARGET_SQUARE_ROOT_SIZE = Math.sqrt(50);

    private final double P_DRIVE = 0.125;
    private final double I_DRIVE = 0;
    private final double D_DRIVE = 0;

    private final double P_TURN = 0.025;
    private final double I_TURN = 0;
    private final double D_TURN = 0.023;

    private boolean pusherHasBeenSetForward = false;

    public DriveToLoadingStation() {
        requires(Robot.drivetrain);
        requires(Robot.limelight);
        requires(Robot.shield);

        pidSourceDrive = new PIDSource() {

            private PIDSourceType pidSource = PIDSourceType.kDisplacement;

            @Override
            public void setPIDSourceType(PIDSourceType pidSource) {
                this.pidSource = pidSource;
            }

            @Override
            public double pidGet() {
                return Math.sqrt(Robot.limelight.getTargetSize());
            }

            @Override
            public PIDSourceType getPIDSourceType() {
                return pidSource;
            }
        };

        pidOutputDrive = new PIDOutput() {
            @Override
            public void pidWrite(double output) {
                drive = output;
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
                turn = -output;
            }
        };

        pidControllerDrive = new PIDController(P_DRIVE, I_DRIVE, D_DRIVE, pidSourceDrive, pidOutputDrive);
        pidControllerTurn = new PIDController(P_TURN, I_TURN, D_TURN, pidSourceTurn, pidOutputTurn);

        ShuffleBoardConfig.pidTuningTab.add("Turn PID", pidControllerTurn);
        ShuffleBoardConfig.pidTuningTab.add("Drive PID", pidControllerDrive);

        pidControllerDrive.setSetpoint(TARGET_SQUARE_ROOT_SIZE);
    }

    @Override
    protected void initialize() {
        pusherHasBeenSetForward = false;
        Robot.shield.setPusher(Value.kReverse);
        Robot.shield.releaseHP();
        pidControllerDrive.enable();
        pidControllerTurn.enable();
    }

    @Override
    protected void execute() {
        if (Math.sqrt(Robot.limelight.getTargetSize()) >= Math.sqrt(30) && !pusherHasBeenSetForward) {
            Robot.shield.setPusher(Value.kForward);
            pusherHasBeenSetForward = true;
        }

        if (Robot.shield.getSwitchPressed(LimitSwitchCombination.EITHER_SWITCH_PRESSED)) {
            drive = 0;
            turn = 0;
        }

        Robot.drivetrain.drive(drive, turn);
        targetAreaEntry.setDouble(Robot.limelight.getTargetSize());
        targetOffsetEntry.setDouble(Robot.limelight.getHOffset());
        // Robot.limelight.takeSnapshot();
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