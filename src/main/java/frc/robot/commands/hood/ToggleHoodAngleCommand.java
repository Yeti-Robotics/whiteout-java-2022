// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.hood;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.HoodConstants;
import frc.robot.subsystems.HoodSubsystem;
import frc.robot.subsystems.HoodSubsystem.HoodStatus;

public class ToggleHoodAngleCommand extends CommandBase {
  private final HoodSubsystem hoodSubsystem;
  private double encoderGoal;
  private double power;
  /** Creates a new ToggleHoodAngleCommand. */
  public ToggleHoodAngleCommand(HoodSubsystem hoodSubsystem, double power) {
    this.hoodSubsystem = hoodSubsystem;
    this.power= Math.abs(power);
    addRequirements(hoodSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if (hoodSubsystem.hoodStatus == HoodStatus.BUMP_FIRE){
      encoderGoal = hoodSubsystem.hoodEncoderFromAngle(HoodConstants.OTHER_FIRING_ANGLE);
      hoodSubsystem.hoodStatus = HoodStatus.INIT_LINE;
    } else {
      encoderGoal = hoodSubsystem.hoodEncoderFromAngle(HoodConstants.BUMP_FIRE_ANGLE);
      hoodSubsystem.hoodStatus = HoodStatus.BUMP_FIRE;
    }
    if (encoderGoal < hoodSubsystem.getEncoder()){
        power = -power;
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    hoodSubsystem.moveHood(power);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    hoodSubsystem.stopHood();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Math.abs(encoderGoal - hoodSubsystem.getEncoder()) <= HoodConstants.HOOD_ANGLE_TOLERANCE;
  }
}
