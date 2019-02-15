package frc.robot.commands.limelight;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Limelight.CameraMode;
import frc.robot.subsystems.Limelight.LEDMode;

public class LimelightDefaultCommand extends Command {

    public LimelightDefaultCommand() {
        requires(Robot.limelight);
    }

    @Override
    public void initialize() {
        Robot.limelight.setCameraMode(CameraMode.DRIVER_CAMERA);
        Robot.limelight.setLEDMode(LEDMode.OFF);
    }

    @Override
    public void interrupted() {
        Robot.limelight.setCameraMode(CameraMode.VISION_PROCESSING);
        Robot.limelight.setLEDMode(LEDMode.USE_PIPELINE);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}