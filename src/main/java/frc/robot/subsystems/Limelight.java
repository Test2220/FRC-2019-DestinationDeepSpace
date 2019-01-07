package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Limelight extends Subsystem {


    /* INSTANCE VARIABLES */

    // Network table for limelight, has camera data
    private final NetworkTable limelight = NetworkTableInstance.getDefault().getTable("limelight");


    /* SUBSYSTEM CONSTRUCTOR */

    // Limelight subsystem constructor, no args necessary
    public Limelight() {
        // Set camera mode to use vision processing
        setCameraMode(CameraMode.VISION_PROCESSING);

        // Set LED mode to use pipeline
        setLEDMode(LEDMode.USE_PIPELINE);

        // Set streaming mode to standard
        setStreamMode(StreamMode.STANDARD);

        // Disable limelight snapshotting. 0 = off, 1 = on
        limelight.getEntry("snapshot").setNumber(0);
    }


    /* LIMELIGHT DATA ACCESSORS */
    
    // True or false for if can see target
    public boolean seeTarget() {
        NetworkTableEntry tv = limelight.getEntry("tv");
        double tvVal = tv.getDouble(0);
        boolean seeTarget = tvVal == 1;
        return seeTarget;
    }

    // Horizontal offset from target, range={-27, 27} degrees TODO Do you prefer Tx, or something like HOffset
    public double getTx() {
        NetworkTableEntry tx = limelight.getEntry("tx");
        double txVal = tx.getDouble(0);
        return txVal;
    }

    // Vertical offset from target, range={-20.5, 20.5} degrees TODO Same thing here, Ty or like VOffset
    public double getTy() {
        NetworkTableEntry ty = limelight.getEntry("ty");
        double tyVal = ty.getDouble(0);
        return tyVal;
    }

    // Size of target in % of screen size
    public double getTargetSize() {
        NetworkTableEntry ta = limelight.getEntry("ta");
        double percent = ta.getDouble(0);
        return percent;
    }

    // Target skew in degrees, range={-90, 0} degrees TODO I'm not immediately sure what this does, so I'll read docs
    public double getTargetSkew() {
        NetworkTableEntry ts = limelight.getEntry("ts");
        double skew = ts.getDouble(0);
        return skew;
    }


    /* LIMELIGHT MEMBER MUTATORS */

    // Set mode of camera
    public void setCameraMode(CameraMode mode) {
        int modeVal = mode.getVal();
        limelight.getEntry("camMode").setNumber(modeVal);
    }

    // Set mode of LED
    public void setLEDMode(LEDMode mode) {
        int modeVal = mode.getVal();
        limelight.getEntry("ledMode").setNumber(modeVal);
    }

    // Set streaming mode
    public void setStreamMode(StreamMode mode) {
        int modeVal = mode.getVal();
        limelight.getEntry("stream").setNumber(modeVal);
    }

    // Set limelight pipeline
    public void setPipeline(int pipeline) {
        limelight.getEntry("pipeline").setNumber(pipeline);
    }


    /* LIMELIGHT OP MODE ENUMERATION */

    // Camera modes enumeration
    enum CameraMode {
        VISION_PROCESSING(0), DRIVER_CAMERA(1);

        private final int val;

        CameraMode(int val) {
            this.val = val;
        }

        public int getVal() {
            return val;
        }
    }

    // Led modes enumeration
    enum LEDMode {
        USE_PIPELINE(0), OFF(1), BLINK(2), ON(3);

        private final int val;

        LEDMode(int val) {
            this.val = val;
        }

        public int getVal() {
            return val;
        }
    }

    // Stream modes enumeration
    enum StreamMode {
        STANDARD(0), PIP_MAIN(1), PIP_SECONDARY(2);

        private final int val;

        StreamMode(int val) {
            this.val = val;
        }

        public int getVal() {
            return val;
        }
    }


    /* EXTRANEOUS METHODS */

    // Default subsystem command
    @Override
    public void initDefaultCommand() {
        // setDefaultCommand(TODO Limelight Command);
    }
}