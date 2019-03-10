package frc.robot.commands.cargo; 

import edu.wpi.first.wpilibj.GenericHID.Hand; 
import edu.wpi.first.wpilibj.command.Command; 
import frc.robot.Robot; 

/**
 * The ManipulateCargo command continuously executes the moveArm and spinIntake
 * methods out of the cargo subsystem by passing (on manipulator controller) the
 * right Y axis -> moveArm, and the left Y axis -> spinIntake
 * 
 * @author Muaad
 */
public class ManipulateCargo extends Command {

    /* COMMAND CONSTRUCTOR */

    /**
     * Manipulate cargo command constructor passes cargo subsystem to super
     * constructor. (requires cargo subsystem)
     */
    public ManipulateCargo() {
        super(Robot.cargo); 
    }

    /* IMPLEMENTED METHODS */

    /**
     * Executes code to run the cargo manipulator in a loop.
     */
    @Override
    protected void execute() {
        // Grab joystick values from manipulator controller
        double armPower = Robot.oi.manipulator.getY(Hand.kRight); 
        double spinSpeed = Robot.oi.manipulator.getY(Hand.kLeft); 

        // Pass joystick values to and call control methods out of subsystem
        // Robot.cargo.moveArm(armPower); 
        Robot.cargo.spinIntake(spinSpeed); 
    }

    /**
     * Manipulate cargo command command never finishes.
     */
    @Override
    protected boolean isFinished() {
        return false; 
    }
}