package frc.robot; 

import edu.wpi.first.wpilibj.TimedRobot; 
import edu.wpi.first.wpilibj.command.Scheduler; 
import frc.robot.subsystems. * ; 
import frc.robot.subsystems.Shield;
import frc.robot.subsystems.Cargo;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.cameraserver.CameraServer;

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
  public static OI oi;

  /**
   * Runs once when robot is started, use it for subsystem init.
   */
  @Override
  public void robotInit() {
    // m_chooser.setDefaultOption("Default Auto", new ExampleCommand());
    // chooser.addOption("My Auto", new MyAutoCommand());
    // SmartDashboard.putData("Auto mode", m_chooser);

    // Initialize subsystem members
    limelight = new Limelight(); 
    navX = new NavX(); 
    drivetrain = new Drivetrain();
    shield = new Shield();
    cargo = new Cargo(); 
    oi = new OI();

    // Start USB camera recording
    CameraServer.getInstance().startAutomaticCapture();
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
 
  }

  /**
   * Loops when no modes are running.
   */
  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run(); 
  }

  /**
   * Runs once on autonomous init.
   */
  @Override
  public void autonomousInit() {
  }

  /**
   * Loops during autonomous.
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