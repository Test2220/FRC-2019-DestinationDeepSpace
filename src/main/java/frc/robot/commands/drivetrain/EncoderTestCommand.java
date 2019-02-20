package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class EncoderTestCommand extends Command {

    public EncoderTestCommand() {
        requires(Robot.drivetrain);
    }

    @Override
    protected void initialize() {
        Robot.drivetrain.resetEncoders();
    }

    @Override
    protected void execute() {
        System.out.printf("Encoder output: %.3f\n", Robot.drivetrain.getEncoderOutput());
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}