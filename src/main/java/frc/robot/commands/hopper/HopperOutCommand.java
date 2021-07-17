package frc.robot.commands.hopper;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.HopperSubsystem;


public class HopperOutCommand extends CommandBase {
    private final HopperSubsystem hopperSubsystem;

    public HopperOutCommand(HopperSubsystem hopperSubsystem) {
        this.hopperSubsystem = hopperSubsystem;
        addRequirements(hopperSubsystem);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        hopperSubsystem.funnelOut();

    }

    @Override
    public boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        hopperSubsystem.funnelStop();
    }
}
