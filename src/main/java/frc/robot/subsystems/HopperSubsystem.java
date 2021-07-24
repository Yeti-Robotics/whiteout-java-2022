package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.HopperConstants;

public class HopperSubsystem extends SubsystemBase {

    private VictorSPX hopperVictor;

    public HopperSubsystem() {
        hopperVictor = new VictorSPX(HopperConstants.HOPPER_VICTOR);
        hopperVictor.setInverted(true);
    }

    public void funnelIn() {
        hopperVictor.set(ControlMode.PercentOutput, HopperConstants.FUNNEL_IN_SPEED);
    }

    public void funnelOut() {
        hopperVictor.set(ControlMode.PercentOutput, HopperConstants.FUNNEL_OUT_SPEED);
    }

    public void funnelStop() {
        hopperVictor.set(ControlMode.PercentOutput, 0);
    }
}

