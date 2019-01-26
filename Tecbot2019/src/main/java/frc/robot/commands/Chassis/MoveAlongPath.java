package frc.robot.commands.Chassis;

import frc.robot.Robot;
import frc.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.PathfinderFRC;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.followers.EncoderFollower;

public class MoveAlongPath extends Command {

    EncoderFollower m_left_follower, m_right_follower;

    String k_path_name;

    public MoveAlongPath(String name) {
        requires(Robot.driveTrain);
        k_path_name = name;
    }

    protected void initialize() {

        Trajectory leftTrajectory, rightTrajectory;
        leftTrajectory = PathfinderFRC.getTrajectory(k_path_name + ".left");
        rightTrajectory = PathfinderFRC.getTrajectory(k_path_name + ".right");

        m_left_follower = new EncoderFollower(leftTrajectory);
        m_right_follower = new EncoderFollower(rightTrajectory);

        m_left_follower.configureEncoder(Robot.leftEncoder.getRaw(), RobotMap.k_tic_per_revolution,
                RobotMap.k_wheel_diameter);
        m_left_follower.configureEncoder(Robot.leftEncoder.getRaw(), RobotMap.k_tic_per_revolution,
                RobotMap.k_wheel_diameter);

        m_left_follower.configurePIDVA(RobotMap.straight_P, RobotMap.straight_I, RobotMap.straight_D, .5, 1);
        m_right_follower.configurePIDVA(RobotMap.straight_P, RobotMap.straight_I, RobotMap.straight_D, .5, 1);

    }

    protected void execute() {
        double deltaAngle = Pathfinder.r2d(m_left_follower.getHeading()) - Robot.tecbotgyro.getYaw();
        if (deltaAngle < -180)
            deltaAngle += 360;
        if (deltaAngle > 180)
            deltaAngle -= 360;

        double leftSpeed = m_left_follower.calculate(Robot.leftEncoder.getRaw());
        double rightSpeed = m_right_follower.calculate(Robot.rightEncoder.getRaw());

        Robot.driveTrain.driveSideLeft(-leftSpeed + ((deltaAngle / 180) * 5));
        Robot.driveTrain.driveSideRight(rightSpeed + ((deltaAngle / 180) * 5));
    }

    protected boolean isFinished() {
        return (m_left_follower.isFinished() || m_right_follower.isFinished());
    }

    protected void end() {

    }

    protected void interrupted() {

    }

}