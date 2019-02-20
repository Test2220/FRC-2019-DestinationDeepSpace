package frc.robot.utils;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SendableBase;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;

public class LimitSwitch extends SendableBase {

    private DigitalInput limitSwitch;
    private boolean inverted;

    public LimitSwitch(int channel) {
        limitSwitch = new DigitalInput(channel);
        inverted = false;
    }

    public LimitSwitch(int channel, boolean inverted) {
        this(channel);
        this.inverted = inverted;
    }

    /**
     * @param inverted the inverted to set
     */
    public void setInverted(boolean inverted) {
        this.inverted = inverted;
    }

    public boolean get() {
        if (inverted) return !limitSwitch.get();
        else return limitSwitch.get();
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        builder.setSmartDashboardType("Limit Switch");
        builder.addBooleanProperty("Value", this::get, null);
    }
}