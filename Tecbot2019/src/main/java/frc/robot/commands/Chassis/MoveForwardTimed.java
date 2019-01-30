package frc.robot.commands.chassis;

import frc.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveForwardTimed extends Command {

	float pctPower;
	
    public MoveForwardTimed(float time, float power) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	setTimeout(time);
    	pctPower = power;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.driveSideLeft(-pctPower);
    	Robot.driveTrain.driveSideRight(pctPower);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.driveSideLeft(0);
    	Robot.driveTrain.driveSideRight(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.driveTrain.driveSideLeft(0);
    	Robot.driveTrain.driveSideRight(0);
    }
}
