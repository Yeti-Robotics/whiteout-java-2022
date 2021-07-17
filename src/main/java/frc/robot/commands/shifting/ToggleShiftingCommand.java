package frc.robot.commands.shifting;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.ShiftGearsSubsystem;


public class ToggleShiftingCommand extends CommandBase {
    private final ShiftGearsSubsystem shiftGearsSubsystem;

    public ToggleShiftingCommand(ShiftGearsSubsystem shiftGearsSubsystem) {
        this.shiftGearsSubsystem = shiftGearsSubsystem;
        addRequirements(shiftGearsSubsystem);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        if (ShiftGearsSubsystem.getShifterPosition() == ShiftGearsSubsystem.ShiftStatus.HIGH) {
            shiftGearsSubsystem.shiftDown();
        } else {
            shiftGearsSubsystem.shiftUp();
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
