package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;


public class ToggleShooterCommand extends CommandBase {
    private final ShooterSubsystem shooterSubsystem;

    public ToggleShooterCommand(ShooterSubsystem shooterSubsystem) {
        this.shooterSubsystem = shooterSubsystem;
        addRequirements(shooterSubsystem);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        if (ShooterSubsystem.getShooterStatus() == ShooterSubsystem.shooterStatus.OFF) {
            shooterSubsystem.shootFlywheel(0.9);
        } else if (ShooterSubsystem.getShooterStatus() == ShooterSubsystem.shooterStatus.FORWARD){
            shooterSubsystem.stopShoot();
        }else {
            shooterSubsystem.stopShoot();
        }

    }

    @Override
    public boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return true;
    }

    @Override
    public void end(boolean interrupted) {
    }
}
