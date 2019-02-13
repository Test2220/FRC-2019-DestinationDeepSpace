package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Hatch panel manipulator subsystem built on basis of SHIELD manipulator
 * design.
 * 
 * @author Dhruv
 */
public class Shield extends Subsystem {

    /* INSTANCE VARIABLES */

    // Pusher and grabber pistons
    private DoubleSolenoid pusher = new DoubleSolenoid(RobotMap.PUSHER_FORWARD, RobotMap.PUSHER_REVERSE);
    private DoubleSolenoid grabber = new DoubleSolenoid(RobotMap.GRABBER_FORWARD, RobotMap.GRABBER_REVERSE);

    // Limit switches
    private DigitalInput leftSwitch = new DigitalInput(RobotMap.LEFT_SWITCH);
    private DigitalInput rightSwitch = new DigitalInput(RobotMap.RIGHT_SWITCH);

    /* SUBSYSTEM CONSTRUCTOR */

    /**
     * Subsystem constructor, no parameters or configuration necessary.
     */
    public Shield() {
    }

    /* CONTROL METHODS */

    /**
     * Sets the direction of the pusher piston based on the value given.
     * 
     * @param direction The direction to actuate the pusher piston to
     */
    public void setPusher(Value direction) {
        pusher.set(direction);
    }

    /**
     * Sets the direction of the thruster piston based on the value given.
     * 
     * @param direction The state to actuate the grabber piston to
     */
    public void setGrabber(State state) {
        grabber.set(state.val);
    }

    /**
     * Boolean method to check if both limit switches are activated.
     * 
     * @return Returns true if both limit switches are pressed, returns false if
     *         otherwise
     */
    public boolean switchesPressed() {
        boolean switchesPressed = (leftSwitch.get() && rightSwitch.get());
        return switchesPressed;
    }

    /**
     * Enumeration of the possible states of the shield's central.
     */
    public enum State {
        GRABBED(Value.kReverse), RELEASED(Value.kForward);

        private final Value val;

        State(Value val) {
            this.val = val;
        }
    }

    /* IMPLEMENTED METHODS */

    /**
     * No default command.
     */
    @Override
    protected void initDefaultCommand() {
    }
}