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
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
private static final Hand ourHands= new Hand();





 Arm ourArm;
      /** The container for the robot. Contains subsystems, OI devices, and commands. */
      Joystick m_armController = new Joystick(0);
      Joystick m_driverController = new Joystick(1);
      double m_deadZone= 0.2;
      double m_forward = 1.00;
      double m_rotate = 0.75;
      UsbCamera camera1 = CameraServer.startAutomaticCapture(0);
      UsbCamera camera2 = CameraServer.startAutomaticCapture(1);
      UsbCamera camera3 = CameraServer.startAutomaticCapture(2);

String trajectoryJSON = "pathplanner/generatedJSON/testpath.wpilib.json";
Trajectory trajectory = new Trajectory();

public RobotContainer() {
    // Configure the button bindings
    ourArm = new Arm();
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

   try (
    // A chooser for autonomous commands
  SendableChooser<Command> m_chooser = new SendableChooser<>()) {
    
    // Add commands to the autonomous command chooser
    m_chooser.setDefaultOption("Score", new ArmToAngle(ourArm, m_deadZone));
    m_chooser.addOption("Score and Drive Straight", null);
    m_chooser.addOption("Left Score and Drive Straight and Platform", null);
    m_chooser.addOption("Right Score and Drive Straight and Platform", null);

    SmartDashboard.putData(m_chooser);
  }

  
           
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
  public Command getAutonomousCommand() {
    // Create a voltage constraint to ensure we don't accelerate too fast
    var autoVoltageConstraint =
        new DifferentialDriveVoltageConstraint(
            new SimpleMotorFeedforward(
                DriveConstants.ksVolts,
                DriveConstants.kvVoltSecondsPerMeter,
                DriveConstants.kaVoltSecondsSquaredPerMeter),
            DriveConstants.kDriveKinematics,
            10);

    // Create config for trajectory
    TrajectoryConfig config =
        new TrajectoryConfig(
                AutoConstants.kMaxSpeedMetersPerSecond,
                AutoConstants.kMaxAccelerationMetersPerSecondSquared)
            // Add kinematics to ensure max speed is actually obeyed
            .setKinematics(DriveConstants.kDriveKinematics)
            // Apply the voltage constraint
            .addConstraint(autoVoltageConstraint);

    // An example trajectory to follow.  All units in meters.
    Trajectory exampleTrajectory =
        TrajectoryGenerator.generateTrajectory(
            // Start at the origin facing the +X direction
            new Pose2d(0, 0, new Rotation2d(0)),
            // Pass through these two interior waypoints, making an 's' curve path
            List.of(new Translation2d(1, 1), new Translation2d(2, -1)),
            // End 3 meters straight ahead of where we started, facing forward
            new Pose2d(3, 0, new Rotation2d(0)),
            // Pass config
            config);

    RamseteCommand ramseteCommand =
        new RamseteCommand(
            exampleTrajectory,
            m_robotDrive::getPose,
            new RamseteController(AutoConstants.kRamseteB, AutoConstants.kRamseteZeta),
            new SimpleMotorFeedforward(
                DriveConstants.ksVolts,
                DriveConstants.kvVoltSecondsPerMeter,
                DriveConstants.kaVoltSecondsSquaredPerMeter),
            DriveConstants.kDriveKinematics,
            m_robotDrive::getWheelSpeeds,
            new PIDController(DriveConstants.kPDriveVel, 0, 0),
            new PIDController(DriveConstants.kPDriveVel, 0, 0),
            // RamseteCommand passes volts to the callback
            m_robotDrive::tankDriveVolts,
            m_robotDrive);

    // Reset odometry to the starting pose of the trajectory.
    m_robotDrive.resetOdometry(trajectory.getInitialPose());

    // Run path following command, then stop at the end.
    return new AutonomousSequence(ourArm, ourHands);
  }

  private void configureButtonBindings() {
    new JoystickButton(m_armController, 1).onTrue(new OpenHands(ourHands));
    new JoystickButton(m_driverController, 3).onTrue(new ArmExtend(ourArm));
    new JoystickButton(m_driverController, 4).onTrue(new ArmRetract(ourArm));
    //TODO: Fix so when button released the motor stops
    new JoystickButton(m_armController, 3).onTrue(ourArm.forwardArm());
    new JoystickButton(m_armController, 3).onFalse(ourArm.stopArm());
    new JoystickButton(m_armController, 2).onTrue(ourArm.backwardArm());
    new JoystickButton(m_armController, 2).onFalse(ourArm.stopArm());
    new JoystickButton(m_armController, 6).onTrue(new BalanceOnBeam());
    new JoystickButton(m_armController, 7).onTrue(new AutonomousSequence(ourArm, ourHands));
    new JoystickButton(m_armController, 7).onFalse((ourArm.stopArm()));
  }
  public static Drivetrain gDrivetrain(){
    return m_robotDrive;
  }
  public static Hand gHands(){
    return ourHands;
  }
}
