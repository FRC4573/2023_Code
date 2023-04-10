package frc.robot;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class ArmSpeed extends CommandBase {
  // The subsystem the command runs on
  Arm m_armSubsystem;
  RelativeEncoder encoder;
  boolean isCommandFinished;
  double angle;
  public ArmSpeed(Arm subsystem, double angle) {
    m_armSubsystem = subsystem;
    this.angle = angle;
    encoder = m_armSubsystem.gEncoder();
    addRequirements(m_armSubsystem);
  }

  @Override
  public void initialize() {
    isCommandFinished = false;
    encoder.setPosition(0);
  }
  @Override
  public void execute(){
    if(encoder.getPosition()>=angle){
      m_armSubsystem.armDownSuperFast();
    }else{
      isCommandFinished = true;
    }
  }

  @Override
  public void end(boolean interrupted){
    m_armSubsystem.stop();
  }

  @Override
  public boolean isFinished(){
    return isCommandFinished;
  }


}