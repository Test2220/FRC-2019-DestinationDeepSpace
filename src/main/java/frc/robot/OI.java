package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.*;
import frc.robot.subsystems.Harles.Piston;

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
  private JoystickButton aButtonManipulator;
  private JoystickButton bButtonManipulator;
  private JoystickButton xButtonManipulator;
  private JoystickButton yButtonManipulator;

  private JoystickButton aButtonDriver;
  private JoystickButton bButtonDriver;

  /**
   * Constructor that initializes the function of each button
   */
  public OI() {
    driverController = new XboxController(0);
    manipulatorController = new XboxController(1);

    aButtonManipulator = new JoystickButton(manipulatorController, 1);
    bButtonManipulator = new JoystickButton(manipulatorController, 2);
    xButtonManipulator = new JoystickButton(manipulatorController, 3);
    yButtonManipulator = new JoystickButton(manipulatorController, 4);

    aButtonDriver = new JoystickButton(driverController, 1);
    bButtonDriver = new JoystickButton(driverController, 2);

    aButtonManipulator.whenPressed(new SetHarles(Value.kForward, Piston.PUSHER));
    bButtonManipulator.whenPressed(new SetHarles(Value.kReverse, Piston.PUSHER));
    xButtonManipulator.whenPressed(new SetHarles(Value.kForward, Piston.THRUSTER));
    yButtonManipulator.whenPressed(new SetHarles(Value.kReverse, Piston.THRUSTER));

    aButtonDriver.whileHeld(new CorrectPosition());
    bButtonDriver.whenPressed(new TurnToAngle(180));
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