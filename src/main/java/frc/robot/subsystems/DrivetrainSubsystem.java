package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

public class DrivetrainSubsystem extends SubsystemBase {
    private TalonFX leftFalcon1, leftFalcon2, rightFalcon1, rightFalcon2;    

    public DrivetrainSubsystem() {
        
        leftFalcon1 = new TalonFX(DriveConstants.LEFT_FALCON_1);
        leftFalcon2 = new TalonFX(DriveConstants.LEFT_FALCON_2);
        rightFalcon1 = new TalonFX(DriveConstants.RIGHT_FALCON_1);
        rightFalcon2 = new TalonFX(DriveConstants.RIGHT_FALCON_2);
        
        leftFalcon1.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 0);
        rightFalcon1.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 0);

        rightFalcon1.setInverted(true);
        rightFalcon2.setInverted(true);
        leftFalcon1.setInverted(false);
        leftFalcon2.setInverted(false);

        leftFalcon1.setNeutralMode(NeutralMode.Brake);
        rightFalcon1.setNeutralMode(NeutralMode.Brake);
        resetEncoders();
                
    }

    public void drive(double leftPower, double rightPower) {
        leftFalcon1.set(ControlMode.PercentOutput, leftPower);
        leftFalcon2.set(ControlMode.PercentOutput, leftPower);
        rightFalcon1.set(ControlMode.PercentOutput, rightPower);
        rightFalcon2.set(ControlMode.PercentOutput, rightPower);
    }

    public void resetEncoders() {
        leftFalcon1.setSelectedSensorPosition(0);
        rightFalcon1.setSelectedSensorPosition(0);
    }

    public void stopDrive() {
        leftFalcon1.set(ControlMode.PercentOutput, 0);
        leftFalcon2.set(ControlMode.PercentOutput, 0);
        rightFalcon1.set(ControlMode.PercentOutput, 0);
        rightFalcon2.set(ControlMode.PercentOutput, 0);
    }

    public double getLeftEncoder() {
        return (leftFalcon1.getSelectedSensorPosition() * DriveConstants.DISTANCE_PER_PULSE);
    }

    public double getRightEncoder() {
        return (-rightFalcon1.getSelectedSensorPosition() * DriveConstants.DISTANCE_PER_PULSE);
    }

    public double getAverageEncoder() {
        return (getLeftEncoder() + getRightEncoder()) / 2;
    }

    public void resetEncoder() {
        rightFalcon1.setSelectedSensorPosition(0);
        leftFalcon1.setSelectedSensorPosition(0);
    }

    public void driveWithMinPower(double leftPower, double rightPower, double minAbsolutePower) {
        double realLeftPower = (leftPower / Math.abs(leftPower)) * Math.max(Math.abs(leftPower), minAbsolutePower);
        double realRightPower = (rightPower / Math.abs(rightPower)) * Math.max(Math.abs(rightPower), minAbsolutePower);
    }

    @Override
    public void periodic() {
    }

}

