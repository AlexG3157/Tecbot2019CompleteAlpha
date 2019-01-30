package frc.robot.commands.chassis;

import frc.robot.Robot;
import frc.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Implements PID to rotate the robot certain degrees from its rotation
 */
public class PIDTurnCertainDegrees extends Command {

	DirectionPID directionPID;
	
	float nextAngle;
	double deltaAngle;
	
    public PIDTurnCertainDegrees(float degrees) {
    requires(Robot.driveTrain);
    nextAngle = (float) (Robot.tecbotgyro.getYaw() + degrees);
    if(nextAngle > 180) nextAngle -= 360;
    if(nextAngle < -180) nextAngle += 360;
    }

    protected void initialize() {
    	directionPID = new DirectionPID(RobotMap.turning_P, RobotMap.turning_I, RobotMap.turning_D);
    	
    	directionPID.setAbsoluteTolerance(RobotMap.turning_Tolerance);
    	directionPID.setOutputRange(-.5, .5);
    	directionPID.setSetpoint(nextAngle);
    	directionPID.enable();
    }

    protected void execute() {
    	deltaAngle = nextAngle - Robot.tecbotgyro.getYaw();
    	if(deltaAngle < -180) deltaAngle += 360;
    	if(deltaAngle > 180) deltaAngle -= 360;
    	directionPID.setDeltaAngle(deltaAngle);
    	
    	//Robot.driveTrain.driveSideLeft(directionPID.getTurnRatio());
    	//Robot.driveTrain.driveSideRight(directionPID.getTurnRatio());
    	//System.out.println(directionPID.getTurnRatio());
    }

    protected boolean isFinished() {
        return directionPID.onTarget();
    }

    protected void end() {
    	directionPID.disable();
    }

    protected void interrupted() {
    }
}
