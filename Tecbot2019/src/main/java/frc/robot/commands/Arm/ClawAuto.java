package frc.robot.commands.arm;

import frc.robot.resources.*;
import frc.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClawAuto extends Command {
    boolean hasToFinish;
    double targetOut;

    public ClawAuto(double target) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.armSub);
        targetOut = target;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        hasToFinish = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        hasToFinish = Robot.armSub.clawRotate(targetOut, TecbotConstants.DEFAULT_MOTOR_MAX_POWER);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return hasToFinish;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        Robot.armSub.resetClawMotors();
    }
}
