package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;
import frc.robot.utils.Limelight;

public class ShooterSubsystem extends SubsystemBase {
    private TalonSRX shooterLeftTalon;
    private TalonSRX shooterRightTalon;
    public TalonSRX hoodTalon;
    private double distance;
    private int setPoint;
    public enum ShooterStatus{
        FORWARD, BACKWARDS, OFF
    }
    public static ShooterStatus shooterStatus;

    public ShooterSubsystem() {
        shooterLeftTalon = new TalonSRX(ShooterConstants.SHOOTER_LEFT_TALON);
        shooterRightTalon = new TalonSRX(ShooterConstants.SHOOTER_RIGHT_TALON);

        // shooterLeftTalon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder); // not working so disconnected temporarily
        shooterRightTalon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);

        // shooterLeftTalon.follow(shooterRightTalon);
        // shooterLeftTalon.setInverted(InvertType.OpposeMaster);
        shooterLeftTalon.setInverted(false);

        // shooterRightTalon.configNominalOutputForward(0.0);
        // shooterRightTalon.configNominalOutputReverse(0.0);

        // shooterRightTalon.selectProfileSlot(0, 0);
        // // maybe make constants for these
        // shooterRightTalon.config_kF(0, 3.09375); // calculated using ((0.75 * 1023) / peak velocity of motor at 75% (in native units per 100ms))
        // shooterRightTalon.config_kP(0, 0.0); 
        // shooterRightTalon.config_kI(0, 0.0);
        // shooterRightTalon.config_kD(0, 0.0);

        setPoint = (int)(ShooterConstants.MAX_RPM * .80); // 80%
    }

    public void shoot() {
        // shooterRightTalon.set(ControlMode.PercentOutput, ShooterConstants.SHOOT_1_SPEED);
        shooterRightTalon.set(ControlMode.Velocity, getVelocityUnitsFromRPM(setPoint));
        shooterStatus = ShooterStatus.FORWARD;
    }

    public void shootFlywheel(double speed){
        shooterRightTalon.set(ControlMode.PercentOutput, speed);
        shooterLeftTalon.set(ControlMode.PercentOutput, speed);
        shooterStatus = ShooterStatus.FORWARD;
    }

    public void reverseShoot(double speed) {
        shooterRightTalon.set(ControlMode.PercentOutput, speed);
        shooterStatus = ShooterStatus.BACKWARDS;
    }

    public void stopShoot() {
        shooterLeftTalon.set(ControlMode.PercentOutput, 0);
        shooterRightTalon.set(ControlMode.PercentOutput, 0);
        shooterStatus = ShooterStatus.OFF;
    }

    // not working so disconnected temporarily
    public double getLeftEncoder() {
        return shooterLeftTalon.getSelectedSensorVelocity();
    }

    public double getRightEncoder() {
        return shooterRightTalon.getSelectedSensorVelocity();
    }

    public double getAverageEncoder() {
        return (getLeftEncoder() + getRightEncoder()) / 2.0;
    }

    public double getSpeed() {
        return shooterLeftTalon.getMotorOutputPercent();
    }

    public double getFlywheelRPM() {
        return (getRightEncoder() * ShooterConstants.PULLEY_RATIO * ShooterConstants.ENCODER_TIME_CONVERSION) / (ShooterConstants.ENCODER_RESOLUTION * ShooterConstants.QUAD_FACTOR);
    }

    public static ShooterStatus getShooterStatus(){
        return shooterStatus;
    }

    public double getVelocityUnitsFromRPM(double RPM){
        return RPM / (ShooterConstants.PULLEY_RATIO * (ShooterConstants.ENCODER_TIME_CONVERSION / ShooterConstants.ENCODER_RESOLUTION));
    }

    public int getSetPoint() {
        return setPoint;
    }

    @Override
    public void periodic() {
        distance = Limelight.getHorDistance();
        // System.out.println("flywheel RPM: " + getFlywheelRPM());
    }
}
