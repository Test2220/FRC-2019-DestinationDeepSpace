package frc.robot.commands.limelight;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Limelight.CameraMode;
import frc.robot.subsystems.Limelight.LEDMode;

/**
 * Runs while limelight is not being used -- allows to turn LEDs off when not
 * necessary.
 * 
 * @author Dhruv
 */
public class LimelightDefaultCommand extends Command {

    /**
     * Constructor that requires the limelight, preventing other commands from using
     * the limelight while this is active.
     */
    public LimelightDefaultCommand() {
        requires(Robot.limelight);
    }

    /**
     * Turn's off LEDs when this command is initialized, or essentially when no
     * other command is active.
     */
    @Override
    public void initialize() {
        Robot.limelight.setCameraMode(CameraMode.DRIVER_CAMERA);
        Robot.limelight.setLEDMode(LEDMode.OFF);
    }

    /**
     * Called when the command ends because somebody called cancel() or another
     * command shared the same requirements as this one, and booted it out. Turns
     * the limelight LEDs on when no other command is using it.
     */
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