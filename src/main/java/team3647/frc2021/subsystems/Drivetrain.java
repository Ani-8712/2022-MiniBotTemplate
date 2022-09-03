package team3647.frc2021.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.DifferentialDrive.WheelSpeeds;

public class Drivetrain implements PeriodicSubsystem {
    private TalonFX left;
    private TalonFX right;
    private double m_maxOutput;
    private PeriodicIO periodicIO = new PeriodicIO();
    private ControlMode controlMode = ControlMode.PercentOutput;

    private static class PeriodicIO {
        public double leftOutput;
        public double rightOutput;
        public ControlMode controlMode;
    }

    public Drivetrain(TalonFX left, TalonFX right) {
        this.left = left;
        this.right = right;
    }

    public void setOpenloop(double leftOut, double rightOut) {
        periodicIO.controlMode = ControlMode.PercentOutput;
        periodicIO.leftOutput = leftOut;
        periodicIO.rightOutput = rightOut;
    }

    public void curvatureDrive(double xSpeed, double zRotation, boolean isQuickTurn) {
        WheelSpeeds ws = DifferentialDrive.curvatureDriveIK(xSpeed, zRotation, isQuickTurn);
        setOpenloop(ws.left, ws.right);
    }

    public void setBrake() {
        this.left.setNeutralMode(NeutralMode.Brake);
        this.right.setNeutralMode(NeutralMode.Brake);
    }

    public void setCoast() {
        this.left.setNeutralMode(NeutralMode.Coast);
        this.right.setNeutralMode(NeutralMode.Coast);
    }


    @Override
    public void init() {
        setBrake();
    }

    @Override
    public void readPeriodicInputs() {

    }

    @Override
    public void writePeriodicOutputs() {
        left.set(controlMode, periodicIO.leftOutput);
        right.set(controlMode, periodicIO.rightOutput);
    }

    @Override
    public String getName() {
        return "Drivetrain";
    }
}
