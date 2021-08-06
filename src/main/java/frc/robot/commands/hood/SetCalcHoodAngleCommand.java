// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.hood;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.HoodConstants;
import frc.robot.subsystems.HoodSubsystem;
import frc.robot.utils.Limelight;

public class SetCalcHoodAngleCommand extends CommandBase {
    private final HoodSubsystem hoodSubsystem;
    private double encoderGoal;
    private double power;
    
    // Sets the hood to the angle calculated the Limelight 
    public SetCalcHoodAngleCommand(HoodSubsystem hoodSubsystem, double power) {
        this.hoodSubsystem = hoodSubsystem;
        this.power = Math.abs(power);
        addRequirements(hoodSubsystem);
    }

    @Override
    public void initialize() {
        encoderGoal = hoodSubsystem.hoodEncoderFromAngle(hoodSubsystem.calcHoodAngle(Limelight.getHorDistance()));
        if (encoderGoal < hoodSubsystem.getEncoder()){
            power = -power;
        }
    }

    @Override
    public void execute() {
        System.out.println("encoder goal: "+ encoderGoal + ", actual enc value: " + hoodSubsystem.getEncoder());
        hoodSubsystem.moveHood(power);
    }

    @Override
    public boolean isFinished() {
        return Math.abs(hoodSubsystem.calcHoodAngle(Limelight.getHorDistance()) - hoodSubsystem.hoodAngleFromEncoder(hoodSubsystem.getEncoder())) <= HoodConstants.HOOD_ANGLE_TOLERANCE;
    }

    @Override
    public void end(boolean interrupted) {
        hoodSubsystem.stopHood();
    }
}