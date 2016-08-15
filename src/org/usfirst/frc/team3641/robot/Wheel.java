package org.usfirst.frc.team3641.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;

public class Wheel
{
	private double radians = 0;
	private double power = 0;
	private double mX, mY;
	private double current = 0;
	private double errorRefresh = 0;
	private double lastError = 0;
	
	public CANTalon rotationTalon;
	public CANTalon driveTalon;
		
	public double getPower()
	{
		return power;
	}
	public double getRotation()
	{
		return radians;
	}
	public void capPower(double max)
	{
		power = power/max;
	}
	
	public void set(double lx, double ly, double rx)
	{
		double[] values = Swerve.helpMePolarize(rx*mX/Constants.WIDTH+lx, -rx*mY/Constants.LENGTH+ly);
		radians = values[0];
		power = values[1];
	}
	
	public void Go()
	{
		if(Swerve.isFieldCentric())
		{
			radians = fixRadians(radians  - Sensors.getAngle()*Constants.DEGREE_TO_RADIAN);
		}
		current = fixRadians(rotationTalon.getEncPosition()*Constants.ENCODER_TICKS_PER_RADIAN);
		double error = calcError(radians,current);
		double output = PID.loop(error, errorRefresh, lastError, Constants.SWERVE_DEADBAND, Constants.SWERVE_kP, Constants.SWERVE_kI);
		errorRefresh += error;
		lastError = error;
		
		rotationTalon.set(output);
		driveTalon.set(power);
	}
	
	Wheel(int RotationTalon, int DriveTalon, int i, int n)
	{
		double theta = 0;
		if(n==4) theta = 5*Math.PI/4;
		
		mX = Math.sin(2*Math.PI*i/n + theta); //X Multiplier when rotating
		mY = Math.cos(2*Math.PI*i/n + theta); //Y Multiplier when rotating
		
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
	
	public static double fixRadians(double radians)
	{
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
