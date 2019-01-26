package frc.robot.math;

public class Math {

	public static double clamp (double p, double mn, double mx){
		
		return java.lang.Math.max(mn, java.lang.Math.min(p,mx));
	}
	public static double max(double a, double b){
		return java.lang.Math.max(a, b);
	}
	public static double min(double a, double b){
		return java.lang.Math.min(a, b);
	}
	public static double abs(double a){
		return java.lang.Math.abs(a);
	}
}
