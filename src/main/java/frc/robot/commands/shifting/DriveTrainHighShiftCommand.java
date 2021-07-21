package frc.robot.commands.shifting;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShiftingGearsSubsystem;


public class DriveTrainHighShiftCommand extends CommandBase {
    private final ShiftingGearsSubsystem shiftingGearsSubsystem;

    public DriveTrainHighShiftCommand(ShiftingGearsSubsystem shiftingGearsSubsystem) {
        this.shiftingGearsSubsystem = shiftingGearsSubsystem;
        addRequirements(shiftingGearsSubsystem);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        shiftingGearsSubsystem.shiftUp();
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
