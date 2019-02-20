package frc.robot.commands.shield;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Shield.Switch;

public class ShieldDefaultCommand extends Command {

    public ShieldDefaultCommand() {
        requires(Robot.shield);
    }

    @Override
    protected void execute() {
        if (Robot.shield.getSwitchPressed(Switch.BOTH_SWITCHES)) {
            System.out.println("Both Switches Pressed");
        }
        else if (Robot.shield.getSwitchPressed(Switch.LEFT_SWITCH)) {
            System.out.println("Left Switch Pressed");
        }
        else if (Robot.shield.getSwitchPressed(Switch.RIGHT_SWITCH)) {
            System.out.println("Right Switch Pressed");
        }
        else {
            System.out.println("No Switches Pressed");
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}