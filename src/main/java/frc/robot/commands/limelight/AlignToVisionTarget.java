package frc.robot.commands.limelight;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.GenericHID.Hand;

/**
 * This command uses the limelight camera in conjunction with a PI controller in
 * order to allow users to drive forward while the robot adjusts the robot's
 * tilt simultaneously
 * 
 * @author Dhruv
 */
public class AlignToVisionTarget extends PIDCommand {

    /** INSTANCE VARIABLES */

    /**
     * Constructor that initialize the pid controller object and all of its
     * necessary components; no arguments present or necessary.
     */
    public AlignToVisionTarget() {
        //TODO: Tune these values:
        super(0.024, 0, 0);

        requires(Robot.limelight);
        requires(Robot.drivetrain);

        SmartDashboard.putData("Correct Position PID Controller", super.getPIDController());
    }

    /* INSTANCE METHODS */

    @Override
    protected double returnPIDInput() {
        return Robot.limelight.getHOffset();
    }

    @Override
    protected void usePIDOutput(double output) {
        double move = (Robot.oi.driver.getY(Hand.kLeft)) * 0.25;
        double turn = -output;
        Robot.drivetrain.drive(move, turn);
    }

    @Override
    protected void execute() {
        SmartDashboard.putNumber("PID Output", super.getPIDController().get());
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}