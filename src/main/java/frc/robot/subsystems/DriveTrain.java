package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.commands.DriveXbox;

public class DriveTrain extends Subsystem {

    public WPI_TalonSRX leftMaster = new WPI_TalonSRX(RobotMap.LEFT_MASTER);
    public WPI_TalonSRX leftSlave = new WPI_TalonSRX(RobotMap.LEFT_SLAVE);
    public WPI_TalonSRX rightMaster = new WPI_TalonSRX(RobotMap.RIGHT_MASTER);
    public WPI_TalonSRX rightSlave = new WPI_TalonSRX(RobotMap.RIGHT_SLAVE);

    public DifferentialDrive drive;

    public DriveTrain() {

        leftSlave.follow(leftMaster);
        rightSlave.follow(rightMaster);

        drive = new DifferentialDrive(leftMaster, rightMaster);
    }

    public void curvatureDrive(double move, double turn) {
        drive.curvatureDrive(move, turn, true);
    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new DriveXbox());
    }
    
}