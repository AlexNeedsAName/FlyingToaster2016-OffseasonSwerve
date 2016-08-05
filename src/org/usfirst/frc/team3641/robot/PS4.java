package org.usfirst.frc.team3641.robot;
import edu.wpi.first.wpilibj.Joystick;

public class PS4 extends Joystick
{
	
	public PS4(int port)
	{
		super(port);
	}
	
	//Sticks:
	public double getLeftX()
	{
		return getRawAxis(0);
	}
	public double getLeftY()
	{
		return getRawAxis(1);
	}
	public double getRightX()
	{
		return getRawAxis(2);
	}
	public double getRightY()
	{
		return getRawAxis(5);
	}
	public boolean getLeftStickButton()
	{
		return getRawButton(11);
	}
	public boolean getRightStickButton()
	{
		return getRawButton(12);
	}

	//Buttons:
	public boolean getSquareButton()
	{
		return getRawButton(1);
	}
	public boolean getXButton()
	{
		return getRawButton(2);
	}
	public boolean getCircleButton()
	{
		return getRawButton(3);
	}
	public boolean getTriangleButton()
	{
		return getRawButton(4);
	}

	//Triggers:
	public double getLeftTrigger()
	{
		double output = (getRawAxis(3) + .5) / 2;
		return output;
	}
	public boolean getLeftTriggerButton()
	{
		return getRawButton(7);
	}
	public double getRightTrigger()
	{
		double output = (getRawAxis(4) + .5) / 2;
		return output;
	}
	public boolean getRightTriggerButton()
	{
		return getRawButton(8);
	}
	
	//Bumpers:
	public boolean getLeftBumper()
	{
		return getRawButton(5);
	}
	public boolean getRightBumper()
	{
		return getRawButton(6);
	}
	
	
	//Misc:
	public boolean getShareButton()
	{
		return getRawButton(9);
	}
	public boolean getOptionsButton()
	{
		return getRawButton(10);
	}	
	public boolean getPlayStationButton()
	{
		return getRawButton(13);
	}
	
	//D-Pad:
	public boolean getDPadTopHalf()
	{
		if(getPOV(0) == 315 || getPOV(1) == 0 || getPOV(1) == 45)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public boolean getDPadBottomHalf()
	{
		if(getPOV(0) == 135 || getPOV(1) == 180 || getPOV(1) == 225)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public boolean getleftDPad()
	{
		if(getPOV(0) == 270)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public boolean getRightDPad()
	{
		if(getPOV(0) == 90)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public boolean getTopDPad()
	{
		if(getPOV(0) == 0)
		{
			return true;
		}
		else
		{
			return false;
		}	
	}
	public boolean getBottomDPad()
	{
		if(getPOV(0) == 180)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
}
