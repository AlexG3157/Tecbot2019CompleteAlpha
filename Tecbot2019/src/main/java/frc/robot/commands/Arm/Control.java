package frc.robot.commands.Arm;

import frc.robot.Robot;
import frc.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Control extends Command {


    public Control() {
        // Use requires() here to declare subsystem dependencies
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	RobotMap.isShift=true;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if(Robot.oi.ps4){
    	if( Robot.oi.L1.get()) {
        return false;}else {
        	return true;
        }}else{
            if(Robot.oi.lb.get()){
                return false;
            }else{
                return true;
            }
        }
    }

    // Called once after isFinished returns true
    protected void end() {
    	RobotMap.isShift=true;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
