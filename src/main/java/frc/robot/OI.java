package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import frc.robot.commands.TurnToAngle;
import frc.robot.commands.cargo.ArmManual;
import frc.robot.commands.cargo.ControlArm;
import frc.robot.commands.cargo.SetHabPiston;
import frc.robot.commands.limelight.*;
import frc.robot.commands.shield.*;
import frc.robot.subsystems.Cargo.CargoDesiredState;
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

  public boolean childMode = true;
  public boolean childActive = false;

  /**
   * OI constructor that maps functions to controller buttons
   */
  public OI() {
    /* DRIVER CONTROLS */

    // Limelight automation
    driver.getButton(Button.A).whileHeld(new AlignToVisionTarget(RobotMap.LOADING_STATION_AND_ROCKET_PIPELINE));
    driver.getButton(Button.A).whileHeld(new TakeSnapshot(false));
    // driver.getButton(Button.B).whileHeld(new DriveToLoadingStation());
    driver.getButton(Button.B).whileHeld(new AlignToVisionTarget(RobotMap.CARGO_SHIP_PIPELINE));
    driver.getButton(Button.B).whileHeld(new TakeSnapshot(false));

    // Turning to angles
    // driver.getButton(Button.RIGHT_BUMPER).whenPressed(new TurnToAngle(180));

    
    /* MANIPULATOR CONROLS */
    
    // SHIELD pusher piston controls
    if (!childMode) {
      manipulator.getButton(Button.A).whenPressed(new SetShieldPusher(Value.kForward));
      manipulator.getButton(Button.A).whenReleased(new SetShieldPusher(Value.kReverse));

      // SHIELD grabber piston controls
      manipulator.getButton(Button.X).whenPressed(new SetShieldGrabber(GrabberState.GRABBED));
      manipulator.getButton(Button.Y).whenPressed(new SetShieldGrabber(GrabberState.RELEASED));

      // Cargo arm preset positions
      manipulator.getDpad(Dpad.DOWN).whenPressed(new ControlArm(CargoDesiredState.LOWER_LIMIT));
      manipulator.getDpad(Dpad.RIGHT).whenPressed(new ControlArm(CargoDesiredState.ROCKET));
      manipulator.getDpad(Dpad.UP).whenPressed(new ControlArm(CargoDesiredState.CARGO_SHIP));
      manipulator.getDpad(Dpad.LEFT).whenPressed(new ControlArm(CargoDesiredState.UPPER_LIMIT));
    
      // Hab climber controls
      // manipulator.getTriggerButton(Hand.kRight).whenPressed(new SetHabPiston(Value.kForward));
      // manipulator.getTriggerButton(Hand.kLeft).whenPressed(new SetHabPiston(Value.kReverse));

      // Set arm to manual mode
      manipulator.getButton(Button.START).whenPressed(new ArmManual());

      // Take limelight snapshot
      // manipulator.getButton(Button.LEFT_BUMPER).whenPressed(new TakeSnapshot(true));
    }
  }
}