package frc.robot.commands.shooting;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;


public class TestHoodMotorCommand extends CommandBase {
    private final ShooterSubsystem shooterSubsystem;
    public double speed;

    public TestHoodMotorCommand(ShooterSubsystem shooterSubsystem, double speed) {
        this.shooterSubsystem = shooterSubsystem;
        addRequirements(shooterSubsystem);
        this.speed = speed;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        shooterSubsystem.moveHood(speed);
    }

    @Override
    public boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        shooterSubsystem.stopHood();

    }
}
