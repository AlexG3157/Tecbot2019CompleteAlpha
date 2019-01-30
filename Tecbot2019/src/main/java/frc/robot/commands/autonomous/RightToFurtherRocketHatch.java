/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autonomous;

import frc.robot.commands.chassis.*;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class RightToFurtherRocketHatch extends CommandGroup {
  
/**
 * @param startingFromLvl2 set true when the robot is starting from level 2
 */

  public RightToFurtherRocketHatch(boolean startingFromLvl2) {
    if(startingFromLvl2) addSequential(new DescendFromRampThenAlign());
    addSequential(new PIDMoveDistance(.2f,0,.5f));
    addSequential(new PIDTurnToAngle(25));
    addSequential(new PIDMoveDistance(7.8f, 20,.5f));
    addSequential(new PIDTurnToAngle(130));
    addSequential(new PIDMoveDistance(1f,130,.5f));
    addSequential(new Wait(2));
    addSequential(new PIDMoveDistance(-1f,130,.5f));
    addSequential(new PIDTurnToAngle(180));
    addSequential(new PIDMoveDistance(8,180,.5f));
  }
}
