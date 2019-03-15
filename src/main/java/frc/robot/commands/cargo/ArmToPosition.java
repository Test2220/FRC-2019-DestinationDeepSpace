package frc.robot.commands.cargo;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * ArmToPosition
 */
public class ArmToPosition extends InstantCommand {

    final int pos;

    public ArmToPosition(int pos) {
        super(Robot.cargo);
        this.pos = pos;
    }

    @Override
    protected void execute() {
        Robot.cargo.requestArmPosition(pos);
    }
}