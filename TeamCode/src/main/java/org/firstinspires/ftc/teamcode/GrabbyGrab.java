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
//@Disabled
public class GrabbyGrab extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    HardwareTest ht = new HardwareTest();


    @Override
    public void runOpMode() {
        ht.init(hardwareMap);


        waitForStart();
        boolean T = true;
        double ServoPower = 0;
        while (opModeIsActive()) {

            //ht.grabServo.setPower(ServoPower);
            double extendPower = Range.clip(gamepad2.left_stick_y, -0.75, 0.75);
            double armPower = Range.clip(gamepad2.right_stick_x, -0.75, 0.75);
            boolean GoServo = gamepad2.a;

            ht.armExtend.setPower(extendPower);
            ht.armFlip.setPower(armPower);

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
        }
    }
}