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


        @Override
        public void runOpMode() {
            Slide  = hardwareMap.get(DcMotor.class, "Slide");
            Slide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);



            waitForStart();
            double button = 0;
            boolean T = true;
            double ServoPower = 0;
            while (opModeIsActive()) {

                //rotate.setPower(ServoPower);
                double extendPower = Range.clip(gamepad1.left_stick_y, -.75, .75);
                //double armPower = Range.clip(gamepad2.right_stick_x, -0.75, 0.75);


                if (gamepad1.a){
                    button= 0.25;
                }
                else if (gamepad1.b){
                    button= -0.25;
                }
                else if (gamepad1.y) {
                    button=  0.50;
                }
                else if (gamepad1.x){
                    button= -0.50;
                }
                else{
                    button = 0;
                }



                Slide.setPower(button);
                Slide.setPower(extendPower);



                String telem = Double.toString(extendPower);
                telemetry.addData("Motor power: ", telem);
                telemetry.update();

            }
        }
    }



