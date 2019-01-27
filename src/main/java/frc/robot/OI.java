package frc.robot;

import edu.wpi.first.wpilibj.XboxController;

/**
 * Stands for Operator Interface, this class is where code relating the gamepad
 * to programmed functions on the robot are placed.
 * 
 * @author Programming Team
 */
public class OI {

  private XboxController driverController;

  public OI() {
    driverController = new XboxController(0);
  }

  public XboxController getDC() {
    return driverController;
  }
}