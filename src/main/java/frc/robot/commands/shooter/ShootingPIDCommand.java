// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.ShooterSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ShootingPIDCommand extends PIDCommand {
  private ShooterSubsystem shooterSubsystem;
  /** Creates a new ShootingPIDCommand. */
  public ShootingPIDCommand(ShooterSubsystem shooterSubsystem) {
    super(
        // The controller that the command will use
        new PIDController(0.0, 0, 0),
        // This should return the measurement
        shooterSubsystem::getFlywheelRPM,
        // This should return the setpoint (can also be a constant)
        ShooterConstants.MAX_RPM,
        // This uses the output
        output -> {
          System.out.println("shooting PID output: " + output);
          shooterSubsystem.shootFlywheel(output);
        });
            
    this.shooterSubsystem = shooterSubsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(shooterSubsystem);
    // Configure additional PID options by calling `getController` here.
    getController().setTolerance(10);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
