package org.usfirst.frc.team3316.robot.commands.intake;

import org.usfirst.frc.team3316.robot.Robot;
import org.usfirst.frc.team3316.robot.commands.DBugCommand;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RollOut extends DBugCommand
{
	//TODO: Add commenting

	private double speed;

	public RollOut()
	{
		requires(Robot.intake);
	}

	protected void init()
	{
	}

	protected void execute()
	{
		speed = (double) config.get("intake_RollOut_Speed");
		isFin = !Robot.intake.setMotors(speed);
		System.out.println(speed);
	}

	protected boolean isFinished()
	{
		return isFin;
	}

	protected void fin()
	{
		Robot.intake.setMotors(0);
	}

	protected void interr()
	{
		fin();
	}

}
