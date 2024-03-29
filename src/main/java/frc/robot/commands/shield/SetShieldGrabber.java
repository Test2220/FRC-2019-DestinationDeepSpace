package frc.robot.commands.shield;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.subsystems.Shield.GrabberState;

/**
 * Command that actuates the grabber piston based on the state passed into the
 * constructor.
 * 
 * @author Dhruv
 */
public class SetShieldGrabber extends InstantCommand {

    /* INSTANCE VARIABLES */

    // Piston actuation state
    private GrabberState state;

    /* COMMAND CONSTRUCTOR */

    /**
     * Constructor that initializes which state (direction) to set the grabber
     * piston to.
     * 
     * @param state The state to actuate the grabber to
     */
    public SetShieldGrabber(GrabberState state) {
        super(Robot.shield);
        this.state = state;
    }

    /* IMPLEMENTED METHODS */

    /**
     * Performs pusher piston actuation in given direction.
     */
    @Override
    protected void execute() {
        if (state == GrabberState.GRABBED) Robot.shield.grabHP();
        if (state == GrabberState.RELEASED) Robot.shield.releaseHP();
    }
}