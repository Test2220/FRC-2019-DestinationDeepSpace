package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.subsystems.Shield.State;

/**
 * Command that sets the piston of the SHIELD subsystem based on the state
 * passed into the constructor.
 * 
 * @author Dhruv
 */
public class SetShieldGrabber extends InstantCommand {

    /* Instance Vars */
    private Value direction;

    /**
     * Constructor that initializes which state, and direction, to set the grabber piston to.
     * 
     * @param state the state to set the grabber to
     */
    public SetShieldGrabber(State state) {
        requires(Robot.shield);
        direction = state.getDirection();
    }

    /**
     * Moves grabber piston as per initialized in the constructor. Runs once before
     * command ends.
     */
    public void execute() {
        Robot.shield.setGrabber(direction);
    }
}