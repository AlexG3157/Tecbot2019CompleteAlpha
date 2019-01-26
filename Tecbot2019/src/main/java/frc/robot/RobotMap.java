/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.Resources.RobotConfigurator;
import frc.robot.RobotMap.ChassisConfiguration;
import frc.robot.RobotMap.MotorConfiguration;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
	
	public enum MotorConfiguration {
		CAN, TALON
	}

	public enum ChassisConfiguration {
		MOTORS_2, MOTORS_4, MOTORS_6;
	}

	public static MotorConfiguration chassis_typeOfMotor = MotorConfiguration.CAN;
	public static ChassisConfiguration chassis_typeOfConfiguration = ChassisConfiguration.MOTORS_6;

	public static int chassis_frontRightMotor = 1;
	public static int chassis_frontLeftMotor = 4;
	public static int chassis_rearRightMotor = 3;
	public static int chassis_rearLeftMotor = 6;
	public static int chassis_middleLeftMotor = 5;
	public static int chassis_middleRightMotor = 2;
	public static int chassis_leftEncoder[] = {RobotConfigurator.CONFIG_NOT_SET,RobotConfigurator.CONFIG_NOT_SET};
  public static int chassis_rightEncoder[] = {RobotConfigurator.CONFIG_NOT_SET,RobotConfigurator.CONFIG_NOT_SET};
  
/*
TALON SRX ENCODERS ON CHASSIS
If there is no SRX encoder, this HAS to be -1, BOTH!
*/
  public static int chassis_leftEncoderSRX = 6;
  public static int chassis_rightEncoderSRX = 1;
	
	public static float straight_P = 1.3672f;
	public static float straight_I = .3593f;
	public static float straight_D = .0898f;
	public static float straight_Tolerance = 10;
	
	//public static float straight_P =  1.58950784091104f;
	//public static float straight_I = 16.1648949353988f;
	//public static float straight_D = .02348467516511333f;
	
	public static float turning_P = 1.58950784091104f;
	public static float turning_I = 16.1648949353988f;
	public static float turning_D = .02348467516511333f;
	public static float turning_Tolerance = 7;
	
	// The equivalence between meters to encoder count 
	// Meter * meters_to_encoder = encoder count 
	public static float k_meters_to_encoder = (float) (RobotMap.k_tic_per_revolution / (RobotMap.k_wheel_diameter * Math.PI));
	public static int k_tic_per_revolution = 4096;
  public static float k_wheel_diameter = .2032f;
  

	// METHODS IN SUBYSTEM INFO.
	// EXTENDER ARM ENCODER STUFF
	public static final double EXTENDER_ENCODER_LIMIT_UP = 100;
	public static final double EXTENDER_ENCODER_LIMIT_DOWN = 0;
	public static final double EXTENDER_CONTROL_MAX_DISTANCE = 100;
	public static final double EXTENDER_CONTROL_MIN_DISTANCE = 10;
	// ARM LINEAR ACTUATORS ENCODER STUFF
	public static final double ACTUATOR_ENCODER_LIMIT_UP = 100;
	public static final double ACTUATOR_ENCODER_LIMIT_DOWN = 0;
	public static final double ACTUATOR_CONTROL_MAX_DISTANCE = 100;
	public static final double ACTUATOR_CONTROL_MIN_DISTANCE = 10;
	// CLAW ENCODER ROTATION STUFF
	public static final double CLAW_ENCODER_LIMIT_UP = 100;
	public static final double CLAW_ENCODER_LIMIT_DOWN = 0;
	public static final double CLAW_CONTROL_MAX_DISTANCE = 90;
	public static final double CLAW_CONTROL_MIN_DISTANCE = 10;

	// Arm
	public static final int ARMMOTOR_1 = 0;
	public static final int ARMMOTOR_2 = 1;
	public static final int STRETCHARM = 1;

	public static final int ARM_UNITS_PER_LEVEL = 2500; // EXAMPLE
	public static final int ARM_STRECH_UNITS_UP = 3850; // EXAMPLE
	public static final int ARM_STRECH_UNITS_DOWN = 100; // EXAMPLE

	public static final int ARMENC_1_PORT1 = 1;
	public static final int ARMENC_1_PORT2 = 1;
	public static final int ARMENC_2_PORT1 = 1;
	public static final int ARMENC_2_PORT2 = 1;
	public static final int EXTEND_ENC_PORT1 = 1;
	public static final int EXTEND_ENC_PORT2 = 1;
	// Claw
	public static final int CLAWMOTOR = 6;
	public static final int ROLLER = 7;
	public static final int ACTUATOR = 8;
	public static final int CLAWENC_1 = 0;
	public static final int CLAWENC_2 = 1;

	public static final int CLAW_UNITS_PER_LEVEL = 1000; // EXAMPLE
	public static final int EXTEND_ARM_PORT_1 = 2;

}
