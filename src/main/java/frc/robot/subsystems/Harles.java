package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Subsystem for manipulation of the hatch panel.
 * 
 * @author Dhruv Balasubramanian
 * @version 1/27/2018
 */
public class Harles extends Subsystem {

    /* INSTANCE VARIABLES */

    private DoubleSolenoid pusher;
    private DoubleSolenoid thruster;

    /* CONSTRUCTOR */

    /**
     * Constructor that initializes both pistons. No parameters necessary or taken.
     */
    public Harles() {
        pusher = new DoubleSolenoid(RobotMap.PUSHER_FORWARD, RobotMap.PUSHER_REVERSE);
        thruster = new DoubleSolenoid(RobotMap.THRUSTER_FORWARD, RobotMap.THRUSTER_REVERSE);
    }

    /* CONTROL METHODS */

    /**
     * Sets the direction of the pusher piston based on the value given.
     * 
     * @param direction the direction to set the pusher piston to.
     */
    public void setPusher(Value direction) {
        pusher.set(direction);
    }

    /**
     * Sets the direction of the thruster piston based on the value given.
     * 
     * @param direction the direction to set the thruster piston to.
     */
    public void setThruster(Value direction) {
        thruster.set(direction);
    }

    // Subsystem piston enumeration
    public enum Piston {
        THRUSTER, PUSHER;
    }

    /* IMPLEMENTED METHODS */

    @Override
    public void initDefaultCommand() {
    }
}