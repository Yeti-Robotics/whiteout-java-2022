package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.HopperConstants;

public class HopperSubsystem extends SubsystemBase {

    private VictorSPX hopperVictorLeft, hopperVictorRight;

    public HopperSubsystem() {
        hopperVictorLeft = new VictorSPX(HopperConstants.HOPPER_LEFT_VICTOR);
        hopperVictorRight = new VictorSPX(HopperConstants.HOPPER_RIGHT_VICTOR);
    }

    public void funnelIn() {
        hopperVictorLeft.set(ControlMode.PercentOutput, HopperConstants.FUNNEL_IN_SPEED);
        hopperVictorRight.set(ControlMode.PercentOutput, -HopperConstants.FUNNEL_IN_SPEED);
    }

    public void funnelOut() {
        hopperVictorLeft.set(ControlMode.PercentOutput, HopperConstants.FUNNEL_OUT_SPEED);
        hopperVictorRight.set(ControlMode.PercentOutput, -HopperConstants.FUNNEL_OUT_SPEED);
    }

    public void funnelStop() {
        hopperVictorLeft.set(ControlMode.PercentOutput, 0);
        hopperVictorRight.set(ControlMode.PercentOutput, 0);
    }
}

