package frc.robot.subsystems; 

import edu.wpi.first.wpilibj.DoubleSolenoid; 
import edu.wpi.first.wpilibj.command.Subsystem; 

/**
 * Subsystem for testing Velcro Hatch Panel Protoype
 * 
 * @author Dhruv Balasubramanian
 * @version 1/12/2019 */
class VelcroHPPrototype extends Subsystem {

    // Instance variables
    private DoubleSolenoid piston1; 
    private DoubleSolenoid piston2; 

    /**
     * Constructor that initializes piston objects
     * 
     * @param forwardChannel1: the channel that sets piston1 forward
     * @param reverseChannel1: the channel that sets piston1 reverse
     * @param forwardChannel2: the channel that sets piston2 forward
     * @param reverseChannel2: the channel that sets piston2 reverse
     */
    public VelcroHPPrototype(int forwardChannel1, int reverseChannel1, int forwardChannel2, int reverseChannel2) {
        piston1 = new DoubleSolenoid(forwardChannel1, reverseChannel1); 
        piston2 = new DoubleSolenoid(forwardChannel2, reverseChannel2); 
    }

    /**
     * Method to set the direction of piston 1.
     * 
     * @param direction: direction to set the piston to: 0 for forward, 1 for
     *        reverse
     */
    public void setPiston1(int direction) {
        if (direction == 0)
            piston1.set(DoubleSolenoid.Value.kForward); 
        else if (direction == 1)
            piston1.set(DoubleSolenoid.Value.kReverse); 
    }

    /**
     * Method to set the direction of piston 2.
     * 
     * @param direction: direction to set the piston to: 0 for forward, 1 for
     *        reverse
     */
    public void setPiston2(int direction) {
        if (direction == 0)
            piston2.set(DoubleSolenoid.Value.kForward); 
        else if (direction == 1)
            piston2.set(DoubleSolenoid.Value.kReverse); 
    }

    @Override
    public void initDefaultCommand() {

    }
}