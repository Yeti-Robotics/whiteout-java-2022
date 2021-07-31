package frc.robot.commands.climbing;

import edu.wpi.first.wpilibj2.command.CommandBase;
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
        // TODO: Make this return true when this Command no longer needs to run execute()
        return false;
        // return climberSubsystem.getBottomLimitSwitch();
    }

    @Override
    public void end(boolean interrupted) {
        climberSubsystem.stopClimb();
    }
}
