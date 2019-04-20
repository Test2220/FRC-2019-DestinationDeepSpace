package frc.robot.subsystems;

import java.util.Map;

import edu.wpi.cscore.HttpCamera;
import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode.PixelFormat;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import frc.robot.ShuffleBoardConfig;
import frc.robot.utils.BrownOutMonitor;

public class Superstructure extends Subsystem {

    /**
     *
     */

    private BrownOutMonitor brownOutMonitor = new BrownOutMonitor();

    PowerDistributionPanel pdp = new PowerDistributionPanel();
    
      
    private static final NetworkTableEntry hasEverBrownedOutEntry = ShuffleBoardConfig.driverTab
            .add("has browned out", false).withSize(2, 2).withPosition(14, 0).withWidget(BuiltInWidgets.kBooleanBox)
            .withProperties(Map.of("Color when true", "#FF0000","Color when false", "#00FF00")).getEntry();
    private static final NetworkTableEntry secondsSinceLastBrownOutEntry = ShuffleBoardConfig.driverTab
            .add("brown out time", -1).withSize(2, 2).withPosition(14, 2).getEntry();

    public Superstructure() {

        MjpegServer server = CameraServer.getInstance().addServer("USB server 0");
        UsbCamera camera = new UsbCamera("USB Camera 0", 0);
        CameraServer.getInstance().addCamera(camera);
        server.setSource(camera);
        server.getProperty("compression").set(10);
        server.getProperty("default_compression").set(10);
        server.getProperty("width").set(320);
        server.getProperty("height").set(240);
        camera.setResolution(320, 240);
        server.getProperty("fps").set(22);
        camera.setFPS(22);
        ShuffleBoardConfig.driverTab.add(camera).withSize(7, 7).withPosition(0, 0);
        HttpCamera httpCamera = new HttpCamera("LimeLightCamera", "http://10.22.20.68:5800");
        httpCamera.setFPS(30);
        httpCamera.setResolution(320, 240);
        httpCamera.setPixelFormat(PixelFormat.kMJPEG);
        CameraServer.getInstance().addCamera(httpCamera);
        ShuffleBoardConfig.driverTab.add(httpCamera).withSize(7, 7).withPosition(7, 0);

        ShuffleBoardConfig.diagnosticsTab.add("PDP", pdp);
        
    }

    @Override
    public void periodic() {
        brownOutMonitor.update();
        hasEverBrownedOutEntry.setBoolean(brownOutMonitor.hasEverBrownedOut());
        secondsSinceLastBrownOutEntry.setDouble(brownOutMonitor.getSecondsSinceLastBrownOut());

    }
    /**
     * @return the brownOutMonitor
     */
    public BrownOutMonitor getBrownOutMonitor() {
        return brownOutMonitor;
    }
    @Override
    protected void initDefaultCommand() {

    }

}