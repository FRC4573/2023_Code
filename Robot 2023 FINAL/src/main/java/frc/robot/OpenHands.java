package frc.robot;


import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * A simple command that grabs a hatch with the {@link HatchSubsystem}. Written explicitly for
 * pedagogical purposes. Actual code should inline a command this simple with {@link
 * edu.wpi.first.wpilibj2.command.InstantCommand}.
 */
public class OpenHands extends CommandBase {
  // The subsystem the command runs on
  private final Hand m_handSubsystem;

  public OpenHands(Hand subsystem) {
    m_handSubsystem = subsystem;
    addRequirements(m_handSubsystem);
  }

  @Override
  public void initialize() {
    if(m_handSubsystem.handState() == Value.kReverse || m_handSubsystem.handState() == Value.kOff )
      m_handSubsystem.openHands();
    else if(m_handSubsystem.handState() == Value.kForward)
      m_handSubsystem.closeHands();
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}