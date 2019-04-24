package frc.robot.commands.cargo;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.subsystems.Cargo.CargoDesiredState;

/**
 * ArmManual
 */
public class ArmManual extends InstantCommand {

    public ArmManual() {
        super(Robot.cargo);
    }

    @Override
    protected void execute() {
        Robot.cargo.setArmState(CargoDesiredState.MANUAL);
    }
}