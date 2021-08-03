package frc.robot.commands.neck;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.NeckSubsystem;


public class NeckUpCommand extends CommandBase {
    private final NeckSubsystem neckSubsystem;
    public NeckUpCommand(NeckSubsystem neckSubsystem) {
        this.neckSubsystem = neckSubsystem;
        addRequirements(neckSubsystem);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        neckSubsystem.moveUp();
    }

    @Override
    public boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        neckSubsystem.stopNeck();
    }
}
