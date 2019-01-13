package frc.robot.subsystems; 

import edu.wpi.first.wpilibj.DoubleSolenoid; 
import edu.wpi.first.wpilibj.command.Subsystem; 

/**
 * Subsystem for testing Spring Hatch Panel Protoype
 * 
 * @author Dhruv Balasubramanian
 * @version 1/12/2019 */
public class SpringHPPrototype extends Subsystem {

    // Instance variables
    private DoubleSolenoid piston; 

    /**
     * Constructor that initializes piston object
     * 
     * @param forwardChannel: the channel that sets the piston forward
     * @param reverseChannel: the channel that sets the piston reverse
     */
    public SpringHPPrototype(int forwardChannel, int reverseChannel) {
        piston = new DoubleSolenoid(forwardChannel, reverseChannel); 
    }

    /**
     * Method to set the direction of the pistons.
     * 
     * @param direction: direction to set the piston to: 0 for forward, 1 for
     *        reverse
     */
    public void setPiston(int direction) {
        if (direction == 0)
            piston.set(DoubleSolenoid.Value.kForward); 
        else if (direction == 1)
            piston.set(DoubleSolenoid.Value.kReverse); 
    }

    @Override
    public void initDefaultCommand() {

    }
}