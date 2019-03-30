package frc.robot.commands.cargo;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.subsystems.Cargo;
import frc.robot.subsystems.Cargo.CargoDesiredState;

/**
 * ArmToPosition
 */
public class ControlArm extends InstantCommand {

    final CargoDesiredState state;

    public ControlArm(CargoDesiredState state) {
        super(Robot.cargo);
        this.state = state;
    }

    @Override
    protected void execute() {
        Robot.cargo.setArmState(state);
    }
}