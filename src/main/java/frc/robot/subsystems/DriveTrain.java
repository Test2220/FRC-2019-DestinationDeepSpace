package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.commands.DriveXbox;

public class DriveTrain extends Subsystem {

    // This initaliaze the talons
    private WPI_TalonSRX leftMaster = new WPI_TalonSRX(RobotMap.LEFT_MASTER);
    private WPI_TalonSRX leftSlave = new WPI_TalonSRX(RobotMap.LEFT_SLAVE);
    private WPI_TalonSRX rightMaster = new WPI_TalonSRX(RobotMap.RIGHT_MASTER);
    private WPI_TalonSRX rightSlave = new WPI_TalonSRX(RobotMap.RIGHT_SLAVE);

    private DifferentialDrive drive;

    /**
     * This constuctor sets the paramters to invert the slave motors
     */
    public DriveTrain() {

        leftMaster.setInverted(true);
        rightMaster.setInverted(false);
        leftSlave.setInverted(true);
        rightSlave.setInverted(false);

        leftSlave.follow(leftMaster); // This makes sure the slave motors follow the
        rightSlave.follow(rightMaster);

        // This invertes the right master motor

        drive = new DifferentialDrive(leftMaster, rightMaster);
    }
        
    /**
     * Controls the drive train
     * 
     * @param move The forward power for the drivetrain 
     * @param turn The turn power for the drivetrain
     */
    public void curvatureDrive(double move, double turn) {
        drive.curvatureDrive(move, turn, true);
    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new DriveXbox());
    }

}