package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimberConstants;

public class ClimberSubsystem extends SubsystemBase {

    private VictorSPX climberLeftVictor;
    private TalonSRX climberRightTalon;
    private DigitalInput topLimitSwitch;
    private DigitalInput bottomLimitSwitch;

    public ClimberSubsystem() {
        climberLeftVictor = new VictorSPX(ClimberConstants.CLIMBER_LEFT_VICTOR);
        climberRightTalon = new TalonSRX(ClimberConstants.CLIMBER_RIGHT_TALON);

        climberLeftVictor.setInverted(true);

        // topLimitSwitch = new DigitalInput(Constants.TOP_LIMIT_SWITCH);
        // bottomLimitSwitch = new DigitalInput(Constants.BOTTOM_LIMIT_SWITCH);

        climberRightTalon.configContinuousCurrentLimit(ClimberConstants.CLIMBER_CONT_CURRENT_LIMIT);
        climberRightTalon.configPeakCurrentLimit(ClimberConstants.CLIMBER_PEAK_CURRENT_LIMIT);
        climberRightTalon.configPeakCurrentDuration(ClimberConstants.CLIMBER_PEAK_CURRENT_DURATION);
        climberRightTalon.enableCurrentLimit(true);
        climberRightTalon.follow(climberLeftVictor);
    }

    public void climbUp(){
        climberLeftVictor.set(ControlMode.PercentOutput, 1.0);
        climberRightTalon.set(ControlMode.PercentOutput, 1.0);
    }

    public void toggleClimbUp(double power){
        climberLeftVictor.set(ControlMode.PercentOutput, power);
        climberRightTalon.set(ControlMode.PercentOutput, power);
    }

    public void climbDown(){
        climberLeftVictor.set(ControlMode.PercentOutput, -1.0);
        climberRightTalon.set(ControlMode.PercentOutput, -1.0);
    }

    public void stopClimb(){
        climberLeftVictor.set(ControlMode.PercentOutput, 0);
        climberRightTalon.set(ControlMode.PercentOutput, 0);
    }

    public boolean getTopLimitSwitch(){
        return topLimitSwitch.get();
    }

    public boolean getBottomLimitSwitch(){
        return bottomLimitSwitch.get();
    }

}

