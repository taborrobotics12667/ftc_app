package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
@TeleOp(name="Just_Drive", group="Linear Opmode")
public class Just_drive extends LinearOpMode {
    HardwareTest ht = new HardwareTest();
    @Override
    public void runOpMode() {
        waitForStart();
        while (opModeIsActive()){
            double Rpower = Range.clip(gamepad1.left_stick_y + gamepad1.left_stick_x, -0.7, 0.7);
            double Lpower = Range.clip(gamepad1.left_stick_y - gamepad1.left_stick_x, -0.7, 0.7);

            ht.rightDrive.setPower(Rpower);
            ht.leftDrive.setPower(Lpower);


        }
    }
}
