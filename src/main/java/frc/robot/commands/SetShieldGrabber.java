package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.subsystems.Shield.State;

/**
 * Command that pushes a piston of the HARLES subsystem based on parameters
 * passed into the constructor.
 * 
 * @author Dhruv
 */
public class SetShieldGrabber extends InstantCommand {

    /* Instance Vars */
    private Value direction;

    /**
     * Constructor that initializes direction and determines which piston to move.
     * 
     * @param state the state to set the grabber to
     */
    public SetShieldGrabber(State state) {
        requires(Robot.shield);
        direction = state.getDirection();
    }

    /**
     * Moves thruster piston as per initialized in the constructor. Runs once before
     * command ends.
     */
    public void execute() {
        Robot.shield.setGrabber(direction);
    }
}