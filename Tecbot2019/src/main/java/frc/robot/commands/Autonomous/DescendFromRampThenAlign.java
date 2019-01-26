package frc.robot.commands.Autonomous;

import frc.robot.commands.Chassis.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DescendFromRampThenAlign extends CommandGroup {

    public DescendFromRampThenAlign() {
       
    	addSequential(new PIDMoveDistance(2f,0,.4f));
    	addSequential(new MoveForwardTimed(1.5f, -.5f));
    }
}
