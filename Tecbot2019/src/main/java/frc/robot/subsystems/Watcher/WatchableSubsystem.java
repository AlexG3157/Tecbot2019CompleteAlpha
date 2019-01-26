/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.Watcher;

/**
 * A subsystem whose state needs to be checked 
 */

public interface WatchableSubsystem
{
    public enum State{Correct, Warning, Danger};

    public State checkState();
    public void correct();
    public void warning();
    public void danger();
}
