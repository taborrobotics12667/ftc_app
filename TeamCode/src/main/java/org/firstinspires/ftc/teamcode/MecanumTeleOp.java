package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

@TeleOp
public class MecanumTeleOp extends LinearOpMode {

    private double maxPower1 = 0.5;
    double rotate;
    boolean grab = true;
    boolean FlipBox = true;
    double armPos = 0.0;
    private int conditional = 1;

    private double maxPower = maxPower1 + 0.2 * conditional;

    HardwareTest ht = new HardwareTest();

    @Override
    public void runOpMode() {

        waitForStart();

        while (opModeIsActive()) {
            double Rpower = Range.clip(gamepad1.left_stick_y + gamepad1.left_stick_x, -maxPower, maxPower);
            double Lpower = Range.clip(gamepad1.left_stick_y - gamepad1.left_stick_x, -maxPower, maxPower);
            double liftPower = Range.clip(gamepad1.left_trigger - gamepad1.right_trigger, -0.75, 0.75);
            double extendPower = Range.clip(gamepad1.right_stick_y, -0.75, 0.75);

            if (gamepad1.right_bumper){
                ht.armFlip.setPower(-maxPower);
            }
            else if (gamepad1.left_bumper){
                ht.armFlip.setPower(maxPower);
            }

            else {
                ht.armFlip.setPower(0);
            }
            if (gamepad1.a){
                if(grab){
                    ht.grabServo.setPower(.75);
                }
                else {
                    ht.grabServo.setPower(0);

                }
                grab = !grab;
            }
            if (gamepad1.a) {
                armPos = 0.0;
            }
            else if (gamepad1.b) {
                armPos = 0.25;
            }
            else if (gamepad1.y){
                armPos = 0.75;
            }


            armPos = Range.clip(armPos, 0.0, 1.0);
            ht.boxFlip.setPosition(armPos);

            telemetry.addData("arm", "%.2f", armPos);

            telemetry.update();
        }





            ht.leftDrive.setPower(Lpower);
            ht.rightDrive.setPower(Rpower);


            String messge = "Right Power: " + Double.toString(Rpower) + "Left Power: " + Double.toString(Lpower);

            telemetry.addData("Info:", messge);

            telemetry.update();

            conditional = gamepad1.left_stick_button ? 1 : 0;
            maxPower = maxPower1 + conditional * 0.2;

            ht.lift.setPower(liftPower);

            telemetry.update();
        }
    }
}