// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import javax.swing.plaf.TreeUI;

import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

//outtake subsystem
//outtake subsystem
//outtake subsystem
//outtake subsystem
public class ShooterSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  CANSparkMax outtakeMotor;
  Timer timer;
  private double m_time;

  public ShooterSubsystem() {
    outtakeMotor = new CANSparkMax(Constants.MotorConstants.kOutTakeMotorId, CANSparkLowLevel.MotorType.kBrushless);
    outtakeMotor.setInverted(true);
    timer = new Timer();
    m_time = 300;
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

  public void go() {
    // if (outtakeMotor != null) {
    outtakeMotor.set(Constants.MotorConstants.Shootspeed);
  // }

}

  public void stop() {
    outtakeMotor.set(0);
  }

  public void go(double time) {
    outtakeMotor.set(Constants.MotorConstants.Shootspeed);
    this.m_time = time;
    timer.start();
  }

  /**
   * An example method querying a boolean state of the subsystem (for example, a
   * digital sensor).
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
    if (timer.hasElapsed(m_time)) {
      // Stops shooter after desired time
      stop();
      timer.stop();
      timer.reset();
    }
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

  public void notGo() {
    stop();
  }

  public void reverseShoot(){
    if (outtakeMotor != null){
      outtakeMotor.set(-Constants.MotorConstants.Shootspeed);
    }
  }
}
