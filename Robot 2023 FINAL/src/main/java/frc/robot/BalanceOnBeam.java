package frc.robot;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class BalanceOnBeam extends CommandBase {
Drivetrain m_DriveSubsystem; // drivetrain subsystem
  double degreesToTurn; // the number of degrees we wish to turn
  double error; // How "incorrect" the current angle of the robot is as its moving
  double currentAngle; // targetAngle = initial angle + degreesToTurn
  boolean isCommandFinished;
  public BalanceOnBeam() {
    m_DriveSubsystem = RobotContainer.gDrivetrain();
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
    this.currentAngle = m_DriveSubsystem.getPitch();
    error = 0 - currentAngle;
    System.out.println("error: " + error);
    //if direction is true then turn right
    boolean direction = degreesToTurn > 0 ? true: false;
    if(0 <  this.currentAngle && error > 1 && !direction){
      //turn left
      m_DriveSubsystem.arcadeDrive(0.5,0.0);
      System.out.println("Current angle 1: " + m_DriveSubsystem.getAngle());
    }else if(0> this.currentAngle && error>1 && direction){
      m_DriveSubsystem.arcadeDrive(-0.5,0.0);
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
