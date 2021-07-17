package frc.robot.commands.hopper;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.IntakeSubsystem;


public class StopFunnelCommand extends CommandBase {
    private final HopperSubsystem hopperSubsystem;

    public StopFunnelCommand(HopperSubsystem hopperSubsystem) {
        this.hopperSubsystem = hopperSubsystem;
        addRequirements(hopperSubsystem);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        hopperSubsystem.funnelStop();
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void end(boolean interrupted) {

    }
}
