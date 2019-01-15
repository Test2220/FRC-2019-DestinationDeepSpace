package frc.robot.utils;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is essentially a mapping from the buttons of the xbox controller
 * to their names. Instantiating it can be done to access all of the button
 * objects of this class.
 * 
 * @author Dhruv Balasubramanian
 * @version 1/13/2018
 */
public class TwilightXboxController {

    private XboxController controller;
    private Button aButton;
    private Button bButton;
    private Button xButton;
    private Button yButton;
    private Button leftBumper;
    private Button rightBumper;
    private Button backButton;
    private Button startButton;

    public TwilightXboxController(int port) {
        controller = new XboxController(port);
        aButton = new JoystickButton(controller, 1);
        bButton = new JoystickButton(controller, 2);
        xButton = new JoystickButton(controller, 3);
        yButton = new JoystickButton(controller, 4);
        leftBumper = new JoystickButton(controller, 5);
        rightBumper = new JoystickButton(controller, 6);
        backButton = new JoystickButton(controller, 7);
        startButton = new JoystickButton(controller, 8);
    }

    /**
     * Getter that returns the Button aButton.
     *
     * @return aButton the Button aButton.
     */
    public Button getAButton() {
        return aButton;
    }

    /**
     * Getter that returns the Button bButton.
     *
     * @return bButton the Button bButton.
     */
    public Button getBButton() {
        return bButton;
    }

    /**
     * Getter that returns the Button xButton.
     *
     * @return xButton the Button xButton.
     */
    public Button getXButton() {
        return xButton;
    }

    /**
     * Getter that returns the Button yButton.
     *
     * @return yButton the Button yButton.
     */
    public Button getYButton() {
        return yButton;
    }

    /**
     * Getter that returns the Button leftBumper.
     *
     * @return leftBumper the Button leftBumper.
     */
    public Button getLeftBumper() {
        return leftBumper;
    }

    /**
     * Getter that returns the Button rightBumper.
     *
     * @return rightBumper the Button rightBumper.
     */
    public Button getRightBumper() {
        return rightBumper;
    }

    /**
     * Getter that returns the Button backButton.
     *
     * @return backButton the Button backButton.
     */
    public Button getBackButton() {
        return backButton;
    }

    /**
     * Getter that returns the Button startButton.
     *
     * @return startButton the Button startButton.
     */
    public Button getStartButton() {
        return startButton;
    }
}