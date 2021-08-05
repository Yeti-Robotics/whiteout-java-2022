package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

public class IntakeSubsystem extends SubsystemBase {

    public enum IntakeStatus {
        DOWN, UP
    }
    public IntakeStatus intakeStatus;

    private VictorSPX intakeVictor;
    private DoubleSolenoid pistons;

    public IntakeSubsystem() {
        pistons = new DoubleSolenoid(IntakeConstants.INTAKE_PISTONS_SOLENOID[0], IntakeConstants.INTAKE_PISTONS_SOLENOID[1]);
        intakeVictor = new VictorSPX(IntakeConstants.INTAKE_VICTOR);
        intakeVictor.setInverted(true);

        intakeStatus = IntakeStatus.UP;
    }

    public void extend(){
        pistons.set(DoubleSolenoid.Value.kForward);
        intakeStatus = IntakeStatus.DOWN;
    }

    public void retract(){
        pistons.set(DoubleSolenoid.Value.kReverse);
        intakeStatus = IntakeStatus.UP;
    }

    public void rollIn(){
        intakeVictor.set(ControlMode.PercentOutput, IntakeConstants.ROLL_IN_SPEED);
    }

    public void rollOut(){
        intakeVictor.set(ControlMode.PercentOutput, IntakeConstants.ROLL_OUT_SPEED);
    }

    public void stopRoll(){
        intakeVictor.set(ControlMode.PercentOutput, 0);
    }

    public IntakeStatus getIntakePosition(){
        return intakeStatus;
    }

    public IntakeStatus getIntakeStatusDown(){
        return IntakeStatus.DOWN;
    }

    public IntakeStatus getIntakeStatusUp(){
        return IntakeStatus.UP;
    }
}

