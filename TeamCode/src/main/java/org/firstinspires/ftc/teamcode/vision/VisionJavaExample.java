package org.firstinspires.ftc.teamcode.vision;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

@TeleOp
//@Disabled
public class VisionJavaExample extends LinearOpMode{
    MasterVision vision;
    SampleRandomizedPositions goldPosition;

    @Override
    public void runOpMode() throws InterruptedException {
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;// recommended camera direction
        parameters.vuforiaLicenseKey = "ASvokdT/////AAABmfd9xAXGsUpUqKSfE0Z4CKVIPyj8vBVQvaa5RQ1NUGcTo0wZf0s9mk5Tke4BUNIL2/FafJMbwn2UalPCZed20sHch/JVeHL1/H2Px3HyQH/qiBASfXVEriB+VGd0RRFlD6ZIDIJy5ErjsCLqlkiQ+sjI4iFMsAK/WwUVioBI3+Kqo/6DEnO/tMrHoMD9X6ovqBjNDaZWnhiFfJbsHKauk8KriZwsLXyRK8Tmr4uxQeVIaCl6EGgywJccpe7i1AugQ64IxeaNFObsQgjZQHBz4kyJ5pGkJcyRGIxouS92NNi0eFrhpviom2CO7+aSOW9ba+ZKhdoDGZBqG9Zh6G/AKwUAxwZM+ffWZ5YtjAmEtmdx";

        vision = new MasterVision(parameters, hardwareMap, true, MasterVision.TFLiteAlgorithm.INFER_LEFT);
        vision.init();// enables the camera overlay. this will take a couple of seconds
        vision.enable();// enables the tracking algorithms. this might also take a little time

        waitForStart();

        vision.disable();// disables tracking algorithms. this will free up your phone's processing power for other jobs.

        goldPosition = vision.getTfLite().getLastKnownSampleOrder();

        while(opModeIsActive()){
            telemetry.addData("goldPosition was", goldPosition);// giving feedback

            switch (goldPosition){ // using for things in the autonomous program
                case LEFT:
                    telemetry.addLine("going to the left");
                    break;
                case CENTER:
                    telemetry.addLine("going straight");
                    break;
                case RIGHT:
                    telemetry.addLine("going to the right");
                    break;
                case UNKNOWN:
                    telemetry.addLine("staying put");
                    break;
            }

            telemetry.update();
        }

        vision.shutdown();
    }
}
