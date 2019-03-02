package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.ShuffleBoardConfig;
import frc.robot.utils.LimitSwitch;

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
    private ShieldState state = ShieldState.RELEASED_READY_TO_AUT0_GRAB;
    private double lastLimitSwitchPressTime = 0;

    // Limit switches
    private LimitSwitch leftSwitch = new LimitSwitch(RobotMap.LEFT_SWITCH, true);
    private LimitSwitch rightSwitch = new LimitSwitch(RobotMap.RIGHT_SWITCH, true);

    /* SUBSYSTEM CONSTRUCTOR */

    /**
     * Subsystem constructor, no parameters or configuration necessary.
     */
    public Shield() {
        ShuffleBoardConfig.shieldLayout.add("Pusher", pusher);
        ShuffleBoardConfig.shieldLayout.add("Grabber", grabber);
        ShuffleBoardConfig.shieldLayout.add("Left Switch", leftSwitch);
        ShuffleBoardConfig.shieldLayout.add("Right Switch", rightSwitch);
    }

    /* CONTROL METHODS */

    /**
     * Getter for the state of the grabber.
     * 
     * @return State the current state of the grabber
     */
    public GrabberState getGrabberState() {
        return GrabberState.ofValue(grabber.get());
    }

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
    public void setGrabber(GrabberState state) {
        grabber.set(state.val);
    }

    public void grab() {
        grabber.set(GrabberState.GRABBED.val);
        state = ShieldState.GRABBED;
    }

    public void release() {
        grabber.set(GrabberState.RELEASED.val);
        state = ShieldState.RELEASE_PENDING;
    }

    public boolean hasWaitedLongEnough() {
        return (Timer.getFPGATimestamp() - lastLimitSwitchPressTime) >= 1;
    }

    // TODO: Recomment & document
    /**
     * Boolean method to check if both limit switches are activated.
     * 
     * @return Returns true if both limit switches are pressed, returns false if
     *         otherwise
     */
    public boolean getSwitchPressed(Switch s) {
        switch (s) {
        case LEFT_SWITCH:
            return leftSwitch.get();
        case RIGHT_SWITCH:
            return rightSwitch.get();
        case BOTH_SWITCHES:
            return leftSwitch.get() && rightSwitch.get();
        case EITHER_SWITCH:
            return leftSwitch.get() || rightSwitch.get();
        case NEITHER_SWITCH:
            return !(leftSwitch.get() || rightSwitch.get());
        default:
            return leftSwitch.get() && rightSwitch.get();
        }
    }

    @Override
    public void periodic() {
        if (getSwitchPressed(Switch.EITHER_SWITCH)) {
            lastLimitSwitchPressTime = Timer.getFPGATimestamp();
        }
        switch (state) {
            case GRABBED:
                break;

            case RELEASED_READY_TO_AUT0_GRAB:
                if (getSwitchPressed(Switch.EITHER_SWITCH)) {
                    grab();
                }
                break;

            case RELEASE_PENDING:
                if (getSwitchPressed(Switch.NEITHER_SWITCH) && hasWaitedLongEnough()) {
                    state = ShieldState.RELEASED_READY_TO_AUT0_GRAB;
                }
                break;
        }
    }

    /**
     * Enumeration of the possible states of the shield's central.
     */
    public enum GrabberState {
        GRABBED(Value.kForward), RELEASED(Value.kReverse);

        private final Value val;

        GrabberState(Value val) {
            this.val = val;
        }

        /**
         * Converts a DoubleSolenoid Value to a State enumeration value.
         * 
         * @param val the DoubleSolenoid Value of the hatch panel
         * 
         * @return GRABBED if the grabber is currently grabbing the hatch panel,
         *         RELEASED otherwise
         */
        public static GrabberState ofValue(Value val) {
            if (val == GRABBED.val)
                return GRABBED;
            else
                return RELEASED;
        }
    }

    public enum ShieldState {
        GRABBED, RELEASED_READY_TO_AUT0_GRAB, RELEASE_PENDING;
    }

    public enum Switch {
        LEFT_SWITCH, RIGHT_SWITCH, BOTH_SWITCHES, EITHER_SWITCH, NEITHER_SWITCH;
    }

    /* IMPLEMENTED METHODS */

    /**
     * No default command.
     */
    @Override
    protected void initDefaultCommand() {
        
    }
}