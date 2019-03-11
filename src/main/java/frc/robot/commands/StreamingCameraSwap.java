package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 * StreamingCameraSwap
 */
public class StreamingCameraSwap extends InstantCommand {

    private final StreamCameraMode camera;

    public StreamingCameraSwap(StreamCameraMode camera) {
        this.camera = camera;
    }

    @Override
    protected void execute() {
        
    }

    public enum StreamCameraMode {
        LIMELIGHT_ONLY, USB_CAMERA_ONLY, BOTH;
    }
}