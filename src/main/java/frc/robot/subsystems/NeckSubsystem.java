package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.VictorSPXConfiguration;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.NeckConstants;

public class NeckSubsystem extends SubsystemBase {
    private VictorSPX neckBeltVictor;
    private VictorSPX neckRollerVictor;
    private DigitalInput neckBeamBreak;

    public NeckSubsystem() {
      neckBeltVictor = new VictorSPX(NeckConstants.NECK_BELT_VICTOR);
      neckRollerVictor = new VictorSPX(NeckConstants.NECK_ROLLER_VICTOR);
      neckBeamBreak = new DigitalInput(NeckConstants.NECK_BEAM_BREAK);
    }

    public void moveUp(){
        neckBeltVictor.set(ControlMode.PercentOutput, NeckConstants.NECK_UP_SPEED);
        neckRollerVictor.set(ControlMode.PercentOutput, NeckConstants.NECK_UP_SPEED);
    }

    public void moveDown(){
        neckBeltVictor.set(ControlMode.PercentOutput, NeckConstants.NECK_DOWN_SPEED);
        neckRollerVictor.set(ControlMode.PercentOutput, NeckConstants.NECK_DOWN_SPEED);
    }

    public void stopNeck(){
        neckBeltVictor.set(ControlMode.PercentOutput, 0);
        neckRollerVictor.set(ControlMode.PercentOutput, 0);
    }


    public boolean getLowerBeamBreak() {
        return neckBeamBreak.get();
    }

}

