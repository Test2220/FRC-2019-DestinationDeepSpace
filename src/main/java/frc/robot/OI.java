package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.POVButton;
import frc.robot.commands.*;
import frc.robot.commands.cargo.ArmToPosition;
import frc.robot.commands.cargo.ArmToSetPosition;
import frc.robot.commands.limelight.*;
import frc.robot.commands.shield.*;
import frc.robot.subsystems.Cargo;
import frc.robot.subsystems.Shield.GrabberState;

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
  private final JoystickButton aButtonManipulator = new JoystickButton(manipulator, 1);
  private final JoystickButton bButtonManipulator = new JoystickButton(manipulator, 2);
  private final JoystickButton xButtonManipulator = new JoystickButton(manipulator, 3);
  private final JoystickButton yButtonManipulator = new JoystickButton(manipulator, 4);
  private final JoystickButton aButtonDriver = new JoystickButton(driver, 1);
  private final JoystickButton bButtonDriver = new JoystickButton(driver, 2);
  private final POVButton dpadUp = new POVButton(manipulator, 0);
  private final POVButton dpadRight = new POVButton(manipulator, 90);
  private final POVButton dpadDown = new POVButton(manipulator, 180);
  private final POVButton dpadLeft = new POVButton(manipulator, 270);
  private final JoystickButton leftBumper = new JoystickButton(manipulator, 5);
  private final JoystickButton rightBumper = new JoystickButton(manipulator, 6);

  /**
   * Static constructor that initializes the function of each button. TODO ->
   * COMMENTS!
   */
  public OI() {
    // manipulator controls
    aButtonManipulator.whenPressed(new SetShieldPusher(Value.kForward));
    aButtonManipulator.whenReleased(new SetShieldPusher(Value.kReverse));
    xButtonManipulator.whenPressed(new SetShieldGrabber(GrabberState.GRABBED));
    yButtonManipulator.whenPressed(new SetShieldGrabber(GrabberState.RELEASED));
    // aButtonManipulator.whileHeld(new ExtendPusher());

    // driver controls
    aButtonDriver.whileHeld(new AlignToVisionTarget());

    dpadUp.whenPressed(new ArmToPosition(Cargo.ARM_CARGOSHIP));
    dpadRight.whenPressed(new ArmToPosition(Cargo.ARM_ROCKET));
    dpadLeft.whenPressed(new ArmToPosition(Cargo.ARM_UP));
    dpadDown.whenPressed(new ArmToPosition(Cargo.ARM_FLOOR));
  }

  /*
   * Xbox controller button map:
   * 
   * kBumperLeft(5), kBumperRight(6), kStickLeft(9), kStickRight(10), kA(1),
   * kB(2), kX(3), kY(4), kBack(7), kStart(8);
   */
}