package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class turretMechanismTutorial {
    private DcMotorEx turret;

    private double kP = 0.0001;
    private double kD = 0.0000;
    private double goalX = 0;
    private double lastError = 0;
    private double angleTolerance = 0.2;
    private final double MAX_POWER = 0.8;
    private double power = 0;
    private final ElapsedTime timer = new ElapsedTime();
    private void init(HardwareMap hwMap) {
        turret = hwMap.get(DcMotorEx.class, "turret");
        turret.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void setkP(double newkP) {
        kP = newkP;
    }

    public double getkP() {
        return kP;
    }

    public void setkD(double newkD) {
        kP = newkD;
    }

    public double getkD() {
        return kD;
    }

    public void resetTimer(){
        timer.reset();
    }

    public void update(AprilTagDetection curID) {
        double deltaTime = timer.seconds();
        timer.reset();

        if(curID == null) {
            turret.setPower(0);
            lastError = 0;
            return;
        }

        // ------ start PD controller -------
        double error = goalX = curId.ftcPose.bearing;
        double pTerm = error * kP;

        double dTerm = 0;
        if (deltaTime > 0) {
            dTerm = ((error - lastError) / deltaTime) * kD;
        }

        if(Math.abs(error) < angleTolerance) {
            power = 0;
        } else {
            power = Range.clip(pTerm + dTerm, -MAX_POWER, MAX_POWER);
        }

        // safety encoder check OR magnetic limit switch check, OR other safeties

        turret.setPower(power);
        lastError = error;
    }

}
