package frc.robot.utils;

import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.POVButton;

/**
 * Wrapper class for the standard xbox controller class that lets us access
 * JoystickButton, POVButton, and TriggerButton versions of the controls.
 * 
 * @author Reece
 */
public class XboxWrapper {

    /* CONSTANTS */

    // Joystick deadzone
    private static final double JOY_DEADZONE = 0.05; // TODO: Test value

    // Trigger deadzone
    private static final double TRIGGER_DEADZONE = 0.1; // TODO: Test value

    // Intensity of controller rumble
    private static final double RUMBLE_INTENSITY = 1;

    // Length of controller rumble in seconds
    public static final double RUMBLE_TIME = 0.5;

    // Length of controller rumble for warnings
    public static final double WARNING_RUMBLE = 1.5;

    /* INSTANCE VARIABLES */

    // Instance xbox controller member
    private final XboxController xb;

    /* RUMBLE HANDLERS */

    // Notifier to stop rumble after certain amount of time
    private final Notifier stopRumble = new Notifier(() -> rumble(0));

    // Controller is rumbling
    private boolean isRumbling = false;

    /* CONSTRUCTOR */

    /**
     * XboxWrapper constructor initializes an xbox controller object instance
     * variable based on specified port
     * 
     * @param xbPort Port of xbox controller on DS
     */
    public XboxWrapper(int xbPort) {
        xb = new XboxController(xbPort);
    }

    /* GETTERS */

    /**
     * Returns the controller object
     * 
     * @return the controller object instance variable
     */

    public XboxController getControllerObject() {
        return xb;
    }

    /**
     * Returns a joystick button off of the specified button
     * 
     * @param button Which button to use
     * @return Returns a joystick button with specified button
     */
    public JoystickButton getButton(Button button) {
        return new JoystickButton(xb, button.value);
    }

    public TriggerButton getTriggerButton(Hand hand) {
        return new TriggerButton(hand);
    }

    /**
     * Returns a POV button off of the specified d-pad angle
     * 
     * @param dpad Which d-pad angle to use
     * @return Returns a POV button with specified d-pad angle
     */
    public POVButton getDpad(Dpad dpad) {
        return new POVButton(xb, dpad.angle);
    }

    /**
     * Gets the X axis value of the stick on the specified hand side if it is larger
     * than the deadzone
     * 
     * @param hand Which hand side
     * @return Returns the X axis value from specified stick
     */
    public double getX(Hand hand) {
        double x = xb.getX(hand);
        return Math.abs(x) > JOY_DEADZONE ? x : 0;
    }

    /**
     * Gets the Y axis value of the stick on the specified hand side if it is larger
     * than the deadzone
     * 
     * @param hand Which hand side
     * @return Returns the Y axis value from specified stick
     */
    public double getY(Hand hand) {
        double y = xb.getY(hand);
        return Math.abs(y) > JOY_DEADZONE ? y : 0;
    }

    /**
     * Gets the value of the trigger on the specified hand side if it is larger than
     * the deadzone
     * 
     * @param hand Which hand side
     * @return Returns the trigger value from specified side
     */
    public double getTrigger(Hand hand) {
        double trigger = xb.getTriggerAxis(hand);
        return trigger > TRIGGER_DEADZONE ? trigger : 0;
    }

    /* CONTROL METHODS */

    /**
     * Rumble the controller for a given time
     * 
     * @param seconds How long to rumble the controller for
     */
    public void rumbleFor(double seconds) {
        if (!isRumbling) {
            rumble(RUMBLE_INTENSITY);
            stopRumble.startSingle(seconds);
        }
    }

    /**
     * Rumble the controller at a specific intensity
     * 
     * @param intensity Intensity at which to rumble the controller
     */
    private void rumble(double intensity) {
        xb.setRumble(RumbleType.kLeftRumble, intensity);
        xb.setRumble(RumbleType.kRightRumble, intensity);
        isRumbling = intensity != 0;
    }

    public void stopRumble() {
        rumble(0);
        stopRumble.stop();
    }

    /* CONTROL ENUMERATION */

    /**
     * Enumeration of all standard buttons on xbox controller, contains their raw
     * axis value
     */
    public enum Button {
        A(1), B(2), X(3), Y(4), LEFT_BUMPER(5), RIGHT_BUMPER(6), BACK(7), START(8), LEFT_STICK(9), RIGHT_STICK(10);

        private final int value;

        Button(int value) {
            this.value = value;
        }
    }

    /**
     * Enumeration of all POV (d-pad) positions on xbox controller, contains their
     * raw angle value
     */
    public enum Dpad {
        UP(0), UP_RIGHT(45), RIGHT(90), DOWN_RIGHT(135), DOWN(180), DOWN_LEFT(225), LEFT(270), UP_LEFT(315);

        private final int angle;

        Dpad(int angle) {
            this.angle = angle;
        }
    }

    /**
     * Subclass of Button for the triggers on an xbox controller. Takes in a hand
     * enum, and takes XboxWrapper's xbox controller instance variable. It becomes
     * activated when the trigger has a higher value than the deadzone set in
     * XboxWrapper.
     */
    public class TriggerButton extends edu.wpi.first.wpilibj.buttons.Button {

        /* INSTANCE VARIABLES */

        // Hand side to get trigger value from
        private final Hand hand;

        /* CONSTRUCTOR */

        /**
         * Create a new trigger button with given xbox controller and hand side.
         * 
         * @param hand Hand side to get trigger value from
         */
        private TriggerButton(Hand hand) {
            this.hand = hand;
        }

        /* IMPLEMENTED METHODS */

        /**
         * Implemented get method. Returns true if the trigger axis is greater than the
         * deadzone constant.
         */
        @Override
        public boolean get() {
            return xb.getTriggerAxis(hand) > TRIGGER_DEADZONE;
        }
    }
}