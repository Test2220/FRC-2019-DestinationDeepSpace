package frc.robot.commands; 

import edu.wpi.first.wpilibj.command.Command; 
import frc.robot.Robot; 

/**
 * Command that runs the spring hatch panel prototype
 * 
 * @author Dhruv Balasubramanian
 * @version 1/12/2019 
 */
public class MoveSpringHP extends Command {

    // Instance Variables
    private boolean isRunning = true; 
    private int direction; 

    /**
     * Constructor that initializes the direction to set the pistons
     * 
     * @param direction the direction to set the piston to: 0 for forward, 1 for
     *        reverse
     */
    public MoveSpringHP(int direction) {
        requires(Robot.springHPPrototype); 
        this.direction = direction; 
    }

    /**
     * Sets the piston in the correct direction and ends command
     */
    public void execute() {
        Robot.springHPPrototype.setPiston(direction); 
        terminate(); 
    }

    /**
     * When called, ends the command by setting the isRunning boolean to false
     */
    public void terminate() {
        isRunning = false; 
    }

    /**
     * Checks to see if command is finished or not. Ends command if true is
     * returned.
     * 
     * @return : returns false if the command is running, true otherwise.
     */
    public boolean isFinished() {
        return ! isRunning; 
    }
}