package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.Arm.*;
import frc.robot.commands.Autonomous.*;

public class OI {
	public boolean ps4 = true;
	public Joystick pilot, copilot;
	public JoystickButton start, select, a, b, rt, lt, rb, lb, y, X, O, square, TRNGL, L1, R1, L2, R2, Share, Options, Pressha;
	public boolean isShift;
	public OI() {
    pilot = new Joystick(0);
  /*  copilot = new Joystick(1);
  
    if(ps4){
		square = new JoystickButton(pilot, 1);
		X = new JoystickButton(pilot, 2);
		O = new JoystickButton(pilot, 3);
		TRNGL = new JoystickButton(pilot, 4);
		L1= new JoystickButton(pilot, 5);
		R1= new JoystickButton(pilot, 6);
		L2 = new JoystickButton(pilot, 7);
		R2 = new JoystickButton(pilot, 8);
		Share = new JoystickButton(pilot, 9);
		Options = new JoystickButton(pilot, 10);
		Pressha = new JoystickButton(pilot,11);
		
		L1.whenPressed(new ToggleShift());
		L1.whenReleased(new ToggleShift());
		
		square.whenPressed(new CargoHatch());
		O.whenPressed(new CargoHatchLv2());
		TRNGL.whenPressed(new CargoHatchLv3());
		
		L2.whenPressed(new PickHatch());
		R1.whenPressed(new ClawTeleop());
		R2.whenPressed(new ExtenderTeleop());
		} else{
		
		a = new JoystickButton(pilot, 1);
		b = new JoystickButton(pilot, 2);
		X = new JoystickButton(pilot, 3);
		y = new JoystickButton(pilot, 4);
		lb= new JoystickButton(pilot, 5);
		rb= new JoystickButton(pilot, 6);
		start = new JoystickButton(pilot, 7);
		select = new JoystickButton(pilot, 8);
		
		lb.whileHeld(new Control());
		
		a.whenPressed(new CargoHatch());
		b.whenPressed(new CargoHatchLv2());
		X.whenPressed(new CargoHatchLv3());
		
		//L2.whenPressed(new PickHatch());
		rb.whenPressed(new ClawTeleop());
		//R2.whenPressed(new ExtenderTeleop());

    }
    */
	}

	public Joystick getPilot() {
		return pilot;
	}

	public Joystick getCopilot() {
		return copilot;
	}
}