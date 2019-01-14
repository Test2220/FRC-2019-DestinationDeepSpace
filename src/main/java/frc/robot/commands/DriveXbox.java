package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DriveXbox extends Command {

    public boolean isFinished() {
        return false;
    }

    public DriveXbox() {
        requires(Robot.m_driveTrain);
    }

    @Override
    protected void initialize() {
        
    }

    @Override
    protected void execute() {

        double move = -Robot.m_oi.driverController.getY(Hand.kRight);
        double turn = Robot.m_oi.driverController.getY(Hand.kRight);
        Robot.m_driveTrain.curvatureDrive(move, turn);
    }

    @Override
    protected void end() {
        
    }
}
