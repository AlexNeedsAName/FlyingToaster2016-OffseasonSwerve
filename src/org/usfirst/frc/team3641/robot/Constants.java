package org.usfirst.frc.team3641.robot;

import java.lang.Math;

public class Constants
{
	//Robot Size Proportions (one of these must equal one)
	public static final double WIDTH	= Math.sqrt( 1 ); //From Wheel I to Wheel II, the front of the robot
	public static final double LENGTH	= Math.sqrt( 1 ); //From Wheel II to Wheel III, the side of the robot
	public static final int NUMBER_OF_WHEELS = 4;

	//IPs:
	public static final String DRIVER_STATION_IP = "10.36.41.5";	//I think this is the IP... I'll check on Tuesday :P TODO: Set IP
	public static final int DRIVER_STATION_PORT = 3641;
	
	//Controllers:
	public static final int PS4_CONTROLLER = 0;
	public static final double IGNORE_INPUT_UNDER = 0.075;
	
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
	public static final double ENCODER_TICKS_PER_RADIAN = 0.0;			//TODO: Set this once we know what encoder we're using
	public static final double DEGREE_TO_RADIAN			= 180/Math.PI;
	
	//PID
	public static final double SWERVE_DEADBAND	= .5;
	public static final double SWERVE_kP 		= 0;					//TODO: Tune this once we have a chassis
	public static final double SWERVE_kI		= 0;					//TODO: Tune this once we have a chassis
}
