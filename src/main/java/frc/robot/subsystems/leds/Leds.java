package frc.robot.subsystems.leds;

import java.awt.Color;

import com.mach.LightDrive.LightDriveCAN;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;

public class Leds extends Subsystem {
    private TimedLedState state = StaticLedState.black;

    private LightDriveCAN lightDrive = new LightDriveCAN();

    @Override
    public void periodic() {
        Robot.superstructure.getBrownOutMonitor().hasEverBrownedOut();
        if (Robot.superstructure.getBrownOutMonitor().hasEverBrownedOut()
                && Robot.superstructure.getBrownOutMonitor().getSecondsSinceLastBrownOut() < 2) {
            state = StaticLedState.red;
        } else if (!DriverStation.getInstance().isDSAttached()) {
            state = BlinkingLedState.orange;
        } else if (Robot.cargo.isNotZeroed()) {
            state = StaticLedState.yellow;
        } else if (Robot.shield.isGrabbed()) {
            state = StaticLedState.green;
        } else if (Robot.cargo.isManualMode()) {
            state = StaticLedState.magenta;
        } else {
            state = StaticLedState.black;
        }

        Color color = state.getColor(Timer.getFPGATimestamp());

        lightDrive.SetColor(1, color, 0.5);

        lightDrive.Update();

    }

    @Override
    protected void initDefaultCommand() {

    }

}
