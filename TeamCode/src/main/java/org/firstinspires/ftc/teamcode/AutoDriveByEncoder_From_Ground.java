package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.teamcode.vision.MasterVision;
import org.firstinspires.ftc.teamcode.vision.SampleRandomizedPositions;

@Autonomous(name="Auto Drive By Encoder From Ground", group="Pushbot")
//@Disabled
public class AutoDriveByEncoder_From_Ground extends LinearOpMode {

    /* Declare OpMode members. */
    HardwareTest robot = new HardwareTest();   // Use a Pushbot's hardware
    private ElapsedTime runtime = new ElapsedTime();

    private double COUNTS_PER_MOTOR_REV = 2240;    // eg: Neverest 40
    private double DRIVE_GEAR_REDUCTION = 1.0;     // This is < 1.0 if geared UP
    private double WHEEL_DIAMETER_INCHES = 3.54;     // For figuring circumference
    private double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * Math.PI);
    private static double DRIVE_SPEED = 0.6;
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
        telemetry.addData("Path0", "Starting at %7d :%7d :%7d",
                robot.leftDrive.getCurrentPosition(),
                robot.rightDrive.getCurrentPosition(),
                robot.lift.getCurrentPosition());
        telemetry.update();

        vision = new MasterVision(parameters, hardwareMap, true, MasterVision.TFLiteAlgorithm.INFER_LEFT);
        vision.init();
        vision.enable();


        goldPosition = vision.getTfLite().getLastKnownSampleOrder();
        while (goldPosition.toString() == "UNKNOWN") {
            goldPosition = vision.getTfLite().getLastKnownSampleOrder();
            telemetry.addLine(goldPosition.toString());
        telemetry.update();}

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        vision.disable();


        robot.lift.setPower(-0.6);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 0.8)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        sleep(250);

        encoderDrive(DRIVE_SPEED, 1.5,1.5,4.0);

        sleep(250);

        robot.lift.setPower(0.6);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 0.8)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }


        encoderDrive(TURN_SPEED, 7.2, -7.2  , 5.0);  // Drive from lander center position
        //encoderDrive(TURN_SPEED, 5, 5, 4.0);  // S2: Turn Right 12 Inches with 4 Sec timeout

        switch (goldPosition){ // using for things in the autonomous program
            case LEFT:
                telemetry.addLine("going to the left");
                telemetry.update();
                encoderDrive(TURN_SPEED, -3.0, 3.0, 5.0);
                encoderDrive(DRIVE_SPEED, 15, 15, 5.0);
                encoderDrive(TURN_SPEED, 5, -5, 5.0);
                encoderDrive(DRIVE_SPEED, 10, 10, 8.0);

                servo();

                //servo
                //encoderDrive(DRIVE_SPEED, -50, -50, 10.0);
                break;
            case CENTER:
                telemetry.addLine("going straight");
                telemetry.update();
                encoderDrive(DRIVE_SPEED, 23, 23, 5.0);
                servo();
                //encoderDrive(TURN_SPEED, 6, -6, 5.0);
                //encoderDrive(TURN_SPEED, -50, -50, 10.0);
                break;
            case RIGHT:
                telemetry.addLine("going to the right");
                telemetry.update();
                encoderDrive(TURN_SPEED, 4, -4, 5.0);
                encoderDrive(DRIVE_SPEED, 18, 18, 5.0);
                encoderDrive(TURN_SPEED, -7, 7, 5.0);
                encoderDrive(DRIVE_SPEED, 5, 5, 5.0);
                servo();
                //encoderDrive(TURN_SPEED, -8, 8, 5.0);
                //encoderDrive(DRIVE_SPEED, 50, 50, 10.0);
                break;
            case UNKNOWN:
                telemetry.addLine("staying put");
                telemetry.addLine("going straight");
                telemetry.update();

                servo();

                break;
        }



        sleep(1000);     // pause for servos to move


        //telemetry.addData("Path", "Complete");
        //telemetry.update();

        vision.shutdown();

    }



    /*
     *  Method to perfmorm a relative move, based on encoder counts.
     *  Encoders are not reset as the move is based on the current position.
     *  Move will stop if any of three conditions occur:
     *  1) Move gets to the desired position
     *  2) Move runs out of time
     *  3) Driver stops the opmode running.
     */

    public void encoderDrive(double speed,
                             double leftInches, double rightInches,
                             double timeoutS) {
        int newLeftTarget;
        int newRightTarget;


        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftTarget = robot.leftDrive.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
            newRightTarget = robot.rightDrive.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);
            robot.leftDrive.setTargetPosition(newLeftTarget);
            robot.rightDrive.setTargetPosition(newRightTarget);

            // Turn On RUN_TO_POSITION
            robot.leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            robot.leftDrive.setPower(Math.abs(speed));
            robot.rightDrive.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (robot.leftDrive.isBusy() && robot.rightDrive.isBusy() && robot.leftDrive.isBusy() && robot.rightDrive.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1", "Running to %7d :%7d", newLeftTarget, newRightTarget);
                telemetry.addData("Path2", "Running at %7d :%7d",
                        robot.leftDrive.getCurrentPosition(),
                        robot.rightDrive.getCurrentPosition()
                );
                telemetry.update();
            }

            // Stop all motion;
            robot.leftDrive.setPower(0);
            robot.rightDrive.setPower(0);

            // Turn off RUN_TO_POSITION
            robot.leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            //  sleep(250);   // optional pause after each move
        }
    }

    public void servo () {
        robot.marker.setPosition(0.5);
        sleep(1000);
    }

}
