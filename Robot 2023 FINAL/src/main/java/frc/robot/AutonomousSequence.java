package frc.robot;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class AutonomousSequence extends SequentialCommandGroup{
    Arm m_arm;
    Hand m_hand;
    private static final String kScoreNdStraightLong = "Just Arm and Straight long";
    private static final String kScoreNdStraightShort = "Just Arm and Straight short";
    private static final String kScoreNdPlatformLeft = "Just Arm and Straight Left";
    private static final String kScoreNdPlatformRight = "Just Arm and Straight Right";
    private static final String kJustArm = "Just Arm";

    public AutonomousSequence(Arm arm, Hand hand, String sequence){
        m_arm = arm;
        m_hand = hand;
        /* 
        addCommands(
            new ArmToAngle(m_arm),
            new WaitCommand(2),
            new OpenHands(m_hand),
            new WaitCommand(2),
            new ArmToAngleBackward(m_arm),
            new DriveForDistanceCommand(1, 0.5)
        );
        */
        /* 
        addCommands(
            //turn right
            new GyroTurnToAngleCommand(180),
            //turn left
            new GyroTurnToAngleCommand(-180)
        );
        */
        //+0.51
        /*
         * Command drop cone, drive forward and turn right to auto balance
         */
        /* 
        addCommands(
            new ArmToAngle(m_arm),
            new WaitCommand(2),
            new OpenHands(m_hand),
            new WaitCommand(1),
            new ArmToAngleBackward(m_arm),
            new DriveForDistanceCommand(5.69, 0.5),
            new GyroTurnToAngleCommand(72),
            new DriveForDistanceCommand(2, 0.5),
            new GyroTurnToAngleCommand(75),
            new DriveForDistanceCommand(1.5, 0.5)
            //new GyroTurnToAngleCommand(-65),
            //new DriveForDistanceCommand(1, 0.5)
        );
        */
        
        //Drop cone drive forward, pick up game piece, auto stabilize
        System.out.println("Chosen Sequence: "+ sequence);
        /* 
        switch (sequence) {
            case kScoreNdPlatformLeft:
              // Put custom auto code here
              System.out.println("k Score and Platform Left");
              addCommands(
                new ArmToAngle(m_arm,-65),
                new WaitCommand(2),
                new OpenHands(m_hand),
                new WaitCommand(1),
                new ArmToAngleBackward(m_arm),
                new DriveForDistanceCommand(5.78, 0.5),
                new GyroTurnToAngleCommand(62),//65
                new DriveForDistanceCommand(1.7, 0.5),
                new WaitCommand(.2),
                new CloseHands(hand),
                new WaitCommand(.2),
                new ArmToAngle(m_arm,-20),
                new GyroTurnToAngleCommand(82)
            );
            break;
              
            case kScoreNdPlatformRight:
              // Put default auto code here
              System.out.println("kScore and Platform Right");
              addCommands(
                new ArmToAngle(m_arm,-65),
                new WaitCommand(2),
                new OpenHands(m_hand),
                new WaitCommand(1),
                new ArmToAngleBackward(m_arm),
                new DriveForDistanceCommand(5.78, 0.5),
                new GyroTurnToAngleCommand(-62),//65
                new DriveForDistanceCommand(1.7, 0.5),
                new WaitCommand(.2),
                new CloseHands(hand),
                new WaitCommand(.2),
                new ArmToAngle(m_arm,-20),
                new GyroTurnToAngleCommand(-82)
                
            );
            break;
            case kScoreNdStraightShort:
            System.out.println("Score and straight Short");
            addCommands(
              new ArmToAngle(m_arm,-65),
              new WaitCommand(2),
              new OpenHands(m_hand),
              new WaitCommand(1),
              new ArmToAngleBackward(m_arm),
              new DriveForDistanceCommand(3.15, 0.5)
          );
          break;
            case kScoreNdStraightLong:
            System.out.println("Score and straight Long");
            addCommands(
                new ArmToAngle(m_arm,-65),
                new WaitCommand(2),
                new OpenHands(m_hand),
                new WaitCommand(1),
                new ArmToAngleBackward(m_arm),
                new DriveForDistanceCommand(5.78, 0.5)
            );
            break;
            case kJustArm:
            System.out.println("Just arm");
            addCommands(
                new ArmToAngle(m_arm,-65),
                new WaitCommand(2),
                new OpenHands(m_hand),
                new WaitCommand(1),
                new ArmToAngleBackward(m_arm)
            );
            break;
            default:
            System.out.println("Default: Just Arm");
            addCommands(
                new ArmToAngle(m_arm,-65),
                new WaitCommand(2),
                new OpenHands(m_hand),
                new WaitCommand(1),
                new ArmToAngleBackward(m_arm)
            );

          }
          */
          addCommands(
            new DriveForDistanceCommand(1, 0.5)
          );
          /* 
          addCommands(
            new GyroTurnToAngleCommand(75)
          );
          */
          /* 
        addCommands(
            new ArmToAngle(m_arm,-65),
            new WaitCommand(2),
            new OpenHands(m_hand),
            new WaitCommand(1),
            new ArmToAngleBackward(m_arm),
            new DriveForDistanceCommand(5.78, 1),
            new GyroTurnToAngleCommand(62),//65
            new DriveForDistanceCommand(1.7, 0.7),
            new WaitCommand(.2),
            new CloseHands(hand),
            new WaitCommand(.2),
            new ArmToAngle(m_arm,-20),
            new GyroTurnToAngleCommand(82),//82
            new DriveForDistanceCommand(3.2, 1)
        );
        */
        //addCommands(new GyroTurnToAngleCommand(-90));
    }
}
