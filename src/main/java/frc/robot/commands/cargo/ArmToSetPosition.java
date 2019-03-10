package frc.robot.commands.cargo;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.ShuffleBoardConfig;

/**
 * ArmToPosition
 */
public class ArmToSetPosition extends InstantCommand {

    int pos;

    public ArmToSetPosition() {
        super(Robot.cargo);
        pos = (int)ShuffleBoardConfig.armUpPos.getDouble(0);
        System.out.println(pos);
    }

    @Override
    protected void execute() {
        Robot.cargo.setArmPosition(pos);
    }
}