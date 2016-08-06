package org.usfirst.frc.team3641.robot;

public class PID
{
	public static PID instance;
	
	public static PID getInstance()
	{
		if(instance == null)
		{
			instance = new PID();
		}
		return instance;
	}
	
	public static double loop(double error, double errorRefresh, double lastError, double deadband, double kP, double kI)
	{
		if((error>=0 && lastError>=0) || (error<=0 && lastError<=0) && (Math.abs(error)<deadband))
		{
			errorRefresh += error;
		}
		else
		{
			errorRefresh = 0;
		}
		return (error * kP) + (errorRefresh * kI);
	}
	
}
