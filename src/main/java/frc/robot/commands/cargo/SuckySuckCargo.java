package frc.robot.commands.cargo;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * The ManipulateCargo command continuously executes the spinIntake method out
 * of the cargo subsystem by passing (on manipulator controller) the the left Y
 * axis -> spinIntake
 * 
 * @author Muaad
 */
public class SuckySuckCargo extends Command {

    /* CONSTANTS */

    // Intake deadzone
    private static final double INTAKE_DEADZONE = 0.15;

    /* COMMAND CONSTRUCTOR */

    /**
     * Manipulate cargo command constructor passes cargo subsystem to super
     * constructor. (requires cargo subsystem)
     */
    public SuckySuckCargo() {
        super(Robot.cargo);
    }

    /* IMPLEMENTED METHODS */

    /**
     * Executes code to run the cargo manipulator in a loop.
     */
    @Override
    protected void execute() {

        // Grab joystick values from manipulator controller
        double spinSpeed = Robot.oi.manipulator.getY(Hand.kLeft);

        // Pass joystick values to and call control methods out of subsystem
        if (!Robot.oi.childMode && (Math.abs(spinSpeed) > INTAKE_DEADZONE)) {
            Robot.cargo.spinIntake(spinSpeed);
        } else {
            Robot.cargo.spinIntake(0);
        }
    }

    /**
     * Manipulate cargo command command never finishes.
     */
    @Override
    protected boolean isFinished() {
        return false;
    }
}