package frc.robot.commands.limelight;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.ShuffleBoardConfig;
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
    private final double PID_OUTPUT_SCALAR = 0.25;

    /**
     * Constructor that initialize the pid controller object and all of its
     * necessary components; no arguments present or necessary.
     */
    public AlignToVisionTarget(int pipeline) {
        //TODO: Better tune these values:
        super(0.024, 0, 0);

        requires(Robot.limelight);
        requires(Robot.drivetrain);

        Robot.limelight.setPipeline(pipeline);

        ShuffleBoardConfig.pidTuningTab.add("Correct Position PID Controller", super.getPIDController()).withSize(2, 2).withPosition(6, 0);
    }

    /* INSTANCE METHODS */

    /**
     * Essentially a getter for the pidInput for the PIDController class.
     * 
     * @return double the PID output for the controller to use.
     */
    @Override
    protected double returnPIDInput() {
        return Robot.limelight.getHOffset();
    }

    /**
     * Writes the PID output by setting power to the motors.
     * 
     * @param output the output given by the PID controller.
     */
    @Override
    protected void usePIDOutput(double output) {
        double move = -(Robot.oi.driver.getY(Hand.kLeft)) * PID_OUTPUT_SCALAR;
        double turn = -output;
        Robot.drivetrain.drive(move, turn);
    }

    /**
     * The execute method is called repeatedly until this Command either finishes or is canceled.
     * Writes values to smartdashboard.
     */
    @Override
    protected void execute() {
        SmartDashboard.putNumber("PID Output", super.getPIDController().get());
        // Robot.limelight.takeSnapshot();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}