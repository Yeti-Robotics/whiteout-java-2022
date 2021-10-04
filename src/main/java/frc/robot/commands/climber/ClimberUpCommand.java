package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ClimberConstants;
import frc.robot.subsystems.ClimberSubsystem;


public class ClimberUpCommand extends CommandBase {

    private final ClimberSubsystem climberSubsystem;

    public ClimberUpCommand(ClimberSubsystem climberSubsystem) {
        this.climberSubsystem = climberSubsystem;
        addRequirements(climberSubsystem);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        climberSubsystem.climbUp();
    }

    @Override
    public boolean isFinished() {
        return climberSubsystem.getAverageEncoder() >= ClimberConstants.CLIMBER_UPPER_LIMIT - ClimberConstants.CLIMBER_TOLERANCE;
    }

    @Override
    public void end(boolean interrupted) {
        climberSubsystem.stopClimb();
    }
}
