package frc.robot.commands.shield;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Shield.State;

public class ShieldDefaultCommand extends Command {

    public ShieldDefaultCommand() {
        requires(Robot.shield);
    }

    @Override
    protected void execute() {
        if (Robot.shield.switchesPressed()) {
            Robot.shield.setGrabber(State.GRABBED);
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}