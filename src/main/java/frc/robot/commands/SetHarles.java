package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.subsystems.Harles.Piston;

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

    /**
     * Constructor that initializes direction and determines which piston to move.
     * 
     * @param direction the direction to set the pistons to
     * @param piston    which piston to move -- a constant of the enum Pistons
     */
    public SetHarles(Value direction, Piston piston) {
        super(Robot.harles);
        this.direction = direction;
        this.piston = piston;
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