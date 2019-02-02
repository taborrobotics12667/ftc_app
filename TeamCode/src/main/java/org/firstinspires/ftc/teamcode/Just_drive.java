package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
@TeleOp(name="Just_Drive", group="Linear Opmode")
public class Just_drive extends LinearOpMode {
    public DcMotor right = null;
    public DcMotor left = null;
    @Override
    public void runOpMode() {
        right = hardwareMap.get(DcMotor.class, "right");
        right.setDirection(DcMotorSimple.Direction.REVERSE);
        left = hardwareMap.get(DcMotor.class, "left");
        waitForStart();
        while (opModeIsActive()){
            double Rpower = Range.clip(gamepad1.left_stick_y + gamepad1.left_stick_x, -0.7, 0.7);
            double Lpower = Range.clip(gamepad1.left_stick_y - gamepad1.left_stick_x, -0.7, 0.7);

            right.setPower(Rpower);
            left.setPower(Lpower);


        }
    }
}
