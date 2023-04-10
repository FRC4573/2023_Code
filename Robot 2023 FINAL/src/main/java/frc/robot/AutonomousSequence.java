package frc.robot;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class AutonomousSequence extends SequentialCommandGroup{
    Arm m_arm;
    Hand m_hand;
    private static final String kScoreNdStraightLong = "Just Arm and Straight long";
    private static final String kScoreNdStraightShort = "Just Arm and Straight short";
    private static final String kScoreNdPlatform = "Just Arm and Balance";
    private static final String kScoreNdPlatformMobile = "Just Arm Mobile & Balance";
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
        if(sequence == null){
          System.out.println("Sequence is null");
          addCommands(
            new ArmToAngle(m_arm,-75),
            new WaitCommand(1),
            new OpenHands(m_hand),
            new ArmToAngleBackward(m_arm)
          );
        }else{
        switch (sequence) {
            case kScoreNdPlatform:
              //Score and balance do not cross line
              System.out.println("k Score and Platform");
              addCommands(
                new ArmToAngle(m_arm,-75),
                new WaitCommand(.2),
                new OpenHands(m_hand),
                new WaitCommand(.4),
                new ArmToAngleBackward(m_arm),
                new DriveForDistanceCommand(1.8, 0.7),
                new Balance()
                );
            break;
              
            case kScoreNdPlatformMobile:
              // Score, drive over platform and drive back
              System.out.println("kScore and Platform Mobile");
                addCommands(
                  new ArmToAngle(m_arm,-75),
                  new WaitCommand(.2),
                  new OpenHands(m_hand),
                  new ParallelCommandGroup(new ArmToAngleBackward(m_arm),new DriveForDistanceCommand(3.2, 0.7)),
                  new WaitCommand(0.4),
                  new DriveForDistanceBackwards(1.20, -0.7),
                  new Balance()
              );
              break;
            case kScoreNdStraightShort:
            System.out.println("Score and straight Short");
            addCommands(
              new ArmToAngle(m_arm,-75),
              new WaitCommand(.5),
              new OpenHands(m_hand),
              new ArmToAngleBackward(m_arm),
              new DriveForDistanceCommand(2.0, 0.5)
          );
          break;
            case kScoreNdStraightLong:
            System.out.println("Score and straight Long");
              addCommands(
                new ArmToAngle(m_arm,-75),
                new WaitCommand(0.4),
                new OpenHands(m_hand),
                new WaitCommand(0.4),
                new ArmToAngleBackward(m_arm),
                new DriveForDistanceCommand(3.0, 0.6)
            );
            break;
            case kJustArm:
            System.out.println("Just arm");
            addCommands(
              new ArmToAngle(m_arm,-75),
              new WaitCommand(1),
              new OpenHands(m_hand),
              new ArmToAngleBackward(m_arm)
            );
            break;
            default:
            System.out.println("Default: Just Arm");
            addCommands(
              new ArmToAngle(m_arm,-75),
              new WaitCommand(.2),
              new OpenHands(m_hand),
              new ArmToAngleBackward(m_arm)
            );

          }
        }
          /* 
          addCommands(
            new DriveForDistanceCommand(1, 0.5)
          );
          */
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
        /*
         * AUTO BALANCE WORKING NO Mobility
         *             addCommands(
              new ArmToAngle(m_arm,-75),
              new WaitCommand(.2),
              new OpenHands(m_hand),
              new ArmToAngleBackward(m_arm),
              new DriveForDistanceCommand(1.8, 0.5),
              new Balance()
            );
         */
        /*
         * Auto balance working drive mobility and score
            new ArmToAngle(m_arm,-75),
            new WaitCommand(.2),
            new OpenHands(m_hand),
            new ParallelCommandGroup(new ArmToAngleBackward(m_arm),new DriveForDistanceCommand(2.6, 0.7)),
            new DriveForDistanceBackwards(0.85, -0.5),
            new Balance()
            );
         */
    }
}
