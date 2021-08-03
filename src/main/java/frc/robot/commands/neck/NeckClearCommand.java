// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.neck;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.NeckSubsystem;

// this command appears to be fairly redundant with MoveDownNeckCommand
public class NeckClearCommand extends CommandBase {
  private NeckSubsystem neckSubsystem;

  public NeckClearCommand(NeckSubsystem neckSubsystem) {
    this.neckSubsystem = neckSubsystem;
    addRequirements(neckSubsystem);
  }

  @Override
  public void initialize(){
    this.neckSubsystem.moveDown();
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted){
    this.neckSubsystem.stopNeck();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
