package frc.robot.commands.chassis;

import frc.robot.Robot;

import edu.wpi.first.wpilibj.command.PIDSubsystem;


public class DirectionPID extends PIDSubsystem {
    //SourceTree
	double deltaAngle;
	
	public void setDeltaAngle(double delta) {
		deltaAngle = delta;
	}
	
    public DirectionPID(float p, float i, float d) {
      
    	super(p,i,d);
    }

    public void initDefaultCommand() {
       
    }

    protected double returnPIDInput() {
       
        return deltaAngle;
    }

    protected void usePIDOutput(double output) {
    	Robot.driveTrain.driveSideLeft(-output);
    	Robot.driveTrain.driveSideRight(-output);
    	//System.out.println(output);
    }
}
