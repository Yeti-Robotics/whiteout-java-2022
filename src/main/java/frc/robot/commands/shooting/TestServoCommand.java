package frc.robot.commands.shooting;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ShooterSubsystem;


public class TestServoCommand extends CommandBase {
    private final ShooterSubsystem shooterSubsystem;
    private double position;
    private double sonic;
    private int count;

    public TestServoCommand(ShooterSubsystem shooterSubsystem){
        this.shooterSubsystem = shooterSubsystem;
        addRequirements(shooterSubsystem);
    }

    @Override
    public void initialize() {
        System.out.println("Command started");

    }

    @Override
    public void execute() {
//        System.out.println("current position: " + shooterSubsystem.hoodServo2.getPosition() + " & raw pwm: " + shooterSubsystem.hoodServo2.getRaw());
//        shooterSubsystem.hoodServo1.set(Robot.robotContainer.getServoThrottle());
//        shooterSubsystem.hoodServo2.set(-Robot.robotContainer.getServoThrottle());
    }

    @Override
    public boolean isFinished() {
        return false;
        
    }

    @Override
    public void end(boolean interrupted) {
        System.out.println("Command over");
    }
}