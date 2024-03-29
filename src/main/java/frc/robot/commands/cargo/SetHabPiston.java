package frc.robot.commands.cargo;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * ControlHabPiston
 */
public class SetHabPiston extends InstantCommand {

    private Value value;

    public SetHabPiston(Value value) {
        super(Robot.cargo);
        this.value = value;
    }

    @Override
    protected void execute() {
        Robot.cargo.setClimber(value);
    }
}