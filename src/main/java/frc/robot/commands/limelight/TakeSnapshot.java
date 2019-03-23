package frc.robot.commands.limelight;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.utils.XboxWrapper;

/**
 * TakeSnapshot
 */
public class TakeSnapshot extends InstantCommand {

    public TakeSnapshot() {
        // Not requiring the limelight subsystem allow this 
        // command to run in parallel with other commands 
        // using the limelight subsytem 
    }

    @Override
    protected void execute() {
        Robot.limelight.takeSnapshot();
        Robot.oi.manipulator.rumbleFor(XboxWrapper.RUMBLE_TIME);
    }

}