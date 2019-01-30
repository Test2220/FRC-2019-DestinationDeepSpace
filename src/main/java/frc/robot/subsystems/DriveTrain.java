package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.commands.DriveXbox;

/**
 * The drivetrain subsystem sets up all the hardware relating to the physical
 * drivetrain and implements the DifferentialDrive class to control driving
 * using curvature drive.
 * 
 * @author Muaad, Reece
 */
public class DriveTrain extends Subsystem {

    /* INSTANCE VARIABLES */

    // Instantiate and intialize drivetrain Talon members
    public WPI_TalonSRX leftMaster = new WPI_TalonSRX(RobotMap.LEFT_MASTER);
    public WPI_TalonSRX leftSlave = new WPI_TalonSRX(RobotMap.LEFT_SLAVE);
    public WPI_TalonSRX rightMaster = new WPI_TalonSRX(RobotMap.RIGHT_MASTER);
    public WPI_TalonSRX rightSlave = new WPI_TalonSRX(RobotMap.RIGHT_SLAVE);

    // Drivetrain controller member
    public DifferentialDrive drive;

    /* SUBSYSTEM CONSTRUCTOR */

    /**
     * Drivetrain subsystem constructor configures motor and sets up drivetrain
     * controller (differential drive)
     */
    public DriveTrain() {
        // Slave motors follow master motors
        leftSlave.follow(leftMaster);
        rightSlave.follow(rightMaster);

        // Set neutral mode of motors
        leftMaster.setNeutralMode(NeutralMode.Brake);
        rightMaster.setNeutralMode(NeutralMode.Brake);

        // Initialize drivetrain controller member (our robot uses differential drive)
        drive = new DifferentialDrive(leftMaster, rightMaster);
    }

    /* CONTROL DRIVETRAIN METHODS */

    /**
     * Control drivetrain with curvature drive
     * 
     * @param move Forward and reverse move power
     * @param turn Turn power
     */
    public void curvatureDrive(double move, double turn) {
        drive.curvatureDrive(move, turn, true);
    }

    /**
     * Set power to all motors
     * 
     * @param power Power to set all motors to
     */
    public void setAllPower(double power) {
        leftMaster.set(power);
        rightMaster.set(power);
    }

    /**
     * Stop motors
     */
    public void stop() {
        setAllPower(0);
    }

    /* IMPLEMENTED METHODS */

    /**
     * Sets drivetrain default command to drive with xbox
     */
    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new DriveXbox());
    }
}