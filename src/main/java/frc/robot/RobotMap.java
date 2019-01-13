/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

  // TODO: Figure out proper values to put here:

  // Intake talons
  public static final int INTAKE_LEFT_BOTTOM = 0;
  public static final int INTAKE_LEFT_TOP = 1;
  public static final int INTAKE_RIGHT_BOTTOM = 2;
  public static final int INTAKE_RIGHT_TOP = 3;

  // Spring Hatch Panel Prototype
  public static final int SPRING_HP_PROTOTYPE_FORWARD = 4;
  public static final int SPRING_HP_PROTOTYPE_REVERSE = 5;

  // Velcro Hatch Panel Prototype
  public static final int VELCRO_HP_PROTOTYPE_FORWARD_1 = 6;
  public static final int VELCRO_HP_PROTOTYPE_REVERSE_1 = 7;
  public static final int VELCRO_HP_PROTOTYPE_FORWARD_2 = 8;
  public static final int VELCRO_HP_PROTOTYPE_REVERSE_2 = 9;
}