package frc.robot.commands.hopper;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.HopperSubsystem;


public class HopperInCommand extends CommandBase {
    private final HopperSubsystem hopperSubsystem;

    public HopperInCommand(HopperSubsystem hopperSubsystem) {
        this.hopperSubsystem = hopperSubsystem;
        addRequirements(hopperSubsystem);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        hopperSubsystem.funnelIn();
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
