package frc.robot.commands.Arm;

import frc.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ToggleShift extends Command {


    public ToggleShift() {
        // Use requires() here to declare subsystem dependencies
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        if(Robot.oi.lb.get()){
            Robot.oi.isShift=true;
        }
        Robot.oi.isShift=false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
     return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}