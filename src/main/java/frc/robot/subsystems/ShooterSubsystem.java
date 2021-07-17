package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.utils.Limelight;

public class ShooterSubsystem extends SubsystemBase {

    private TalonSRX shooterLeftTalon;
    private TalonSRX shooterRightTalon;
//    public Servo hoodServo1;
//    public Servo hoodServo2;
    public TalonSRX hoodTalon;
    private double distance;
    public enum ShooterStatus{
        FORWARD, BACKWARDS, OFF
    }
    public static ShooterStatus shooterStatus;
    public ShooterSubsystem() {
        shooterLeftTalon = new TalonSRX(Constants.SHOOTER_LEFT_TALON);
        shooterRightTalon = new TalonSRX(Constants.SHOOTER_RIGHT_TALON);
       /* hoodServo1 = new Servo(Constants.SHOOTER_SERVO_1);
        hoodServo2 = new Servo(Constants.SHOOTER_SERVO_2);
        hoodServo1.set(0);
        hoodServo2.set(1);*/
        shooterLeftTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        shooterRightTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);

        hoodTalon = new TalonSRX(Constants.HOOD_TALON);
        hoodTalon.setNeutralMode(NeutralMode.Brake);
        hoodTalon.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 0);
//        hoodServo1.setBounds(2.0,1.52,1.5,1.48,1.0);
//        hoodServo2.setBounds(2.0,1.52,1.5,1.48,1.0);
//        hoodServo1.enableDeadbandElimination(true);
//        hoodServo2.enableDeadbandElimination(true);
//        hoodServo1.set(0);
//        hoodServo1.setAngle(0);
//        hoodServo2.set(1);
//        hoodServo2.setAngle(180);
//        hoodServo1.setBounds(500,1167,1750,2333,2500);
//        hoodServo2.setBounds(2500,1833,1250,667,500);

//        shooterLeftTalon.follow(shooterRightTalon);
    }

    public void shoot() {
        shooterLeftTalon.set(ControlMode.PercentOutput, Constants.SHOOT_1_SPEED);
        shooterRightTalon.set(ControlMode.PercentOutput, Constants.SHOOT_2_SPEED);
        shooterStatus = ShooterStatus.FORWARD;
    }

    public void reverseShoot() {
        shooterLeftTalon.set(ControlMode.PercentOutput, -Constants.REVERSE_SHOOT_1_SPEED);
        shooterRightTalon.set(ControlMode.PercentOutput, Constants.REVERSE_SHOOT_2_SPEED);
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

//    public void resetServo() {
//        hoodServo1.set(0.5);
//    }
//
//    public void stopServo() {
//        hoodServo1.setSpeed(0);
//    }
//
//    public void setServo(double pos) {
////        pos *= Constants.SERVO_RATIO;
//        hoodServo1.setSpeed(pos);
//        hoodServo2.setSpeed(pos);
//    }
//
//    public double getHoodPosition() {
//        return hoodServo1.getPosition();
//        }
//


    public void setHoodAngle(double angle) {
        if (hoodTalon.getSelectedSensorPosition() != angle) {
            hoodTalon.set(ControlMode.PercentOutput, 0.2);
        } else {
            hoodTalon.set(ControlMode.PercentOutput, 0.0);
        }

    }

    public void moveHood(double power) {
        hoodTalon.set(ControlMode.PercentOutput, power);
    }

    public void stopHood() {
        hoodTalon.set(ControlMode.PercentOutput, 0.0);
    }
//
//    public double getHoodAngle() {
////        return hoodServo1.getAngle() * Constants.SERVO_GEAR_RATIO;
//        return hoodServo1.getAngle();
//    }


    public static ShooterStatus getShooterStatus(){
        return shooterStatus;
    }

    public double calcHoodAngle() {
        return Math.toDegrees(Math.asin( -Constants.GRAVITY * distance) / Constants.SHOOT_1_SPEED);
    }

    public double getSpeed() {
        return shooterLeftTalon.getMotorOutputPercent();
    }

    @Override
    public void periodic() {
        distance = Limelight.getHorDistance();
    }
}
