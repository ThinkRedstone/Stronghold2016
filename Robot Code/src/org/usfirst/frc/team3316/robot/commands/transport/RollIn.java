package org.usfirst.frc.team3316.robot.commands.transport;

import org.usfirst.frc.team3316.robot.Robot;
import org.usfirst.frc.team3316.robot.commands.DBugCommand;
import org.usfirst.frc.team3316.robot.config.Config.ConfigException;

public class RollIn extends DBugCommand
{

	private double rollInSpeed;

	public RollIn()
	{
		requires(Robot.transport);
	}

	protected void init()
	{
		rollInSpeed = (double) Robot.config.get("TRANSPORT_ROLL_IN_SPEED");
	}

	protected void execute()
	{
		Robot.transport.setMotor(rollInSpeed);
	}

	protected boolean isFinished()
	{
		return false;
	}

	protected void fin()
	{
	}

	protected void interr()
	{
	}

}