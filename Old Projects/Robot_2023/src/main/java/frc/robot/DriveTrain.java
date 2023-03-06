package frc.robot;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

 import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveTrain{

    private WPI_VictorSPX m_frontRight = new WPI_VictorSPX(1);
    private WPI_VictorSPX m_frontLeft = new WPI_VictorSPX(2);
    private WPI_VictorSPX m_rearRight = new WPI_VictorSPX(3);
    private WPI_VictorSPX m_rearLeft = new WPI_VictorSPX(4);
 
    private MotorControllerGroup m_left = new MotorControllerGroup(m_frontLeft, m_rearLeft);
    private MotorControllerGroup m_right = new MotorControllerGroup(m_frontRight, m_rearRight);
    private DifferentialDrive m_drive = new DifferentialDrive(this.m_left, this.m_right);

  public DriveTrain(){
    m_drive.setExpiration(0.50);
    m_drive.arcadeDrive(0, 0, true);
    m_drive.setSafetyEnabled(false);
  }

public DifferentialDrive getDriveTrain(){
  return this.m_drive;
}

  public MotorControllerGroup getLeftSpeedController(){
    return this.m_left;
  }

  public MotorControllerGroup getRightSpeedController(){
    return this.m_right;
  }
}