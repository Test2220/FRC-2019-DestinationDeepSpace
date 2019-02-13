package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Subsystem for manipulation of the hatch panel.
 * 
 * @author Dhruv Balasubramanian
 * @version 1/27/2018
 */
public class Shield extends Subsystem {

    /* INSTANCE VARIABLES */

    private DoubleSolenoid pusher;
    private DoubleSolenoid grabber;

    /* CONSTRUCTOR */

    /**
     * Constructor that initializes both pistons. No parameters necessary or taken.
     */
    public Shield() {
        pusher = new DoubleSolenoid(RobotMap.PUSHER_FORWARD, RobotMap.PUSHER_REVERSE);
        grabber = new DoubleSolenoid(RobotMap.GRABBER_FORWARD, RobotMap.GRABBER_REVERSE);
    }

    /* CONTROL METHODS */

    /**
     * Sets the direction of the pusher piston based on the value given.
     * 
     * @param direction the direction to set the pusher piston to.
     */
    public void setPusher(Value direction) {
        pusher.set(direction);
    }

    /**
     * Sets the direction of the thruster piston based on the value given.
     * 
     * @param direction the direction to set the thruster piston to.
     */
    public void setGrabber(Value direction) {
        grabber.set(direction);
    }

    /**
     * Enumeration of the possible states of the "flower petal".
     * There are two possible states: grabbed or released
     */
    public enum State {
        GRABBED(Value.kReverse), RELEASED(Value.kForward);

        private final Value val;

        State(Value val) {
            this.val = val;
        }

        /**
         * @return val the direction associated with a specific state
         */
        public Value getDirection() {
            return val;
        }
    }

    /* IMPLEMENTED METHODS */

    @Override
    public void initDefaultCommand() {
    }
}