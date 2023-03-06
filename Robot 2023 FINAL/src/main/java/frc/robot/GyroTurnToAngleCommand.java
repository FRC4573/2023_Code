package frc.robot;

// Author: UMN Robotics Ri3d
// Last Updated : January 2023


import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.Drivetrain;

// Uses PID control to align to the target angle using gyroscope feedback
public class GyroTurnToAngleCommand extends CommandBase {

  Drivetrain m_DriveSubsystem; // drivetrain subsystem
  double degreesToTurn; // the number of degrees we wish to turn
  double error; // How "incorrect" the current angle of the robot is as its moving
  double targetAngle; // targetAngle = initial angle + degreesToTurn
  boolean isCommandFinished;
  /** Turns to an angle relative to the current angle using the gyro */
  public GyroTurnToAngleCommand(double degreesToTurn) {
    m_DriveSubsystem = RobotContainer.gDrivetrain();
    m_DriveSubsystem.zeroGyro();
    addRequirements(m_DriveSubsystem);
    this.degreesToTurn = degreesToTurn;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_DriveSubsystem.resetEncoders();
    m_DriveSubsystem.zeroGyro();
    this.targetAngle = degreesToTurn + m_DriveSubsystem.getAngle();
    System.out.println("CURRENT ANGLE:" + m_DriveSubsystem.getAngle());
    System.out.println("TARGET ANGLE:" + targetAngle);
    isCommandFinished = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double error = Math.abs(targetAngle - m_DriveSubsystem.getAngle());
    System.out.println("target angle: " + targetAngle);
    //figure out the direction of the turn
    boolean direction = degreesToTurn > 0 ? true: false;
    //if the direction is false turn right
    //modify so it stops once the angle is exceeded
    if(targetAngle <  m_DriveSubsystem.getAngle() && error > 5 && !direction){
      //turn left
      m_DriveSubsystem.arcadeDrive(0.0,0.6);
      System.out.println("Current angle 1: " + m_DriveSubsystem.getAngle());
    //otherwise if the direction is true turn left
    }else if(targetAngle> -m_DriveSubsystem.getAngle() && error>5 && direction){
      m_DriveSubsystem.arcadeDrive(0.0,-0.6);
      System.out.println("Current angle 2: " + m_DriveSubsystem.getAngle());
    }else{
      isCommandFinished = true;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_DriveSubsystem.stop(); // Stop the drivetrain motors
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return isCommandFinished; // End the command when we are within the specified threshold of our target
  }
}