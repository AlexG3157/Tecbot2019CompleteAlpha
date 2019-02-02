package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.arm.*;
import frc.robot.commands.autonomous.*;
import frc.robot.commands.chassis.ToggleTransmission;

public class OI {
	public boolean ps4 = RobotMap.isUsingPS4Controller;
	public Joystick pilot, copilot;
	public JoystickButton start, back, a, b, x, y, rt, lt, rb, lb;
	public boolean isShift;

	public OI() {
		pilot = new Joystick(0);
		a = new JoystickButton(pilot, 1);
		a.whenPressed(new ToggleTransmission());
		copilot = new Joystick(1);

		if (ps4) {
			x = new JoystickButton(pilot, 1);
			a = new JoystickButton(pilot, 2);
			b = new JoystickButton(pilot, 3);
			y = new JoystickButton(pilot, 4);
			lb = new JoystickButton(pilot, 5);
			rb = new JoystickButton(pilot, 6);
			back = new JoystickButton(pilot, 9);
			start = new JoystickButton(pilot, 10);

			lb.whenPressed(new ToggleShift());
			lb.whenReleased(new ToggleShift());

			a.whenPressed(new CargoHatch());
			b.whenPressed(new CargoHatchLv2());
			y.whenPressed(new CargoHatchLv3());

			rb.whenPressed(new PickHatch());
			// R1.whenPressed(new ClawTeleop());
			// R2.whenPressed(new ExtenderTeleop());
		} else {

			a = new JoystickButton(pilot, 1);
			b = new JoystickButton(pilot, 2);
			x = new JoystickButton(pilot, 3);
			y = new JoystickButton(pilot, 4);
			lb = new JoystickButton(pilot, 5);
			rb = new JoystickButton(pilot, 6);
			back = new JoystickButton(pilot, 7);
			start = new JoystickButton(pilot, 8);

			lb.whenPressed(new ToggleShift());
			lb.whenReleased(new ToggleShift());

			a.whenPressed(new CargoHatch());
			b.whenPressed(new CargoHatchLv2());
			x.whenPressed(new CargoHatchLv3());

			// L2.whenPressed(new PickHatch());
			rb.whenPressed(new ClawTeleop());
			// R2.whenPressed(new ExtenderTeleop());

		}

	}

	public Joystick getPilot() {
		return pilot;
	}

	public Joystick getCopilot() {
		return copilot;
	}

	public double getPilotRightStickX() {
		if (ps4) {
			return pilot.getRawAxis(2);
		} else {
			return pilot.getRawAxis(4);
		}
	}

	public double getPilotRightStickY() {
		return pilot.getRawAxis(5);
	}
}