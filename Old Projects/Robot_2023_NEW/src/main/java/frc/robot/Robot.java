// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Encoder;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  Joystick drive_stick = new Joystick(0);
  Joystick control_stick = new Joystick(1);
  //DoubleSolenoid soli = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 0, 1);

  double m_deadZone;
  double m_driveMotorSpeed;
  double m_driveTurnSpeed;
  double displayCtr;

  WPI_VictorSPX m_frontRight = new WPI_VictorSPX(3);
  WPI_VictorSPX m_frontLeft = new WPI_VictorSPX(4);
  WPI_VictorSPX m_rearRight = new WPI_VictorSPX(1);
  WPI_VictorSPX m_rearLeft = new WPI_VictorSPX(2);


 /*
  CANSparkMax m_frontRight = new CANSparkMax(5,MotorType.kBrushed);
  CANSparkMax m_frontLeft = new CANSparkMax(7,MotorType.kBrushed);
  CANSparkMax m_rearRight = new CANSparkMax(6,MotorType.kBrushed);
  CANSparkMax m_rearLeft = new CANSparkMax(8,MotorType.kBrushed);
  */




  
  MotorControllerGroup m_left = new MotorControllerGroup(m_frontLeft, m_rearLeft);
  MotorControllerGroup m_right = new MotorControllerGroup(m_frontRight, m_rearRight);

  DifferentialDrive m_drive = new DifferentialDrive(m_left, m_right);

  AHRS gyro = new AHRS(SPI.Port.kMXP);
  DoubleSolenoid soli = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 0, 1);
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {

    System.out.println("Robot Init: ");

    m_deadZone = 0.3;
    m_driveMotorSpeed = 1;
    m_driveTurnSpeed = 0.75;
    displayCtr = 0;
    //////////////////////////Speed Settings\\\\\\\\\\\\\\\\\\\\\\\\\



    //////////////////////////Speed Settings\\\\\\\\\\\\\\\\\\\\\\\\\
    

    m_drive.setExpiration(0.50);
    m_drive.arcadeDrive(0, 0, true);
    m_drive.setSafetyEnabled(false);
   
  }

  //*********************************************************************************\\
  // This function is called every robot packet, no matter the mode. Use this for    \\
  // items like diagnostics that you want ran during disabled, autonomous,           \\
  // teleoperated and test.                                                          \\
  //                                                                                 \\                                                                            \\
  // This runs after the mode specific periodic functions, but before LiveWindow     \\
  // and SmartDashboard integrated updating.                                         \\
  //*********************************************************************************\\
  

  @Override
  public void autonomousInit() {
    System.out.println("StartAutoInit");
    System.out.println("EndAutoInit");
  }

 
  @Override
  public void autonomousPeriodic() {
  
  //code for autonomous period, timer and while recommended \\

    }

  //********************************************************\\
  // This function is called at the start of Teloeop.       \\
  //********************************************************\\
  @Override
  public void teleopInit() {
    m_drive.arcadeDrive(0.0,0.0);
  }

  //*******************************************************************\\
  // This function is called periodically during operator control.     \\
  //*******************************************************************\\
  @Override
  public void teleopPeriodic() {

    // Get Drive Joystick input for arcade driving

    double X = getJoystickValue(drive_stick, 1) * m_driveMotorSpeed;
    double Z = getJoystickValue(drive_stick, 2) * m_driveTurnSpeed;
    
    m_drive.arcadeDrive(-X, Z, true); // Drive the robot
   
    soli.set(Value.kOff);
    if(control_stick.getRawButtonPressed(1) == true)
      soli.set(Value.kForward);
    else if (control_stick.getRawButton(2) == true)
      soli.set(Value.kReverse);

  }
  @Override
  public void robotPeriodic() {
    
  }
  

  @Override
  public void testPeriodic() {
    
  }

  //*******************************************************************\\
  //This function is used to read joystick & eliminate deadzone issues \\
  //*******************************************************************\\

  public double getJoystickValue(Joystick joystick, int iKey) {
    double dVal = joystick.getRawAxis(iKey);
    if (Math.abs(dVal) < m_deadZone) {
      return 0;
    } else {
      return dVal;
    }
  }
}
  
  