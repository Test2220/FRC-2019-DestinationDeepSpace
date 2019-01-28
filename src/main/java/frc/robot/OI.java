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
public class OI {

  XboxController manipulator = new XboxController(0);

  JoystickButton aButton = new JoystickButton(manipulator, 1);
  JoystickButton bButton = new JoystickButton(manipulator, 2);
  JoystickButton xButton = new JoystickButton(manipulator, 3);
  JoystickButton yButton = new JoystickButton(manipulator, 4);
  JoystickButton rightBumper = new JoystickButton(manipulator, 6);

  /**
   * Constructor that initializes the function of each button
   */
  public OI() {
    aButton.whenPressed(new SetHarles(Value.kForward, 1));
    bButton.whenPressed(new SetHarles(Value.kReverse, 1));
    xButton.whenPressed(new SetHarles(Value.kForward, 2));
    yButton.whenPressed(new SetHarles(Value.kReverse, 2));
  }

  /*
   * Xbox controller button map:
   * 
   * kBumperLeft(5), kBumperRight(6), kStickLeft(9), kStickRight(10), kA(1),
   * kB(2), kX(3), kY(4), kBack(7), kStart(8);
   */
}
