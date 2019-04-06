package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.commands.TurnToAngle;
import frc.robot.commands.cargo.ArmToPosition;
import frc.robot.commands.cargo.ReZeroArm;
import frc.robot.commands.limelight.*;
import frc.robot.commands.shield.*;
import frc.robot.subsystems.Cargo;
import frc.robot.subsystems.Shield.GrabberState;
import frc.robot.utils.XboxWrapper;
import frc.robot.utils.XboxWrapper.Button;
import frc.robot.utils.XboxWrapper.Dpad;

/**
 * Stands for Operator Interface, this class is where code relating the gamepad
 * to programmed functions on the robot are placed.
 * 
 * @author Programming Team
 */
public class OI {

  // Xbox controllers
  public final XboxWrapper driver = new XboxWrapper(RobotMap.DRIVER_CONTROLLER);
  public final XboxWrapper manipulator = new XboxWrapper(RobotMap.MANIPULATOR_CONTROLLER);

  /**
   * OI constructor that maps functions to controller buttons
   */
  public OI() {

    /* DRIVER CONTROLS */

    // Limelight automation
    driver.getButton(Button.A).whileHeld(new AlignToVisionTarget(RobotMap.LOADING_STATION_AND_ROCKET_PIPELINE));
    // driver.getButton(Button.B).whileHeld(new DriveToLoadingStation());
    driver.getButton(Button.B).whileHeld(new AlignToVisionTarget(RobotMap.CARGO_SHIP_PIPELINE));

    // Turning to angles
    driver.getButton(Button.RIGHT_BUMPER).whenPressed(new TurnToAngle(180));

    /* MANIPULATOR CONROLS */
    
    // SHIELD pusher piston controls
    manipulator.getButton(Button.A).whenPressed(new SetShieldPusher(Value.kForward));
    manipulator.getButton(Button.A).whenReleased(new SetShieldPusher(Value.kReverse));

    // SHIELD grabber piston controls
    manipulator.getButton(Button.X).whenPressed(new SetShieldGrabber(GrabberState.GRABBED));
    manipulator.getButton(Button.Y).whenPressed(new SetShieldGrabber(GrabberState.RELEASED));

    // Cargo arm preset positions
    manipulator.getDpad(Dpad.DOWN).whenPressed(new ArmToPosition(Cargo.ARM_FLOOR));
    manipulator.getDpad(Dpad.RIGHT).whenPressed(new ArmToPosition(Cargo.ARM_ROCKET));
    manipulator.getDpad(Dpad.UP).whenPressed(new ArmToPosition(Cargo.ARM_CARGOSHIP));
    manipulator.getDpad(Dpad.LEFT).whenPressed(new ArmToPosition(Cargo.ARM_UP));

    // Rezero cargo arm
    manipulator.getButton(Button.START).whenPressed(new ReZeroArm());

    // Take limelight snapshot
    manipulator.getButton(Button.LEFT_BUMPER).whenPressed(new TakeSnapshot());
  }
}