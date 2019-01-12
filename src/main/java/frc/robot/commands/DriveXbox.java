package frc.robot.commands;


import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
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

        double move = -OI.driverController.getY();
        double turn = OI.driverController.getX();
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
