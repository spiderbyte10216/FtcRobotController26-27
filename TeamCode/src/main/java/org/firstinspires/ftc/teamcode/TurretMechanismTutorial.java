package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class TurretMechanismTutorial {
    private DcMotorEx turret;

    private double kP = 0.0001;
    private double kD = 0.0000;
    private double goalX = 0;
    private double lastError = 0;
    private double angleTolerance = 0.2;
    private final double MAX_POWER = 0.6;
    private double power = 0;
    private final ElapsedTime timer = new ElapsedTime();
    public void init(HardwareMap hwMap) {
        turret = hwMap.get(DcMotorEx.class,"turret");
        turret.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }
    public void setkP(double newKP) {
        kP = newKP;
    }

    public double getkP() {
        return kP;

    }

    public void setkD(double newKD) {
        kD = newKD;
    }

    public double getkD() {
        return kD;
    }

    public void resetTimer() {
        timer.reset();
    }
}
