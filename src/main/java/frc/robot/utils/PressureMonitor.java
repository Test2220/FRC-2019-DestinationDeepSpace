package frc.robot.utils;

import edu.wpi.first.wpilibj.AnalogInput;
import frc.robot.RobotMap;

public class PressureMonitor {

    public static AnalogInput pressureSensor = new AnalogInput(RobotMap.ANALOG_INPUT);

    public static int getPressure() {
        double voltage = pressureSensor.getVoltage();
        double pressure = (voltage - 1.2604) / 0.0239325;
        return (int)pressure;
    }
    public static boolean isLowPressure() {
        return getPressure() < 40;
    }
}
