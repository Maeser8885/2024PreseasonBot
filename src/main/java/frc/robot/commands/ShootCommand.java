package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.AgitatorSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class ShootCommand extends Command{
    ShooterSubsystem shooter;
    AgitatorSubsystem agitator;
    public ShootCommand(ShooterSubsystem m_ShooterSubsystem, AgitatorSubsystem m_AgitatorSubsystem){
        shooter = m_ShooterSubsystem;
        addRequirements(shooter, agitator);
    }

    @Override
    public void initialize(){
        agitator.motorToggleGo();
    }

    @Override
    public void execute(){
        for(int i = 0; i<5; i++){
            shooter.go();
        }
        shooter.notGo();
    }

}
