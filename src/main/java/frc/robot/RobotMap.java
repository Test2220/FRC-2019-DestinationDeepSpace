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

  /* CARGO SUBSYSTEM  IDS */

  // Talon IDs
  public static final int LEFT_ARM_FOLLOWER = 6;
  public static final int RIGHT_ARM_MASTER = 13;    // On the external CAN Bus -- has encoders
  public static final int INTAKE = 8;

  // Limit switch IDs
  public static final int UPPER_CARGO_LIMIT = 2;
  public static final int LOWER_CARGO_LIMIT = 3;

  /* SHIELD SUBSYSTEM IDS */

  // Double solenoid channel IDs
  public static final int PUSHER_FORWARD = 0;
  public static final int PUSHER_REVERSE = 1;
  public static final int GRABBER_FORWARD = 4;
  public static final int GRABBER_REVERSE = 5;

  /* LIMIT SWITCH DIGITAL INPUT IDS */
  public static final int LEFT_SWITCH = 0;
  public static final int RIGHT_SWITCH = 1;

  /* DRIVETRAIN SUBSYSTEM IDS -- TALON SRX CAN IDS */
  public static final int LEFT_MASTER = 11;       // On the external CAN Bus -- has encoders
  public static final int LEFT_FOLLOWER = 1;
  public static final int RIGHT_MASTER = 12;      // On the external CAN Bus -- has encoders
  public static final int RIGHT_FOLLOWER = 2;

  /* ENCODER CONSTANTS */
  public static final int WHEEL_RADIUS = 3;

  /* LIMELIGHT SUBSYSTEM IDS */
  public static final String LIMELIGHT_TABLE_NAME = "limelight";
}
