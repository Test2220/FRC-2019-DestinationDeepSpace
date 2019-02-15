package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.*;
import frc.robot.commands.limelight.*;
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
  public final XboxController driver = new XboxController(RobotMap.DRIVER_CONTROLLER);
  public final XboxController manipulator = new XboxController(RobotMap.MANIPULATOR_CONTROLLER);

  // Joystick buttons
  private final JoystickButton aButtonManipulator;
  private final JoystickButton bButtonManipulator;
  private final JoystickButton xButtonManipulator;
  private final JoystickButton yButtonManipulator;
  private final JoystickButton aButtonDriver;
  private final JoystickButton bButtonDriver;
  private final JoystickButton xButtonDriver;

  /**
   * Static constructor that initializes the function of each button. TODO -> COMMENTS!
   */
  public OI() {
    aButtonManipulator = new JoystickButton(manipulator, 1);
    bButtonManipulator = new JoystickButton(manipulator, 2);
    xButtonManipulator = new JoystickButton(manipulator, 3);
    yButtonManipulator = new JoystickButton(manipulator, 4);

    aButtonDriver = new JoystickButton(driver, 1);
    bButtonDriver = new JoystickButton(driver, 2);
    xButtonDriver = new JoystickButton(driver, 3);

    aButtonManipulator.whenPressed(new SetShieldPusher(Value.kForward));
    bButtonManipulator.whenPressed(new SetShieldPusher(Value.kReverse));
    xButtonManipulator.whenPressed(new SetShieldGrabber(State.GRABBED));
    yButtonManipulator.whenPressed(new SetShieldGrabber(State.GRABBED));

    aButtonDriver.whileHeld(new AlignToVisionTarget());
    bButtonDriver.whenPressed(new TurnToAngle(180));
    xButtonDriver.whileHeld(new AimLimelight());
  }

  /*
   * Xbox controller button map:
   * 
   * kBumperLeft(5), kBumperRight(6), kStickLeft(9), kStickRight(10), kA(1),
   * kB(2), kX(3), kY(4), kBack(7), kStart(8);
   */
}