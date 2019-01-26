package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class PlaceHatchPanel extends CommandGroup {
    public PlaceHatchPanel() {
        addSequential(new SetHarles(Value.kForward, 1));
        addSequential(new SetHarles(Value.kReverse, 1));
        //addSequential(new SetHarles(Value.kForward, 2));
    }
}