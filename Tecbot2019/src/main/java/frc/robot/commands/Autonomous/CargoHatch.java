package frc.robot.commands.Autonomous;

import frc.robot.commands.Arm.*;

import frc.robot.Resources.TecbotConstants;
import frc.robot.Robot;
import frc.robot.RobotMap;


import edu.wpi.first.wpilibj.command.CommandGroup;

public class CargoHatch extends CommandGroup {
	public boolean shifted;

	public boolean isShifted() {
		return shifted;
	}

	public void setShifted(boolean sh) {
		this.shifted = sh;
	}

	public CargoHatch() {

		setShifted(Robot.oi.isShift);

		if (isShifted()) {
			addSequential(new ArmMoveAuto(TecbotConstants.ARM_ANGLER_CARGO1_PERCENTAGE));
			addSequential(new ClawAuto(TecbotConstants.CLAW_90));
			addSequential(new MoveRollAuto(5, 0.5, TecbotConstants.ROLLER_STATES.CARGO_OUT));
			addSequential(new StartConfiguration(45, .5));
		} else {
			addSequential(new ArmMoveAuto(TecbotConstants.ARM_ANGLER_HATCH1_PERCENTAGE));
			addSequential(new ClawAuto(TecbotConstants.CLAW_90));
			addSequential(new ArmMoveAuto(TecbotConstants.ARM_ZERO_PERCENTAGE));
			addSequential(new StartConfiguration(0.5, 0));

		}
	}
}