package frc.robot;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

public class ShuffleBoardConfig {
        public static final ShuffleboardTab driverTab = Shuffleboard.getTab("Driver");
        public static final ShuffleboardTab diagnosticsTab = Shuffleboard.getTab("Diagnostics Tab");
        public static final ShuffleboardTab pidTuningTab = Shuffleboard.getTab("PID Tuning");
        public static final ShuffleboardTab matchDetailsTab = Shuffleboard.getTab("MatchDetails");
        public static final ShuffleboardTab driveTrain = Shuffleboard.getTab("Drive Train");
        public static final ShuffleboardTab shield = Shuffleboard.getTab("Shield Tab");
        public static final ShuffleboardTab cargo = Shuffleboard.getTab("Cargo Tab");

        public static final NetworkTableEntry armUpPos = cargo.add("Raised Encoder Position", 0).withSize(2, 1).withPosition(2, 1).getEntry();

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