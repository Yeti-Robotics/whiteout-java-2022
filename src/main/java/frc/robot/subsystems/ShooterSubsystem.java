package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CalcConstants;
import frc.robot.Constants.ShooterConstants;
import frc.robot.utils.Limelight;

public class ShooterSubsystem extends SubsystemBase {

    private TalonSRX shooterLeftTalon;
    private TalonSRX shooterRightTalon;
//    public Servo hoodServo1;
//    public Servo hoodServo2;
    private double distance;
    public enum ShooterStatus{
        FORWARD, BACKWARDS, OFF
    }
    public static ShooterStatus shooterStatus;
    
    public ShooterSubsystem() {
        shooterLeftTalon = new TalonSRX(ShooterConstants.SHOOTER_LEFT_TALON);
        shooterRightTalon = new TalonSRX(ShooterConstants.SHOOTER_RIGHT_TALON);

        shooterLeftTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        shooterRightTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    }

    public void shoot() {
        shooterLeftTalon.set(ControlMode.PercentOutput, ShooterConstants.SHOOT_1_SPEED);
        shooterRightTalon.set(ControlMode.PercentOutput, ShooterConstants.SHOOT_2_SPEED);
        shooterStatus = ShooterStatus.FORWARD;
    }

    public void reverseShoot() {
        shooterLeftTalon.set(ControlMode.PercentOutput, -ShooterConstants.REVERSE_SHOOT_1_SPEED);
        shooterRightTalon.set(ControlMode.PercentOutput, ShooterConstants.REVERSE_SHOOT_2_SPEED);
        shooterStatus = ShooterStatus.BACKWARDS;
    }

    public void stopShoot() {
        shooterLeftTalon.set(ControlMode.PercentOutput, 0);
        shooterRightTalon.set(ControlMode.PercentOutput, 0);
        shooterStatus = ShooterStatus.OFF;
    }

    public double getLeftEncoder() {
        return shooterLeftTalon.getSelectedSensorVelocity();
    }

    public double getRightEncoder() {
        return shooterRightTalon.getSelectedSensorVelocity();
    }

    public double getAverageEncoder() {
        return (getLeftEncoder() + getRightEncoder()) / 2;
    }

    public double getSpeed() {
        return shooterLeftTalon.getMotorOutputPercent();
    }

    public double getFlywheelRPM() {
        return getAverageEncoder() * ShooterConstants.PULLEY_RATIO * (ShooterConstants.ENCODER_TIME_CONVERSION / ShooterConstants.ENCODER_RESOLUTION);
    }

    @Override
    public void periodic() {
        distance = Limelight.getHorDistance();
    }
}
