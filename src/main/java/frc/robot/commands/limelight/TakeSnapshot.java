package frc.robot.commands.limelight;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.utils.XboxWrapper;

/**
 * TakeSnapshot
 */
public class TakeSnapshot extends InstantCommand {

    public TakeSnapshot() {
        super(Robot.limelight);
    }

    @Override
    protected void execute() {
        Robot.limelight.takeSnapshot();
        Robot.oi.manipulator.rumbleFor(XboxWrapper.RUMBLE_TIME);
    }
    
}