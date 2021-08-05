package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

public class ToggleIntakeCommand extends CommandBase {
    private IntakeSubsystem intakeSubsystem;

    public ToggleIntakeCommand(IntakeSubsystem intakeSubsystem) {
        this.intakeSubsystem = intakeSubsystem;
        addRequirements(intakeSubsystem);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        if(intakeSubsystem.getIntakePosition() == intakeSubsystem.getIntakeStatusUp()) {
            intakeSubsystem.extend();
        } else {
            intakeSubsystem.retract();
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
