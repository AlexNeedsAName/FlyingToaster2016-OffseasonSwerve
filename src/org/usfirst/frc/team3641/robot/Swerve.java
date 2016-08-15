package org.usfirst.frc.team3641.robot;
import java.lang.Math;

public class Swerve
{
	public static Swerve instance;
	public static Wheel[] Wheels = new Wheel[Constants.NUMBER_OF_WHEELS];
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
		Wheels[0] = new Wheel(Constants.ROTATE_WHEEL_I,Constants.DRIVE_WHEEL_I, 1, Constants.NUMBER_OF_WHEELS);
		Wheels[1] = new Wheel(Constants.ROTATE_WHEEL_II,Constants.DRIVE_WHEEL_II, 2, Constants.NUMBER_OF_WHEELS);
		Wheels[2] = new Wheel(Constants.ROTATE_WHEEL_III,Constants.DRIVE_WHEEL_III, 3, Constants.NUMBER_OF_WHEELS);
		Wheels[3] = new Wheel(Constants.ROTATE_WHEEL_IV,Constants.DRIVE_WHEEL_IV, 4, Constants.NUMBER_OF_WHEELS);
	}
	
	public static void Drive(double leftX, double leftY, double rightX)
	{	
		double[] inputs = fixInputOCD(leftX, leftY, rightX);			//Corrects inputs having a greater distance from the origin than 1,
		leftX = inputs[0];												//and stops wheels from spinning when the stick is released.
		leftY = inputs[1];
		rightX = inputs[2];
		
		double max = 0;
		for(int i = 0; i<Constants.NUMBER_OF_WHEELS; i++)
		{
			Wheels[i].set(leftX,leftY,rightX);							//Adds the vectors for starving and rotation, then converts to polar coords
			if(Wheels[i].getPower() > max) max = Wheels[i].getPower();	//Finds the maximum power so we can limit it
		}
		if(max > 1)
		{
			for(int i = 0; i<Constants.NUMBER_OF_WHEELS; i++)
			{
				Wheels[i].capPower(max);								//Slows all wheels so the none are over one
			}
		}
		for(int i = 0; i<Constants.NUMBER_OF_WHEELS; i++)
		{
			Wheels[i].Go();												//PIDs to the correct Rotation and outputs the power to the drive wheel
		}
				
	}
	public static double[] fixInputOCD(double lx, double ly, double rx)
	{
		//Fix my OCD about controllers returning values greater than 1
		input = helpMePolarize(lx, ly);
		if(input[1] > 1)
		{
			lx = Math.sin(-input[0]+Math.PI/2);
			ly = Math.cos(-input[0]+Math.PI/2);
		}
		//Fixing my OCD about the wheels spinning when controller is released
		else if(input[1] < Constants.IGNORE_INPUT_UNDER)
		{
			lx = 0.0001*Math.sin(-lastInput[0]+Math.PI/2);	//This should just leave the wheels where they are, but supply no power
			ly = 0.0001*Math.cos(-lastInput[0]+Math.PI/2);
		}
		lastInput = input;
		
		if(rx > 1) rx=1;
		else if(rx < -1) rx = -1;
		
		double[] values = {lx, ly, rx};
		return values;
		
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
	
	public static void sendPositions()
	{
		//UDP.driverStation.sendData(":SWERVE:R="+ Sensor.gyro.getAngle() +";wIR="+ wI.getRotation() +";wIP="+ wI.getPower() +";wIIR="+ wII.getRotation() +";wIIP="+ wII.getPower() +";wIIIR="+ wIII.getRotation() +";wIIIP="+ wIII.getPower() +";wIVR="+ wIV.getRotation() +";wIVP="+ wIV.getPower());
	}
}
