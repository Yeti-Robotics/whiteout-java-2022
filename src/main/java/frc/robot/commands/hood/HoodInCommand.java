// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.hood;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.HoodConstants;
import frc.robot.subsystems.HoodSubsystem;

public class HoodInCommand extends CommandBase {
  private HoodSubsystem hoodSubsystem; 

  public HoodInCommand(HoodSubsystem hoodSubsystem) {
    this.hoodSubsystem = hoodSubsystem;
    addRequirements(hoodSubsystem);
  }

  @Override
  public void initialize() {

  }

  @Override
  public void execute() {
    if(!(hoodSubsystem.getEncoder() - HoodConstants.HOOD_ANGLE_TOLERANCE >= HoodConstants.MAX_HOOD_ANGLE)){
      hoodSubsystem.moveHood(-HoodConstants.HOOD_SPEED);
    }
    System.out.println("encoder limit: " + hoodSubsystem.hoodEncoderFromAngle(HoodConstants.MAX_HOOD_ANGLE));
    System.out.println("current encoder: " + hoodSubsystem.getEncoder());
  }

  @Override
  public void end(boolean interrupted) {
    hoodSubsystem.stopHood();
  }

  @Override
  public boolean isFinished() {
    return hoodSubsystem.getEncoder() - hoodSubsystem.hoodEncoderFromAngle(HoodConstants.HOOD_ANGLE_TOLERANCE) >= hoodSubsystem.hoodEncoderFromAngle(HoodConstants.MAX_HOOD_ANGLE);
  }
}
