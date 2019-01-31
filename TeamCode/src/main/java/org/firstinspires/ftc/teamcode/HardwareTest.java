package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


public class HardwareTest
{
    /* Public OpMode members. */
    public DcMotor  leftDrive   = null;
    public DcMotor  rightDrive  = null;
    public DcMotor  lift        = null;
    public DcMotor  armExtend   = null;
    public DcMotor  armFlip     = null;

    public CRServo  grabServo   = null;
    public Servo boxFlip     = null;


    public static final double MID_SERVO       =  0.5 ;
    public static final double ARM_UP_POWER    =  0.45 ;
    public static final double ARM_DOWN_POWER  = -0.45 ;

    /* local OpMode members. */
    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public HardwareTest(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        leftDrive  = hwMap.get(DcMotor.class, "Motor 1");
        rightDrive = hwMap.get(DcMotor.class, "Motor 2");
        lift    = hwMap.get(DcMotor.class, "Lift");
        armExtend = hwMap.get(DcMotor.class, "armExtend");
        armFlip = hwMap.get(DcMotor.class, "armFlip");

        grabServo = hwMap.get(CRServo.class, "grabServo");
        boxFlip = hwMap.get(Servo.class, "boxFlip");

        //grabServo = hwMap.get(CRServo.class, "Grab");
        //armMotor = hwMap.get(DcMotor.class, "Arm");

        leftDrive.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        rightDrive.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors

        // Set all motors to zero power
        leftDrive.setPower(0);
        rightDrive.setPower(0);
        lift.setPower(0);
        //leftArm.setPower(0);

        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //leftArm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Define and initialize ALL installed servos.
        //leftClaw  = hwMap.get(Servo.class, "left_hand");
        //rightClaw = hwMap.get(Servo.class, "right_hand");
        //leftClaw.setPosition(MID_SERVO);
        //rightClaw.setPosition(MID_SERVO);
    }
}