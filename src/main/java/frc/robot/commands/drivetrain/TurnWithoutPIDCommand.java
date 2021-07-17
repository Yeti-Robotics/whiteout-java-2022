package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.utils.Limelight;


public class TurnWithoutPIDCommand extends CommandBase {
    private final DrivetrainSubsystem drivetrainSubsystem;
    private Limelight limelight;

    public TurnWithoutPIDCommand(DrivetrainSubsystem drivetrainSubsystem, Limelight limelight) {
        this.drivetrainSubsystem = drivetrainSubsystem;
        this.limelight = limelight;
        addRequirements(drivetrainSubsystem);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        if(limelight.getTx()<0){
            drivetrainSubsystem.drive(-0.4,0.4);
            System.out.println("turning left");
        }else if(limelight.getTx()>0){
            drivetrainSubsystem.drive(0.4,-0.4);
            System.out.println("turning right");
        }
    }

    @Override
    public boolean isFinished() {
        return limelight.getTx() < .1 || limelight.getTx() > -.1;
    }

    @Override
    public void end(boolean interrupted) {
        drivetrainSubsystem.stopDrive();
    }
}
