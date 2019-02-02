package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
@TeleOp(name="just_Mechs", group="Linear Opmode")
public class just_Mechs extends LinearOpMode {
    public DcMotor left = null;
    public DcMotor right = null;
    public DcMotor out = null;
    public DcMotor around = null;
    public CRServo grab = null;
    public Servo dump = null;
    private double armPos;
    private int FlipBox = 0;
    private boolean graB = true;

    @Override
    public void runOpMode() {
        left = hardwareMap.get(DcMotor.class, "left");
        right = hardwareMap.get(DcMotor.class, "right");
        right.setDirection(DcMotorSimple.Direction.REVERSE);
        out = hardwareMap.get(DcMotor.class, "out");
        around = hardwareMap.get(DcMotor.class, "around");
        out.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        around.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        double run = around.getCurrentPosition();
        //grab = hardwareMap.get(CRServo.class, "grab");
        dump = hardwareMap.get(Servo.class, "dump");

        waitForStart();
        while (opModeIsActive()) {
            double flipPower = Range.clip(gamepad1.right_stick_y, -0.75, 0.75);
            
            double Rpower = Range.clip(gamepad1.left_stick_y + gamepad1.left_stick_x, -0.7, 0.7);
            double Lpower = Range.clip(gamepad1.left_stick_y - gamepad1.left_stick_x, -0.7, 0.7);

            if(gamepad1.right_bumper){
                out.setPower(0.5);
            }
            else if (gamepad1.left_bumper){
                out.setPower(-0.5);
            }
            else {
                out.setPower(0);
            }
            double place = around.getCurrentPosition();
            if (flipPower < 0) {
                if (place > run - 300) {
                    if (place > run - 45) {
                        around.setPower(0.7);
                    } else {
                        around.setPower(0.3);
                    }
                }
            }
            else if (flipPower > 0){
                around.setPower(0.05);
            }

            if (gamepad1.a) {
                if (graB) {
                    grab.setPower(.75);
                } else {
                    grab.setPower(0);

                }
                graB = !graB;
            }
            if (gamepad1.b) {
                armPos = 0.0;
            } else if (gamepad1.y) {
                armPos = 0.25;
            } else if (gamepad1.x) {
                armPos = 0.75;
            }
            dump.setPosition(armPos);


            left.setPower(Lpower);
            right.setPower(Rpower);
        }
    }
}
