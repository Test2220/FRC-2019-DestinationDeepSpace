package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Harles extends Subsystem {

    private DoubleSolenoid pusher;
    private DoubleSolenoid thruster;

    public Harles(int pusherForwardChannel, int pusherReverseChannel, int thrusterForwardChannel, int thrusterReverseChannel) {
        pusher = new DoubleSolenoid(pusherForwardChannel, pusherReverseChannel);
        thruster = new DoubleSolenoid(thrusterForwardChannel, thrusterReverseChannel);
    }

    public void setPusher(Value val) {
        pusher.set(val);
    }

    public void setThruster(Value val) {
        pusher.set(val);
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