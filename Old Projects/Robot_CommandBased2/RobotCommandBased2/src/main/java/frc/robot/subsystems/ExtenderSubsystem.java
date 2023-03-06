package frc.robot.subsystems;

import frc.robot.Constants;


import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ExtenderSubsystem extends SubsystemBase {
  private DoubleSolenoid extenderPiston; // Our extender has a pneumatic piston for raising/lowering the whole subsystem
  private boolean isRaised; // Keeps track of whether the extender is currently raised (piston extended)


  /** Subsystem for controlling the extender */
  public ExtenderSubsystem() {


    // Instantiate the solenoid
    extenderPiston = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.EXTENDER_SOLENOID_ID_1, Constants.EXTENDER_SOLENOID_ID_2);
    isRaised = false; // Initial position of the extender piston
  }



  // Methods for controlling the state of the solenoid //
  public void lowerExtender() {
    extenderPiston.set(Value.kForward);
    isRaised = false;
  }
  public void raiseExtender() {
    extenderPiston.set(Value.kReverse);
    isRaised = true;
  }
  public void toggleExtenderRaiser() {
    if (isRaised) {
      lowerExtender();
    } else {
      raiseExtender();
    }
  }


}