package frc.robot;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

public class ShuffleBoardConfig {
    public static final ShuffleboardTab subsystemsTab = Shuffleboard.getTab("subsystems");
    public static final ShuffleboardTab driverTab = Shuffleboard.getTab("Driver");
    public static final ShuffleboardTab diagnosticsTab = Shuffleboard.getTab("Diagnostics Tab");
    public static final ShuffleboardTab pidTuningTab = Shuffleboard.getTab("PID Tuning");
    public static final ShuffleboardTab matchDetailsTab = Shuffleboard.getTab("MatchDetails");

    public static final ShuffleboardLayout driveTrainLayout = subsystemsTab
            .getLayout("Drive Train", BuiltInLayouts.kList).withSize(3, 4).withPosition(0, 0);
    public static final ShuffleboardLayout shieldLayout = ShuffleBoardConfig.subsystemsTab
            .getLayout("Shield", BuiltInLayouts.kList).withSize(3, 2).withPosition(3, 0);
    public static final ShuffleboardLayout cargoLayout = ShuffleBoardConfig.subsystemsTab
            .getLayout("Cargo", BuiltInLayouts.kList).withSize(3, 2).withPosition(5, 0);


    private static final NetworkTableEntry allianceEntry = matchDetailsTab.add("Alliance", "").getEntry();
    private static final NetworkTableEntry eventNameEntry = matchDetailsTab.add("Event name", "").getEntry();
    private static final NetworkTableEntry locationEntry = matchDetailsTab.add("Location", 0).getEntry();
    private static final NetworkTableEntry matchNumberEntry = matchDetailsTab.add("Match Number", 0).getEntry();
    private static final NetworkTableEntry matchTypeEntry = matchDetailsTab.add("Match Type", "").getEntry();
    private static final NetworkTableEntry replayNumberEntry = matchDetailsTab.add("Replay Number", 0).getEntry();

    public static void updateMatchDetails() {
        allianceEntry.setString(DriverStation.getInstance().getAlliance().toString());
        eventNameEntry.setString(DriverStation.getInstance().getEventName());
        locationEntry.setNumber(DriverStation.getInstance().getLocation());
        matchNumberEntry.setNumber(DriverStation.getInstance().getMatchNumber());
        matchTypeEntry.setString(DriverStation.getInstance().getMatchType().toString());
        replayNumberEntry.setNumber(DriverStation.getInstance().getReplayNumber());
    }
}