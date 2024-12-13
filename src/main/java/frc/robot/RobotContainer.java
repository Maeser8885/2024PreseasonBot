// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import edu.wpi.first.cscore.UsbCamera;
import frc.robot.subsystems.AgitatorSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.*;;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final IntakeSubsystem m_intakeSubsystem = new IntakeSubsystem();
  private final DriveSubsystem m_driveSubsystem = new DriveSubsystem();
  //comment this back in for an agitator! Yippee!
  //private final AgitatorSubsystem m_agitatorSubsystem = new AgitatorSubsystem();
  private final ShooterSubsystem m_outtakeSubsystem = new ShooterSubsystem();
  UsbCamera camera;

  // Instantiate Joystick (change this to xbox controller if we need it)
  public static final CommandJoystick m_driverController =
      new CommandJoystick(OperatorConstants.kDriverControllerPort);
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
    camera = CameraServer.startAutomaticCapture(0);
    camera.setFPS(15);

    
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
    // Get the default command from the Drive Subsystem
      m_driveSubsystem.setDefaultCommand(m_driveSubsystem.getArcadeDriveCommand());
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    new Trigger(m_exampleSubsystem::exampleCondition)
        .onTrue(new ExampleCommand(m_exampleSubsystem));

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
    m_driverController.button(2).whileTrue(new RunCommand(()-> {m_intakeSubsystem.intake();}, m_intakeSubsystem ));
    m_driverController.button(2).whileFalse(new RunCommand(()-> {m_intakeSubsystem.stop();}, m_intakeSubsystem ));
    //m_driverController.button(Constants.OperatorConstants.agitatorToggle).toggleOnTrue(new RunCommand(() -> {m_agitatorSubsystem.motorToggleGo();}, m_agitatorSubsystem));
    m_driverController.button(1).whileTrue(new RunCommand(()-> {m_outtakeSubsystem.go();}, m_outtakeSubsystem ));
   m_driverController.button(1).whileFalse(new RunCommand(()-> {m_outtakeSubsystem.stop();}, m_outtakeSubsystem ));
    m_driverController.button(7).whileTrue(new RunCommand(()-> {m_outtakeSubsystem.reverseShoot();}, m_outtakeSubsystem ));
    m_driverController.button(8).whileTrue(new RunCommand(()-> {m_intakeSubsystem.reverseIntake();}, m_intakeSubsystem ));
       m_driverController.button(7).whileFalse(new RunCommand(()-> {m_outtakeSubsystem.stop();}, m_outtakeSubsystem ));
       m_driverController.button(8).whileFalse(new RunCommand(()-> {m_intakeSubsystem.stop();}, m_intakeSubsystem ));
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
    public SendableChooser<SequentialCommandGroup> AutoChooser = new SendableChooser<SequentialCommandGroup>();


    public void setupDashboard() {
    AutoChooser.setDefaultOption("Drive", new SequentialCommandGroup(new DriveTimedCommand(Constants.AutoConstants.aSpeed, Constants.AutoConstants.aTime, Constants.AutoConstants.aTurnSpeed, m_driveSubsystem)));
    AutoChooser.addOption("Drive + Shoot", new SequentialCommandGroup(new DriveTimedCommand(3, 0, 0.1, m_driveSubsystem), new ShootCommand(m_outtakeSubsystem)));

    SmartDashboard.putData(AutoChooser);
  }

  public Command getAutonomousCommand() {
    //distance from starting point to scoring zone253: 
    return new SequentialCommandGroup(new DriveTimedCommand(3, -0.8, 0, m_driveSubsystem), new ShootCommand(m_outtakeSubsystem));
  }
}
  