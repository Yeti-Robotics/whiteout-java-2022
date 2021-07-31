package frc.robot.commands.climbing;

import edu.wpi.first.wpilibj2.command.CommandBase;
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
        // TODO: Make this return true when this Command no longer needs to run execute()
        // return climberSubsystem.getTopLimitSwitch();
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        climberSubsystem.stopClimb();
    }
}
