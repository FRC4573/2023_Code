package frc.robot;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * It is advised to statically import this class (or one of its inner classes) 
 * wherever the constants are needed, to reduce verbosity.
 */
public final class Constants {
    // Physical Robot Constants //
    public static final double WHEEL_DIAMETER = Units.inchesToMeters(6); // Convert from inches to meters
    public static final double WHEEL_CIRCUMFERENCE = Math.PI * WHEEL_DIAMETER; // Measured in meters
	public static final double TRACK_WIDTH = Units.inchesToMeters(21.75); // Distance between centers of right and left wheels on robot (in meters)

    // Controller Input Axes //
    public static final int ARM_USB_PORT_ID = 0; // USB port that the controller is plugged in to
    public static final int DRIVE_USB_PORT_ID = 1;
    public static final double m_deadZone = 0.2;
    public static final double m_forward = 1.00;
    public static final double m_rotate = 0.75;



    // Victor PWM Ports //
    public static final int LEFT_FRONT_DRIVE_MOTOR_ID = 0;
    public static final int RIGHT_FRONT_DRIVE_MOTOR_ID = 3;
    public static final int LEFT_REAR_DRIVE_MOTOR_ID = 1;
    public static final int RIGHT_REAR_DRIVE_MOTOR_ID = 2;
    
    // PCM (Pneumatics Control Module) Channels //
    public static final int GRABBER_SOLENOID_ID_1 = 0;
    public static final int GRABBER_SOLENOID_ID_2 = 1;
    public static final int EXTENDER_SOLENOID_ID_1 = 2;
    public static final int EXTENDER_SOLENOID_ID_2 = 3;
    
    // DIO (Digital Input/Output) Channels //
    public static final int RIGHT_ENCODER_CHANNEL_A = 0;
    public static final int RIGHT_ENCODER_CHANNEL_B = 1;
    public static final int LEFT_ENCODER_CHANNEL_A = 0;
    public static final int LEFT_ENCODER_CHANNEL_B = 1;

    // Drivetrain Constants //
    public static final boolean DRIVE_INVERT_LEFT = false; // Whether to reverse the left drivetrain motors or not
    public static final boolean DRIVE_INVERT_RIGHT = true; // Whether to reverse the right drivetrain motors or not
    public static final double GYRO_TURN_KP = 0.007; // P (Proportional) constant of a PID loop
    public static final double BEAM_BALANACED_DRIVE_KP = 0.015; // P (Proportional) constant of a PID loop
    public static final double BEAM_BALANCED_GOAL_DEGREES = 0;
    public static final double BEAM_BALANCED_ANGLE_TRESHOLD_DEGREES = 1;
    public static final double BACKWARDS_BALANCING_EXTRA_POWER_MULTIPLIER = 1.35;
    public static final double DRIVE_TURNING_THRESHOLD_DEGREES = 3;
    public static final int LEFT_ENCODER_COUNTS_PER_REV = 1440; // The number of encoder counts equal to one full revolution of the encoder 
    public static final int RIGHT_ENCODER_COUNTS_PER_REV = 1440; // The number of encoder counts equal to one full revolution of the encoder 

    //Camera Servers //
    UsbCamera camera1 = CameraServer.startAutomaticCapture(0);
    UsbCamera camera2 = CameraServer.startAutomaticCapture(1);
    UsbCamera camera3 = CameraServer.startAutomaticCapture(2);

}