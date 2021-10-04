package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
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

        climberLeftFalcon.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
        climberRightFalcon.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);

        // set neutral to brake to make sure falcons don't move (holding the climb)
        climberLeftFalcon.setNeutralMode(NeutralMode.Brake);
        climberRightFalcon.setNeutralMode(NeutralMode.Brake);
    }

    @Override
    public void periodic() {
        System.out.println("climber encoder: " + getAverageEncoder());
        System.out.println("encoder pos (test): " + ((climberLeftFalcon.getSelectedSensorPosition() + climberRightFalcon.getSelectedSensorPosition()) / 2.0));
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
