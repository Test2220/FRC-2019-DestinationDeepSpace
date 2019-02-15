package frc.robot;

/**
 * Robot Map contains the constant IDs that correlate to connections set up
 * outside of programming. Maintain it so it is accurate and clear to be
 * adjusted. Convention is to make all IDs constants, or public static final.
 * 
 * @author Programming Team
 */
public class RobotMap {
  
  /* XBOX CONTROLLER IDS */
  public static final int DRIVER_CONTROLLER = 0;
  public static final int MANIPULATOR_CONTROLLER = 1;

  /* CARGO SUBSYSTEM IDS */
  public static final int LEFT_ARM = 6;
  public static final int RIGHT_ARM = 7;
  public static final int INTAKE = 8;

  /* SHIELD SUBSYSTEM IDS */

  // Double solenoid channel IDs
  public static final int GRABBER_FORWARD = 0;
  public static final int GRABBER_REVERSE = 1;
  public static final int PUSHER_FORWARD = 2;
  public static final int PUSHER_REVERSE = 3;

  // Limit switch digital input IDs TODO -> set actual IDs before testing limit switches
  public static final int LEFT_SWITCH = 0;
  public static final int RIGHT_SWITCH = 1;

  /* DRIVETRAIN SUBSYSTEM IDS */
  public static final int LEFT_MASTER = 1;
  public static final int LEFT_SLAVE = 2;
  public static final int RIGHT_MASTER = 3;
  public static final int RIGHT_SLAVE = 4;

  /* LIMELIGHT SUBSYSTEM IDS */
  public static final String LIMELIGHT_TABLE_NAME = "limelight";
}
