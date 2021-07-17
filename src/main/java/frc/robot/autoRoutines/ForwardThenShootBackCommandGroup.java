package frc.robot.autoRoutines;


import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.drivetrain.DriveForDistanceCommand;
import frc.robot.commands.shooting.StopSpinCommand;
import frc.robot.subsystems.*;
import frc.robot.utils.Limelight;

public class ForwardThenShootBackCommandGroup extends SequentialCommandGroup {
    public ForwardThenShootBackCommandGroup(ShooterSubsystem shooterSubsystem, HopperSubsystem hopperSubsystem, NeckSubsystem neckSubsystem, Limelight limelight, DrivetrainSubsystem drivetrainSubsystem, IntakeSubsystem intakeSubsystem) {
       super();
        addCommands(
                new DriveForDistanceCommand(drivetrainSubsystem, 62, .5,  .5 ),
                new ShootWithTurnCommandGroup(shooterSubsystem, hopperSubsystem, neckSubsystem, intakeSubsystem, drivetrainSubsystem, limelight).withTimeout(5),
                new StopSpinCommand(shooterSubsystem)
        );
    }
}