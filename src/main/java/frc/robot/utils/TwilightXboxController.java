package frc.robot.utils;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is a mapping from the buttons of the xbox controller to their
 * names.
 * 
 * @author Dhruv Balasubramanian
 * @version 1/13/2018
 */
public class TwilightXboxController {

    private XboxController controller;
    public final Button A_BUTTON;
    public final Button B_BUTTON;
    public final Button X_BUTTON;
    public final Button Y_BUTTON;
    public final Button LEFT_BUMPER ;
    public final Button RIGHT_BUMPER;
    public final Button BACK_BUTTON ;
    public final Button START_BUTTON;

    public TwilightXboxController() {
        controller = new XboxController(0);
        A_BUTTON = new JoystickButton(controller, 1);
        B_BUTTON = new JoystickButton(controller, 2);
        X_BUTTON = new JoystickButton(controller, 3);
        Y_BUTTON = new JoystickButton(controller, 4);
        LEFT_BUMPER = new JoystickButton(controller, 5);
        RIGHT_BUMPER = new JoystickButton(controller, 6);
        BACK_BUTTON = new JoystickButton(controller, 7);
        START_BUTTON = new JoystickButton(controller, 8);
    }
}