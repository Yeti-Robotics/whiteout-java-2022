// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autoRoutines;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.commands.drivetrain.DriveForDistanceCommand;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class driveforwardCommandGroup extends ParallelCommandGroup {
  /** Creates a new driveforwardCommandGroup. */
  public driveforwardCommandGroup(DrivetrainSubsystem drivetrainSubsystem, int drivePower) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new DriveForDistanceCommand(drivetrainSubsystem, 60.0, drivePower, drivePower).withTimeout(3.5)
    );
  }
}
