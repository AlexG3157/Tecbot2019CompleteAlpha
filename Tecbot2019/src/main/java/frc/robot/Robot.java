/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.subsystems.watcher.WatcherSubsystem;
import frc.robot.resources.TecbotEncoder;
import frc.robot.commands.autonomous.*;
import frc.robot.commands.chassis.*;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.chassis.DriveTrain;
import frc.robot.subsystems.arm.*;

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
		armSub = new ArmSubsystem();
		watcher = new WatcherSubsystem();
		tecbotgyro = new Navx();
		leftEncoder = driveTrain.getLeftEncoder();
		rightEncoder = driveTrain.getRightEncoder();

		m_chooser.addOption("Turn 90 Degrees", new PIDTurnToAngle(90));
		m_chooser.addOption("Move 1 mt", new PIDMoveDistance(1f, .7f));
		m_chooser.addOption("Move -1 mt", new PIDMoveDistance(-1f, .7f));
		m_chooser.addOption("Move 5 mt with gyo", new PIDMoveDistance(5f, 0));
		m_chooser.addOption("Move -1 mt with gyro", new PIDMoveDistance(-1f, 0));
		m_chooser.addOption("Descend then align", new DescendFromRampThenAlign());
		m_chooser.addOption("Follow Juan", new MoveAlongPath("Juan"));
		m_chooser.addOption("RightToRight PID", new RightToFurtherRocketHatch(true));
		SmartDashboard.putData("Auto mode", m_chooser);
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
		armSub.armTeleop();
		armSub.extendTeleop(oi.getPilot().getRawAxis(2));
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
		System.out.println(leftEncoder.getRaw());
		System.out.println(rightEncoder.getRaw());
		System.out.println("--------------------------------------");

	}

	@Override
	public void testPeriodic() {
	}
}
