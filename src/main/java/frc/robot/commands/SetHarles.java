package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.subsystems.Harles;

public class SetHarles extends InstantCommand {
   
    private static Runnable func = new Runnable() {
        @Override
        public void run() {
            Robot.harles.setPusher(Value.kForward);
            Robot.harles.setThruster(Value.kForward);
        }
    };

    public SetHarles() {
        super(Robot.harles, func);
    }
}