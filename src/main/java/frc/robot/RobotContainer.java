/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.HoodConstants;
import frc.robot.Constants.OIConstants;
import frc.robot.autoRoutines.DriveForwardThenBumpFireCommandGroup;
import frc.robot.autoRoutines.FireThreeThenForwardCommandGroup;
import frc.robot.commands.AllInCommand;
import frc.robot.commands.AllOutCommand;
import frc.robot.commands.climber.ClimberDownCommand;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.climber.ClimberUpCommand;
import frc.robot.commands.climber.ToggleBrakeCommand;
import frc.robot.commands.drivetrain.TurnToTargetPIDCommand;
import frc.robot.commands.groups.AimToShootCommandGroup;
import frc.robot.commands.hood.HoodInCommand;
import frc.robot.commands.hood.HoodOutCommand;
import frc.robot.commands.hood.MoveHoodCommand;
import frc.robot.commands.hood.ResetHoodEncoderCommand;
import frc.robot.commands.hood.SetHoodAngle;
import frc.robot.commands.hood.ToggleHoodAngleCommand;
import frc.robot.commands.intake.IntakeInCommand;
import frc.robot.commands.intake.ToggleIntakeCommand;
import frc.robot.commands.neck.NeckClearCommand;
import frc.robot.commands.neck.NeckUpCommand;
import frc.robot.commands.shifting.ToggleShiftingCommand;
import frc.robot.commands.shooter.ShootingPIDCommand;
import frc.robot.commands.shooter.ToggleShooterCommand;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.HoodSubsystem;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.NeckSubsystem;
import frc.robot.subsystems.ShiftingGearsSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.utils.Limelight;
//import frc.robot.utils.DoubleButton;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  // public final Joystick leftJoy;
  // public final Joystick rightJoy;
  // public final Joystick secondaryJoy;
  // private WheelOfFortuneSubsystem wheelOfFortuneSubsystem;
  public final Joystick driverStationJoy;
  // public final Joystick climberJoy;
  Joystick servoJoy;
  public DrivetrainSubsystem drivetrainSubsystem;
  public IntakeSubsystem intakeSubsystem;
  public NeckSubsystem neckSubsystem;
  public ShooterSubsystem shooterSubsystem;
  public HopperSubsystem hopperSubsystem;
  public HoodSubsystem hoodSubsystem;
  public ClimberSubsystem climberSubsystem;
  public ShiftingGearsSubsystem shiftingGearsSubsystem;
  public Limelight limelight;
  // private DoubleButton toggleClimb;

  public RobotContainer() {
    driverStationJoy = new Joystick(OIConstants.DRIVER_STATION_JOY);
    // climberJoy = new Joystick(OIConstants.CLIMBER_JOY);

    drivetrainSubsystem = new DrivetrainSubsystem();
    intakeSubsystem = new IntakeSubsystem();
    neckSubsystem = new NeckSubsystem();
    shooterSubsystem = new ShooterSubsystem();
    hopperSubsystem = new HopperSubsystem();
    hoodSubsystem = new HoodSubsystem();
    shiftingGearsSubsystem = new ShiftingGearsSubsystem();
    climberSubsystem = new ClimberSubsystem();
    limelight = new Limelight();


    //enable this to drive!!
    switch (drivetrainSubsystem.getDriveMode()) {
      case TANK:
        drivetrainSubsystem.setDefaultCommand(new RunCommand(() -> drivetrainSubsystem.tankDrive(getLeftY(), getRightY()), drivetrainSubsystem));
        break;
      case CHEEZY:
        drivetrainSubsystem.setDefaultCommand(new RunCommand(() -> drivetrainSubsystem.cheezyDrive(getLeftY(), getRightX()), drivetrainSubsystem));
        break;
      case ARCADE:
        drivetrainSubsystem.setDefaultCommand(new RunCommand(() -> drivetrainSubsystem.arcadeDrive(getLeftY(), getRightX()), drivetrainSubsystem));
        break;
      default:
        // tank 
        drivetrainSubsystem.setDefaultCommand(new RunCommand(() -> drivetrainSubsystem.tankDrive(getLeftY(), getRightY()), drivetrainSubsystem));
        break;
    }    
    configureButtonBindings();
  }
  
  private void configureButtonBindings() {
    // bottom row
    setJoystickButtonWhileHeld(driverStationJoy, 1, new AllInCommand(intakeSubsystem, hopperSubsystem, neckSubsystem));
    setJoystickButtonWhenPressed(driverStationJoy, 2, new ToggleShooterCommand(shooterSubsystem));
    setJoystickButtonWhileHeld(driverStationJoy, 3, new NeckClearCommand(neckSubsystem)); //Change from while held to when pressed, just have to figure out the correct time value
    setJoystickButtonWhileHeld(driverStationJoy, 4, new HoodInCommand(hoodSubsystem));
    
    setJoystickButtonWhileHeld(driverStationJoy, 5, new ClimberDownCommand(climberSubsystem));

    // top row
    setJoystickButtonWhileHeld(driverStationJoy, 6, new AllOutCommand(intakeSubsystem, hopperSubsystem, neckSubsystem));
    setJoystickButtonWhileHeld(driverStationJoy, 7, new IntakeInCommand(intakeSubsystem));
    setJoystickButtonWhenPressed(driverStationJoy, 8, new ToggleBrakeCommand(climberSubsystem));
    setJoystickButtonWhileHeld(driverStationJoy, 9, new HoodOutCommand(hoodSubsystem));
    
    setJoystickButtonWhileHeld(driverStationJoy, 10, new ClimberUpCommand(climberSubsystem));

    //joystick buttons
    setJoystickButtonWhenPressed(driverStationJoy, 11, new ToggleShiftingCommand(shiftingGearsSubsystem));
    setJoystickButtonWhenPressed(driverStationJoy, 12, new ToggleIntakeCommand(intakeSubsystem));

    // climber joystick buttons
    // setJoystickButtonWhileHeld(climberJoy, 10, new ClimberDownCommand(climberSubsystem));
    // setJoystickButtonWhileHeld(climberJoy, 11, new ClimberUpCommand(climberSubsystem));
    // setJoystickButtonWhenPressed(climberJoy, 1, new ToggleBrakeCommand(climberSubsystem));
  }

  public double getServoThrottle() {
    return servoJoy.getZ();
  }

  public double getLeftY() {
    return -driverStationJoy.getRawAxis(0);
  }

  public double getLeftX() {
    return driverStationJoy.getRawAxis(1);
  }

  public double getRightY() {
    return -driverStationJoy.getRawAxis(2);
  }

  public double getRightX() {
    return -driverStationJoy.getRawAxis(3);
  }

  public double getLeftThrottle() {
    return (driverStationJoy.getThrottle() + 1) / 2;
  }

  private void setJoystickButtonWhenPressed(Joystick joystick, int button, CommandBase command) {
    new JoystickButton(joystick, button).whenPressed(command);
  }

  private void setJoystickButtonWhileHeld(Joystick joystick, int button, CommandBase command) {
    new JoystickButton(joystick, button).whileHeld(command);
  }

  public Command getAutonomousCommand(){
    switch ((Robot.AutoModes) Robot.autoChooser.getSelected()) {
			case FIRE_FORWARD:
				return new FireThreeThenForwardCommandGroup(0.5, shooterSubsystem, intakeSubsystem, hopperSubsystem, neckSubsystem, drivetrainSubsystem, hoodSubsystem);
			case FORWARD_FIRE:
        return new DriveForwardThenBumpFireCommandGroup(0.5, drivetrainSubsystem, shooterSubsystem, intakeSubsystem, hopperSubsystem, neckSubsystem, hoodSubsystem);
			default:
        return new DriveForwardThenBumpFireCommandGroup(0.5, drivetrainSubsystem, shooterSubsystem, intakeSubsystem, hopperSubsystem, neckSubsystem, hoodSubsystem);
		}
  }
}
