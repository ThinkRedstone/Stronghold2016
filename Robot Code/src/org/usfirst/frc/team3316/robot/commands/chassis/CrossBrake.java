package org.usfirst.frc.team3316.robot.commands.chassis;

import java.util.Timer;

import org.usfirst.frc.team3316.robot.Robot;
import org.usfirst.frc.team3316.robot.commands.DBugCommand;
import org.usfirst.frc.team3316.robot.utils.Point;
import org.usfirst.frc.team3316.robot.utils.Utils;

public class CrossBrake extends DBugCommand
{
	private double speed, currentSpeed, timeout;
	private boolean reverse;
	private int counter = 0;

	public CrossBrake(boolean reverse) {
		requires(Robot.chassis);
		
		this.reverse = reverse;
	}
	
	protected void init()
	{
		speed = (reverse ? -1 : 1) * (double) config.get("chassis_CrossDefense_Back_Voltage");
		timeout = (double) config.get("chassis_CrossBack_Timeout");
		counter = 0;
	}

	protected void execute()
	{
		currentSpeed = Utils.scale(counter, new Point(0, 0), new Point(timeout / 20.0, speed));
		Robot.chassis.setMotors(currentSpeed, currentSpeed);
		counter++;
	}

	protected boolean isFinished()
	{
		return counter >= timeout / 20.0;
	}

	protected void fin()
	{
		Robot.chassis.setMotors(0, 0);
	}

	protected void interr()
	{
		fin();
	}

}
