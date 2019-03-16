package frc.robot.commands.cargo;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.utils.XboxWrapper;

/**
 * ReZeroArm
 */
public class ReZeroArm extends InstantCommand {

    public ReZeroArm() {
        super(Robot.cargo);
    }

    @Override
    protected void execute() {
        Robot.cargo.reZeroArm();
        Robot.oi.manipulator.rumbleFor(XboxWrapper.WARNING_RUMBLE);
    }

}