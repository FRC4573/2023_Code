package frc.robot;

// Author: UMN Robotics Ri3d
// Last Updated : January 2023


import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.Drivetrain;

// Uses PID control to align to the target angle using gyroscope feedback
public class BalanceOnBeam extends CommandBase {

  Drivetrain m_DriveSubsystem; // drivetrain subsystem
  double degreesToTurn; // the number of degrees we wish to turn
  double error; // How "incorrect" the current angle of the robot is as its moving
  double targetAngle; // targetAngle = initial angle + degreesToTurn
  boolean isCommandFinished;
  /** Turns to an angle relative to the current angle using the gyro */
  public BalanceOnBeam() {
    m_DriveSubsystem = RobotContainer.gDrivetrain();
    m_DriveSubsystem.zeroGyro();
    addRequirements(m_DriveSubsystem);
    this.degreesToTurn = -6;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_DriveSubsystem.resetEncoders();
    m_DriveSubsystem.zeroGyro();
    this.targetAngle = 11;
    System.out.println("CURRENT ANGLE:" + m_DriveSubsystem.getPitch());
    System.out.println("TARGET ANGLE:" + targetAngle);
    isCommandFinished = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    System.out.println("target angle: " + targetAngle);
    //figure out the direction of the turn
    boolean direction = degreesToTurn > 0 ? true: false;
    //if the direction is false turn right
    //modify so it stops once the angle is exceeded
    System.out.println("Target Angle " + targetAngle + "current Angle " + m_DriveSubsystem.getPitch()+ "direction "+ direction );
    if(targetAngle <  m_DriveSubsystem.getPitch() ){
      //turn left
      System.out.println("drive forward");
      m_DriveSubsystem.arcadeDrive(0.5,0.0);
    //otherwise if the direction is true turn left
    }else if(m_DriveSubsystem.getPitch() < targetAngle){
      System.out.println("drive backwards");
      m_DriveSubsystem.arcadeDrive(-0.5,0.0);
    }else{
      System.out.println("finished");
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