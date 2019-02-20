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
  public static final int RIGHT_ARM = 13;
  public static final int INTAKE = 8;

  /* SHIELD SUBSYSTEM IDS */

  // Double solenoid channel IDs
  public static final int PUSHER_FORWARD = 0;
  public static final int PUSHER_REVERSE = 1;
  public static final int GRABBER_FORWARD = 4;
  public static final int GRABBER_REVERSE = 5;

  // Limit switch digital input IDs TODO -> set actual IDs before testing limit switches
  public static final int LEFT_SWITCH = 0;
  public static final int RIGHT_SWITCH = 1;

  /* DRIVETRAIN SUBSYSTEM IDS */
  public static final int LEFT_MASTER = 11;
  public static final int LEFT_SLAVE = 1;
  public static final int RIGHT_MASTER = 12;
  public static final int RIGHT_SLAVE = 2;


  /* ENCODER IDS */
  public static final int WHEEL_RADIUS = 3;

  /* LIMELIGHT SUBSYSTEM IDS */
  public static final String LIMELIGHT_TABLE_NAME = "limelight";
}
