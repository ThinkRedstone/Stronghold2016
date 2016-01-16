
package org.usfirst.frc.team3316.robot;

import java.util.Timer;

import org.usfirst.frc.team3316.robot.config.Config;
import org.usfirst.frc.team3316.robot.humanIO.Joysticks;
import org.usfirst.frc.team3316.robot.humanIO.SDB;
import org.usfirst.frc.team3316.robot.logger.DBugLogger;
import org.usfirst.frc.team3316.robot.robotIO.Actuators;
import org.usfirst.frc.team3316.robot.robotIO.Sensors;
import org.usfirst.frc.team3316.robot.subsystems.Chassis;
import org.usfirst.frc.team3316.robot.subsystems.DBugSubsystem;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot 
{
	public static Config config;
    public static DBugLogger logger;
    public static Timer timer;
    
    /*
     * Human IO
     */
    public static Joysticks joysticks;
    public static SDB sdb;
    /*
     * Robot IO
     */
    public static Actuators actuators;
    public static Sensors sensors;
    
    /*
     * Subsystems
     */
    public static Chassis chassis;
    
    Command autonomousCommand;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() 
    {
    	System.out.println("Started robotInit()");
    	/*
    	 * Above all else
    	 */
    	logger = new DBugLogger();
    	System.out.println("logger initialized");
    	
    	config = new Config();
    	System.out.println("config initialized");
    	
    	timer = new Timer();
    	System.out.println("timer initialized");
    	
    	/*
    	 * Human IO (that does not require subsystems)
    	 */
    	joysticks = new Joysticks();
    	System.out.println("joysticks initialized");
    	
    	/*
    	 * Robot IO
    	 */
    	actuators = new Actuators();
    	System.out.println("actuators initialized");
    	
    	sensors = new Sensors();
    	System.out.println("sensors initialized");
    	
    	/*
    	 * Subsystems
    	 */
    	chassis = new Chassis();
    	System.out.println("chassis subsystem initialized");

    	/*
    	 * Human IO (that requires subsystems)
    	 */
    	joysticks.initButtons();
    	System.out.println("joysticks buttons initialized");
    	
    	sdb = new SDB();
    	System.out.println("sdb initialized");
    }
	
	public void disabledPeriodic() 
	{
		Scheduler.getInstance().run();
	}

    public void autonomousInit() 
    {
        if (autonomousCommand != null) autonomousCommand.start();
    }

    public void autonomousPeriodic() 
    {
        Scheduler.getInstance().run();
    }

    public void teleopInit() 
    {
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    public void disabledInit()
    {

    }

    public void teleopPeriodic()
    {
        Scheduler.getInstance().run();
    }
    
    public void testPeriodic() 
    {
        LiveWindow.run();
    }
    
}