package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.ShooterSubsystem;

public class ShootCommand extends Command{
    ShooterSubsystem shooter;
    public ShootCommand(ShooterSubsystem m_ShooterSubsystem){
        shooter = m_ShooterSubsystem;
        addRequirements(shooter);
    }

    @Override
    public void initialize(){
    }

    @Override
    public void execute(){
        shooter.go(Constants.AutoConstants.shootTime);
    }

}
