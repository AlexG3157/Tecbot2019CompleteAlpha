/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.Watcher;


import edu.wpi.first.wpilibj.command.Subsystem;
import java.util.ArrayList;

/**
 * Add your docs here.
 */
public class WatcherSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public ArrayList<WatchableSubsystem> subsystems = new ArrayList<WatchableSubsystem>();

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void addWatchable(WatchableSubsystem sub){
    subsystems.add(sub);
  }
  public void checkStates(){
    for(WatchableSubsystem subsystem : subsystems){
      WatchableSubsystem.State state = subsystem.checkState();
      switch(state){
        case Correct:
        subsystem.correct();
        break;
        case Warning: 
        subsystem.warning();
        break;
        case Danger:
        subsystem.danger();
        break;
      }
    }
  }

}
