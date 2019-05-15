package frc.robot.subsystems.leds;

import java.awt.Color;

public class StaticLedState implements TimedLedState {
    private Color color;

    public StaticLedState(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor(double time) {
        return color;
    }

    public static final StaticLedState red = new StaticLedState(Color.red);
    public static final StaticLedState yellow = new StaticLedState(Color.yellow);
    public static final StaticLedState green = new StaticLedState(Color.green);
    public static final StaticLedState black = new StaticLedState(Color.black);
    public static final StaticLedState magenta = new StaticLedState(Color.magenta); 
    public static final StaticLedState cyan = new StaticLedState(Color.cyan);
}
