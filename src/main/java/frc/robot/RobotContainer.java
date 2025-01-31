// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.LimelightInterface;
import frc.robot.subsystems.SelfDriveSubsystem;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.RobotSelf;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {

  private RobotSelf robotSelf = new RobotSelf();

  // Declare the controller
  private final XboxController PRIMARY_CONTROLLER =
      new XboxController(OperatorConstants.kDriverControllerPort);

  // The robot's subsystems and commands are defined here...
  private static final DriveSubsystem DRIVE_SUBSYSTEM = new DriveSubsystem(DriveSubsystem.initializeHardware());
  private final LimelightInterface limelightInterface = new LimelightInterface();
  private final SelfDriveSubsystem selfDriveSubsystem = new SelfDriveSubsystem(PRIMARY_CONTROLLER, robotSelf,limelightInterface, DRIVE_SUBSYSTEM);
  

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    CommandScheduler.getInstance().registerSubsystem(limelightInterface);
    CommandScheduler.getInstance().registerSubsystem(selfDriveSubsystem);
    // Configure the trigger bindings
    configureBindings();
    
    // Set the default command
    DRIVE_SUBSYSTEM.setDefaultCommand(
      new RunCommand(
        // This is now negative
          () -> {
            if (!robotSelf.getselfdrive()){
            DRIVE_SUBSYSTEM.teleop(-PRIMARY_CONTROLLER.getLeftY(), -PRIMARY_CONTROLLER.getRightX());
            }
          }, 
          DRIVE_SUBSYSTEM
        )
    );
    
  }
  
  

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
  
  }
  
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  
   public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return null;
  }



  
}

