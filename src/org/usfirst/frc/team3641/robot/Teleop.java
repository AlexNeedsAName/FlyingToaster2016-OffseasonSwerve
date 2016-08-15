package org.usfirst.frc.team3641.robot;

public class Teleop
{
	public static Teleop instance;
	public static PS4 dualshock;
	
	public static Teleop getInstance()
	{
		if(instance == null)
		{
			instance = new Teleop();
		}
		return instance;
	}
	
	private Teleop()
	{
		dualshock = new PS4(Constants.PS4_CONTROLLER);
	}
	
	public static void run()
	{
		Sensors.readSensors();
				
		if(dualshock.getLeftBumper()) Swerve.setRobotCentric();
		else if(dualshock.getRightBumper()) Swerve.setFieldCentric();
		
		Swerve.Drive(dualshock.getLeftX(), dualshock.getLeftY(), dualshock.getRightX());
		
		if(dualshock.getSquareButton()) Swerve.sendPositions();
	}
}
