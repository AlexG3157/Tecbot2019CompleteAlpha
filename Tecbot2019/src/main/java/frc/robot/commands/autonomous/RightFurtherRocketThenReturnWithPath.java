/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.chassis.MoveAlongPath;

public class RightFurtherRocketThenReturnWithPath extends CommandGroup {
  
  /**
   * @param startFromLvl2 set true when the robot is starting in the level 2
   */
  public RightFurtherRocketThenReturnWithPath(boolean startFromLvl2) {
    
    if(startFromLvl2) addSequential(new DescendFromRampThenAlign());
    addSequential(new MoveAlongPath("RightToFurtherRightRocket"));
    addSequential(new MoveAlongPath("RightFurtherRocketToLoad"));


  }
}
