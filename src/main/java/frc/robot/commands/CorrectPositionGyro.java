package frc.robot.commands;

import java.beans.Encoder;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Limelight;

public class CorrectPositionGyro extends Command {

    private double currentPosition, wantedPosition;

    public CorrectPositionGyro() {
        requires(Robot.limelight);
        requires(Robot.drivetrain);
    }

    @Override
    public void execute() {
        //currentPosition = Robot.gyro.getAngle();
        //wantedPosition = currentPosition - Robot.limelight.getHOffset();

    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}