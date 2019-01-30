package frc.robot.commands.chassis;

import frc.robot.Robot;
import frc.robot.resources.TecbotEncoder;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 * Gives a motor a certain power based on the encoder
 */
public class DistancePID extends PIDSubsystem {

	TecbotEncoder wheelEncoder;
	float deltaAngle = 0;
	
	void setDeltaAngle(float delta) {
		deltaAngle = delta;
	}
	
    public DistancePID(float p, float i, float d, TecbotEncoder encoder) {
    	super(p,i,d);
    	wheelEncoder = encoder;
    
    }
    public void initDefaultCommand() {
    }

    protected double returnPIDInput() {
        return wheelEncoder.getRaw();
    }

    protected void usePIDOutput(double output) {
    	//power = output;
    	Robot.driveTrain.driveSideLeft(-output + ((deltaAngle / 180*3) ) );
    	Robot.driveTrain.driveSideRight(output + ((deltaAngle / 180)*3 ) );
    	System.out.println(deltaAngle);
    	System.out.println(output);
    }
}
