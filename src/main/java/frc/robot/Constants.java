// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
    public static final int agitatorToggle = 1;
  }

  public static final class MotorConstants {
    public static final int kIntakeMotorId = 1;
    public static final double IntakeSpeed = .5;
    public static final int frMotorPort = 0;
    public static final int flMotorPort = 7;
    public static final int brMotorPort = 0;
    public static final int blMotorPort = 7;
    public static final int agitatorMotorPort = 7;
    public static double agitatorMotorSpeed = 0.1;
    public static final int kOutTakeMotorId = 2;
    public static final double Shootspeed = 1;
  } 

  public static final class AutoConstants{
    public static final double aTime = 2;
    public static final double aSpeed = 0;
    public static final double shootTime = 0.5; //This is in seconds
    public static double aTurnSpeed = 1;
  }


}
