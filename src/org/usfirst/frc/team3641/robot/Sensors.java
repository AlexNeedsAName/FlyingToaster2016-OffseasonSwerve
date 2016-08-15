package org.usfirst.frc.team3641.robot;

import edu.wpi.first.wpilibj.SPI;
import com.kauailabs.navx.frc.AHRS;

public class Sensors
{
	public static Sensors instance;
	private static AHRS gyro;
	private static double angle;
	
	public static Sensors getInstance()
	{
		if(instance == null)
		{
			instance = new Sensors();
		}
		return instance;
	}
	
	public Sensors()
	{
		gyro = new AHRS(SPI.Port.kMXP);
	}
	
	public static void readSensors()
	{
		angle = gyro.getAngle();
	}
	
	public static double getAngle()
	{
		return angle;
	}

}
