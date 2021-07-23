// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.groups;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.hood.SetCalcHoodAngleCommand;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.HoodSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AimToShootCommandGroup extends ParallelCommandGroup {
  public AimToShootCommandGroup(DrivetrainSubsystem drivetrainSubsystem, HoodSubsystem hoodSubsystem) {
    addCommands(
      //insert turn to target with PID here
      new SetCalcHoodAngleCommand(hoodSubsystem, 0.1)
    );
  }
}

