package frc.robot.utils;

import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.Timer;
 
public class BrownOutMonitor {
    private boolean hasEverBrownedOut = false;
    private double lastBrownOutTime = -1;

    public void update() {
        if (RobotController.isBrownedOut()) {
            hasEverBrownedOut = true;
            lastBrownOutTime = Timer.getFPGATimestamp();
        } 
    } 

    public boolean hasEverBrownedOut() {
        return hasEverBrownedOut;
    }
    public double getSecondsSinceLastBrownOut() {
        if(hasEverBrownedOut) {
        
        return Timer.getFPGATimestamp() - lastBrownOutTime;
        }
        return(-1);
    }
}
