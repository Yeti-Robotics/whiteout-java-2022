package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;


public class SpinWithStopCommand extends CommandBase {
    private final ShooterSubsystem shooterSubsystem;

    public SpinWithStopCommand(ShooterSubsystem shooterSubsystem) {
        this.shooterSubsystem = shooterSubsystem;
        addRequirements(shooterSubsystem);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        shooterSubsystem.shoot();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        shooterSubsystem.stopShoot();
    }
}
