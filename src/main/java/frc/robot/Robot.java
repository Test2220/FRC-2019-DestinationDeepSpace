package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import frc.robot.subsystems.Harles;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.cscore.HttpCamera;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.HttpCamera.HttpCameraKind;
import edu.wpi.first.cameraserver.CameraServer;

/**
 * Main robot class, root of the whole robot and calls all subsystems and
 * commands using the scheduler placed inside each of the necessary methods.
 * 
 * @author Programming Team
 */
public class Robot extends TimedRobot {

  public static Harles harles = new Harles();

  // Subsystem Members
  public static OI oi;
  public static DriveTrain drivetrain;

  // DoubleSolenoid pusher;

  /**
   * Runs once when robot is started, use it for subsystem init
   */
  @Override
  public void robotInit() {
    oi = new OI();
    // m_chooser.setDefaultOption("Default Auto", new ExampleCommand());
    // chooser.addOption("My Auto", new MyAutoCommand());
    // SmartDashboard.putData("Auto mode", m_chooser);
    drivetrain = new DriveTrain();
    // UsbCamera usbCamera = CameraServer.getInstance().startAutomaticCapture();
    // usbCamera.setFPS(6);
    // usbCamera.setResolution(640, 320);
    // ShuffleBoardConfig.driverTab.add("USB Camera", usbCamera).withWidget(BuiltInWidgets.kCameraStream).withSize(4, 3)
    //     .withPosition(3, 0);
    // ShuffleBoardConfig.driverTab
    //     .add("LimeLight", new HttpCamera("LImeLight", "http://10.22.20.11:5800", HttpCameraKind.kMJPGStreamer))
    //     .withWidget(BuiltInWidgets.kCameraStream).withSize(3, 3).withPosition(0, 0);
  }

  /**
   * Loops when robot is on
   */
  @Override
  public void robotPeriodic() {
    ShuffleBoardConfig.updateMatchDetails();
  }

  /**
   * Runs once when no modes are running
   */
  @Override
  public void disabledInit() {
  }

  /**
   * Loops when no modes are running
   */
  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * Runs once on autonomous init
   */
  @Override
  public void autonomousInit() {
  }

  /**
   * Loops during autonomous
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * Runs once on teleop init
   */
  @Override
  public void teleopInit() {
  }

  /**
   * Loops during teleop
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * Loops during test mode. Please clear this when merging with master unless you
   * need to keep code in here
   */
  @Override
  public void testPeriodic() {
  }
}