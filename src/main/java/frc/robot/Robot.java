package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.subsystems.*;
import frc.robot.subsystems.Shield;
import frc.robot.subsystems.leds.Leds;
import frc.robot.subsystems.Cargo;
import frc.robot.subsystems.Drivetrain;

/**
 * Main robot class, root of the whole robot and calls all subsystems and
 * commands using the scheduler placed inside each of the necessary methods.
 * 
 * @author Programming Team
 */
public class Robot extends TimedRobot {

  // Subsystem Members
  public static Limelight limelight;
  public static Drivetrain drivetrain;
  public static NavX navX;
  public static Shield shield;
  public static Cargo cargo;
  public static Superstructure superstructure;
  public static Leds leds;
  public static OI oi;

  /**
   * Runs once when robot is started, use it for subsystem init.
   */
  @Override
  public void robotInit() {
    // Initialize subsystem members
    limelight = new Limelight();
    drivetrain = new Drivetrain();
    navX = new NavX();
    shield = new Shield();
    cargo = new Cargo();
    superstructure = new Superstructure();
    leds = new Leds();
    oi = new OI();
  }

  /**
   * Loops when robot is on.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * Runs once when no modes are running.
   */
  @Override
  public void disabledInit() {
    cargo.setNeutral(NeutralMode.Coast);
  }

  /**
   * Loops when no modes are running.
   */
  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
    ShuffleBoardConfig.updateMatchDetails();
    Robot.limelight.setCameraMode(Limelight.CameraMode.DRIVER_CAMERA);
    Robot.limelight.setLEDMode(Limelight.LEDMode.OFF);

  }

  /**
   * Runs once on autonomous init.
   */
  @Override
  public void autonomousInit() {
    shield.grabHP();
    cargo.setNeutral(NeutralMode.Brake);
  }

  /**
   * ep Loops during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * Runs once on teleop init.
   */
  @Override
  public void teleopInit() {
    cargo.setNeutral(NeutralMode.Brake);
  }

  /**
   * Loops during teleop.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * Loops during test mode. Please clear this when merging with master unless you
   * need to keep code in here.
   */
  @Override
  public void testPeriodic() {
  }
}