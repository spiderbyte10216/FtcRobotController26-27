
package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Hive Cluster Aim Test")
public class limelightHiveTest extends LinearOpMode {

    private Limelight3A limelight;

    // Change to false for blue alliance
    private static final boolean RED_ALLIANCE = true;

    private static final int PIPELINE = 3;

    @Override
    public void runOpMode() {

        limelight = hardwareMap.get(
                Limelight3A.class, "limelight");

        limelight.setPollRateHz(50);
        limelight.pipelineSwitch(PIPELINE);
        limelight.start();

        waitForStart();

        while (opModeIsActive()) {

            LLResult result = limelight.getLatestResult();

            int tag1 = RED_ALLIANCE ? 30 : 44;
            int tag2 = RED_ALLIANCE ? 31 : 45;

            boolean found1 = false;
            boolean found2 = false;

            double tx1 = 0;
            double tx2 = 0;

            if (result != null
                    && result.isValid()
                    && result.getPipelineIndex() == PIPELINE
                    && result.getStaleness() < 100) {

                for (LLResultTypes.FiducialResult tag :
                        result.getFiducialResults()) {

                    int id = tag.getFiducialId();

                    if (id == tag1) {
                        found1 = true;
                        tx1 = tag.getTargetXDegrees();
                    }

                    if (id == tag2) {
                        found2 = true;
                        tx2 = tag.getTargetXDegrees();
                    }
                }
            }

            telemetry.addData("Alliance",
                    RED_ALLIANCE ? "RED" : "BLUE");

            telemetry.addData("Tag " + tag1, found1);
            telemetry.addData("Tag " + tag2, found2);

            if (found1 && found2) {

                double centerTx = (tx1 + tx2) / 2.0;

                telemetry.addData("Tag 1 Angle",
                        "%.2f degrees", tx1);

                telemetry.addData("Tag 2 Angle",
                        "%.2f degrees", tx2);

                telemetry.addData("Center Angle",
                        "%.2f degrees", centerTx);

                if (Math.abs(centerTx) < 1.5) {
                    telemetry.addLine("ALIGNED");
                } else {
                    telemetry.addLine("NOT ALIGNED");
                }

            } else {
                telemetry.addLine(
                        "Both scoring tags must be visible");
            }

            telemetry.update();
        }

        limelight.stop();
    }
}
