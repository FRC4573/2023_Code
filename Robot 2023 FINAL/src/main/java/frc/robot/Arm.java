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


import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.kForward;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.kReverse;

import edu.wpi.first.util.sendable.SendableBuilder;
public class Arm extends SubsystemBase{
  CANSparkMax m_ArmAngle;
  RelativeEncoder encoder;
  DoubleSolenoid m_armSolenoid;
         
public Arm(){
  m_ArmAngle = new CANSparkMax(10,MotorType.kBrushless);
  encoder =  m_ArmAngle.getEncoder();
  m_armSolenoid =     new DoubleSolenoid(
        PneumaticsModuleType.CTREPCM,
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
    return run(()->m_ArmAngle.set(0.3));
  }
  public CommandBase stopArm(){
    return run(()->m_ArmAngle.set(0.0));
  }
  public CommandBase backwardArm(){
    return run(()->m_ArmAngle.set(-0.3));
  }
  public CommandBase getArmAngle(){
    return run(()->System.out.println(encoder.getPosition()));
  }
  boolean isNext = false;

  public CommandBase setArmAngleBackward(double angle){
    return run(()->{
      if(encoder.getPosition()>= angle){ 
        m_ArmAngle.set(-0.25);
      }
    });
  }
  public CommandBase setArmAngleForward(double angle){
    return run(()->{
      if(encoder.getPosition()<= angle){ 
        m_ArmAngle.set(0.25);
      }
    });
  }
  public CommandBase extendArmAuto(){
    return run(()->{
      m_armSolenoid.set(kForward);
    });
  }
  public CommandBase retractArmAuto(){
    return run(()->{
      m_armSolenoid.set(kReverse);
    });
  }
  public CommandBase openHands(){
    return run (()->{
      RobotContainer.gHands().openHands();
    });
  }
  public SequentialCommandGroup routine(){
    return new ScheduleCommand(setArmAngleForward(-60)).andThen(stopArm()).andThen(extendArmAuto()).andThen(setArmAngleBackward(-17).andThen(stopArm()));
  }
  public CommandBase setArmAngle(){
    return run(()->{
      if(encoder.getPosition()>=-60){ 
        m_ArmAngle.set(-0.25);
    }else{
      //This is polling
      if(!isNext){
        m_ArmAngle.set(0);
        isNext = true;
      }
      retractArm();

      try {
        Thread.sleep(2500);
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      RobotContainer.gHands().openHands();
      if(encoder.getPosition()<=-30){ 
        m_ArmAngle.set(0.4);
      }else{
        m_ArmAngle.set(0);
      }
    
    }});
  }
}
