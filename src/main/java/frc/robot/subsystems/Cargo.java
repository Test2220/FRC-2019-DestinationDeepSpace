package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.RobotMap;
import frc.robot.commands.ManipulateCargo;

public class Cargo extends Subsystem {

    private WPI_TalonSRX leftArmMaster;
    private WPI_TalonSRX rightArmSlave;
    private WPI_TalonSRX intake;

    public Cargo() {
        leftArmMaster = new WPI_TalonSRX(RobotMap.LEFT_ARM_MASTER);
        rightArmSlave = new WPI_TalonSRX(RobotMap.RIGHT_ARM_SLAVE);
        intake = new WPI_TalonSRX(RobotMap.INTAKE);

        rightArmSlave.setInverted(true);

        rightArmSlave.follow(leftArmMaster);
    }

    public void moveArm(double value) {
        leftArmMaster.set(ControlMode.PercentOutput, value);
    }

    public void spinIntake(double value) {
        intake.set(ControlMode.PercentOutput, value);
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new ManipulateCargo());
    }
}