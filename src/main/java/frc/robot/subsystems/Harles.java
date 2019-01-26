package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Harles extends Subsystem {

    private DoubleSolenoid pusher;
    private DoubleSolenoid thruster;

    public Harles() {
        //pusher = new DoubleSolenoid(RobotMap.PUSHER_FORWARD, RobotMap.PUSHER_REVERSE);
        thruster = new DoubleSolenoid(RobotMap.THRUSTER_FORWARD, RobotMap.THRUSTER_REVERSE);
    }

    public void setPusher(Value val) {
        //pusher.set(val);
    }

    public void setThruster(Value val) {
        thruster.set(val);
    }

    public Value getPusher() {
        return pusher.get();
    }

    public Value getThruster() {
        return thruster.get();
    }

    @Override
    public void initDefaultCommand() {

    }
}