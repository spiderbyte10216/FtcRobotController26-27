
package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Limelight AprilTag Test")
public class limelightTest extends LinearOpMode {

    private Limelight3A limelight;
    private static final int TAGS = 3;

    @Override
    public void runOpMode() {

        // Initialize Limelight
        limelight = hardwareMap.get(
                Limelight3A.class, "limelight"
        );

        // Select AprilTag pipeline
        limelight.pipelineSwitch(TAGS);

        // Start Limelight
        limelight.start();

        telemetry.addLine("Limelight Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {

            LLResult result = limelight.getLatestResult();

            if (result != null && result.isValid()) {

                for (LLResultTypes.FiducialResult tag :
                        result.getFiducialResults()) {

                    telemetry.addData(
                            "Tag ID", tag.getFiducialId()
                    );

                    telemetry.addData(
                            "Horizontal Angle",
                            "%.1f degrees",
                            tag.getTargetXDegrees()
                    );
                }

            } else {
                telemetry.addLine("No AprilTags Detected");
            }

            telemetry.update();
        }

        limelight.stop();
    }
}
