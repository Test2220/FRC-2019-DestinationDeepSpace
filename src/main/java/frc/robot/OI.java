package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.shield.*;
import frc.robot.subsystems.Shield.State;

/**
 * Stands for Operator Interface, this class is where code relating the gamepad
 * to programmed functions on the robot are placed.
 * 
 * @author Programming Team
 */
public class OI {

  // Xbox controllers
  public static final XboxController driver = new XboxController(RobotMap.DRIVER_CONTROLLER);
  public static final XboxController manipulator = new XboxController(RobotMap.MANIPULATOR_CONTROLLER);

  // Joystick buttons
  private static final JoystickButton aButton;
  private static final JoystickButton bButton;
  private static final JoystickButton xButton;
  private static final JoystickButton yButton;

  /**
   * Disable OI constructor.
   */
  private OI() {
  }

  /**
   * Static constructor that initializes the function of each button. TODO -> COMMENTS!
   */
  static {
    aButton = new JoystickButton(manipulator, 1);
    bButton = new JoystickButton(manipulator, 2);
    xButton = new JoystickButton(manipulator, 3);
    yButton = new JoystickButton(manipulator, 4);

    aButton.whenPressed(new SetShieldPusher(Value.kForward));
    bButton.whenPressed(new SetShieldPusher(Value.kReverse));
    xButton.whenPressed(new SetShieldGrabber(State.GRABBED));
    yButton.whenPressed(new SetShieldGrabber(State.RELEASED));
  }

  /*
   * Xbox controller button map:
   * 
   * kBumperLeft(5), kBumperRight(6), kStickLeft(9), kStickRight(10), kA(1),
   * kB(2), kX(3), kY(4), kBack(7), kStart(8);
   */
}