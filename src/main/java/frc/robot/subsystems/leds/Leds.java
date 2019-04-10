package frc.robot.subsystems.leds;

import java.awt.Color;

import com.mach.LightDrive.LightDriveCAN;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Leds extends Subsystem {
    private TimedLedState state = new BlinkingLedState(Color.blue, Color.pink, 0.5);

    private LightDriveCAN lightDrive = new LightDriveCAN();

    @Override
    public void periodic() {
        double timeInState = Timer.getFPGATimestamp() - stateStartTime;
        Color color = state.getColor(timeInState);

        lightDrive.SetColor(1, color, 0.5);

        lightDrive.Update();

    }

    public void setState(TimedLedState state) {
        stateStartTime = Timer.getFPGATimestamp();
        this.state = state;
    }

    private double stateStartTime;

    @Override
    protected void initDefaultCommand() {

    }

}