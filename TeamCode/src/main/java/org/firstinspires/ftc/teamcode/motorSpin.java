
package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "motorSpin", group = "Mecanum")
public class motorSpin extends LinearOpMode {

    private DcMotorEx motor;
    private Servo servo;
    private Servo servo1;

    // Target speed in encoder ticks per second
    private static final double TARGET_VELOCITY = -1500.0;

    @Override
    public void runOpMode() {

        telemetry = new MultipleTelemetry(
                telemetry,
                FtcDashboard.getInstance().getTelemetry()
        );

        motor = hardwareMap.get(DcMotorEx.class, "motor");
        servo = hardwareMap.get(Servo.class, "servo");
        servo1 = hardwareMap.get(Servo.class, "servo1");

        motor.setDirection(DcMotorSimple.Direction.FORWARD);

        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {

            // Hold A to spin at a constant target speed
            if (gamepad1.a) {
                motor.setVelocity(TARGET_VELOCITY);
            } else {
                motor.setVelocity(0);
            }

            // Original servo controls
            //originally 0.6
            if (gamepad1.x) {
                servo.setPosition(0.55);
            }

            //originally 0.3
            if (gamepad1.b) {
                servo.setPosition(0.3);
            }

            if (gamepad1.dpad_right) {
                servo1.setPosition(0.85);
            }

            if (gamepad1.dpad_left) {
                servo1.setPosition(0.4);
            }

            telemetry.addData(
                    "Target Velocity",
                    gamepad1.a ? TARGET_VELOCITY : 0
            );

            telemetry.addData(
                    "Actual Velocity",
                    motor.getVelocity()
            );

            telemetry.addData(
                    "Encoder Ticks",
                    motor.getCurrentPosition()
            );

            telemetry.update();
        }

        motor.setVelocity(0);
    }
}
