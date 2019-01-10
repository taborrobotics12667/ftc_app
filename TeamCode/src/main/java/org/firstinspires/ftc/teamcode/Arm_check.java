package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.teamcode.HardwareTest;

@TeleOp
public class Arm_check extends LinearOpMode {


//@Disabled


        // Declare OpMode members.
        private DcMotor Slide = null;
        private CRServo rotate = null;


        @Override
        public void runOpMode() {
            Slide  = hardwareMap.get(DcMotor.class, "Slide");

            rotate = hardwareMap.get(CRServo.class, "spin");


            waitForStart();
            boolean T = true;
            double ServoPower = 0;
            while (opModeIsActive()) {

                rotate.setPower(ServoPower);
                double extendPower = Range.clip(gamepad1.left_stick_y, -0.75, 0.75);
                //double armPower = Range.clip(gamepad2.right_stick_x, -0.75, 0.75);
                boolean GoServo = gamepad1.a;

                Slide.setPower(extendPower);
                //ht.armMotor.setPower(armPower);

                if (GoServo) {
                    if (T) {
                        ServoPower = 1;
                        T = false;
                    }
                    else {
                        T = true;
                        ServoPower = 0;
                    }
                }
                String telem = Double.toString(extendPower);
                telemetry.addData("Motor power: ", telem);
                telemetry.update();

            }
        }
    }



