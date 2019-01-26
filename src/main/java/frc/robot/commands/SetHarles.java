package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class SetHarles extends InstantCommand {

    private Value direction;
    private int piston;

    public SetHarles(Value direction, int piston) {
        this.direction = direction;
        this.piston = piston;
        requires(Robot.harles);
    }

    public void execute() {
        if (piston == 1) {
            Robot.harles.setPusher(direction);
        } else {
            Robot.harles.setThruster(direction);
        }
    }
}