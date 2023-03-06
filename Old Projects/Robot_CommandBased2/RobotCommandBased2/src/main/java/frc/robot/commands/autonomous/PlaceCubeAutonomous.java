package frc.robot.commands.autonomous;

import frc.robot.subsystems.GrabberSubsystem;
import frc.robot.Robot;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/** Autonomous Mode (Score Pre-Loaded Cube) ******************************************************
 * This is an autonomous routine  for scoring a pre-loaded cube by autonomously driving to the nearest Apriltag */
public class PlaceCubeAutonomous extends SequentialCommandGroup {
  private GrabberSubsystem m_grabberSubsystem = Robot.m_grabberSubsystem;

  public PlaceCubeAutonomous() { // List commands here sequentially


    addCommands(new InstantCommand(() -> m_grabberSubsystem.extend())); // drop the cube  by extending grabber holding it

  }
}