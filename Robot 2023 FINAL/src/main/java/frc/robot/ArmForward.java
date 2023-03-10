package frc.robot;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ArmForward extends CommandBase {
  // The subsystem the command runs on
  private final Arm m_armSubsystem;

  public ArmForward(Arm subsystem) {
    m_armSubsystem = subsystem;
    addRequirements(m_armSubsystem);
  }

  @Override
  public void initialize() {
    
  }
  @Override
  public void execute(){
    m_armSubsystem.armUpSlow();
  }


  @Override
  public boolean isFinished() {
    m_armSubsystem.stop();
    return true;
  }
}