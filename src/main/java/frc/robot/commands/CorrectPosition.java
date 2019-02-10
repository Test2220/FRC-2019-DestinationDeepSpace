package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.GenericHID.Hand;

/**
 * This command uses the limelight camera in conjunction with a PI controller in
 * order to allow users to drive forward while the robot adjusts the robot's
 * tilt simultaneously
 * 
 * @author Dhruv
 */
public class CorrectPosition extends Command {

    /** INSTANCE VARIABLES */

    private PIDController pidController;

    /**
     * Constructor that initialize the pid controller object and all of its
     * necessary components; no arguments present or necessary.
     */
    public CorrectPosition() {

        requires(Robot.limelight);
        requires(Robot.drivetrain);

        PIDSource limelightPIDSource = new PIDSource() {

            private PIDSourceType pidSource = PIDSourceType.kDisplacement;

            @Override
            public void setPIDSourceType(PIDSourceType pidSource) {
                this.pidSource = pidSource;
            }

            @Override
            public double pidGet() {
                return Robot.limelight.getHOffset();
            }

            @Override
            public PIDSourceType getPIDSourceType() {
                return pidSource;
            }
        };

        PIDOutput limelightPIDOutput = new PIDOutput() {
            @Override
            public void pidWrite(double output) {
                double move = (Robot.oi.getDriver().getY(Hand.kLeft)) * 0.25;
                double turn = -output;
                Robot.drivetrain.curvatureDrive(move, turn);
            }
        };

        pidController = new PIDController(0.024, 0, 0, limelightPIDSource, limelightPIDOutput);

        SmartDashboard.putData("Correct Position PID Controller",pidController);
    }

    /** INSTANCE METHODS */

    /**
     * The initialize method is called the first time this Command is run after
     * being started. Enables the PID controller.
     */
    @Override
    public void initialize() {
        //Robot.limelight.setLEDMode(LEDMode.ON);
        pidController.enable();
    }

    @Override
    protected void execute() {
        SmartDashboard.putNumber("PID Output", pidController.get());
    }

    /**
     * Called when the command ended peacefully. Disables the PI controller.
     */
    @Override
    public void end() {
        pidController.disable();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}