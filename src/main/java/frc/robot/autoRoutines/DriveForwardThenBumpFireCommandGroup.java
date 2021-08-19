// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autoRoutines;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants.HoodConstants;
import frc.robot.commands.AllInCommand;
import frc.robot.commands.drivetrain.DriveForDistanceCommand;
import frc.robot.commands.hood.SetCalcHoodAngleCommand;
import frc.robot.commands.hood.SetHoodAngle;
import frc.robot.commands.shooter.StartSpinCommand;
import frc.robot.commands.shooter.StopSpinCommand;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.HoodSubsystem;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.NeckSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class DriveForwardThenBumpFireCommandGroup extends SequentialCommandGroup {
  public DriveForwardThenBumpFireCommandGroup(double drivePower, DrivetrainSubsystem drivetrainSubsystem, ShooterSubsystem shooterSubsystem, IntakeSubsystem intakeSubsystem, HopperSubsystem hopperSubsystem, NeckSubsystem neckSubsystem, HoodSubsystem hoodSubsystem) {
    addCommands(
      // new DriveForDistanceCommand(drivetrainSubsystem, 98.0, drivePower, drivePower),
      new SetHoodAngle(hoodSubsystem, HoodConstants.BUMP_FIRE_ANGLE, HoodConstants.HOOD_SPEED),
      new DriveForDistanceCommand(drivetrainSubsystem, 60.0, drivePower, drivePower).withTimeout(3.5),
      new WaitCommand(0.5), 
      new StartSpinCommand(shooterSubsystem), 
      new WaitCommand(2.0), 
      new AllInCommand(intakeSubsystem, hopperSubsystem, neckSubsystem).withTimeout(0.1), 
      new WaitCommand(1.0), 
      new AllInCommand(intakeSubsystem, hopperSubsystem, neckSubsystem).withTimeout(0.25),
      new WaitCommand(1.0), 
      new AllInCommand(intakeSubsystem, hopperSubsystem, neckSubsystem).withTimeout(0.5), 
      new StopSpinCommand(shooterSubsystem)
    );
  }
}
