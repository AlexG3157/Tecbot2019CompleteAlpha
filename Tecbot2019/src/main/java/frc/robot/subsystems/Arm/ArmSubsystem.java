package frc.robot.subsystems.arm;

import frc.robot.Robot;
import frc.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.math.Math;
import frc.robot.resources.*;
import frc.robot.resources.TecbotSpeedController.TypeOfMotor;

public class ArmSubsystem extends Subsystem {
	// Claw
	TecbotSpeedController clawMotor, roller, actuator;// actuator may not be used
	TecbotEncoder clawEnc, m1Encoder, m2Encoder;

	// Motor
	TecbotSpeedController m1arm, m2arm, extenderMotor1, extenderMotor2;

	// **there may be 2strechMotors**
	/*
	 * subsystem constructor, motors and encoders declaration
	 */
	public boolean shift = false;

	public ArmSubsystem() {
		// Arm constructor
		m1arm = new TecbotSpeedController(RobotMap.ARMMOTOR_1, TypeOfMotor.TALON_SRX);
		m2arm = new TecbotSpeedController(RobotMap.ARMMOTOR_2, TypeOfMotor.TALON_SRX);
		extenderMotor1 = new TecbotSpeedController(RobotMap.EXTEND_ARM_MOTOR_1_PORT, TypeOfMotor.TALON_SRX);
		extenderMotor2 = new TecbotSpeedController(RobotMap.EXTEND_ARM_MOTOR_2_PORT, TypeOfMotor.TALON_SRX);

		// Claw constructor
		clawMotor = new TecbotSpeedController(RobotMap.CLAWMOTOR, TypeOfMotor.TALON_SRX);
		roller = new TecbotSpeedController(RobotMap.ROLLER, TypeOfMotor.TALON_SRX);
		actuator = new TecbotSpeedController(RobotMap.ACTUATOR, TypeOfMotor.TALON_SRX);
		clawEnc = new TecbotEncoder(RobotMap.CLAWENC_1, RobotMap.CLAWENC_2);
		clawEnc.setDistancePerPulse(1);
		m1Encoder = new TecbotEncoder(m1arm);
		m2Encoder = new TecbotEncoder(m2arm);
	}

	// Arm
	/*
	 * Returns the max value of the arm encoders that change the arm's
	 * angle/position
	 */
	public double getArmEnc() {
		return (Math.max(m1arm.getEncPosition(), m2arm.getEncPosition()));
	}

	/**
	 * It is used to set the location in encoder units for the Arm to go to
	 * 
	 * @param target   represents the position you want to reach to reach in a form
	 *                 of percentage from 0 to 1
	 * @param maxPower is the maximum amount of voltage given to the motors in a
	 *                 form of percentage from 0 to 1
	 * 
	 */
	public boolean moveArm(double target, double maxPower) {
		maxPower = Math.clamp(maxPower, 0, 1);
		target = Math.clamp(target, 0, 1);
		// target is secure objective
		double encoderSetPoint = RobotMap.ACTUATOR_ENCODER_LIMIT_DOWN
				+ (RobotMap.ACTUATOR_ENCODER_LIMIT_UP - RobotMap.ACTUATOR_ENCODER_LIMIT_DOWN) * target;
		double distance = encoderSetPoint - getArmEnc();
		// double power = Math.clamp(distance /
		// RobotMap.ACTUATOR_CONTROL_MAX_DISTANCE, -1, 1);

		if (Math.abs(distance) <= RobotMap.ACTUATOR_CONTROL_MIN_DISTANCE) {
			m1arm.set(0);
			m2arm.set(0);
			return true;
		}
		m1arm.set(maxPower);
		m2arm.set(maxPower);
		return false;
	}

	/**
	 * sets both motor's speed to 0
	 */
	public void resetArmMotors() {
		m1arm.set(0);
		m2arm.set(0);
	}

	/**
	 * sets the encoders to 0
	 */
	public void resetArmEncoder() {
		m1Encoder.setEncoderPosition(0);
		m2arm.setEncoderPosition(0);
	}

	// Encoder Linear Actuators
	// Encoder Stretch Motor
	/**
	 * gets the current encoder position
	 */
	public double getExtenderEncoder() {

		double d = extenderMotor1.getEncPosition();
		return d;
	}

	/**
	 * Method used to extend the arm in the direction of 's', being s motor power
	 * 
	 * @param target Set the target to reach in a form of percentage from 0 to 1
	 */
	public boolean extendArm(double target, double maxPower) {
		maxPower = Math.clamp(maxPower, 0, 1);
		target = Math.clamp(target, 0, 1);
		// target is secure objective
		double encoderSetPoint = RobotMap.EXTENDER_ENCODER_LIMIT_DOWN
				+ (RobotMap.EXTENDER_ENCODER_LIMIT_UP - RobotMap.EXTENDER_ENCODER_LIMIT_DOWN) * target;
		double distance = encoderSetPoint - getExtenderEncoder();
		double power = Math.clamp(distance / RobotMap.EXTENDER_CONTROL_MAX_DISTANCE, -1, 1);

		if (Math.abs(distance) <= RobotMap.EXTENDER_CONTROL_MIN_DISTANCE) {
			extenderMotor1.set(0);
			extenderMotor1.set(0);
			return true;
		}
		extenderMotor1.set(power * maxPower);
		extenderMotor1.set(power * maxPower);
		return false;

	}

	public void resetExtendArm() {
		extenderMotor1.set(0);
	}

	public boolean startConfiguration(double d, double s) {
		return this.extendArm(d, s);
	}

	// Claw

	public double getClawEnc() {
		double degree = clawEnc.getRaw();
		return degree;
	}

	public void resetClawEnc() {
		clawEnc.resetEncoder();
	}

	public boolean clawRotate(double target, double maxPower) {
		maxPower = Math.clamp(maxPower, 0, 1);
		target = Math.clamp(target, 0, 1);
		// target is secure objective
		double encoderSetPoint = RobotMap.CLAW_ENCODER_LIMIT_DOWN
				+ (RobotMap.CLAW_ENCODER_LIMIT_UP - RobotMap.CLAW_ENCODER_LIMIT_DOWN) * target;
		double distance = encoderSetPoint - getClawEnc();
		double power = Math.clamp(distance / RobotMap.CLAW_CONTROL_MAX_DISTANCE, -1, 1);

		if (Math.abs(distance) <= RobotMap.CLAW_CONTROL_MIN_DISTANCE) {
			clawMotor.set(0);
			return true;
		}
		clawMotor.set(power * maxPower);
		return false;

		// clawMotor.set(speed);
	}

	public void rollActivate(double speed, TecbotConstants.ROLLER_STATES state) {
		switch (state) {
		case CARGO_IN:
			roller.set(-speed);
			break;
		case CARGO_OUT:
			roller.set(speed);
			break;
		case HATCH_IN:
			roller.set(speed);
			break;
		case HATCH_OUT:
			roller.set(-speed);
			break;
		case OFF:
			roller.set(0);
			break;
		default:
			roller.set(0);
		}
	}

	public void actMove(double speed) {
		actuator.set(speed);
	}

	public void resetClawMotors() {
		clawMotor.set(0);
		roller.set(0);
		actuator.set(0);

	}

	// Teleop

	public void armTeleop() {
		m1arm.set(Robot.oi.getCopilot().getRawAxis(5));
		m2arm.set(Robot.oi.getCopilot().getRawAxis(5));

	}

	public void clawTeleopMove(double speed) {
		clawMotor.set(speed);
	}

	public void extendTeleop(double speed) {
		extenderMotor1.set(speed);
		extenderMotor2.set(-speed);
	}

	public void rollersTeleop(double speed) {
		roller.set(speed);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}