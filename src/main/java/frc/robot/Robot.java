/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.autoRoutines.DriveForwardThenBumpFireCommandGroup;
import frc.robot.autoRoutines.FireThreeThenForwardCommandGroup;
import frc.robot.subsystems.HoodSubsystem.HoodStatus;
import frc.robot.utils.Limelight;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  public static RobotContainer robotContainer;

  public static NetworkTable networkTable;
  public static NetworkTable rootNetworkTable;
  public static SendableChooser autoChooser;

	public static enum AutoModes {
		FIRE_FORWARD, FORWARD_FIRE
	};
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    robotContainer = new RobotContainer();

    autoChooser = new SendableChooser();
    autoChooser.setDefaultOption("default is fire then move forward", AutoModes.FIRE_FORWARD);
    autoChooser.addOption("forward then bump fire", AutoModes.FORWARD_FIRE);
		autoChooser.addOption("fire then forward", AutoModes.FIRE_FORWARD);
    SmartDashboard.putData("Auto Chooser", autoChooser);
    SmartDashboard.putNumber("Hood Angle (degrees): ", robotContainer.hoodSubsystem.hoodAngleFromEncoder(robotContainer.hoodSubsystem.getEncoder()));
    SmartDashboard.putNumber("Flywheel RPM: ", robotContainer.shooterSubsystem.getFlywheelRPM());
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
    // System.out.println("upper: " + robotContainer.neckSubsystem.getUpperBeamBreak());
    // System.out.println("hood angle: " + robotContainer.hoodSubsystem.hoodAngleFromEncoder(robotContainer.hoodSubsystem.getEncoder()));
    // System.out.println("limelight gettx: " + Limelight.getTx());
    // System.out.println("lower beam break: " + robotContainer.neckSubsystem.getLowerBeamBreak());
    // System.out.println("velocity units: " + robotContainer.shooterSubsystem.getVelocityUnitsFromRPM(robotContainer.shooterSubsystem.getFlywheelRPM())+ "; right encoder value: " + robotContainer.shooterSubsystem.getRightEncoder() +"; flywheel rpm: " + robotContainer.shooterSubsystem.getFlywheelRPM() + "; error: " + (robotContainer.shooterSubsystem.getSetPoint() - robotContainer.shooterSubsystem.getFlywheelRPM()));
    // System.out.println("flywheel rpm: " + robotContainer.shooterSubsystem.getFlywheelRPM());
    System.out.println("hood encoder: " + robotContainer.hoodSubsystem.getEncoder());
    System.out.println("distance from center of target: " + Limelight.getTx());
    CommandScheduler.getInstance().run();
  }

 
  /**
   * This function is called once each time the robot enters Disabled mode. 
   */
  @Override
  public void disabledInit() {
    
    
  }

  @Override
  public void disabledPeriodic() {
    if (robotContainer.hoodSubsystem.getBeamBreak()) {
      robotContainer.hoodSubsystem.resetEncoder();
      robotContainer.hoodSubsystem.hoodStatus = HoodStatus.LOWER_LIMIT;
      // System.out.println("Hood beam break:" + robotContainer.hoodSubsystem.getBeamBreak());
    }
  }

  /**
   * This autonomous runs the autonomous command selected by your {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = robotContainer.getAutonomousCommand();

    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }
   
  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();

  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
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
