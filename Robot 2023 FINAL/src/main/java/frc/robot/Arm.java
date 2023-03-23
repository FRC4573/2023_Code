package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ScheduleCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.kForward;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.kReverse;

import edu.wpi.first.util.sendable.SendableBuilder;
public class Arm extends SubsystemBase{
  CANSparkMax m_ArmAngle;
  RelativeEncoder encoder;
  DoubleSolenoid m_armSolenoid;
  Compressor compressor;
         
public Arm(){
  m_ArmAngle = new CANSparkMax(10,MotorType.kBrushless);
  encoder =  m_ArmAngle.getEncoder();
  m_armSolenoid =     new DoubleSolenoid(
        PneumaticsModuleType.REVPH,
        2,
        3);
}
  
  public RelativeEncoder gEncoder(){
    return encoder;
  }
  public void extendArm() {
    m_armSolenoid.set(kForward);
  }

  
  /** Releases the hatch. */
  public void retractArm() {
    m_armSolenoid.set(kReverse);
  }
  public void armUpSlow(){
    m_ArmAngle.set(0.3);
  }
  public void armDownSlow(){
    m_ArmAngle.set(-0.3);
  }
  public void armUpFast(){
    m_ArmAngle.set(0.4);
  }
  public void armDownFast(){
    m_ArmAngle.set(-0.4);
  }
  public void stop(){
    m_ArmAngle.set(0.0);
  }
  @Override
  public void initSendable(SendableBuilder builder) {
    super.initSendable(builder);
    // Publish the solenoid state to telemetry.
    builder.addBooleanProperty("extended", () -> m_armSolenoid.get() == kForward, null);
  }
  public CommandBase forwardArm(){
    return run(()->m_ArmAngle.set(0.5));
  }
  public CommandBase stopArm(){
    return run(()->m_ArmAngle.set(0.0));
  }
  public CommandBase backwardArm(){
    return run(()->m_ArmAngle.set(-0.5));
  }
  public CommandBase getArmAngle(){
    return run(()->System.out.println(encoder.getPosition()));
  }
  
}
