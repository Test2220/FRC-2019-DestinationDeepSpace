package frc.robot.commands.limelight.unfinished;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.drivetrain.MoveBack;
import frc.robot.commands.limelight.AlignToVisionTarget;
import frc.robot.commands.shield.SetShieldGrabber;
import frc.robot.commands.shield.SetShieldPusher;
import frc.robot.subsystems.Shield.GrabberState;

public class PutHatchPanelAuto extends CommandGroup {

    public PutHatchPanelAuto() {
        addSequential(new AlignToVisionTarget());
        addSequential(new SetShieldPusher(Value.kForward));
        addSequential(new SetShieldGrabber(GrabberState.RELEASED));
        addSequential(new MoveBack(2));
    }
}