// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANDigitalInput;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.SoftLimitDirection;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.HoodConstants;

public class HoodSubsystem extends SubsystemBase {
  private CANSparkMax hoodSpark;
  private CANEncoder hoodEncoder;
  private CANDigitalInput beamBreak;

  public HoodSubsystem() {
    hoodSpark = new CANSparkMax(HoodConstants.HOOD_SPARK, MotorType.kBrushless);  
    hoodEncoder = hoodSpark.getEncoder();
    hoodSpark.setInverted(false);
    hoodSpark.setSoftLimit(SoftLimitDirection.kForward, (float)hoodEncoderFromAngle(HoodConstants.MAX_HOOD_ANGLE));
    hoodSpark.setSoftLimit(SoftLimitDirection.kReverse, (float)hoodEncoderFromAngle(0));
  }

  @Override
  public void periodic() {
    System.out.println("upper limit: " + hoodSpark.getSoftLimit(SoftLimitDirection.kForward) + "; lower limit: " + hoodSpark.getSoftLimit(SoftLimitDirection.kReverse) );
  }

  public void moveHood(double power) {
    hoodSpark.set(power);
  }

  public void stopHood() {
    hoodSpark.set(0);
  }

  public double hoodEncoderFromAngle(double angle){
    return HoodConstants.COUNTS_PER_DEGREE * angle;
  }

  public double hoodAngleFromEncoder(double encoderValue){
    return encoderValue / HoodConstants.COUNTS_PER_DEGREE;
  }

  public void resetEncoder(){
    hoodEncoder.setPosition(0);
}

  public double getEncoder(){
    return hoodEncoder.getPosition();
  }                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        

  public boolean getBeamBreak(){
    return beamBreak.get();
  }

   public double calcHoodAngle(double distance) {
     //y = mx+b values based on hood testing
     return ((.0867898 * distance) + 12.4589);
   }
}
