package frc.robot.subsystems;

import java.awt.Color;

import com.mach.LightDrive.LightDriveCAN;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Leds extends Subsystem {

    private LightDriveCAN lightDrive = new LightDriveCAN();

    
    public void test() {
        // if ((int) Timer.getFPGATimestamp() % 2 == 0) {
        //     lightDrive.SetColor(1, Color.green);
            
        // } else {
            lightDrive.SetColor(1, Color.pink,0.5);
        //}
        lightDrive.Update();
        //System.out.println(Timer.getFPGATimestamp());
    }

    @Override
    protected void initDefaultCommand() {

    }

}