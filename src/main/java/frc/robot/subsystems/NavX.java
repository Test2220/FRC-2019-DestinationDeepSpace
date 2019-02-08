package frc.robot.subsystems; 

import com.kauailabs.navx.frc.AHRS; 

import edu.wpi.first.wpilibj.SPI; 
import edu.wpi.first.wpilibj.command.Subsystem; 

public class NavX extends Subsystem {

    private AHRS navX; 

    public NavX() {
        navX = new AHRS(SPI.Port.kMXP); 
    }

    public double getAngle() {
        return navX.getAngle(); 
    }

    public void zeroAngle() {
        navX.zeroYaw();
    }

    @Override
    protected void initDefaultCommand() {
    }
}