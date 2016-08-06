package org.usfirst.frc.team3641.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;

public class Wheel
{
	private double radians = 0;
	private double power = 0;
	private double current = 0;
	private double errorRefresh = 0;
	private double lastError = 0;
	
	public CANTalon rotationTalon;
	public CANTalon driveTalon;
		
	public double getPower()
	{
		return power;
	}
	public void setPower(double Power)
	{
		power = Power;
	}
	
	public void set(double[] values)
	{
		radians = values[0];
		power = values[1];
	}
	
	public void Go()
	{
		current = encoderToRadians(rotationTalon.getEncPosition());
		double error = calcError(radians,current);
		double output = PID.loop(error, errorRefresh, lastError, Constants.SWERVE_DEADBAND, Constants.SWERVE_kP, Constants.SWERVE_kI);
		errorRefresh += error;
		lastError = error;
		
		rotationTalon.set(output);
		driveTalon.set(power);
	}
	
	Wheel(int RotationTalon, int DriveTalon)
	{
		rotationTalon = new CANTalon(RotationTalon);
		rotationTalon.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Absolute);
		driveTalon = new CANTalon(DriveTalon);
		radians = 0;
		power = 0;
	}

	public static double calcError(double target, double current)
	{
		double error;
		double ccw;
		double cw;
		if(target == current)
		{
			error = 0;
		}
		else
		{
			ccw = target - current;
			if(ccw < 0)
			{
				ccw += Math.PI*2;
			}
			cw = Math.PI*2 - (target - current);
			if(cw >= Math.PI*2)
			{
				 cw -= Math.PI*2;
			}
			
			if(ccw < cw)
			{
				error = ccw;
			}
			else
			{
				error = -cw;
			}
		}
		return error;
	}
	
	public static double encoderToRadians(double ticks)
	{
		double radians = ticks * Constants.ENCODER_TICKS_PER_RADIAN;
		while(radians >= Math.PI*2)
		{
			radians -= Math.PI*2;
		}
		while(radians < 0)
		{
			radians += Math.PI*2;
		}
		return radians;
	}

}
