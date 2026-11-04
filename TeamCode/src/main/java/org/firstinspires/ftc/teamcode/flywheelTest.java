package org.firstinspires.ftc.teamcode;
import static java.lang.Math.abs;
import static java.lang.Math.max;

import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "flywheelTest", group = "Mecanum")
public class flywheelTest extends LinearOpMode {
    private DcMotor motor;
    private Servo servo;

    private Servo servo1;

    private ElapsedTime runtime = new ElapsedTime();
    public FtcDashboard ftcDashboard;

    @Override
    public void runOpMode() {
        this.ftcDashboard = FtcDashboard.getInstance();
        this.telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        telemetry.addData( "Status", "Initializing");
        telemetry.update();

        motor = hardwareMap.get(DcMotor.class, "motor");
        servo = hardwareMap.get(Servo.class, "servo");
        servo1 = hardwareMap.get(Servo.class, "servo1");


        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        motor.setDirection(DcMotorSimple.Direction.FORWARD);

        telemetry.addData("Status","Initializing");
        telemetry.update();

        waitForStart();
        runtime.reset();


        while(opModeIsActive()) {
            if(gamepad1.a) {
                motor.setPower(-0.8);
            } else {
                motor.setPower(0);
            }

            if (gamepad1.x) {
                servo.setPosition(0.6);
            }
            if (gamepad1.b) {
                servo.setPosition(0.3);
            }

            if (gamepad1.dpad_right) {
                servo1.setPosition(0.85);
            }

            //making position less compress more
            if (gamepad1.dpad_left) {
                servo1.setPosition(0.45);
            }


            telemetry.update();
        }




    }

}