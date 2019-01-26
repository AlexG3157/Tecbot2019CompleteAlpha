package frc.robot.commands.Autonomous;

import frc.robot.Resources.TecbotConstants;
import frc.robot.Robot;
import frc.robot.RobotMap;

import frc.robot.commands.Arm.*;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class CargoHatchLv2 extends CommandGroup {
	public boolean shifted;

	
	public CargoHatchLv2() {


		if (Robot.oi.isShift) {
			addSequential(new ArmMoveAuto(TecbotConstants.ARM_ANGLER_CARGO2_PERCENTAGE));
			addSequential(new ClawAuto(TecbotConstants.CLAW_90));
			addSequential(new ArmExtenderAuto(TecbotConstants.ARM_EXTENDER_CARGO2));
			addSequential(new MoveRollAuto(5, 0.5, TecbotConstants.ROLLER_STATES.CARGO_OUT));
			addSequential(new StartConfiguration(45, .5));
		} else {
			addSequential(new ArmMoveAuto(TecbotConstants.ARM_ANGLER_HATCH2_PERCENTAGE));
			addSequential(new ClawAuto(TecbotConstants.CLAW_90));
			addSequential(new ArmMoveAuto(TecbotConstants.ARM_ANGLER_HATCH2_PERCENTAGE));
			addSequential(new StartConfiguration(0.5, 0));
		}
		// A command group will require all of the subsystems that each member
		// would require.
		// e.g. if Command1 requires chassis, and Command2 requires arm,
		// a CommandGroup containing them would require both the chassis and the
		// arm.
	}
}