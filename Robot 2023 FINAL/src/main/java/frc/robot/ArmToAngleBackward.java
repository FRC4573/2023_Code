package frc.robot;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class ArmToAngleBackward extends CommandBase {
  // The subsystem the command runs on
  private Arm m_armSubsystem;
  RelativeEncoder encoder;
  boolean isCommandFinished;

  public ArmToAngleBackward(Arm subsystem) {
    m_armSubsystem = subsystem;
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
    if(encoder.getPosition()<=60){
      m_armSubsystem.armUpFast();
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