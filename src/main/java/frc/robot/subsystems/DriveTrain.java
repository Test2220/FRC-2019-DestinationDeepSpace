package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;

public class DriveTrain extends Subsystem {

    public WPI_TalonSRX leftMaster = new WPI_TalonSRX(RobotMap.leftMaster);
    public WPI_TalonSRX leftslave = new WPI_TalonSRX(RobotMap.leftSlave);
    public WPI_TalonSRX rightMaster = new WPI_TalonSRX(RobotMap.rightMaster);
    public WPI_TalonSRX rightslave = new WPI_TalonSRX(RobotMap.rightSlave);

    public DifferentialDrive drive = new DifferentialDrive(leftMaster, rightMaster);

    public DriveTrain() {

        leftslave.follow(leftMaster);
        rightslave.follow(rightMaster);
    }

    public void manualDrive(double move, double turn) {

        drive.curvatureDrive(move, turn, true);
    }

    @Override
    public void initDefaultCommand() {

    }
}