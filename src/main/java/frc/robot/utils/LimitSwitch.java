package frc.robot.utils;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SendableBase;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;

/**
 * Class that allows for adjustment with inversion when dealing with limit switches.
 * 
 * @author Dhruv
 */
public class LimitSwitch extends SendableBase {

    private DigitalInput limitSwitch;
    private boolean inverted;

    /**
     * Constructor the initializes a DigitalOutout object based on the channel
     * passed in as a parameter.
     * 
     * @param channel the channel of the LimitSwitch
     */
    public LimitSwitch(int channel) {
        limitSwitch = new DigitalInput(channel);
        inverted = false;
    }

    /**
     * Constructor the initializes a DigitalOutout object based on the channel
     * passed in as a parameter, and sets the inverted property based on the
     * parameter passed in.
     * 
     * @param channel  the channel of the LimitSwitch
     * @param inverted 
     */
    public LimitSwitch(int channel, boolean inverted) {
        this(channel);
        this.inverted = inverted;
    }

    /**
     * Setter for the inverted property.
     * 
     * @param inverted the inverted to set
     */
    public void setInverted(boolean inverted) {
        this.inverted = inverted;
    }

    /**
     * Gets the state of the limit switch.
     * 
     * @return true if the limit switch is activated, false otherwise.
     */
    public boolean get() {
        if (inverted)
            return !limitSwitch.get();
        else
            return limitSwitch.get();
    }

    /**
     * This method allows the Limit Switch to be sendable data to the shuffleboard.
     */
    @Override
    public void initSendable(SendableBuilder builder) {
        builder.setSmartDashboardType("Limit Switch");
        builder.addBooleanProperty("Value", this::get, null);
    }
}