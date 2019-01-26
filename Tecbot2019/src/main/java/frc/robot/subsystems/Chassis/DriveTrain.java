package frc.robot.subsystems.Chassis;



import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.Resources.DifferentialDrive;
import frc.robot.Resources.RobotConfigurator;
import frc.robot.Resources.TecbotEncoder;
import frc.robot.Resources.TecbotSpeedController;
import frc.robot.Resources.TecbotSpeedController.TypeOfMotor;
import frc.robot.commands.Chassis.DefaultDriveCommand;
import frc.robot.subsystems.Watcher.WatchableSubsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem implements WatchableSubsystem{

	TecbotSpeedController frontRightWheel, frontLeftWheel, rearRightWheel, rearLeftWheel, middleLeftWheel,
			middleRightWheel;
	DifferentialDrive drive;
	DoubleSolenoid transmission;
	boolean reverse= false;
	boolean transmissionState = false;
	boolean arrivedToThePosition = false;
	double target, diffPos, diffAng;
	TecbotEncoder leftEncoder, rightEncoder;
	Command defaultCommand;
	boolean transmissionStatus = false;
	
	// The constructor is to set all the motor types and ports, create the
	// DifferentialDrive,
	// Set the solenoid ports if transmission exists and the encoders if exists
	// Requires the RobotMapTecbot

    public TecbotSpeedController isMotorWithEncoder(TecbotSpeedController tsc, int m1, int e1){

        if( m1 == e1)
            return tsc;
        return null;
    }

	public DriveTrain() {
		if (RobotMap.class.getSuperclass().getName().equals("RobotMap"))
            throw new NullPointerException("RobotMap is not extending from RobotMapTecbot");
            
        TecbotSpeedController leftEncoderMotor = null;
        TecbotSpeedController rightEncoderMotor = null;
        

		switch (RobotMap.chassis_typeOfConfiguration) {
		case MOTORS_2:
			switch (RobotMap.chassis_typeOfMotor) {
			case CAN:
				rearRightWheel = new TecbotSpeedController(RobotMap.chassis_rearRightMotor, TypeOfMotor.TALON_SRX);
				rearLeftWheel = new TecbotSpeedController(RobotMap.chassis_rearLeftMotor,  TypeOfMotor.TALON_SRX);
                rightEncoderMotor = isMotorWithEncoder(rearRightWheel, RobotMap.chassis_rearRightMotor, RobotMap.chassis_rightEncoderSRX );
                leftEncoderMotor = isMotorWithEncoder(rearLeftWheel, RobotMap.chassis_rearLeftMotor ,RobotMap.chassis_leftEncoderSRX );

                break;
			case TALON:
				rearRightWheel = new TecbotSpeedController(RobotMap.chassis_rearRightMotor,  TypeOfMotor.PWM_TALON_SRX);
				rearLeftWheel = new TecbotSpeedController(RobotMap.chassis_rearLeftMotor,  TypeOfMotor.PWM_TALON_SRX);
				break;
			}
			drive = new DifferentialDrive(rearLeftWheel, rearRightWheel);
			break;
		case MOTORS_4:
			switch (RobotMap.chassis_typeOfMotor) {
			case CAN:
				frontRightWheel = new TecbotSpeedController(RobotMap.chassis_frontRightMotor,  TypeOfMotor.TALON_SRX);
				frontLeftWheel = new TecbotSpeedController(RobotMap.chassis_frontLeftMotor,  TypeOfMotor.TALON_SRX);
				rearRightWheel = new TecbotSpeedController(RobotMap.chassis_rearRightMotor,  TypeOfMotor.TALON_SRX);
				rearLeftWheel = new TecbotSpeedController(RobotMap.chassis_rearLeftMotor,  TypeOfMotor.TALON_SRX);
                
                rightEncoderMotor = isMotorWithEncoder(frontRightWheel, RobotMap.chassis_frontRightMotor, RobotMap.chassis_rightEncoderSRX );
                if( rightEncoderMotor == null)
                    rightEncoderMotor = isMotorWithEncoder(rearRightWheel, RobotMap.chassis_rearRightMotor, RobotMap.chassis_rightEncoderSRX );
                
                leftEncoderMotor = isMotorWithEncoder(frontLeftWheel, RobotMap.chassis_frontLeftMotor, RobotMap.chassis_leftEncoderSRX );
                if( leftEncoderMotor == null)
                leftEncoderMotor = isMotorWithEncoder(rearLeftWheel, RobotMap.chassis_rearLeftMotor, RobotMap.chassis_leftEncoderSRX );
                
                
                break;
			case TALON:
				frontRightWheel = new TecbotSpeedController(RobotMap.chassis_frontRightMotor,TypeOfMotor.PWM_TALON_SRX );
				frontLeftWheel = new TecbotSpeedController(RobotMap.chassis_frontLeftMotor,  TypeOfMotor.PWM_TALON_SRX);
				rearRightWheel = new TecbotSpeedController(RobotMap.chassis_rearRightMotor,  TypeOfMotor.PWM_TALON_SRX);
				rearLeftWheel = new TecbotSpeedController(RobotMap.chassis_rearLeftMotor,    TypeOfMotor.PWM_TALON_SRX);
				break;
			}
			drive = new DifferentialDrive(frontLeftWheel, rearLeftWheel, frontRightWheel, rearRightWheel);
			break;
		case MOTORS_6:
			switch (RobotMap.chassis_typeOfMotor) {
			case CAN:
				frontRightWheel = new TecbotSpeedController(RobotMap.chassis_frontRightMotor, TypeOfMotor.TALON_SRX);
				frontLeftWheel = new TecbotSpeedController(RobotMap.chassis_frontLeftMotor, TypeOfMotor.TALON_SRX);
				rearRightWheel = new TecbotSpeedController(RobotMap.chassis_rearRightMotor, TypeOfMotor.TALON_SRX);
				rearLeftWheel = new TecbotSpeedController(RobotMap.chassis_rearLeftMotor, TypeOfMotor.TALON_SRX);
				middleLeftWheel = new TecbotSpeedController(RobotMap.chassis_middleLeftMotor, TypeOfMotor.TALON_SRX);
				middleRightWheel = new TecbotSpeedController(RobotMap.chassis_middleRightMotor, TypeOfMotor.TALON_SRX);
                
                
                rightEncoderMotor = isMotorWithEncoder(frontRightWheel, RobotMap.chassis_frontRightMotor, RobotMap.chassis_rightEncoderSRX );
                if( rightEncoderMotor == null)
                    rightEncoderMotor = isMotorWithEncoder(rearRightWheel, RobotMap.chassis_rearRightMotor, RobotMap.chassis_rightEncoderSRX );
                if( rightEncoderMotor == null)
                    rightEncoderMotor = isMotorWithEncoder(middleRightWheel, RobotMap.chassis_middleRightMotor, RobotMap.chassis_rightEncoderSRX );
                
                leftEncoderMotor = isMotorWithEncoder(frontLeftWheel, RobotMap.chassis_frontLeftMotor, RobotMap.chassis_leftEncoderSRX );
                if( leftEncoderMotor == null)
                    leftEncoderMotor = isMotorWithEncoder(rearLeftWheel, RobotMap.chassis_rearLeftMotor, RobotMap.chassis_leftEncoderSRX );
                if( leftEncoderMotor == null)
                    leftEncoderMotor = isMotorWithEncoder(middleLeftWheel, RobotMap.chassis_middleLeftMotor, RobotMap.chassis_leftEncoderSRX );
                
                drive = new DifferentialDrive(frontLeftWheel , frontRightWheel, 
                                                middleLeftWheel,middleRightWheel,
                                                rearLeftWheel,  rearRightWheel
                                                );
            
                break;
			case TALON:
				frontRightWheel = new TecbotSpeedController(RobotMap.chassis_frontRightMotor, TypeOfMotor.PWM_TALON_SRX);
				frontLeftWheel = new TecbotSpeedController(RobotMap.chassis_frontLeftMotor, TypeOfMotor.PWM_TALON_SRX);
				rearRightWheel = new TecbotSpeedController(RobotMap.chassis_rearRightMotor,  TypeOfMotor.PWM_TALON_SRX);
				rearLeftWheel = new TecbotSpeedController(RobotMap.chassis_rearLeftMotor,  TypeOfMotor.PWM_TALON_SRX);
				middleLeftWheel = new TecbotSpeedController(RobotMap.chassis_middleLeftMotor,  TypeOfMotor.PWM_TALON_SRX);
				middleRightWheel = new TecbotSpeedController(RobotMap.chassis_middleRightMotor,  TypeOfMotor.PWM_TALON_SRX);
                drive = new DifferentialDrive(frontLeftWheel , frontRightWheel, 
                middleLeftWheel,middleRightWheel,
                rearLeftWheel,  rearRightWheel
                );				break;
				
            }
            
          
		}
        leftEncoder = RobotConfigurator.buildEncoder(leftEncoderMotor , RobotMap.chassis_leftEncoder[0], RobotMap.chassis_leftEncoder[1]);
        rightEncoder = RobotConfigurator.buildEncoder(rightEncoderMotor , RobotMap.chassis_rightEncoder[0],RobotMap.chassis_rightEncoder[1]);
        
	}

	// Returns encoders

	public TecbotEncoder getLeftEncoder() {
		return leftEncoder;
	}

	public void driveSideLeft(double s){
		switch(RobotMap.chassis_typeOfConfiguration){
		case MOTORS_2:
			rearLeftWheel.set(s);
			break;
		case MOTORS_4:
			frontLeftWheel.set(s);
			rearLeftWheel.set(s);
			break;
		case MOTORS_6:
			frontLeftWheel.set(s);
			rearLeftWheel.set(s);
			middleLeftWheel.set(s);
		}
	}
	public void driveSideRight(double s){
		switch(RobotMap.chassis_typeOfConfiguration){
		case MOTORS_2:
			rearRightWheel.set(s);
			break;
		case MOTORS_4:
			rearRightWheel.set(s);
			frontRightWheel.set(s);
			break;
		case MOTORS_6:
			rearRightWheel.set(s);
			frontRightWheel.set(s);
			middleRightWheel.set(s);
		}
	}
	
	
	public TecbotEncoder getRightEncoder() {
		return rightEncoder;
	}

	// Transmission of the wheel
	public boolean getTransmissionStatus() {
		return transmissionStatus;
	}


	
	// Invert the front orientation from the chassis

	public void changeOrientation() {
		reverse = !reverse;
	}

	// Change the axis orientation of the robot
	// @Param reverse is to change a desired state true or false

	public void setOrientation(boolean reverse) {
		this.reverse = reverse;
	}

	// Returns if the robot orientation is inverted

	public boolean getOrientation() {
		return reverse;
	}

	// Set a speed and rotation angle for the robot

	public void drive(double axis, double rotationAngle) {
		if (reverse)
			drive.arcadeDrive(-axis, rotationAngle);
		else
			drive.arcadeDrive(axis, rotationAngle);
	}

	public void printEncValues(){
		System.out.println("FR Enc Value: " + leftEncoder.getDistance());
		System.out.println("FL Enc Value: " + rightEncoder.getDistance());
	}
	
	public double drivingrot(double targetIn){
		double MAX_FORCE = 1;
		double MAX_DIFF_ALLOWED = 45;
		//double targetToIn = 0.0;
		double actualEncValue = 0.0;
		
		actualEncValue = Robot.tecbotgyro.getYaw(); 
		System.out.println("------Angleeeeeeeee!!!!!!" + actualEncValue + "/// " + targetIn);
		//double actualInEncValue = actualEncValue / TecbotConstants.kIn_Conversion_Number;
//		if (targetIn > 0)
//			targetToIn = actualInEncValue + targetIn;
//		else
//			targetToIn = actualInEncValue - targetIn;
//		target = targetToIn * TecbotConstants.kIn_Conversion_Number;
		target = targetIn;
		
		double diff = Math.abs(target - actualEncValue);

		double pct = Math.min(1, diff / MAX_DIFF_ALLOWED);

		int direction = 1;
		
		if( target < actualEncValue){
			direction = -1;
		}

		double finalMagnitude = direction * MAX_FORCE - (1 - pct * MAX_FORCE);

		System.out.println("Final Mag " + finalMagnitude );
		
		diffAng = diff;
		drive(0,finalMagnitude);
		return diff;
	}
	
	public void driving(double targetIn, int position) {

		double MAX_FORCE = 0.6;
		double MAX_DIFF_ALLOWED = 1000;
		//double targetToIn = 0.0;
		double actualEncValue = 0.0;
		switch (position) {
		case 0:
			actualEncValue = leftEncoder.getDistance();
			break;
		case 1:
			actualEncValue = rightEncoder.getDistance();
			break;
		default:
			actualEncValue = 0.0;
		}
//		double actualInEncValue = actualEncValue / TecbotConstants.kIn_Conversion_Number;
//		if (targetIn > 0)
//			targetToIn = actualInEncValue + targetIn;
//		else
//			targetToIn = actualInEncValue - targetIn;
//		target = targetToIn * TecbotConstants.kIn_Conversion_Number;
		target = targetIn;
		
		double diff = target - actualEncValue;

		double pct = Math.min(1, diff / MAX_DIFF_ALLOWED);

		int direction = -1;
		if (diff > 0) {
			direction = 1;
		}

		double finalMagnitude = direction * MAX_FORCE - (1 - pct * MAX_FORCE);

		diffPos = Math.abs(diff);
		switch(position){
		case 0:
			driveSideLeft(finalMagnitude);
			break;
		case 1:
			driveSideRight(finalMagnitude);
			break;
			
		default:
			
			break;
		}
		
		/*
		 * if(Math.abs(target-actualEncValue)>=1000 && target-actualEncValue<0){
		 * Robot.t_tester.driveTalon(-0.6); }
		 * if(Math.abs(target-actualEncValue)>=1000 && target-actualEncValue>0){
		 * Robot.t_tester.driveTalon(0.6); }
		 * if(Math.abs(target-actualEncValue)>=800 &&
		 * Math.abs(target-actualEncValue)<899 && (target-actualEncValue<0)){
		 * Robot.t_tester.driveTalon(-0.5); }
		 * if(Math.abs(target-actualEncValue)>=800 &&
		 * Math.abs(target-actualEncValue)<899&& (target-actualEncValue>0)){
		 * Robot.t_tester.driveTalon(0.5); }
		 * if(Math.abs(target-actualEncValue)>=600 &&
		 * Math.abs(target-actualEncValue)<699 && (target-actualEncValue<0)){
		 * Robot.t_tester.driveTalon(-0.4); }
		 * if(Math.abs(target-actualEncValue)>=600 &&
		 * Math.abs(target-actualEncValue)<699 && (target-actualEncValue>0)){
		 * Robot.t_tester.driveTalon(0.4); }
		 * if(Math.abs(target-actualEncValue)>=400 &&
		 * Math.abs(target-actualEncValue)<499 && (target-actualEncValue<0)){
		 * Robot.t_tester.driveTalon(-0.3); }
		 * if(Math.abs(target-actualEncValue)>=400 &&
		 * Math.abs(target-actualEncValue)<499 && (target-actualEncValue>0)){
		 * Robot.t_tester.driveTalon(0.3); }
		 * if(Math.abs(target-actualEncValue)<=399 &&
		 * Math.abs(target-actualEncValue)>=0 && (target-actualEncValue<0)){
		 * Robot.t_tester.driveTalon(0); }
		 * if(Math.abs(target-actualEncValue)<=399 &&
		 * Math.abs(target-actualEncValue)>=0 && (target-actualEncValue>0)){
		 * Robot.t_tester.driveTalon(0); } System.out.println(
		 * "Difference between targets: " + (Math.abs(target-actualEncValue)));
		 * System.out.println(Robot.t_tester.getTalonSetValue());
		 */
	}

	// Uses the joystick to move the robot

	public double getDiff() {
		return diffPos;
	}
	
	public double getDiffAng(){
		return diffAng;
	}

	public void drive() {	
		drive(-Robot.oi.getPilot().getRawAxis(1), Robot.oi.getPilot().getRawAxis(0));
		//drive(Math.max(Robot.oi.getPilot().getY(), Robot.oi.getCopilot().getY()), Math.max((Robot.oi.getPilot().getX()), Robot.oi.getCopilot().getX()));
	}

	// Switch drivers controls

	

	// Stop DriveTrain

	public void stop() {
		drive.arcadeDrive(0, 0);
	}

	// Turn transmission on

	public void transmissionOn() {
		transmission.set(DoubleSolenoid.Value.kForward);
		transmissionState = true;
	}

	// Turn transmission off

	public void transmissionOff() {
		transmission.set(DoubleSolenoid.Value.kReverse);
		transmissionState = false;
	}

	// Set transmission to a desired state
	// @Param state is for enabling the transmission on a desired state

	public void setTransmission() {
		if (transmissionState)
			transmissionOn();
		else
			transmissionOff();
	}

	// @return transmission state

	public boolean getTransmission() {
		return transmissionState;
	}

	// Switch transmission state

	public void transmissionLatch() {
		if (transmissionState)
			transmissionOff();
		else
			transmissionOn();
	}

	// Returns the motors

	public TecbotSpeedController getFrontRightWheel() {
		return frontRightWheel;
	}

	public TecbotSpeedController getFrontLeftWheel() {
		return frontLeftWheel;
	}

	public TecbotSpeedController getRearRightWheel() {
		return rearRightWheel;
	}

	public TecbotSpeedController getRearLeftWheel() {
		return rearLeftWheel;
	}

	public TecbotSpeedController getMiddleRightWheel() {
		return middleRightWheel;
	}

	public TecbotSpeedController getMiddleLeftWheel() {
		return middleLeftWheel;
	}

	// Set the default command for the driveTrain if this subsystem is not on
	// use

	protected void initDefaultCommand() {
		setDefaultCommand(new DefaultDriveCommand() );
	}


	@Override
	public State checkState() {
		return State.Correct;
	}

	@Override
	public void correct() {
		System.out.println("Jala shido");
	}

	@Override
	public void warning() {
		System.out.println("Warning");
	}

	@Override
	public void danger() {
		System.out.println("Daaaaangerouuuusssssss");
	}
}



