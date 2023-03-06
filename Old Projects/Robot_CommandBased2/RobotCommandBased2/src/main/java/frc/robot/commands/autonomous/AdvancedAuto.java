package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;
import frc.robot.commands.DriveForDistanceCommand;
import frc.robot.commands.GyroTurnToAngleCommand;

// FIXME: This command group has NEVER been tested and probably does NOT work how we want it to yet; use this code with caution

public class AdvancedAuto extends SequentialCommandGroup {
  /** This is an exaple of what an advanced autonomous routine could look like this season!
   * Score the preloaded cube, then pick up another cube and score it, then drive onto the charging station and self-balance
   */
  public AdvancedAuto() {
    addCommands(new InstantCommand(() -> Robot.m_grabberSubsystem.retract())); // Release the preloaded cube
    addCommands(new DriveForDistanceCommand(-0.4, 0.7)); // Backup to turn
    addCommands(new GyroTurnToAngleCommand(90)); //Figure out if positive or negative, turns 90
    addCommands(new DriveForDistanceCommand(0.5, 0.8)); // Drives to side
    addCommands(new GyroTurnToAngleCommand(90)); //Figure out if positive or negative, turns 90
    addCommands(new DriveForDistanceCommand(2, 0.8)); // Drives foward towards nearest cube 
    addCommands(new InstantCommand(() -> Robot.m_grabberSubsystem.extend())); // Grab a cube
    addCommands(new DriveForDistanceCommand(-2, 0.8)); // Go back
    addCommands(new GyroTurnToAngleCommand(-90));
    addCommands(new DriveForDistanceCommand(0.2, 0.5));
    addCommands(new GyroTurnToAngleCommand(-90));
    addCommands(new InstantCommand(() -> Robot.m_grabberSubsystem.retract())); // Release a cube
    addCommands(new DriveForDistanceCommand(-0.4, 0.7)); // Backup to turn
    addCommands(new GyroTurnToAngleCommand(180)); //Figure out if positive or negative, turns 90
    addCommands(new DriveForDistanceCommand(1, 0.5)); // Drives to side
    addCommands(new BalanceBeamAutonomous());
  }
}