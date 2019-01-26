/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */

 /*
  kBumperLeft(5),
  kBumperRight(6),
  kStickLeft(9),
  kStickRight(10),
  kA(1),
  kB(2),
  kX(3),
  kY(4),
  kBack(7),
  kStart(8);
 */
public class OI {

  XboxController controller = new XboxController(0);

  JoystickButton aButton = new JoystickButton(controller, 1);
  JoystickButton bButton = new JoystickButton(controller, 2);
  JoystickButton xButton = new JoystickButton(controller, 3);
  JoystickButton yButton = new JoystickButton(controller, 4);
  JoystickButton rightBumper = new JoystickButton(controller, 6);

  public OI() {
    aButton.whenPressed(new SetHarles(Value.kForward, 1));
    bButton.whenPressed(new SetHarles(Value.kReverse, 1));
    xButton.whenPressed(new SetHarles(Value.kForward, 2));
    yButton.whenPressed(new SetHarles(Value.kReverse, 2));
    rightBumper.whenPressed(new PlaceHatchPanel());
  }

  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());
}
