package frc.robot.commands.hood;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;

//unsure of if this command needs to exist at all. the function is unclear
public class SetHoodAngleCommand extends CommandBase {
    private final ShooterSubsystem shooterSubsystem;
    private double hoodAngle;

    public SetHoodAngleCommand(ShooterSubsystem shooterSubsystem, int a) {
        this.shooterSubsystem = shooterSubsystem;
        addRequirements(shooterSubsystem);
        hoodAngle = a;
    }

    @Override
    public void initialize() {
//        hoodAngle = shooterSubsystem.calcHoodAngle();
//        System.out.println("hood angle is: " + hoodAngle);

    }

    @Override
    public void execute() {
        shooterSubsystem.setHoodAngle(hoodAngle);
        //right now set hood angle sets the SPEED!
    }

    @Override
    public boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
//        return shooterSubsystem.getHoodAngle() >= hoodAngle;
        return false;
    }

    @Override
    public void end(boolean interrupted) {
    }
}
