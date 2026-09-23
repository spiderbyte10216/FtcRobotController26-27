package org.firstinspires.ftc.teamcode;
import static java.lang.Math.abs;
import static java.lang.Math.max;

import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "drivetrain", group = "Mecanum")
public class drivetrain extends LinearOpMode {
    private DcMotor rightFront;
    private DcMotor leftFront;
    private DcMotor leftBack;
    private DcMotor rightBack;
    private ElapsedTime runtime = new ElapsedTime();
    public FtcDashboard ftcDashboard;

    @Override
    public void runOpMode() {
            this.ftcDashboard = FtcDashboard.getInstance();
            this.telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
            telemetry.addData("Status", "Initializing..");
            telemetry.update();


            rightFront = hardwareMap.get(DcMotor.class, "rightFront");
            leftFront = hardwareMap.get(DcMotor.class, "leftFront");
            leftBack = hardwareMap.get(DcMotor.class, "leftBack");
            rightBack = hardwareMap.get(DcMotor.class, "rightBack");

            leftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            rightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            leftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            rightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            // Set directions for mecanum drive
            leftFront.setDirection(DcMotorSimple.Direction.FORWARD);
            rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
            leftBack.setDirection(DcMotorSimple.Direction.FORWARD);
            rightBack.setDirection(DcMotorSimple.Direction.REVERSE);

            telemetry.addData("Status", "Initialized");
            telemetry.update();

            waitForStart();
            runtime.reset();

            double y = gamepad1.left_stick_y;
            double x = -gamepad1.left_stick_x;
            double r = -gamepad1.right_stick_x;
            //while loop


            while (opModeIsActive()) {
                double p1 = y + x + r;
                double p2 = y - x - r;
                double p3 = y - x + r;
                double p4 = y + x - r;
                double maxval = max(abs(p1), max(abs(p2), max(abs(p3), abs(p4))));

                if (maxval > 1.0) {
                    p1 /= maxval;
                    p2 /= maxval;
                    p3 /= maxval;
                    p4 /= maxval;

                    leftFront.setPower(p1);
                    rightFront.setPower(p2);
                    leftBack.setPower(p3);
                    rightBack.setPower(p4);

                    telemetry.update();

                }
            }
        }



        }







