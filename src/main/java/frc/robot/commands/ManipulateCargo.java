package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ManipulateCargo extends Command {

    public ManipulateCargo() {
        requires(Robot.cargo);
    }

    @Override
    protected void execute() {
        //Robot.cargo.spinIntake();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}