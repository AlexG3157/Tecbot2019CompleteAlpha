package frc.robot.commands.autonomous;

import frc.robot.resources.TecbotConstants;
import frc.robot.Robot;

import frc.robot.commands.arm.*;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class CargoHatchLv3 extends CommandGroup {



	public CargoHatchLv3() {


		if (Robot.oi.isShift) {
			addSequential(new ArmMoveAuto(TecbotConstants.ARM_ANGLER_CARGO3_PERCENTAGE));
			addSequential(new ClawAuto(TecbotConstants.CLAW_90));
			addSequential(new ArmExtenderAuto(TecbotConstants.ARM_EXTENDER_CARGO3));
			addSequential(new MoveRollAuto(5, 0.5, TecbotConstants.ROLLER_STATES.CARGO_OUT));
			addSequential(new StartConfiguration(45, .5));
		} else {
			addSequential(new ArmMoveAuto(TecbotConstants.ARM_ANGLER_HATCH3_PERCENTAGE));
			addSequential(new ClawAuto(TecbotConstants.CLAW_90));
			addSequential(new ArmExtenderAuto(TecbotConstants.ARM_EXTENDER_HATCH3));// up 45�
			addSequential(new ArmMoveAuto(TecbotConstants.ARM_ANGLER_HATCH3_PERCENTAGE));
			addSequential(new ArmExtenderAuto(TecbotConstants.ARM_EXTENDER_ZERO));// down 45�
			addSequential(new StartConfiguration(0.5, 0));
		}
		// use addParallel()
		// e.g. addParallel(new Command1());
		// addSequential(new Command2());
		// Command1 and Command2 will run in parallel.

		// A command group will require all of the subsystems that each member
		// would require.
		// e.g. if Command1 requires chassis, and Command2 requires arm,
		// a CommandGroup containing them would require both the chassis and the
		// arm.
	}
}