package frc.robot.commands; 

import com.ctre.phoenix.motorcontrol.ControlMode; 

import edu.wpi.first.wpilibj.Joystick; 
import edu.wpi.first.wpilibj.command.Command; 
import frc.robot.subsystems. * ; 

/**
 * Command that runs the Active Intake.
 * 
 * @author Dhruv Balasubramanian
 * @version 1/12/2019 */
public class ActiveIntake extends Command {

    // Instance variables
    private boolean isRunning = true; 
    private double speed; 
    Intake intakeObject = new Intake(); 
    Joystick stick; 

    /**
     * Constructor for the ActiveIntake Command, initializes this class's Joystick
     * object
     * 
     * @param stick the stick that the manipulator will use in order to control the
     *              intake.
     */
    public ActiveIntake(Joystick stick) {
        requires(intakeObject);
        this.stick = stick;
    }

    /**
     * Spins the motors while command is running, and uses exponential drive to give
     * drivers better control.
     */
    @Override
    public void execute() {
        if (isRunning) {
            // Takes the speed to the 1.75th power in order to improve control
            speed = stick.getY();
            speed = Math.pow(speed, 1.75);
            intakeObject.spin(ControlMode.PercentOutput, speed);
        }
    }

    public void terminate() {
        isRunning = false;
    }

    /**
     * Determines whether the command is finished or not.
     * 
     * @return true if the command is finished, false otherwise.
     */
    @Override
    public boolean isFinished() {
        return !isRunning;
    }
}