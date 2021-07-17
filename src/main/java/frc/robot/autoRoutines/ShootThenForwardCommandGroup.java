package frc.robot.autoRoutines;


import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.drivetrain.DriveForDistanceCommand;
import frc.robot.commands.shooting.StopSpinCommand;
import frc.robot.subsystems.*;
import frc.robot.utils.Limelight;

public class ShootThenForwardCommandGroup extends SequentialCommandGroup {
    public ShootThenForwardCommandGroup(ShooterSubsystem shooterSubsystem, HopperSubsystem hopperSubsystem, NeckSubsystem neckSubsystem, DrivetrainSubsystem drivetrainSubsystem, IntakeSubsystem intakeSubsystem, Limelight limelight) {
       super();
        addCommands(

                new ShootWithTurnCommandGroup(shooterSubsystem, hopperSubsystem, neckSubsystem,  intakeSubsystem,drivetrainSubsystem,limelight).withTimeout(5),
                new StopSpinCommand(shooterSubsystem),
                new DriveForDistanceCommand(drivetrainSubsystem, 30, .5,  .5 )
        );
    }
}