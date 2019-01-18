package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DriveXbox extends Command {

    public DriveXbox() {
        requires(Robot.drivetrain);
    }

    @Override
    protected void initialize() {
        
    }

    @Override
    protected void execute() {
        double move = -Robot.oi.getDC().getY(Hand.kLeft);
        double turn = Robot.oi.getDC().getY(Hand.kLeft);
        Robot.drivetrain.curvatureDrive(move, turn);
    }

    public boolean isFinished() {
        return false;
    }
}