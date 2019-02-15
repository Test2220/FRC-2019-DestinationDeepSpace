package frc.robot.commands.shield;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * Command that actuates the pusher piston (linear thruster) based on parameters
 * passed into the constructor.
 * 
 * @author Dhruv
 */
public class SetShieldPusher extends InstantCommand {

    /* INSTANCE VARIABLES */

    // Direction of piston actuation
    private Value direction;

    /* COMMAND CONSTRUCTOR */

    /**
     * Constructor that determines which direction to move the pusher piston.
     * 
     * @param direction The direction to actuate the pusher to
     */
    public SetShieldPusher(Value direction) {
        super(Robot.shield);
        this.direction = direction;
    }

    /* IMPLEMENTED METHODS */

    /**
     * Actuates pusher piston into given state.
     */
    @Override
    protected void execute() {
        Robot.shield.setPusher(direction);
    }
}