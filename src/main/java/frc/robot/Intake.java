package frc.robot;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 * Intake subsystem for the robot to intake cargo. Contains all necessary
 * methods for using the robot's intake subsystem.
 * 
 * @author Dhruv Balasubramanian
 * @version 1/10/2019
 */

public class Intake extends Subsystem {

    // Initializes talons
    WPI_TalonSRX leftBottom;
    WPI_TalonSRX leftTop;
    WPI_TalonSRX rightBottom;
    WPI_TalonSRX rightTop;

    /**
     * Constructor for this subsystem. No arguments necessary or present.
     * Initializes talon objects and their correct direction.
     */
    public Intake() {
        // initializes talons
        WPI_TalonSRX leftBottom = new WPI_TalonSRX(RobotMap.INTAKE_LEFT_BOTTOM);
        WPI_TalonSRX leftTop = new WPI_TalonSRX(RobotMap.INTAKE_LEFT_TOP);
        WPI_TalonSRX rightBottom = new WPI_TalonSRX(RobotMap.INTAKE_RIGHT_BOTTOM);
        WPI_TalonSRX rightTop = new WPI_TalonSRX(RobotMap.INTAKE_RIGHT_TOP);

        // Sets directions of talons
        leftBottom.setInverted(true);
        leftTop.setInverted(true);
        rightBottom.setInverted(false);
        rightTop.setInverted(false);
    }

    /**
     * Spins the motors based upon the control mode and value that are passed as
     * arguments.
     * 
     * @param controlMode the mode of control of the talons
     * @param value       the threshold at which to spin the motors
     */
    public void spin(ControlMode controlMode, double value) {
        leftBottom.set(controlMode, value);
        leftTop.set(controlMode, value);
        rightBottom.set(controlMode, value);
        rightTop.set(controlMode, value);
    }

    public void initDefaultCommand() {

    }
}