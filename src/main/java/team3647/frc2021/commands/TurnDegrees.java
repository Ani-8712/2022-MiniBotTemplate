package team3647.frc2021.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import team3647.frc2021.subsystems.Drivetrain;

public class TurnDegrees extends CommandBase {

    private final Drivetrain drivetrain;
    private final double degree;
    
    public TurnDegrees(Drivetrain drivetrain, double degree) {
        this.degree = degree % 360;
        this.drivetrain = drivetrain;

        addRequirements(drivetrain);
    }
    
    @Override
    public void initialize() {
        this.drivetrain.setLeftPositionDegrees(this.degree);
        
    }
    

    @Override
    public boolean isFinished() {
        return Math.abs(this.drivetrain.getLeftDegrees() - this.degree) < 0.05;
    }
}
