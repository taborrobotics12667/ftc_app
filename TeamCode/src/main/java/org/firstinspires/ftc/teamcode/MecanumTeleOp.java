package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.DriveTrain;

import java.util.ArrayList;

@TeleOp
public class MecanumTeleOp extends LinearOpMode {

    private double maxPower1 = 0.5;

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