package team3647.frc2021.commands;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import team3647.frc2021.subsystems.Drivetrain;

public class ArcadeDrive extends CommandBase {
    private Drivetrain dt;
    private BooleanSupplier go;
    private DoubleSupplier turn;
    //constructor
    public ArcadeDrive(Drivetrain dt, BooleanSupplier throttle, DoubleSupplier turn) {
        this.dt = dt;
        this.go = throttle;
        this.turn = turn;
        addRequirements(dt);
    }

    @Override
    public void initialize() {
        dt.init();
    }

    @Override
    public void execute() {
        if(go.getAsBoolean()){
            dt.setDegs();
        }
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        dt.end();
    }

}
