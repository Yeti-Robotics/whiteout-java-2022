package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimberConstants;

public class ClimberSubsystem extends SubsystemBase {

    private VictorSPX climberVictor;
    private TalonSRX climberTalon;
    private DigitalInput topLimitSwitch;
    private DigitalInput bottomLimitSwitch;

    public ClimberSubsystem() {
        climberVictor = new VictorSPX(ClimberConstants.CLIMBER_LEFT_VICTOR);
        climberTalon = new TalonSRX(ClimberConstants.CLIMBER_RIGHT_TALON);

        climberVictor.setInverted(true);

        // topLimitSwitch = new DigitalInput(Constants.TOP_LIMIT_SWITCH);
        // bottomLimitSwitch = new DigitalInput(Constants.BOTTOM_LIMIT_SWITCH);

        climberTalon.configContinuousCurrentLimit(ClimberConstants.CLIMBER_CONT_CURRENT_LIMIT);
        climberTalon.configPeakCurrentLimit(ClimberConstants.CLIMBER_PEAK_CURRENT_LIMIT);
        climberTalon.configPeakCurrentDuration(ClimberConstants.CLIMBER_PEAK_CURRENT_DURATION);
        climberTalon.enableCurrentLimit(true);
        climberTalon.follow(climberVictor);
    }

    public void climbUp(){
        climberVictor.set(ControlMode.PercentOutput, ClimberConstants.CLIMBER_SPEED);
        climberTalon.set(ControlMode.PercentOutput, ClimberConstants.CLIMBER_SPEED);
    }

    public void toggleClimbUp(double power){
        climberVictor.set(ControlMode.PercentOutput, power);
        climberTalon.set(ControlMode.PercentOutput, power);
    }

    public void climbDown(){
        climberVictor.set(ControlMode.PercentOutput, -ClimberConstants.CLIMBER_SPEED);
        climberTalon.set(ControlMode.PercentOutput, -ClimberConstants.CLIMBER_SPEED);
    }

    public void stopClimb(){
        climberVictor.set(ControlMode.PercentOutput, 0);
        climberTalon.set(ControlMode.PercentOutput, 0);
    }

    public boolean getTopLimitSwitch(){
        return topLimitSwitch.get();
    }

    public boolean getBottomLimitSwitch(){
        return bottomLimitSwitch.get();
    }

}

