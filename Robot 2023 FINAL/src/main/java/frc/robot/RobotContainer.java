package frc.robot;

import java.util.List;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.math.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.AutoConstants;
import frc.robot.Constants.DriveConstants;

public class RobotContainer {
private static final Drivetrain m_robotDrive = new Drivetrain();
Hand ourHands;
private static final String kDefaultAuto = "Default";
private static final String kCustomAuto = "My Auto";


Feeder ourFeeder;
 Arm ourArm;
      /** The container for the robot. Contains subsystems, OI devices, and commands. */
      Joystick m_armController = new Joystick(0);
      Joystick m_driverController = new Joystick(1);
      double m_deadZone= 0.2;
      double m_forward = 1.00;
      double m_rotate = 0.75;
      UsbCamera camera1 = CameraServer.startAutomaticCapture(0);
      UsbCamera camera2 = CameraServer.startAutomaticCapture(1);

String trajectoryJSON = "pathplanner/generatedJSON/testpath19.wpilib.json";
Trajectory trajectory = new Trajectory();

public RobotContainer() {
    // Configure the button bindings
    ourArm = new Arm();
    ourHands = new Hand();
    ourFeeder = new Feeder();
    configureButtonBindings();
    // Configure default commands
    // Set the default drive command to split-stick arcade drive
    m_robotDrive.setDefaultCommand(
        // A split-stick arcade command, with forward/backward controlled by the left
        // hand, and turning controlled by the right.
        new RunCommand(
            () ->
                m_robotDrive.arcadeDrive(
                    -getJoystickValue(m_driverController, 1)*m_forward, -getJoystickValue(m_driverController, 2)*m_rotate),
            m_robotDrive));


  
           
  }
  public double getJoystickValue(Joystick joystick, int iKey) {
    double dVal = joystick.getRawAxis(iKey);
    if (Math.abs(dVal) < m_deadZone) {
      return 0;
    } else {
      return dVal;
    }
  }
    /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand(String m_autoSelected) {
    // Create a voltage constraint to ensure we don't accelerate too fast
    System.out.println("Robot Container auto " +m_autoSelected );
    return new AutonomousSequence(ourArm, ourHands, m_autoSelected);
        

    // Run path following command, then stop at the end.
  }

  private void configureButtonBindings() {
    new JoystickButton(m_armController, 1).onTrue(new OpenHands(ourHands));
    new JoystickButton(m_driverController, 3).onTrue(new ArmExtend(ourArm));
    new JoystickButton(m_driverController, 4).onTrue(new ArmRetract(ourArm));
    //TODO: Fix so when button released the motor stops
    new JoystickButton(m_armController, 3).onTrue(ourArm.forwardArm());
    new JoystickButton(m_armController, 3).onFalse(ourArm.stopArm());
    new JoystickButton(m_armController, 8).onTrue(ourFeeder.feedOutCommand());
    new JoystickButton(m_armController, 8).onFalse(ourFeeder.stopFeed());
    new JoystickButton(m_armController, 9).onTrue(ourFeeder.feedInCommand());
    new JoystickButton(m_armController, 9).onFalse(ourFeeder.stopFeed());
    new JoystickButton(m_armController, 2).onTrue(ourArm.backwardArm());
    new JoystickButton(m_armController, 2).onFalse(ourArm.stopArm());
    new JoystickButton(m_armController, 6).onTrue(new Balance());
        new JoystickButton(m_armController, 6).onFalse(new DriveForDistanceCommand(0, 0));
    new JoystickButton(m_armController, 7).onTrue(new AutonomousSequence(ourArm, ourHands,"neither"));
    new JoystickButton(m_armController, 7).onFalse((ourArm.stopArm()));
  }
  public static Drivetrain gDrivetrain(){
    return m_robotDrive;
  }
  public Hand gHands(){
    return ourHands;
  }
}
