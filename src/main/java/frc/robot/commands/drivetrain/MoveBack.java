package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class MoveBack extends Command {

    public MoveBack(int time) {
        setTimeout(time);
    }

    @Override
    protected void execute() {
        Robot.drivetrain.drive(-0.5, 0);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}