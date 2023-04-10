package frc.robot;

// Author: UMN Robotics Ri3d
// Last Updated : January 2023


import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.Drivetrain;

// This command drives a specified number of meters
public class DriveForDistanceCommand extends CommandBase {

  Drivetrain m_DriveSubsystem;
  double initialDistance;
  double distance;
  double percentPower;
  boolean isCommandFinished;
  /** Creates a new DriveForDistanceCommand. */
  public DriveForDistanceCommand(double distance, double percentPower) {
    m_DriveSubsystem = RobotContainer.gDrivetrain();

    this.distance = distance;
    this.percentPower = percentPower;
    addRequirements(m_DriveSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    m_DriveSubsystem.resetEncoders();
    m_DriveSubsystem.zeroGyro();
    initialDistance = m_DriveSubsystem.getRightDistance();
    System.out.println("INITIAL DISTANCE: " + initialDistance);
    isCommandFinished = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // Print statements for debugging
    if(m_DriveSubsystem.getRightDistance()<distance){
      m_DriveSubsystem.drive(percentPower, percentPower);
      System.out.println("current Distance" + m_DriveSubsystem.getRightDistance() + "Target Distance" + distance);
    }else{
      isCommandFinished = true;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
      m_DriveSubsystem.stop(); // Stop the rivetrain motors
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return isCommandFinished;
  }
}