package frc.robot.autoRoutines;


import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.drivetrain.DriveForDistanceCommand;
import frc.robot.commands.intake.RetractIntakeCommand;
import frc.robot.commands.shooter.StopSpinCommand;
import frc.robot.subsystems.*;
import frc.robot.utils.Limelight;

public class ShootThenBackCommandGroup extends SequentialCommandGroup {
    public ShootThenBackCommandGroup(ShooterSubsystem shooterSubsystem, HopperSubsystem hopperSubsystem, NeckSubsystem neckSubsystem, DrivetrainSubsystem drivetrainSubsystem, IntakeSubsystem intakeSubsystem, Limelight limelight) {
        super();
        addCommands(
                new RetractIntakeCommand(intakeSubsystem),
                new ShootWithTurnCommandGroup(shooterSubsystem, hopperSubsystem, neckSubsystem, intakeSubsystem, drivetrainSubsystem, limelight).withTimeout(5),
                new StopSpinCommand(shooterSubsystem),
                new DriveForDistanceCommand(drivetrainSubsystem, 18, -.5,  -.5 )
        );
    }
}