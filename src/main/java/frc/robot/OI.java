package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.*;
import frc.robot.subsystems.Shield.State;

/**
 * Stands for Operator Interface, this class is where code relating the gamepad
 * to programmed functions on the robot are placed.
 * 
 * @author Programming Team
 */
public class OI {

  // Xbox controllers
  private XboxController driverController;
  private XboxController manipulatorController;

  // Joystick buttons
  private JoystickButton aButton;
  private JoystickButton bButton;
  private JoystickButton xButton;
  private JoystickButton yButton;

  /**
   * Constructor that initializes the function of each button
   */
  public OI() {
    driverController = new XboxController(0);
    manipulatorController = new XboxController(1);
    
    aButton = new JoystickButton(manipulatorController, 1);
    bButton = new JoystickButton(manipulatorController, 2);
    xButton = new JoystickButton(manipulatorController, 3);
    yButton = new JoystickButton(manipulatorController, 4);

    aButton.whenPressed(new SetShieldPusher(Value.kForward));
    bButton.whenPressed(new SetShieldPusher(Value.kReverse));
    xButton.whenPressed(new SetShieldGrabber(State.GRABBED));
    yButton.whenPressed(new SetShieldGrabber(State.RELEASED));
  }

  public XboxController getDriver() {
    return driverController;
  }

  public XboxController getManipulator() {
    return manipulatorController;
  }

  /*
   * Xbox controller button map:
   * 
   * kBumperLeft(5), kBumperRight(6), kStickLeft(9), kStickRight(10), kA(1),
   * kB(2), kX(3), kY(4), kBack(7), kStart(8);
   */
}