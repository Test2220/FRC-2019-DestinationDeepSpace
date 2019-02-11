package frc.robot;

/**
 * Robot Map contains the constant IDs that correlate to connections set up
 * outside of programming. Maintain it so it is accurate and clear to be
 * adjusted. Convention is to make all IDs constants, or public static final.
 * 
 * @author Programming Team
 */
public class RobotMap {

  /* SHIELD SUBSYSTEM IDS */

  // Piston channel IDs
  public static final int GRABBER_FORWARD = 0;
  public static final int GRABBER_REVERSE = 1;
  public static final int PUSHER_FORWARD = 2;
  public static final int PUSHER_REVERSE = 3;

  // Digital limit swithc IDs TODO Get limit switch IDs
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