package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimberConstants;

public class ClimberSubsystem extends SubsystemBase {

    private WPI_TalonFX climberLeftFalcon;
    private WPI_TalonFX climberRightFalcon; 

    public ClimberSubsystem() {
        climberLeftFalcon = new WPI_TalonFX(ClimberConstants.CLIMBER_LEFT_FALCON);
        climberRightFalcon = new WPI_TalonFX(ClimberConstants.CLIMBER_RIGHT_FALCON);

        // one of these will likely need to be inverted
        climberLeftFalcon.setInverted(false);
        climberRightFalcon.setInverted(false);

        climberRightFalcon.follow(climberLeftFalcon);
    }

    public void climbUp(){
        climberLeftFalcon.set(ControlMode.PercentOutput, ClimberConstants.CLIMBER_SPEED);
    }

    public void climbDown(){
        climberLeftFalcon.set(ControlMode.PercentOutput, -ClimberConstants.CLIMBER_SPEED);
    }

    public void stopClimb(){
        climberLeftFalcon.set(ControlMode.PercentOutput, 0.0);
    }

    public double getLeftEncoder(){
        return climberLeftFalcon.getSelectedSensorVelocity(); 
    }

    public double getRightEncoder(){
        return climberRightFalcon.getSelectedSensorVelocity();
    }

    public double getAverageEncoder(){
        return (getLeftEncoder() + getRightEncoder()) / 2.0;
    }

    public void resetEncoders(){
        climberLeftFalcon.setSelectedSensorPosition(0);
        climberRightFalcon.setSelectedSensorPosition(0);
    }
}
