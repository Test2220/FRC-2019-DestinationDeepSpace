package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * Command that runs the velcro hatch panel prototype
 * 
 * @author Dhruv Balasubramanian
 * @version 1/12/2019
 */
public class MoveVelcroHP extends Command {

    // Instance Variables
    private boolean isRunning = true;
    private int pistonToMove;
    private int direction;

    /**
     * Constructor that initializes the direction to set the pistons and which
     * piston to move
     * 
     * @param pistonToMove the piston that will be moved; 1 for piston1, 2 for
     *                     piston2
     * @param direction    the direction to set to the pistons; 0 for forward, 1 for
     *                     reverse
     */
    public MoveVelcroHP(int pistonToMove, int direction) {
        requires(Robot.velcroHPPrototype);
        this.pistonToMove = pistonToMove;
        this.direction = direction;
    }

    /**
     * Sets the piston in the correct direction and ends command
     */
    @Override
    public void execute() {
        if (pistonToMove == 1) {
            Robot.velcroHPPrototype.setPiston1(direction);
        } else if (pistonToMove == 2) {
            Robot.velcroHPPrototype.setPiston2(direction);
        }
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
    @Override
    public boolean isFinished() {
        return !isRunning();
    }
}