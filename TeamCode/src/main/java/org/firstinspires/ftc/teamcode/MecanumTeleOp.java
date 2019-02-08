package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

@TeleOp
public class MecanumTeleOp extends LinearOpMode {

    private double maxPower1 = 0.5;
    double rotate;
    boolean grab = true;
    private double armPos = 0.0;
    private int conditional = 1;

    private double maxPower = maxPower1 + 0.2 * conditional;

    TeleopHard ht = new TeleopHard();



    @Override
    public void runOpMode() {

        ht.init(hardwareMap);

        waitForStart();
        //ht.armFlip.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //ht.armFlip.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        ht.leftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        ht.rightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        ht.lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //double run = ht.armFlip.getCurrentPosition();
        while (opModeIsActive()) {
            double Rpower = Range.clip(gamepad1.left_stick_y + gamepad1.left_stick_x, -maxPower, maxPower);
            double Lpower = Range.clip(gamepad1.left_stick_y - gamepad1.left_stick_x, -maxPower, maxPower);
            double liftPower = Range.clip(gamepad1.left_trigger - gamepad1.right_trigger, -0.75, 0.75);
            double flipPower = Range.clip(gamepad1.right_stick_y, -0.75, 0.75);

            if (gamepad1.y) {
                flipPower = 0.3;

            } else if (gamepad1.x) {
                flipPower = -0.3;

            } else {
                flipPower = 0;
            }


            //ht.armFlip.setPower(flipPower);


            //if (gamepad1.right_bumper) {
             //   ht.armExtend.setPower(0.3);
            //}
            //else if (gamepad1.left_bumper) {
            //    ht.armExtend.setPower(-0.3);
            //}
            //else{
            //    ht.armExtend.setPower(0);
            //}


            //if (gamepad1.a) {
            //    if (grab) {
            //        ht.grabServo.setPower(.75);
            //    } else {
             //       ht.grabServo.setPower(0);

               // }
                //grab = !grab;
            //}




            telemetry.update();

            ht.leftDrive.setPower(Lpower);
            ht.rightDrive.setPower(Rpower);

            ht.lift.setPower(liftPower);


            String messge = "Right Power: " + Double.toString(Rpower) + "Left Power: " + Double.toString(Lpower);

            telemetry.addData("Info:", messge);

            telemetry.update();

            conditional = gamepad1.left_stick_button ? 1 : 0;
            maxPower = maxPower1 + conditional * 0.2;


            telemetry.update();
        }
    }
}