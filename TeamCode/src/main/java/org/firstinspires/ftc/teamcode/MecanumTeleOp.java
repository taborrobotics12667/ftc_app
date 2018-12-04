package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.MecanumDriveTrain;

import java.util.ArrayList;

@TeleOp
public class MecanumTeleOp extends LinearOpMode {

    private double maxPower1 = 0.5;
    private double maxPower2 = 0.7;

    private int conditional = 1;

    private double maxPower = maxPower1 + 0.2*conditional;

    private MecanumDriveTrain robot;


    @Override
    public void runOpMode(){

        robot = new MecanumDriveTrain(hardwareMap,"Motor1","Motor2","Motor3","Motor4");;
        waitForStart();

        while(opModeIsActive()){
            double powerR = Range.clip(gamepad1.left_stick_y-gamepad1.left_stick_x,-maxPower,maxPower);
            double powerL = Range.clip(gamepad1.left_stick_y+ gamepad1.left_stick_x,-maxPower,maxPower);

            double slide = Range.clip(gamepad1.right_stick_x,-maxPower,maxPower);

            if (slide != 0){
                robot.slide(slide);
            }
            else{
                robot.setPower(powerR,powerL);
            }

            String messge = "Right Power: " + Double.toString(powerR) + "Left Power: " + Double.toString(powerL) + "Slide: " + Double.toString(slide);
            telemetry.addData("Info:",messge);
            telemetry.update();

            conditional = gamepad1.right_bumper?1:0;
            maxPower = maxPower1 + conditional*0.2;

        }
    }
}