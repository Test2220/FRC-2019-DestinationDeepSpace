/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.utils.*;
import frc.robot.commands.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

  // Instance Variables:
  TwilightXboxController driverController;
  TwilightXboxController hatchPanelTestController1;
  TwilightXboxController hatchPanelTestController2;

  /**
   * Constructor that assigns certain functions to certain buttons of the
   * controller
   */
  public OI() {
    hatchPanelTestController1 = new TwilightXboxController(0);
    hatchPanelTestController2 = new TwilightXboxController(1);
    hatchPanelTestController1.getAButton().whenPressed(new MoveSpringHP(0));
    hatchPanelTestController1.getBButton().whenPressed(new MoveSpringHP(1));

    hatchPanelTestController2.getAButton().whenPressed(new MoveVelcroHP(1, 0));
    hatchPanelTestController2.getBButton().whenPressed(new MoveVelcroHP(1, 1));
    hatchPanelTestController2.getXButton().whenPressed(new MoveVelcroHP(2, 0));
    hatchPanelTestController2.getYButton().whenPressed(new MoveVelcroHP(2, 1));
  }
}