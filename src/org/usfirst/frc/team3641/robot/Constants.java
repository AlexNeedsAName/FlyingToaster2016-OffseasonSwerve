package org.usfirst.frc.team3641.robot;

public class Constants
{
	//Controllers:
	public static final int PS4_CONTROLLER = 0;
	
	//Talons:
	public static final int DRIVE_WHEEL_I		= 0;
	public static final int DRIVE_WHEEL_II		= 1;
	public static final int DRIVE_WHEEL_III		= 2;
	public static final int DRIVE_WHEEL_IV		= 3;
	public static final int ROTATE_WHEEL_I		= 4;
	public static final int ROTATE_WHEEL_II		= 5;
	public static final int ROTATE_WHEEL_III	= 6;
	public static final int ROTATE_WHEEL_IV		= 7;
	
	//Misc Conversions:
	public static final double ENCODER_TICKS_PER_RADIAN = 0.0174533; //360 ticks per rotation, probably not the final value?
	
	//PID
	public static final double SWERVE_DEADBAND	= .5;
	public static final double SWERVE_kP 		= 0;
	public static final double SWERVE_kI		= 0;
}
