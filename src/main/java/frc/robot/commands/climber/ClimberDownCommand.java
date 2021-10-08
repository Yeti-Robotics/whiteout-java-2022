package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ClimberConstants;
import frc.robot.subsystems.ClimberSubsystem;

public class ClimberDownCommand extends CommandBase {

    private final ClimberSubsystem climberSubsystem;

    public ClimberDownCommand(ClimberSubsystem climberSubsystem) {
        this.climberSubsystem = climberSubsystem;
        addRequirements(climberSubsystem);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        climberSubsystem.climbDown();
    }

    @Override
    public boolean isFinished() {
        return climberSubsystem.getAverageEncoder() <= ClimberConstants.CLIMBER_TOLERANCE;
    }

    @Override
    public void end(boolean interrupted) {
        climberSubsystem.stopClimb();
    }
}
