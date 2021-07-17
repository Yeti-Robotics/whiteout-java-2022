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
import frc.robot.autoRoutines.ShootCommandGroup;
import frc.robot.autoRoutines.ShootWithTurnCommandGroup;
import frc.robot.commands.climbing.ClimbDownCommand;
import frc.robot.commands.climbing.ClimbUpCommand;
import frc.robot.commands.drivetrain.DriveForDistanceCommand;
import frc.robot.commands.drivetrain.ResetEncodersCommand;
import frc.robot.commands.drivetrain.StopDriveCommand;
import frc.robot.commands.drivetrain.TurnNoPIDCommand;
import frc.robot.commands.hopper.HopperOutCommand;
import frc.robot.commands.hopper.StopFunnelCommand;
import frc.robot.commands.intake.*;
import frc.robot.commands.neck.MoveDownNeckCommand;
import frc.robot.commands.neck.StopNeckCommand;
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
  public ClimberSubsystem climberSubsystem;
  public ShiftGearsSubsystem shiftGearsSubsystem;
  public Limelight limelight;
  // private DoubleButton toggleClimb;

  public RobotContainer() {
    driverStationJoy = new Joystick(Constants.DRIVER_STATION_JOY);
    servoJoy = new Joystick(1);

    drivetrainSubsystem = new DrivetrainSubsystem();
    intakeSubsystem = new IntakeSubsystem();
    neckSubsystem = new NeckSubsystem();
    shooterSubsystem = new ShooterSubsystem();
    hopperSubsystem = new HopperSubsystem();
    shiftGearsSubsystem = new ShiftGearsSubsystem();
    climberSubsystem = new ClimberSubsystem();
    limelight = new Limelight();


    //enable this to drive!!
    drivetrainSubsystem.setDefaultCommand(new RunCommand(() -> drivetrainSubsystem.drive(getLeftY(), getRightY()), drivetrainSubsystem));
////
//    shooterSubsystem.setDefaultCommand(new RunCommand(() -> shooterSubsystem.moveServo(getLeftY() ), shooterSubsystem));

//    shooterSubsystem.setDefaultCommand(
//        new RunCommand(() -> shooterSubsystem.setHoodAngle(getLeftThrottle() * 180), shooterSubsystem));
    configureButtonBindings();
  }

  private void configureButtonBindings() {

    //Intake Pistons
    setJoystickButtonWhenPressed(driverStationJoy, 6, new ToggleIntakeCommand(intakeSubsystem));

    //Intake, Hopper, Neck (no shooter)
    setJoystickButtonWhileHeld(driverStationJoy, 7, new ParallelCommandGroup(
            new IntakeInCommand(intakeSubsystem),
            new MoveUpNeckCommand(neckSubsystem),
            new HopperInCommand(hopperSubsystem)));

    //Runs intake and hopper
    setJoystickButtonWhileHeld(driverStationJoy, 8, new ParallelCommandGroup(
      new IntakeInCommand(intakeSubsystem),
      new HopperInCommand(hopperSubsystem)));
      
    //shooter toggle
    setJoystickButtonWhenPressed(driverStationJoy, 9, new ToggleShooterCommand(shooterSubsystem));

    //Intake, Hopper, Tower, Shooter (all)
    setJoystickButtonWhileHeld(driverStationJoy, 10, new ParallelCommandGroup(
            new IntakeInCommand(intakeSubsystem),
            new HopperInCommand(hopperSubsystem),
            new MoveUpNeckCommand(neckSubsystem),
            new SpinWithStopCommand(shooterSubsystem))
    );

    //runs everything but shooter backwards
    setJoystickButtonWhileHeld(driverStationJoy, 1, new ParallelCommandGroup(
      new IntakeOutCommand(intakeSubsystem),
      new HopperOutCommand(hopperSubsystem),
      new MoveDownNeckCommand(neckSubsystem)
    ));
    
    //Climber Up
//    setJoystickButtonWhileHeld(driverStationJoy, 2, new ClimbUpCommand(climberSubsystem));

    //Climbs Down
//    setJoystickButtonWhileHeld(driverStationJoy, 3, new ClimbDownCommand(climberSubsystem));

    //intake in
//    setJoystickButtonWhileHeld(driverStationJoy, 4, new IntakeInCommand(intakeSubsystem));

    setJoystickButtonWhileHeld(driverStationJoy, 2, new TestHoodMotorCommand(shooterSubsystem, 0.05));
    setJoystickButtonWhileHeld(driverStationJoy, 3, new TestHoodMotorCommand(shooterSubsystem, -0.05));


    setJoystickButtonWhenPressed(driverStationJoy, 4, new ExtendIntakeCommand(intakeSubsystem));
    setJoystickButtonWhenPressed(driverStationJoy, 5, new TestServoCommand(shooterSubsystem));


    //intake reverse
//    setJoystickButtonWhileHeld(driverStationJoy, 5, new IntakeOutCommand(intakeSubsystem));

    //Shift Gears
    setJoystickButtonWhenPressed(driverStationJoy, 11, new ToggleShiftingCommand(shiftGearsSubsystem));

    //Turn to target No PID
    setJoystickButtonWhenPressed(driverStationJoy, 12, new TurnNoPIDCommand(drivetrainSubsystem, limelight));

  }

  public double getServoThrottle() {
    return servoJoy.getZ();
  }

  public double getLeftY() {
    if(driverStationJoy.getRawAxis(1) >= .1 || driverStationJoy.getRawAxis(1) <= -.1){
      return driverStationJoy.getRawAxis(1);
    }else{
      return 0;
    }

    // return leftJoy.getRawAxis(RobotMap.DRIVERSTATION_LEFT_Y_AXIS);
  }

  // Gets the Y direction of the left drive joystick
  public double getLeftX() {
    return driverStationJoy.getX();
  }

  // Gets the Y direction of the right drive joystick
  public double getRightY() {

    if(driverStationJoy.getRawAxis(3) >= .1 || driverStationJoy.getRawAxis(3) <= -.1){
      return driverStationJoy.getRawAxis(3);
    }else{
      return 0;
    }
    // return rightJoy.getRawAxis(RobotMap.DRIVERSTATION_RIGHT_Y_AXIS);
  }

  // Gets the X direction of the right drive joystick
  public double getRightX() {
    return driverStationJoy.getX();
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

  // playing with pressing both buttons to execute a command different from the
  // command bound to pressing a button by itself
  // private void setJoystickButtonsWhileHeld(Joystick joystick, int button1, int
  // button2, Command command){
  // JoystickButton ButtonOne = new JoystickButton(joystick, button1);
  // JoystickButton ButtonTwo = new JoystickButton(joystick, button2);
  // DoubleButton doubleButton = new DoubleButton(ButtonOne, ButtonTwo);
  // doubleButton.whileHeld(command);
  // }

  //Hood angle that doesn't work
//        setJoystickButtonWhileHeld(driverStationJoy, 3, new SetHoodAngleCommand(shooterSubsystem, 90));
}
