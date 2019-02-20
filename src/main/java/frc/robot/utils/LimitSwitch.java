package frc.robot.utils;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SendableBase;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;

public class LimitSwitch extends SendableBase {

    private DigitalInput limitSwitch;

    public LimitSwitch(int channel) {
        limitSwitch = new DigitalInput(channel);
    }

    public boolean get() {
        return !limitSwitch.get();
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        builder.setSmartDashboardType("Limit Switch");
        builder.addBooleanProperty("Value", this::get, null);
    }
}