package frc.robot.subsystems.leds;

import java.awt.Color;

public class StaticLedState implements TimedLedState {
    private Color color;

    public StaticLedState(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor(double timeInState) {
        return color;
    }

}
