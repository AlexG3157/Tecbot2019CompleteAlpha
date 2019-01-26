package frc.robot.commands.Arm;

import frc.robot.Resources.TecbotConstants;
import frc.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class MoveRollAuto extends Command {
	double time;
	double speed;
	TecbotConstants.ROLLER_STATES stateOut;
    public MoveRollAuto(double timeF, double speedR, TecbotConstants.ROLLER_STATES state) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires (Robot.armSub);
    	time = timeF;
    	speed = speedR;
    	stateOut = state;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout (time);
    
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.armSub.rollActivate(speed, stateOut);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut ();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.armSub.resetClawMotors();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.armSub.resetClawMotors();
    }
}
