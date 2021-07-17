package frc.robot.commands.shooting;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ShooterSubsystem;


public class StartSpinCommand extends CommandBase {

    private final ShooterSubsystem shooterSubsystem;

    public StartSpinCommand(ShooterSubsystem shooterSubsystem) {
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
        // TODO: Make this return true when this Command no longer needs to run execute()
        return Math.abs(shooterSubsystem.getSpeed()) >= Constants.SHOOT_1_SPEED;
    }

    @Override
    public void end(boolean interrupted) {

    }
}
