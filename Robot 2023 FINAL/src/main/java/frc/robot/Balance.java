package frc.robot;

import javax.sound.midi.SysexMessage;

// Author: UMN Robotics Ri3d
// Last Updated : January 2023


import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.Drivetrain;

// Uses PID control to align to the target angle using gyroscope feedback
public class Balance extends CommandBase {

  Drivetrain m_DriveSubsystem; // drivetrain subsystem
  double degreesToTurn; // the number of degrees we wish to turn
  double error; // How "incorrect" the current angle of the robot is as its moving
  double targetAngle; // targetAngle = initial angle + degreesToTurn
  boolean isCommandFinished;
  double distanceToDrive;
  double prevDistance;
  int counter;
  boolean firstIteration;
  /** Turns to an angle relative to the current angle using the gyro */
  public Balance() {
    m_DriveSubsystem = RobotContainer.gDrivetrain();
    m_DriveSubsystem.zeroGyro();
    addRequirements(m_DriveSubsystem);
    
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_DriveSubsystem.resetEncoders();
    m_DriveSubsystem.zeroGyro();
    System.out.println("CURRENT ANGLE:" + m_DriveSubsystem.getPitch());
    System.out.println("TARGET ANGLE:" + targetAngle);
    isCommandFinished = false;
    this.distanceToDrive = 0.002;//0.0015 //0.01 0.045
    firstIteration = true;
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    //if the direction is false turn right
    //modify so it stops once the angle is exceeded
    /*
     * Set a starting distance of 1 meter
     * If the angle is the same sign as the previous angle go in the same direction same distance
     * If angle is the opposite sign go half the distance in the opposite direction
     *  Check if angle is negative or positive
     * 
     * 
     */
    //if the direction is in range of 7 and -7 we want to stop
    //if the pitch is between -7 and 7 set = 0, else if m_DriveSubsystem.getPitch()>-7 set 1//drive back if -1 drive forward
    int todo = m_DriveSubsystem.getPitch()>-6 && m_DriveSubsystem.getPitch() < 6 ? 0 : m_DriveSubsystem.getPitch()>-6 ? 1 : -1;
    prevDistance = this.distanceToDrive;
    if(todo == 1){
        //drive forward
        System.out.println("Drive Forward");
        if(m_DriveSubsystem.getRightDistance() < distanceToDrive){
            m_DriveSubsystem.arcadeDrive(0.6, 0.0 );
        }else{
            //if the distance is driven reset the encoders
            this.distanceToDrive = m_DriveSubsystem.getPitch()>10 ? Math.abs(this.distanceToDrive):-Math.abs(this.distanceToDrive/-350);
            System.out.println(this.distanceToDrive);
            m_DriveSubsystem.resetEncoders();
        }
            //once 1 meter is reached check the angle and update the distance
            /* 
            this.distanceToDrive = Math.floor(m_DriveSubsystem.getPitch())<-7 ? -Math.abs(this.distanceToDrive):Math.abs(this.distanceToDrive/-2);
            if(prevDistance!=this.distanceToDrive){
                System.out.println("Encoders reset");
                 m_DriveSubsystem.resetEncoders();
        
            }
            */
    }else if(todo == -1){
        //drive forward
        System.out.println("Drive Backward");
        if(m_DriveSubsystem.getRightDistance() > distanceToDrive){
            m_DriveSubsystem.arcadeDrive(-0.6, 0.0 );
        }else{
            //if the distance is driven reset the encoders
            this.distanceToDrive = m_DriveSubsystem.getPitch()<-10 ? -Math.abs(this.distanceToDrive):Math.abs(this.distanceToDrive/-350);
            m_DriveSubsystem.resetEncoders();
        }
        /* 
        this.distanceToDrive = Math.floor(m_DriveSubsystem.getPitch())>7 ? Math.abs(this.distanceToDrive):-Math.abs(this.distanceToDrive/-2);
        if(prevDistance!=this.distanceToDrive){
            System.out.println("Encoders reset");
            m_DriveSubsystem.resetEncoders();
       }
       */
    }else if(todo==0){
        //finished
        System.out.println("Command Finished");
        //isCommandFinished = true;
    }
    /* 
    boolean direction = Math.floor(m_DriveSubsystem.getPitch())>0 ? true:false;
    prevDistance = this.distanceToDrive;
    //TODO: If we reached the distance and the direction is the same reset the encoders
    //problem distance is not changed when 
    if(direction){
        //go forward 1 meter
        System.out.println("Go Forward");
        if(m_DriveSubsystem.getRightDistance() < distanceToDrive){
            m_DriveSubsystem.arcadeDrive(0.55, 0.0 );
        }
            //once 1 meter is reached check the angle and update the distance
        this.distanceToDrive = Math.floor(m_DriveSubsystem.getPitch())>0 ? Math.abs(this.distanceToDrive):-Math.abs(this.distanceToDrive/-2);
        if(prevDistance!=this.distanceToDrive || Math.floor(m_DriveSubsystem.getPitch())>0){
            m_DriveSubsystem.resetEncoders();
            counter++;
        }
        }
    else if(!direction){
        //go backward 1 meter
        System.out.println("Go Backward");
        System.out.println("right distance" + m_DriveSubsystem.getRightDistance()
        );
        System.out.println("distance to drive" + distanceToDrive);
        if(m_DriveSubsystem.getRightDistance() > distanceToDrive){
            m_DriveSubsystem.arcadeDrive(-0.55, 0.0 );
        }
            //once 1 meter is reached check the angle and update the distance
            this.distanceToDrive = Math.floor(m_DriveSubsystem.getPitch())<0 ? -Math.abs(this.distanceToDrive):Math.abs(this.distanceToDrive/-2);
            if(prevDistance!=this.distanceToDrive||Math.floor(m_DriveSubsystem.getPitch())<0){
                m_DriveSubsystem.resetEncoders();
                counter++;
            }
    }
    //this should execute after the prev distance changes 4 times
    if(counter >= 10 && Math.floor(m_DriveSubsystem.getPitch())>=-6 && Math.floor(m_DriveSubsystem.getPitch())<=6 ){
            isCommandFinished = true;
            System.out.println("command concluded");
        }
        */
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