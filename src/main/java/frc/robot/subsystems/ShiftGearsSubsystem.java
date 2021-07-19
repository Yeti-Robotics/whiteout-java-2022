package frc.robot.subsystems;


import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

public class ShiftGearsSubsystem extends SubsystemBase {
    private DoubleSolenoid shifter;
    public enum ShiftStatus{
        HIGH, LOW
    }
    public static ShiftStatus shiftStatus;

    public ShiftGearsSubsystem() {
        shifter = new DoubleSolenoid(DriveConstants.SHIFTER_SOLENOID[0], DriveConstants.SHIFTER_SOLENOID[1]);
        shiftStatus = ShiftStatus.LOW;
    }

    public void shiftUp() {
        shifter.set(DoubleSolenoid.Value.kForward);
        shiftStatus = ShiftStatus.HIGH;
    }

    //Shifts the drive train into low gear
    public void shiftDown() {
        shifter.set(DoubleSolenoid.Value.kReverse);
        shiftStatus = ShiftStatus.LOW;
    }



    //Finds out what position the shifter is currently in
    public static ShiftStatus getShifterPosition() {
        return shiftStatus;
    }

}

