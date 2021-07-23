package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.utils.Limelight;


public class TurnNoPIDCommand extends CommandBase {
    private final DrivetrainSubsystem drivetrainSubsystem;
    private Limelight limelight;

    public TurnNoPIDCommand(DrivetrainSubsystem drivetrainSubsystem, Limelight limelight) {
        this.drivetrainSubsystem = drivetrainSubsystem;
        this.limelight = limelight;
        addRequirements(drivetrainSubsystem);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        System.out.println(limelight.getTx());
        if(limelight.getTx() < 0){
            drivetrainSubsystem.tankDrive(-0.2, 0.2);
            System.out.println("turning left");
        }else if(limelight.getTx() > 0){
            drivetrainSubsystem.tankDrive(0.2, -0.2);
            System.out.println("turning right");
        }
    }

    @Override
    public boolean isFinished() {
        return Math.abs(limelight.getTx()) < 3;
    }

    @Override
    public void end(boolean interrupted) {
        drivetrainSubsystem.stopDrive();
    }
}
