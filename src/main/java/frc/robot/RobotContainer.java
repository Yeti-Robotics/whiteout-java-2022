/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.OIConstants;
import frc.robot.autoRoutines.ShootCommandGroup;
import frc.robot.autoRoutines.ShootWithTurnCommandGroup;
import frc.robot.commands.AllInCommand;
import frc.robot.commands.AllOutCommand;
import frc.robot.commands.climbing.ClimberDownCommand;
import frc.robot.commands.climbing.ClimberUpCommand;
import frc.robot.commands.drivetrain.DriveForDistanceCommand;
import frc.robot.commands.drivetrain.ResetDriveEncodersCommand;
import frc.robot.commands.drivetrain.StopDriveCommand;
import frc.robot.commands.drivetrain.TurnNoPIDCommand;
import frc.robot.commands.drivetrain.TurnToTargetPIDCommand;
import frc.robot.commands.groups.AimToShootCommandGroup;
import frc.robot.commands.hood.MoveHoodCommand;
import frc.robot.commands.hood.ResetHoodEncoderCommand;
import frc.robot.commands.hopper.HopperOutCommand;
import frc.robot.commands.hopper.StopFunnelCommand;
import frc.robot.commands.intake.*;
import frc.robot.commands.neck.MoveDownNeckCommand;
import frc.robot.commands.neck.StopNeckCommand;
import frc.robot.commands.neck.TowerClearCommand;
import frc.robot.commands.shifting.ToggleShiftingCommand;
import frc.robot.commands.neck.MoveUpNeckCommand;
import frc.robot.commands.hopper.HopperInCommand;
import frc.robot.commands.shooting.*;
import frc.robot.subsystems.*;
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
    // setJoystickButtonWhileHeld(driverStationJoy, 1, new AllInCommand(intakeSubsystem, hopperSubsystem, neckSubsystem));
    setJoystickButtonWhenPressed(driverStationJoy, 1, new TurnToTargetPIDCommand(drivetrainSubsystem));
    //setJoystickButtonWhenPressed(driverStationJoy, 1, new ResetHoodEncoderCommand(hoodSubsystem)); // Allison wanted this made, we're not sure why
    setJoystickButtonWhenPressed(driverStationJoy, 2, new ToggleIntakeCommand(intakeSubsystem));
    setJoystickButtonWhileHeld(driverStationJoy, 3, new MoveHoodCommand(hoodSubsystem, -0.1)); // hood out
    setJoystickButtonWhenPressed(driverStationJoy, 4, new AimToShootCommandGroup(drivetrainSubsystem, hoodSubsystem)); // AimToShoot not written yet
    // setJoystickButtonWhenPressed(driverStationJoy, 5, new ClimbDownCommand(climberSubsystem));
    setJoystickButtonWhileHeld(driverStationJoy, 6, new AllOutCommand(intakeSubsystem, hopperSubsystem, neckSubsystem));
    setJoystickButtonWhileHeld(driverStationJoy, 7, new TowerClearCommand(neckSubsystem)); //Change from while held to when pressed, just have to figure out the correct time value
    setJoystickButtonWhileHeld(driverStationJoy, 8, new MoveHoodCommand(hoodSubsystem, 0.1)); // hood in
    setJoystickButtonWhenPressed(driverStationJoy, 9, new ToggleShooterCommand(shooterSubsystem));
    // setJoystickButtonWhenPressed(driverStationJoy, 10, new ClimbUpCommand(climberSubsystem));
    setJoystickButtonWhenPressed(driverStationJoy, 11, new ToggleShiftingCommand(shiftingGearsSubsystem));
  }

  public double getServoThrottle() {
    return servoJoy.getZ();
  }

  public double getLeftY() {
    // if(driverStationJoy.getRawAxis(0) >= .1 || driverStationJoy.getRawAxis(0) <= -.1){
      return -driverStationJoy.getRawAxis(0);
    // } else {
    //   return 0;
    // }
  }

  public double getLeftX() {
    return driverStationJoy.getRawAxis(1);
  }

  public double getRightY() {

    // if(driverStationJoy.getRawAxis(2) >= .1 || driverStationJoy.getRawAxis(2) <= -.1){
      return -driverStationJoy.getRawAxis(2);
    // } else {
    //   return 0;
    // }
  }

  public double getRightX() {
    return driverStationJoy.getRawAxis(3);
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
}
