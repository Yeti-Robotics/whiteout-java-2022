package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class HopperSubsystem extends SubsystemBase {

    private VictorSPX hopperVictorLeft, hopperVictorRight;

    public HopperSubsystem() {
        hopperVictorLeft = new VictorSPX(Constants.HOPPER_LEFT_VICTOR);
        hopperVictorRight = new VictorSPX(Constants.HOPPER_RIGHT_VICTOR);
    }

    public void funnelIn() {
        hopperVictorLeft.set(ControlMode.PercentOutput, Constants.FUNNEL_IN_SPEED);
        hopperVictorRight.set(ControlMode.PercentOutput, -Constants.FUNNEL_IN_SPEED);
    }

    public void funnelOut() {
        hopperVictorLeft.set(ControlMode.PercentOutput, Constants.FUNNEL_OUT_SPEED);
        hopperVictorRight.set(ControlMode.PercentOutput, -Constants.FUNNEL_OUT_SPEED);
    }

    public void funnelStop() {
        hopperVictorLeft.set(ControlMode.PercentOutput, 0);
        hopperVictorRight.set(ControlMode.PercentOutput, 0);
    }
}

