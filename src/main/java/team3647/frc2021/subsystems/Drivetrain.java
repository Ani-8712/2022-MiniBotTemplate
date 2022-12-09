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
    private final TalonFX left;
    private final TalonFX right;
    private final double kPositionConversion;
    private PeriodicIO periodicIO = new PeriodicIO();

    private static class PeriodicIO {
        public double leftOutput = 0;
        public double rightOutput = 0;
        public ControlMode controlMode = ControlMode.PercentOutput;
        


        public double leftPosition;
        public double rightPosition;
    }

    public Drivetrain(TalonFX left, TalonFX right, double positionConversion) {
        this.left = left;
        this.right = right;
        this.kPositionConversion = positionConversion;
        resetEncoders();
    }

d
    public void setLeftPositionDegrees(double degrees) {
        this.periodicIO.leftOutput = (degrees % 360) / this.kPositionConversion;
        periodicIO.controlMode = ControlMode.Position;
        this.periodicIO.rightOutput = periodicIO.rightPosition;
    }


    public void curvatureDrive(double xSpeed, double zRotation, boolean isQuickTurn) {
        //WheelSpeeds ws = DifferentialDrive.curvatureDriveIK(xSpeed, zRotation, isQuickTurn);
        //setOpenloop(ws.left, ws.right);
    }

    public void setBrake() {
        this.left.setNeutralMode(NeutralMode.Brake);
        this.right.setNeutralMode(NeutralMode.Brake);
    }

    private void resetEncoders(){
        right.setSelectedSensorPosition(0);
        left.setSelectedSensorPosition(0);
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
        periodicIO.leftPosition = left.getSelectedSensorPosition() * kPositionConversion % 360;
        periodicIO.rightPosition = right.getSelectedSensorPosition() * kPositionConversion % 360;
    }

    public double getLeftDegrees() {
        return periodicIO.leftPosition;
    }

    @Override
    public void writePeriodicOutputs() {
        left.set(periodicIO.controlMode, periodicIO.leftPosition);
        right.set(periodicIO.controlMode, periodicIO.rightPosition);
    }

    @Override
    public String getName() {
        return "Drivetrain";
    }
}
