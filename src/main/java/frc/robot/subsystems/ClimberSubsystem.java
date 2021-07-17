package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClimberSubsystem extends SubsystemBase {

    private TalonSRX climberTalon1;
    private TalonSRX climberTalon2;
    private DigitalInput topLimitSwitch;
    private DigitalInput bottomLimitSwitch;

    public ClimberSubsystem() {
        climberTalon1 = new TalonSRX(Constants.LEFT_CLIMBER_TALON);
        climberTalon2 = new TalonSRX(Constants.RIGHT_CLIMBER_TALON);

        climberTalon1.setInverted(true);

        // topLimitSwitch = new DigitalInput(Constants.TOP_LIMIT_SWITCH);
        // bottomLimitSwitch = new DigitalInput(Constants.BOTTOM_LIMIT_SWITCH);

        climberTalon2.configContinuousCurrentLimit(Constants.CLIMBER_CONT_CURRENT_LIMIT);
        climberTalon2.configPeakCurrentLimit(Constants.CLIMBER_PEAK_CURRENT_LIMIT);
        climberTalon2.configPeakCurrentDuration(Constants.CLIMBER_PEAK_CURRENT_DURATION);
        climberTalon2.enableCurrentLimit(true);
        climberTalon2.follow(climberTalon1);
    }

    public void climbUp(){
        climberTalon1.set(ControlMode.PercentOutput, Constants.CLIMBER_SPEED);
        climberTalon2.set(ControlMode.PercentOutput, Constants.CLIMBER_SPEED);
    }

    public void toggleClimbUp(double power){
        climberTalon1.set(ControlMode.PercentOutput, power);
        climberTalon2.set(ControlMode.PercentOutput, power);
    }

    public void climbDown(){
        climberTalon1.set(ControlMode.PercentOutput, -Constants.CLIMBER_SPEED);
        climberTalon2.set(ControlMode.PercentOutput, -Constants.CLIMBER_SPEED);
    }

    public void stopClimb(){
        climberTalon1.set(ControlMode.PercentOutput, 0);
        climberTalon2.set(ControlMode.PercentOutput, 0);
    }

    public boolean getTopLimitSwitch(){
        return topLimitSwitch.get();
    }

    public boolean getBottomLimitSwitch(){
        return bottomLimitSwitch.get();
    }

}

