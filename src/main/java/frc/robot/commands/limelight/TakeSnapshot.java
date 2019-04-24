package frc.robot.commands.limelight;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.utils.XboxWrapper;

/**
 * TakeSnapshot
 */
public class TakeSnapshot extends InstantCommand {

    private final boolean rumble;

    public TakeSnapshot(boolean rumble) {
        this.rumble = rumble;
        // Not requiring the limelight subsystem allow this
        // command to run in parallel with other commands
        // using the limelight subsytem
    }

    @Override
    protected void execute() {
        Robot.limelight.takeSnapshot();
        if (rumble) Robot.oi.manipulator.rumbleFor(XboxWrapper.RUMBLE_TIME);
    }


}