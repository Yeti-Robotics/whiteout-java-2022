package frc.robot.autoRoutines;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.drivetrain.DriveForDistanceCommand;
import frc.robot.commands.shooting.StopSpinCommand;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.NeckSubsystem;
import frc.robot.subsystems.ShiftingGearsSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.utils.Limelight;


public class TurnThenForwardThenShootCommandGroup extends SequentialCommandGroup{

    public TurnThenForwardThenShootCommandGroup(ShooterSubsystem shooterSubsystem, HopperSubsystem hopperSubsystem, NeckSubsystem neckSubsystem, DrivetrainSubsystem drivetrainSubsystem, IntakeSubsystem intakeSubsystem, Limelight limelight, ShiftingGearsSubsystem shiftingGearsSubsystem) {
        super();
        addCommands(
                // new DriveTrainLowShiftCommand(shiftGearsSubsystem),
                // new RetractIntakeCommand(intakeSubsystem),
                // new TurnNoPIDCommand(drivetrainSubsystem, limelight),
                // new StartSpinCommand(shooterSubsystem).withTimeout(1),
                new DriveForDistanceCommand(drivetrainSubsystem, 72, .5,  .5 ),
                new ShootCommandGroup(shooterSubsystem, hopperSubsystem, neckSubsystem, intakeSubsystem).withTimeout(6),
                new StopSpinCommand(shooterSubsystem)
        );
    }
}
