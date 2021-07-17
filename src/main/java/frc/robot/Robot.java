/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

import frc.robot.autoRoutines.ForwardThenShootCommandGroup;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.autoRoutines.TurnThenForwardThenShootCommandGroup;
import frc.robot.subsystems.*;
import frc.robot.subsystems.IntakeSubsystem.IntakeStatus;
import frc.robot.subsystems.ShooterSubsystem.ShooterStatus;
import frc.robot.utils.Limelight;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private ShooterSubsystem shooterSubsystem;

  private HopperSubsystem hopperSubsystem;

  private NeckSubsystem neckSubsystem;

  private DrivetrainSubsystem drivetrainSubsystem;

  private IntakeSubsystem intakeSubsystem;

  public static RobotContainer robotContainer;

  public static NetworkTable networkTable;
  public static NetworkTable rootNetworkTable;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    // final UsbCamera cam = CameraServer.getInstance().startAutomaticCapture(0);
    // cam.setVideoMode(VideoMode.PixelFormat.kMJPEG, 200, 150, 30);
    // cam.setBrightness(50);

    
    robotContainer = new RobotContainer();

    networkTable = NetworkTableInstance.getDefault().getTable("SmartDashboard");
    rootNetworkTable = NetworkTableInstance.getDefault().getTable("");

    UsbCamera driverCam = CameraServer.getInstance().startAutomaticCapture();
    driverCam.setVideoMode(VideoMode.PixelFormat.kMJPEG, 200, 150, 30);
    driverCam.setBrightness(50);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like diagnostics that you want ran during disabled, autonomous,
   * teleoperated and test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // SmartDashboard.putString("i'm working", "ok");
    // Runs the Scheduler. This is responsible for polling buttons, adding
    // newly-scheduled
    // commands, running already-scheduled commands, removing finished or
    // interrupted commands,
    // and running subsystem periodic() methods. This must be called from the
    // robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
    // System.out.println(WheelOfFortuneSubsystem.wheelColor);
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ty = table.getEntry("ty");
    NetworkTableEntry ta = table.getEntry("ta");
    NetworkTableEntry tlong = table.getEntry("tlong");

//read values periodically
    double x = tx.getDouble(0.0);
    double y = ty.getDouble(0.0);
    double area = ta.getDouble(0.0);
    double schlong = tlong.getDouble(0.0);

//post to smart dashboard periodically
    SmartDashboard.putNumber("LimelightX", x);
    SmartDashboard.putNumber("LimelightY", y);
    SmartDashboard.putNumber("LimelightArea", area);

    SmartDashboard.putNumber("schlong", schlong);

    SmartDashboard.putNumber("distance", Limelight.getDistance());

    SmartDashboard.putNumber("hor distance", Limelight.getHorDistance());

    //Secondary Controls Shuffleboard
    if(IntakeSubsystem.getIntakePosition() == IntakeStatus.DOWN) {
      SmartDashboard.putString("Intake Status", "DOWN");
    } else if(IntakeSubsystem.getIntakePosition() == IntakeStatus.UP) {
      SmartDashboard.putString("Intake Status", "UP");
    }

    if(ShooterSubsystem.getShooterStatus() == ShooterStatus.BACKWARDS) {
      SmartDashboard.putString("Flywheel Status", "REVERSE");
    } else if(ShooterSubsystem.getShooterStatus() == ShooterStatus.FORWARD) {
      SmartDashboard.putString("Flywheel Status", "FORWARD");
    } else {
      SmartDashboard.putString("Flywheel Status", "OFF");
    }
}

 
  /**
   * This function is called once each time the robot enters Disabled mode.z
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  /**
   * This autonomous runs the autonomous command selected by your {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
//m_autonomousCommand =  shootAutoCommandGroup;
    // schedule the autonomous command (example)

    m_autonomousCommand = new TurnThenForwardThenShootCommandGroup(robotContainer.shooterSubsystem, robotContainer.hopperSubsystem, robotContainer.neckSubsystem, robotContainer.drivetrainSubsystem, robotContainer.intakeSubsystem, robotContainer.limelight, robotContainer.shiftGearsSubsystem);

    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }
   
  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {

    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    // if (m_autonomousCommand != null) {
    //   m_autonomousCommand.cancel();
    // }

  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
