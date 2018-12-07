package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.MecanumDriveTrain;

import java.util.ArrayList;

@TeleOp
public class MecanumTeleOp extends LinearOpMode {

    private double maxPower1 = 0.5;

    private int conditional = 1;

    private double maxPower = maxPower1 + 0.2 * conditional;

    private MecanumDriveTrain robot;


    @Override
    public void runOpMode() {

        robot = new MecanumDriveTrain(hardwareMap, "Motor 1", "Motor 2", "Motor 3", "Motor 4");

        waitForStart();

        while (opModeIsActive()) {
            double Rpower = Range.clip(gamepad1.left_stick_y + gamepad1.left_stick_x, -maxPower, maxPower);
            double Lpower = Range.clip(gamepad1.left_stick_y - gamepad1.left_stick_x, -maxPower, maxPower);

            boolean slideR = gamepad1.right_bumper;
            boolean slideL = gamepad1.left_bumper;


            double diagonalY = Range.clip(gamepad1.right_stick_y, -maxPower, maxPower);
            double diagonalX = Range.clip(gamepad1.right_stick_x, -maxPower, maxPower);


            if (slideR) {
                double Rslide = -0.3;
                robot.slide(Rslide);

            }
            else if (slideL) {
                double Lslide = 0.3;
                robot.slide(Lslide);

            }
            else if (diagonalY != 0) {
                if (diagonalY > 0) {
                    if (diagonalX > 0) {
                        robot.diagonalTL(maxPower1, -1);
                    }
                    else if (diagonalX > 0) {
                        robot.diagonalTR(maxPower1, -1);
                    }
                }
                else if (diagonalY < 0) {
                    if (diagonalX > 0) {
                        robot.diagonalTR(maxPower1, 1);
                    }
                    else if (diagonalX < 0) {
                        robot.diagonalTL(maxPower1, 1);
                    }
                }


            }
            else {
                robot.setPower(-Rpower, -Lpower);

                String messge = "Right Power: " + Double.toString(Rpower) + "Left Power: " + Double.toString(Lpower);
                telemetry.addData("Info:", messge);
                telemetry.update();

                conditional = gamepad1.left_stick_button ? 1 : 0;
                maxPower = maxPower1 + conditional * 0.2;

            }
        }
    }
}