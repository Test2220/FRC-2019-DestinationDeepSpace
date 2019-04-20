package frc.robot.subsystems.leds;

import java.awt.Color;

public class BlinkingLedState implements TimedLedState {
    private Color color1;
    private Color color2;
    private double duration;

    public BlinkingLedState(Color color1, Color color2, double duration) {
        this.color1 = color1;
        this.color2 = color2;
        this.duration = duration;
    }

    @Override
    public Color getColor(double time) {
        if ((int) (time / duration) % 2 == 0) {
            return color1;
        }
        return color2;
    }

    public static final BlinkingLedState orange = new BlinkingLedState(Color.orange, Color.black, 0.5);
}