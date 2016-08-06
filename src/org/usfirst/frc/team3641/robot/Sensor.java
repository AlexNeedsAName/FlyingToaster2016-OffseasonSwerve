package org.usfirst.frc.team3641.robot;

import edu.wpi.first.wpilibj.SPI;
import com.kauailabs.navx.frc.AHRS;

public class Sensor
{
	public static Sensor instance;
	public static AHRS gyro;
	
	public static Sensor getInstance()
	{
		if(instance == null)
		{
			instance = new Sensor();
		}
		return instance;
	}
	
	public Sensor()
	{
		gyro = new AHRS(SPI.Port.kMXP);
	}

}
