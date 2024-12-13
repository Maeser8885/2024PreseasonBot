// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import com.revrobotics.CANSparkLowLevel;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import frc.robot.Constants;

public class IntakeSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */

  CANSparkMax m_intakeMotor;

  public IntakeSubsystem() {
    m_intakeMotor = new CANSparkMax(Constants.MotorConstants.kIntakeMotorId, CANSparkLowLevel.MotorType.kBrushless);
    m_intakeMotor.setInverted(true);
    
  }

  /**
   * Example command factory method.
   *
   * @return a command
   */
  public Command exampleMethodCommand() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce(
        () -> {
          /* one-time action goes here */
        });
  }

  public void intake() {
    if (m_intakeMotor != null){
      m_intakeMotor.set(Constants.MotorConstants.IntakeSpeed);
    }
  }

  public void stop(){
    if (m_intakeMotor != null){
      m_intakeMotor.set(0);
    }
  }

  public void reverseIntake(){
    if (m_intakeMotor != null){
      m_intakeMotor.set(-Constants.MotorConstants.IntakeSpeed);
    }
  }
  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  public boolean exampleCondition() {
    // Query some boolean state, such as a digital sensor.
    return false;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
