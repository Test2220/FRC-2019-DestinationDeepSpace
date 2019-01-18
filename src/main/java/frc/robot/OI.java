/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

  public OI() {

  }

  private XboxController driverController = new XboxController(0);
  // private XboxController manipulatorController = new XboxController(1);

  public XboxController getDC() {
    return driverController;
  }

  // public XboxController getMC() {
  //   return manipulatorController;
  // }
}