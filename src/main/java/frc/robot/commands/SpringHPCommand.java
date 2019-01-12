package frc.robot.commands; 

import edu.wpi.first.wpilibj.command.Command; 
import frc.robot.RobotMap; 
import frc.robot.subsystems.SpringHPPrototype; ; 

/**
 * Command that runs the spring hatch panel prototype
 * 
 * @author Dhruv Balasubramanian
 * @version 1/12/2019 */
public class SpringHPCommand extends Command {

    // Instance Variables
    boolean isRunning = true; 
    int direction; 
    SpringHPPrototype springHPObject = new SpringHPPrototype(RobotMap.SPRING_HP_PROTOTYPE_FORWARD, 
            RobotMap.SPRING_HP_PROTOTYPE_REVERSE); 

    /**
     * Constructor that initializes the direction to set the pistons
     * 
     * @param direction: the direction to set to the pistons
     */
    public SpringHPCommand(int direction) {
        requires(springHPObject); 
        this.direction = direction; 
    }

    /**
     * Sets the piston in the correct direction while command is active
     */
    public void execute() {
        springHPObject.setPiston(direction); 
    }

    /**
     * When called, ends the command by setting the isRunning boolean false
     */
    public void terminate() {
        isRunning = false; 
    }

    /**
     * Checks to see if command is finished or not.
     * 
     * @return !isrunning: returns false if the command is running, true otherwise.
     */
    public boolean isFinished() {
        return ! isRunning; 
    }
}