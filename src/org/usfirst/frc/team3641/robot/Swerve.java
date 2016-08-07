package org.usfirst.frc.team3641.robot;
import java.lang.Math;

public class Swerve
{
	public static Swerve instance;
	public static Wheel wI, wII, wIII, wIV;
	private static boolean fieldCentric = true;
	private static double[] input, lastInput;

	public static Swerve getInstance()
	{
		if(instance == null)
		{
			instance = new Swerve();
		}
		return instance;
	}
	
	public Swerve()
	{
		wI = new Wheel(Constants.ROTATE_WHEEL_I,Constants.DRIVE_WHEEL_I);
		wII = new Wheel(Constants.ROTATE_WHEEL_I,Constants.DRIVE_WHEEL_I);
		wIII = new Wheel(Constants.ROTATE_WHEEL_I,Constants.DRIVE_WHEEL_I);
		wIV = new Wheel(Constants.ROTATE_WHEEL_I,Constants.DRIVE_WHEEL_I);
	}
	
	public static void Drive(double leftX, double leftY, double rightX)
	{
		//Fix my OCD about controllers returning values greater than 1
		input = helpMePolarize(leftX, leftY);
		if(input[1] > 1)
		{
			leftX = Math.sin(-input[0]+Math.PI/2);
			leftY = Math.cos(-input[0]+Math.PI/2);
		}
		//Fixing my OCD about the wheels spinning when controller is released
		else if(input[1] < .1)
		{
			leftX = 0.0001*Math.sin(-lastInput[0]+Math.PI/2);	//This should just leave the wheels where they are, but supply no power
			leftY = 0.0001*Math.cos(-lastInput[0]+Math.PI/2);
		}
		lastInput = input;
		
		if(rightX > 1) rightX=1;
		else if(rightX < -1) rightX = -1;
		
		//Calculate polar coordinates for each wheel
		wI.set(helpMePolarize(rightX+leftX, -rightX+leftY));
		wII.set(helpMePolarize(rightX+leftX, rightX+leftY));
		wIII.set(helpMePolarize(-rightX+leftX, rightX+leftY));
		wIV.set(helpMePolarize(-rightX+leftX, -rightX+leftY));
		
		//Adjust output so no motor is given a value greater than 1
		double max = findMax(wI.getPower(),wII.getPower(),wIII.getPower(),wIV.getPower());
		if(max > 1)
		{
			wI.setPower(wI.getPower()/max);
			wII.setPower(wII.getPower()/max);
			wIII.setPower(wIII.getPower()/max);
			wIV.setPower(wIV.getPower()/max);
		}
		
		wI.Go();
		wII.Go();
		wIII.Go();
		wIV.Go();
				
	}
	
	public static double[] helpMePolarize(double x, double y)
	{
		double radians;
		if(x==0)
		{
			if(y==0) radians = 0.0;
			else if(y<0) radians = Math.PI*3/2;
			else radians = Math.PI/2;
		}
		else if(y==0)
		{
			if(x>0) radians = 0.0;
			else radians = Math.PI;
		}
		else
		{
			radians = Math.atan(Math.abs(y/x));					//Quadrant I
			if(x<0 && y>0) radians = Math.PI - radians;			//Quadrant II
			else if(x<0 && y<0) radians += Math.PI;				//Quadrant III
			else if(x>0 && y<0) radians = Math.PI*2 - radians;	//Quadrant IV
		}
		double[] answer = {radians, Math.hypot(x, y)};
		return answer;
	}
		
	public static double findMax(double... values)
	{
		double max = Double.NEGATIVE_INFINITY;
		
		for (double d : values)	if (d > max) max = d;
		
		return max;
	}

	public static boolean isFieldCentric()
	{
		return fieldCentric;
	}
	
	public static void setFieldCentric()
	{
		fieldCentric = true;
	}
	
	public static void setRobotCentric()
	{
		fieldCentric = false;
	}
}
