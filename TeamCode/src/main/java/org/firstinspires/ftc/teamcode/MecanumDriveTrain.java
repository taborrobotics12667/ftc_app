package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.ArrayList;


public class MecanumDriveTrain {

    ArrayList<DcMotor> motors = new ArrayList<DcMotor>();
    int[] right = {1, 3};
    int[] left = {0, 2};
    int[] front = {0, 1};
    int[] back = {2, 3};
    int[] diagTL = {0, 3};
    int[] diagTR = {1, 2};
    int[] lift = {4};

    public MecanumDriveTrain(HardwareMap hardwareMap, String... motorNames) {
        for (int i = 0; i < motorNames.length; i++) {
            DcMotor currentMotor = hardwareMap.get(DcMotor.class, motorNames[i]);
            currentMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            motors.add(currentMotor);

        }

        for (int index : right) {
            motors.get(index).setDirection(DcMotorSimple.Direction.REVERSE);
        }
    }

    public void setPower(double power) {
        for (DcMotor motor : motors) {
            motor.setPower(power);
        }
    }

    public void setPower(double powerR, double powerL) {
        for (int index : right) {
            motors.get(index).setPower(powerR);
        }
        for (int index : left) {
            motors.get(index).setPower(powerL);
        }
    }

    public void rotate(double power) {
        for (int index : right) {
            motors.get(index).setPower(power);
        }
        for (int index : left) {
            motors.get(index).setPower(-power);
        }
    }

    public void slide(double power) {
        for (int index : diagTR) {
            motors.get(index).setPower(-power);
        }
        for (int index : diagTL) {
            motors.get(index).setPower(power);
        }
    }

    public void diagonalTL(double power, int dir) {
        for (int index : diagTL) {
            motors.get(index).setPower(power * dir);
        }
        for (int index : diagTR) {
            motors.get(index).setPower(power / 3 * -dir);
        }

    }

    public void diagonalTR(double power, int dir) {
        for (int index : diagTR) {
            motors.get(index).setPower(power * dir);
        }
        for (int index : diagTL) {
            motors.get(index).setPower(power / 3 * -dir);
        }

    }

    public void Lift(double power) {
        for(int index: lift){
            motors.get(index).setPower(power);
        }

    }
}