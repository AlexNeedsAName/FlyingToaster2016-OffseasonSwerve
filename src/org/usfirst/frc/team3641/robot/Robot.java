
package org.usfirst.frc.team3641.robot;

import edu.wpi.first.wpilibj.IterativeRobot;

public class Robot extends IterativeRobot {

	public void robotInit()
	{
		Teleop.getInstance();
		Swerve.getInstance();
		PID.getInstance();
		UDP.driverStation = new UDP(Constants.DRIVER_STATION_IP, Constants.DRIVER_STATION_PORT);
	}
	
	public void autonomousInit()
	{
		
	}

	public void autonomousPeriodic()
	{
		
	}

	public void teleopPeriodic()
	{
		Teleop.run();
	}
	
	public void testPeriodic()
	{
		
	}
	
}
