

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;


/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 * 
 */
public final class Constants {

    public static final class DriveConstants{
        //drive motor ports
        public static final int LEFT_FALCON_1 = 15;
        public static final int LEFT_FALCON_2 = 14;
        public static final int RIGHT_FALCON_1 = 0;
        public static final int RIGHT_FALCON_2 = 1;

        //shifter solenoid ports
        public static final int[] SHIFTER_SOLENOID = {0,2};

        //pid drive constants
        public static final double kTurnP = 1;
        public static final double kTurnI = 0;
        public static final double kTurnD = 0;

        //drive calc constants
        public static final double DRIVE_ENCODER_RESOLUTION = 2048.0;
        public static final double DRIVE_WHEEL_DIAMETER = 5.6875; //inches
        public static final double DISTANCE_PER_PULSE = (DRIVE_WHEEL_DIAMETER * Math.PI) / DRIVE_ENCODER_RESOLUTION; //inches

        //drivetrain gear ratios
        public static final double HIGH_GEAR_RATIO = 9.0/1.0;
        public static final double LOW_GEAR_RATIO = 19.6/1.0;
    }

    public static final class IntakeConstants{
        //intake motor port
        public static final int INTAKE_VICTOR = 5;

        //intake piston solenoid ports
        public static final int[] INTAKE_PISTONS_SOLENOID = {1, 3};

        //intake motor speed
        public static final double ROLL_IN_SPEED = 1.0;
        public static final double ROLL_OUT_SPEED = -1.0;
    }

    public static final class HopperConstants {
        //hopper motor ports
        public static final int HOPPER_VICTOR = 4;

        //hopper motor speed
        public static final double FUNNEL_IN_SPEED = 0.75;
        public static final double FUNNEL_OUT_SPEED = -0.75;
    }

    public static final class NeckConstants{
        //neck motor ports
        public static final int NECK_BELT_VICTOR = 8;
        public static final int NECK_ROLLER_VICTOR = 11;

        //beam break sensor dio ports
        public static final int NECK_BEAM_BREAK = 2;

        //neck motor speed
        public static final double NECK_UP_SPEED = 0.75;
        public static final double NECK_DOWN_SPEED = -0.5;
    }

    public static final class ShooterConstants{
        //shooter motor ports
        public static final int SHOOTER_LEFT_TALON = 9; //left
        public static final int SHOOTER_RIGHT_TALON = 10; //right

        //shooter motor speeds    
        public static final double SHOOT_1_SPEED = .9;
        public static final double SHOOT_2_SPEED = .9;
        public static final double REVERSE_SHOOT_1_SPEED = -0.5;
        public static final double REVERSE_SHOOT_2_SPEED = -0.5;

        //shooter rpm calc constants
        public static final double PULLEY_RATIO = 12.0 / 48.0;
        public static final double ENCODER_TIME_CONVERSION = 600.0; // 100 ms per minute
        public static final double ENCODER_RESOLUTION = 12.0;
        public static final double QUAD_FACTOR = 4.0; // quadrature encoder factor
        public static final double MAX_RPM = 1425.0;
    }

    public static final class HoodConstants{
        //hood motor port
        public static final int HOOD_SPARK = 7;

        //hood beam break dio
        public static final int HOOD_BEAM_BREAK = 0;

        //hood angle toggle
        public static final double BUMP_FIRE_ANGLE = 4.0;
        public static final double INIT_FIRING_ANGLE = 45.0; //from front ideally, works from behind as well

        //hood calc constants
        public static final double COUNTS_PER_REVOLUTION = 42.0;
        public static final double HOOD_GEAR_RATIO = 340.0;//510.0/1.0;
        public static final double COUNTS_PER_DEGREE = (HOOD_GEAR_RATIO) / 360.0;
        public static final double HOOD_ANGLE_TOLERANCE = .25;
        public static final double MAX_HOOD_ANGLE = 45.0;
    }

    public static final class ClimberConstants{
        //climber motor constants
        public static final int CLIMBER_LEFT_VICTOR = 3; //left
        public static final int CLIMBER_RIGHT_TALON = 2; //right

        //climber motor speed
        public static final double CLIMBER_SPEED = 0.5;

        //climber current limiting constants
        public static final int CLIMBER_CONT_CURRENT_LIMIT = 15;
        public static final int CLIMBER_PEAK_CURRENT_LIMIT = 25;
        public static final int CLIMBER_PEAK_CURRENT_DURATION = 200;

        //climber limit switch ports
        public static final int TOP_LIMIT_SWITCH = 1;
        public static final int BOTTOM_LIMIT_SWITCH = 0;
    }

    public static final class OIConstants{
        //joystick ports (only 2 axis for each joystick)
        public static final int RIGHT_JOYSTICK = 1;
        public static final int LEFT_JOYSTICK = 0;
        public static final int SECONDARY_JOYSTICK = 2;

        //driverstation port (the whole thing is one joystick, we read from four axis)
        public static final int DRIVER_STATION_JOY = 0;
    }

    public static final class CalcConstants{
        // distance calc constants
        public static final double KNOWN_DISTANCE = 161.3; //inches
        public static final int PIXEL_WIDTH_KNOWN = 65; //pixels
        public static final double KNOWN_TAPE_BOUND_WIDTH = 39.25; //inches
        public static final double FOCAL_LENGTH = ( KNOWN_DISTANCE * PIXEL_WIDTH_KNOWN) / KNOWN_TAPE_BOUND_WIDTH;

        //trajectory constants
        public static final int SHOOTER_HEIGHT = 23; // inches
        public static final double GRAVITY = 386.09; // inches/ sec ^2
    }
}
