package frc.robot.commands.Autonomous;

import frc.robot.Resources.TecbotConstants;

import frc.robot.commands.Arm.*;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PickHatch extends CommandGroup {

	public PickHatch() {
		addSequential(new ArmMoveAuto(TecbotConstants.ARM_ZERO_PERCENTAGE));
		addSequential(new ClawAuto(TecbotConstants.CLAW_90));
		addSequential(new MoveRollAuto(5, 0.5, TecbotConstants.ROLLER_STATES.HATCH_IN));
		addSequential(new StartConfiguration(0.5, 0));
		// A command group will require all of the subsystems that each member
		// would require.
		// e.g. if Command1 requires chassis, and Command2 requires arm,
		// a CommandGroup containing them would require both the chassis and the
		// arm.
	}
}