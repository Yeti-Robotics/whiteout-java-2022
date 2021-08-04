// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.hood.ToggleHoodAngleCommand;
import frc.robot.commands.neck.OscillateNeckCommand;
import frc.robot.commands.shooter.StartSpinCommand;
import frc.robot.subsystems.HoodSubsystem;
import frc.robot.subsystems.NeckSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ShootThreeBallsAutoCommand extends ParallelCommandGroup {
  /** Creates a new ShootThreeBallsAutoCommand. */
  public ShootThreeBallsAutoCommand(HoodSubsystem hoodSubsystem, NeckSubsystem neckSubsystem, ShooterSubsystem shooterSubsystem) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new ToggleHoodAngleCommand(hoodSubsystem, 0.25), //bump fire angle
      new ToggleHoodAngleCommand(hoodSubsystem, 0.25), //init line angle
      new StartSpinCommand(shooterSubsystem),
      new OscillateNeckCommand(neckSubsystem)




    );
  }
}
