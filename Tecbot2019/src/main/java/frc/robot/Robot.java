/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;


import frc.robot.subsystems.Watcher.WatcherSubsystem;
import frc.robot.Resources.RobotConfigurator;
import frc.robot.Resources.TecbotEncoder;
import frc.robot.commands.Autonomous.*;
import frc.robot.commands.Chassis.*;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrame;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.Chassis.DriveTrain;
import frc.robot.subsystems.Arm.*;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	public static OI oi;
	
	public static Navx tecbotgyro;
  public static DriveTrain driveTrain;
  public static ArmSubsystem armSub;
	
	public static TecbotEncoder leftEncoder, rightEncoder;

  public static WatcherSubsystem watcher;

  public static TalonSRX talon;
	
	Command m_autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();

	@Override
	public void robotInit() {
    driveTrain = new DriveTrain();
    /*
		m_chooser.addOption("Turn 90 Degrees", new PIDTurnToAngle(90));
		m_chooser.addOption("Move 1 mt", new PIDMoveDistance(1f,.7f));
		m_chooser.addOption("Move -1 mt", new PIDMoveDistance(-1f,.7f));
		m_chooser.addOption("Move 5 mt with gyo", new PIDMoveDistance(5f, 0));
		m_chooser.addOption("Move -1 mt with gyro", new PIDMoveDistance(-1f, 0));
    m_chooser.addOption("Descend then align", new DescendFromRampThenAlign());
  */
		SmartDashboard.putData("Auto mode", m_chooser);
		tecbotgyro = new Navx();
		leftEncoder = driveTrain.getLeftEncoder();
    rightEncoder = driveTrain.getRightEncoder();
    //watcher = new WatcherSubsystem();
    //armSub = new ArmSubsystem();
     oi = new OI();

	}

	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		tecbotgyro.reset();
		m_autonomousCommand = m_chooser.getSelected();
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		tecbotgyro.run();
	}

	@Override
	public void teleopInit() {
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		//.driveSideLeft(Robot.oi.getPilot().getRawAxis(5) + Robot.oi.getPilot().getRawAxis(4));
		//driveTrain.driveSideRight(-Robot.oi.getPilot().getRawAxis(5) + Robot.oi.getPilot().getRawAxis(4));
    //driveTrain.drive();
    //System.out.println(driveTrain.getLeftEncoder().getRaw());
    System.out.println(leftEncoder.getRaw());
    System.out.println(rightEncoder.getRaw());
    System.out.println("--------------------------------------");

	}

	@Override
	public void testPeriodic() {
	}
}
