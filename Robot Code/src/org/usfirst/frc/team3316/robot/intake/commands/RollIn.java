package org.usfirst.frc.team3316.robot.intake.commands;

import org.usfirst.frc.team3316.robot.Robot;
import org.usfirst.frc.team3316.robot.commands.DBugCommand;
import org.usfirst.frc.team3316.robot.config.Config.ConfigException;

public class RollIn extends DBugCommand
{
	private double speed;

	public RollIn()
	{
		requires(Robot.intake);
	}

	protected void init()
	{
	}

	protected void execute()
	{
		try
		{
			speed = (double) Robot.config.get("intake_RollIn_Speed");
		}
		catch (ConfigException e)
		{
			logger.severe(e);
		}
		
		Robot.intake.setMotor(speed);
	}

	protected boolean isFinished()
	{
		return false;
	}

	protected void fin()
	{
		Robot.intake.setMotor(0);
	}

	protected void interr()
	{
		fin();
	}
}
