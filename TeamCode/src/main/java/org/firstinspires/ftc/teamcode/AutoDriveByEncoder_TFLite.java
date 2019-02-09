package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.teamcode.vision.MasterVision;
import org.firstinspires.ftc.teamcode.vision.SampleRandomizedPositions;

@Autonomous(name="Auto Drive By Encoder TFLite", group="Pushbot")
//@Disabled
public class AutoDriveByEncoder_TFLite extends LinearOpMode {

    /* Declare OpMode members. */
    HardwareTest robot = new HardwareTest();   // Use a Pushbot's hardware
    private ElapsedTime runtime = new ElapsedTime();

    private double COUNTS_PER_MOTOR_REV = 2240;    // eg: Neverest 40
    private double DRIVE_GEAR_REDUCTION = 1.0;     // This is < 1.0 if geared UP
    private double WHEEL_DIAMETER_INCHES = 3.54;     // For figuring circumference
    private double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * Math.PI);
    private static double DRIVE_SPEED = 0.7;
    private static double TURN_SPEED = 0.5;

    MasterVision vision;
    SampleRandomizedPositions goldPosition;


    @Override
    public void runOpMode() throws InterruptedException {
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;// recommended camera direction
        parameters.vuforiaLicenseKey = "ASvokdT/////AAABmfd9xAXGsUpUqKSfE0Z4CKVIPyj8vBVQvaa5RQ1NUGcTo0wZf0s9mk5Tke4BUNIL2/FafJMbwn2UalPCZed20sHch/JVeHL1/H2Px3HyQH/qiBASfXVEriB+VGd0RRFlD6ZIDIJy5ErjsCLqlkiQ+sjI4iFMsAK/WwUVioBI3+Kqo/6DEnO/tMrHoMD9X6ovqBjNDaZWnhiFfJbsHKauk8KriZwsLXyRK8Tmr4uxQeVIaCl6EGgywJccpe7i1AugQ64IxeaNFObsQgjZQHBz4kyJ5pGkJcyRGIxouS92NNi0eFrhpviom2CO7+aSOW9ba+ZKhdoDGZBqG9Zh6G/AKwUAxwZM+ffWZ5YtjAmEtmdx";

        /*
         * Initialize the drive system variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);


        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Resetting Encoders");    //
        telemetry.update();

        robot.leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //robot.armExtend.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //robot.armExtend.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Send telemetry message to indicate successful Encoder reset
        telemetry.addData("Path0", "Starting at %7d :%7d",
                robot.leftDrive.getCurrentPosition(),
                robot.rightDrive.getCurrentPosition());
        telemetry.update();

        vision = new MasterVision(parameters, hardwareMap, true, MasterVision.TFLiteAlgorithm.INFER_RIGHT);
        vision.init();
        vision.enable();

        sleep(5000);

        goldPosition = vision.getTfLite().getLastKnownSampleOrder();
        telemetry.addLine(goldPosition.toString());
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        vision.disable();


        // Step through each leg of the path,
        // Note: Reverse movement is obtained by setting a negative distance (not speed)
        robot.lift.setTargetPosition(-200);
        robot.lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.lift.setPower(Math.abs(0.6));
        while (robot.lift.isBusy()) {
            telemetry.addData("Lift",Integer.toString(robot.lift.getCurrentPosition()));
            telemetry.update();
        }
        robot.lift.setPower(Math.abs(0));
        robot.lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);



        /*
        robot.lift.setTargetPosition(-500);
        robot.lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.lift.setPower(Math.abs(0.6));
        while (robot.lift.isBusy()) {
            telemetry.update();
        }
        robot.lift.setPower(Math.abs(0));
        robot.lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        */


    }
}
