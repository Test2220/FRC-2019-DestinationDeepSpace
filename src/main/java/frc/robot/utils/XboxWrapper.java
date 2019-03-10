package frc.robot.utils;

import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.POVButton;

/**
 * Wrapper class for the standard xbox controller class that lets us access
 * JoystickButton and POVButton versions of the controls.
 * 
 * @author Reece
 */
public class XboxWrapper {

    /* CONSTANTS */

    // Intensity of controller rumble
    private static final double RUMBLE_INTENSITY = 1;

    /* INSTANCE VARIABLES */

    // Instance xbox controller member
    private final XboxController xb;

    // Notifier to stop rumble after certain amount of time
    private final Notifier stopRumble = new Notifier(() -> rumble(0));

    /**
     * XboxWrapper constructor initializes an xbox controller object instance
     * variable based on specified port
     * 
     * @param xbPort Port of xbox controller on DS
     */
    public XboxWrapper(int xbPort) {
        xb = new XboxController(xbPort);
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
     * Gets the X axis value of the stick on the specified hand side
     * 
     * @param hand Which hand side
     * @return Returns the X axis value from specified stick
     */
    public double getX(Hand hand) {
        return xb.getX(hand);
    }

    /**
     * Gets the Y axis value of the stick on the specified hand side
     * 
     * @param hand Which hand side
     * @return Returns the Y axis value from specified stick
     */
    public double getY(Hand hand) {
        return xb.getY(hand);
    }

    /**
     * Gets the value of the trigger on the specified hand side
     * 
     * @param hand Which hand side
     * @return Returns the trigger value from specified side
     */
    public double getTrigger(Hand hand) {
        return xb.getTriggerAxis(hand);
    }

    /**
     * Rumble the controller for a given time
     * 
     * @param seconds How long to rumble the controller for
     */
    public void rumbleFor(double seconds) {
        rumble(RUMBLE_INTENSITY);
        stopRumble.startSingle(seconds);
    }

    /**
     * Rumble the controller at a specific intensity
     * 
     * @param intensity Intensity at which to rumble the controller
     */
    private void rumble(double intensity) {
        xb.setRumble(RumbleType.kLeftRumble, intensity);
        xb.setRumble(RumbleType.kRightRumble, intensity);
    }

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
}