package frc.robot.commands.shooting;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ShooterConstants;
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
        return Math.abs(shooterSubsystem.getSpeed()) >= ShooterConstants.SHOOT_1_SPEED;
    }

    @Override
    public void end(boolean interrupted) {

    }
}
