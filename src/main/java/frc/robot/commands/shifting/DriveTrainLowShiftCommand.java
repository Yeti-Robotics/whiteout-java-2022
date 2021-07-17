package frc.robot.commands.shifting;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShiftGearsSubsystem;


public class DriveTrainLowShiftCommand extends CommandBase {
    private final ShiftGearsSubsystem shiftGearsSubsystem;

    public DriveTrainLowShiftCommand(ShiftGearsSubsystem shiftGearsSubsystem) {
        this.shiftGearsSubsystem = shiftGearsSubsystem;
        addRequirements(shiftGearsSubsystem);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        shiftGearsSubsystem.shiftDown();
    }

    @Override
    public boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return true;
    }

    @Override
    public void end(boolean interrupted) {

    }
}
