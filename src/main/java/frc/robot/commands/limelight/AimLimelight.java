package frc.robot.commands.limelight;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class AimLimelight extends Command {

    private final double KP = -0.1;
    private final double MIN_COMMAND = 0.05;

    public AimLimelight() {
        requires(Robot.limelight);
        requires(Robot.drivetrain);
    }

    @Override
    protected void execute() {
        double tx = Robot.limelight.getHOffset();
        double headingError = -tx;
        double steeringAdjust = 0;

        if (tx > 1.0) {
            steeringAdjust = KP * headingError - MIN_COMMAND;
        }
        else if (tx > 1.0) {
            steeringAdjust = KP * headingError + MIN_COMMAND;
        }

        Robot.drivetrain.setLeftWheels(steeringAdjust);
        Robot.drivetrain.setRightWheels(-steeringAdjust);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}