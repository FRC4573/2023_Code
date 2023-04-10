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
  double speed = 0.6;
  int offbalancepositivehalf = 7;
  /** Turns to an angle relative to the current angle using the gyro */
  public BalanceOnBeam() {
    m_DriveSubsystem = RobotContainer.gDrivetrain();
    m_DriveSubsystem.zeroGyro();
    addRequirements(m_DriveSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_DriveSubsystem.resetEncoders();
    m_DriveSubsystem.zeroGyro();
    isCommandFinished = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    if (m_DriveSubsystem.getPitch() >= offbalancepositivehalf){
      m_DriveSubsystem.arcadeDrive(speed,0.0);
    //otherwise if the direction is true turn left
    }else if(m_DriveSubsystem.getPitch() <= -offbalancepositivehalf){
      System.out.println("drive backwards");
      m_DriveSubsystem.arcadeDrive(speed, 0.0);
    }else{
      isCommandFinished = true;
    }
    speed = 0.36 + m_DriveSubsystem.getPitch()/100;
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