package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.Encoder;

public class DrivetrainSubsystem extends SubsystemBase {
    private TalonFX leftFalcon1, leftFalcon2, rightFalcon1, rightFalcon2;    

    public DrivetrainSubsystem() {
        
        leftFalcon1 = new TalonFX(Constants.LEFT_FALCON_1);
        leftFalcon2 = new TalonFX(Constants.LEFT_FALCON_2);
        rightFalcon1 = new TalonFX(Constants.RIGHT_FALCON_1);
        rightFalcon2 = new TalonFX(Constants.RIGHT_FALCON_2);

        
        leftFalcon1.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 0);
        rightFalcon1.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 0);
//        leftVictor1.set(VictorSPXControlMode.Follower, Constants.LEFT_VICTOR_1);
//        leftVictor2.set(VictorSPXControlMode.Follower, Constants.LEFT_VICTOR_2);
//        rightVictor1.set(VictorSPXControlMode.Follower, Constants.RIGHT_VICTOR_1);
//        rightVictor2.set(VictorSPXControlMode.Follower, Constants.RIGHT_VICTOR_2);
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
        return (leftFalcon1.getSelectedSensorPosition() * Constants.DISTANCE_PER_PULSE);
    }

    public double getRightEncoder() {
        return (-rightFalcon1.getSelectedSensorPosition() * Constants.DISTANCE_PER_PULSE);
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

        // System.out.println("drivetrain periodic");

//        NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
//        NetworkTableEntry tx = table.getEntry("tx");
//        NetworkTableEntry ty = table.getEntry("ty");
//        NetworkTableEntry ta = table.getEntry("ta");

//read values periodically
//        double x = tx.getDouble(0.0);
//        double y = ty.getDouble(0.0);
//        double area = ta.getDouble(0.0);

        // post to smart dashboard periodically
//        SmartDashboard.putNumber("LimelightX", x);
//        SmartDashboard.putNumber("LimelightY", y);
//        SmartDashboard.putNumber("LimelightArea", area);


//
//        System.out.println("Right Encoder: " + getRightEncoder());
//        System.out.println("Left Encoder: " + getLeftEncoder());
//        System.out.println("Average Encoder: " + getAverageEncoder());
    }

}

