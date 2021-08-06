// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.hood;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.HoodSubsystem;

public class MoveHoodCommand extends CommandBase {
  private HoodSubsystem hoodSubsystem;
  private double power;
  /** Creates a new TestHoodCommand. */
  public MoveHoodCommand(HoodSubsystem hoodSubsystem, double power) {
    this.hoodSubsystem = hoodSubsystem;
    this.power = power;
    addRequirements(hoodSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    hoodSubsystem.moveHood(power);
    // System.out.println("hood angle: " + hoodSubsystem.hoodAngleFromEncoder(hoodSubsystem.getEncoder()));
    // System.out.println("hood position: " + hoodSubsystem.getEncoder());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    hoodSubsystem.stopHood();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}