package frc.robot.commands.shield;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * GrabHatch
 */
public class GrabHatch extends Command {

    @Override
    protected void execute() {
        if (Robot.shield.switchesPressed() && !Robot.shield.releaseRequested()) {

        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}