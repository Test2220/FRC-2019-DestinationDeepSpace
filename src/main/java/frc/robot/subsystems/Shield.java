package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Hatch panel manipulator subsystem using SHIELD design as basis
 * 
 * @author Dhruv, Reece
 * @version 1/27/2018
 */
public class Shield extends Subsystem {

    /* INSTANCE VARIABLES */

    private boolean releaseRequested = false;

    // Pistons
    private DoubleSolenoid pusher = new DoubleSolenoid(RobotMap.PUSHER_FORWARD, RobotMap.PUSHER_REVERSE);
    private DoubleSolenoid grabber = new DoubleSolenoid(RobotMap.GRABBER_FORWARD, RobotMap.GRABBER_REVERSE);

    // Limit switches
    private DigitalInput leftSwitch = new DigitalInput(RobotMap.LEFT_SWITCH);
    private DigitalInput rightSwitch = new DigitalInput(RobotMap.RIGHT_SWITCH);

    /* SUBSYSTEM CONSTRUCTOR */

    /**
     * Empty subsystem constructor, no parameters or config necessary
     */
    public Shield() {
    }

    /* CONTROL METHODS */

    /**
     * Actuate pusher piston (linear thruster) to direction given
     * 
     * @param direction The direction to set the pusher piston
     */
    public void setPusher(Value direction) {
        pusher.set(direction);
    }

    /**
     * Actuate grabber piston to direction given
     * 
     * @param direction The direction to set the grabber piston
     */
    public void setGrabber(Value direction) {
        grabber.set(direction);
    }

    /**
     * Set grabber piston to actuate into grab position
     */
    public void grab() {
        grabber.set(Value.kForward);
    }

    /**
     * Set grabber piston to actuate into release position
     */
    public void release() {
        releaseRequested = true;
        grabber.set(Value.kReverse);
    }

    public boolean switchesPressed() {
        return (leftSwitch.get() && rightSwitch.get());
    }

    public boolean releaseRequested() {
        return releaseRequested;
    }

    public boolean grabbing() {
        return grabber.get() == Value.kForward;
    }

    public enum State {
        GRABBED(Value.kForward), RELEASED(Value.kForward);

        public final Value direction;

        private State(Value direction) {
            this.direction = direction;
        }
    }

    /* IMPLEMENTED METHODS */

    @Override
    public void initDefaultCommand() {
    }
}