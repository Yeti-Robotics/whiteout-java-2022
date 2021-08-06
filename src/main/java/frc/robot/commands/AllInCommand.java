// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.NeckSubsystem;

public class AllInCommand extends CommandBase {
  private IntakeSubsystem intakeSubsystem;
  private HopperSubsystem hopperSubsystem;
  private NeckSubsystem neckSubsystem;

  public AllInCommand(IntakeSubsystem intakeSubsystem, HopperSubsystem hopperSubsystem, NeckSubsystem neckSubsystem) {
    this.intakeSubsystem = intakeSubsystem;
    this.hopperSubsystem = hopperSubsystem; 
    this.neckSubsystem = neckSubsystem;
    addRequirements(intakeSubsystem, hopperSubsystem, neckSubsystem);
  }

  @Override
  public void initialize(){
    if(intakeSubsystem.getIntakePosition() != intakeSubsystem.getIntakeStatusDown()){ // logic should be inverse but it works so whatever
      this.intakeSubsystem.rollIn();
    }
    this.hopperSubsystem.funnelIn();
    this.neckSubsystem.moveUp();
  }

  @Override
  public void execute(){
    // if(!this.neckSubsystem.getLowerBeamBreak()){
      // this.neckSubsystem.stopNeck();
    // } else {
      // this.neckSubsystem.moveUp();
    // }
  }

  @Override
  public void end(boolean interrupted){
    this.intakeSubsystem.stopRoll();
    this.hopperSubsystem.funnelStop();
    this.neckSubsystem.stopNeck();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
