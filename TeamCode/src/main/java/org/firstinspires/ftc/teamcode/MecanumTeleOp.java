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

        robot = new MecanumDriveTrain(hardwareMap, "Motor 1", "Motor 2", "Motor 3", "Motor 4","Lift");

        waitForStart();

        while (opModeIsActive()) {
            double Rpower = Range.clip(gamepad1.left_stick_y + gamepad1.left_stick_x, -maxPower, maxPower);
            double Lpower = Range.clip(gamepad1.left_stick_y - gamepad1.left_stick_x, -maxPower, maxPower);
            double diagonalY = Range.clip(gamepad1.right_stick_y, -maxPower, maxPower);
            double diagonalX = Range.clip(gamepad1.right_stick_x, -maxPower, maxPower);

            double liftPower = Range.clip(gamepad1.left_trigger - gamepad1.right_trigger, -0.75, 0.75);


            if (gamepad1.right_bumper){
                robot.slide(0.7);
            }
            else if (gamepad1.left_bumper){
                robot.slide(-0.7);
            }



            if (diagonalX != 0 && diagonalY != 0) {
                if (diagonalY > 0) {
                    if (diagonalX < 0) {
                        robot.diagonalTL(maxPower1, -1);
                    }
                    else if (diagonalX > 0){
                        robot.diagonalTR(maxPower1, -1);
                    }
                }
                else if (diagonalY < 0) {

                    if (diagonalX < 0){
                        robot.diagonalTR(maxPower1, 1);
                    }
                    else if (diagonalX > 0) {
                        robot.diagonalTL(maxPower1, 1);
                        String yes = "YES";
                        telemetry.addData("IF?", yes);
                        telemetry.update();
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



            robot.Lift(liftPower);


            String diags = "X: " + Double.toString(diagonalX) + " Y: " + Double.toString(diagonalY);
            telemetry.addData("Diagonals: ", diags);
            telemetry.update();
        }
    }
}