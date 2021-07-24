// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.hood;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.HoodSubsystem;

public class ResetHoodEncoderCommand extends CommandBase {
  private HoodSubsystem hoodSubsystem;

  public ResetHoodEncoderCommand(HoodSubsystem hoodSubsystem) {
    this.hoodSubsystem = hoodSubsystem;
    addRequirements(hoodSubsystem);
  }

  @Override
  public void initialize() {
    this.hoodSubsystem.resetEncoder();
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return true;
  }
}
