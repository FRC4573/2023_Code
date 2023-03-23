package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ScheduleCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Feeder extends SubsystemBase{
  CANSparkMax m_ArmAngle;
  RelativeEncoder encoder;
         
public Feeder(){
  m_ArmAngle = new CANSparkMax(8,MotorType.kBrushless);
  encoder =  m_ArmAngle.getEncoder();
}
  
  public RelativeEncoder gEncoder(){
    return encoder;
  }
  public void feedOut(){
    m_ArmAngle.set(0.3);
  }
  public void feedIn(){
    m_ArmAngle.set(-0.3);
  }
  public void stop(){
    m_ArmAngle.set(0.0);
  }
  public CommandBase feedOutCommand(){
    return run(()->m_ArmAngle.set(0.3));
  }
  public CommandBase stopFeed(){
    return run(()->m_ArmAngle.set(0.0));
  }
  public CommandBase feedInCommand(){
    return run(()->m_ArmAngle.set(-1.0));
  }
  public CommandBase getArmAngle(){
    return run(()->System.out.println(encoder.getPosition()));
  }
  
}
