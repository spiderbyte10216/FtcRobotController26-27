package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Turret Encoder Test")
public class TurretEncoderTest extends OpMode {
    private DcMotorEx encoder;
    private int previousTicks = 0;

    // Replace with encoder's ticks per revolution
    private static final double TICKS_PER_REV = 4096.0;

    @Override
    public void init() {
        encoder = hardwareMap.get(DcMotorEx.class, "turretEncoder");

        encoder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        encoder.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        telemetry.addLine("Encoder Initialized!");
        telemetry.update();
    }

    @Override
    public void loop() {

        int ticks = encoder.getCurrentPosition();

        double angle = (ticks / TICKS_PER_REV) * 360.0;

        int deltaTicks = ticks - previousTicks;

        String direction = "STATIONARY";

        if (deltaTicks > 0) {
            direction = "POSITIVE";
        }
        else if (deltaTicks < 0) {
            direction = "NEGATIVE";
        }

        telemetry.addData("Encoder Ticks", ticks);
        telemetry.addData("Angle (Degrees)", "%.2f", angle);
        telemetry.addData("Delta Ticks", deltaTicks);
        telemetry.addData("Direction", direction);

        telemetry.update();

        previousTicks = ticks;
    }
}