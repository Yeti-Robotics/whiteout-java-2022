package frc.robot.commands.shifting;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.ShiftingGearsSubsystem;


public class ToggleShiftingCommand extends CommandBase {
    private final ShiftingGearsSubsystem shiftingGearsSubsystem;

    public ToggleShiftingCommand(ShiftingGearsSubsystem shiftingGearsSubsystem) {
        this.shiftingGearsSubsystem = shiftingGearsSubsystem;
        addRequirements(shiftingGearsSubsystem);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        if (ShiftingGearsSubsystem.getShifterPosition() == ShiftingGearsSubsystem.ShiftStatus.HIGH) {
            shiftingGearsSubsystem.shiftDown();
        } else {
            shiftingGearsSubsystem.shiftUp();
        }
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void end(boolean interrupted) {

    }
}
