package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * Command that pushes a piston of the HARLES subsystem based on parameters
 * passed into the constructor.
 * 
 * @author Dhruv Balasubramanian
 * @version 1/27/2018
 */
public class SetHarles extends InstantCommand {

    // Instance Vars
    private Value direction;
    private Piston piston;

    public enum Piston {
        THRUSTER,
        PUSHER;
    }

    /**
     * Constructor that initializes direction and determines which piston to move.
     * 
     * @param direction the direction to set the pistons to
     * @param piston    which piston to move: 1 for the pusher, 2 for the thruster.
     */
    public SetHarles(Value direction, Piston piston) {
        this.direction = direction;
        this.piston = piston;
        requires(Robot.harles);
    }

    /**
     * Moves selected piston as per initialized in the constructor. Runs once before
     * command ends.
     */
    public void execute() {
        if (piston == Piston.PUSHER) {
            Robot.harles.setPusher(direction);
        } else if (piston == Piston.THRUSTER) {
            Robot.harles.setThruster(direction);
        }
    }
}