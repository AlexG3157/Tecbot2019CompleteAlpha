package frc.robot.commands.chassis;

import frc.robot.Robot;
import frc.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Implements PID to move the robot to a certain angle
 */
public class PIDTurnToAngle extends Command {

	DirectionPID directionPID;
	
	float nextAngle;
	double deltaAngle;
	boolean bestTurn;
	
    public PIDTurnToAngle(float angle) {
    requires(Robot.driveTrain);
    nextAngle = angle;
    }
    // if bestTurn is enabled, robot will turn right or left depending of which is nearer
    public PIDTurnToAngle(float angle, boolean g_bestTurn) {
        requires(Robot.driveTrain);
        nextAngle = angle;
        bestTurn = g_bestTurn;
        }

    protected void initialize() {
    	directionPID = new DirectionPID(RobotMap.turning_P, RobotMap.turning_I, RobotMap.turning_D);
    	
    	directionPID.setAbsoluteTolerance(RobotMap.turning_Tolerance);
    	directionPID.setOutputRange(-.3, .3);
    	directionPID.setSetpoint(0);
    	directionPID.enable();
    }

    protected void execute() {
    	deltaAngle = nextAngle - Robot.tecbotgyro.getYaw();
    	if(bestTurn) 
    	{
    	if(deltaAngle < -180) deltaAngle += 360;
    	if(deltaAngle > 180) deltaAngle -= 360;
    	}
    	directionPID.setDeltaAngle(deltaAngle);
    }

    protected boolean isFinished() {
    	return directionPID.onTarget();
    }

    protected void end() {
    	directionPID.disable();
    }

    protected void interrupted() {
    	directionPID.disable();
    }
}
