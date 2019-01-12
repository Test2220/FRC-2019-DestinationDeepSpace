package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DriveXbox extends Command {

    public boolean isFinished() {
        return false;
    }

    public DriveXbox() {
        requires(frc.robot.Robot.m_driveTrain);
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {

        double move = -frc.robot.Robot.m_oi.stick.getY();
        double turn = frc.robot.Robot.m_oi.stick.getX();
        Robot.m_driveTrain.curvatureDrive(move, turn);

    }

    @Override
    protected void end() {

    }

    @Override
    protected void interrupted() {
        end();
    }

}
