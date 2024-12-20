// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class AgitatorSubsystem extends SubsystemBase {

  CANSparkMax agitatorMotor;
  public AgitatorSubsystem() {
    agitatorMotor = new CANSparkMax(Constants.MotorConstants.agitatorMotorPort, CANSparkLowLevel.MotorType.kBrushless);
    agitatorMotor.set(0);
  }
  //carkey
  public void motorToggleGo(){
    if(agitatorMotor != null){
    if(agitatorMotor.get() == 0){
    agitatorMotor.set(Constants.MotorConstants.agitatorMotorSpeed);
    }
    
    else{
      agitatorMotor.set(0);
    }
  }
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
