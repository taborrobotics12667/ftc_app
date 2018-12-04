package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.ArrayList;


public class MecanumDriveTrain  {

    ArrayList<DcMotor> motors = new ArrayList<DcMotor>();
    int[] right = {1,3};
    int[] left = {0,2};
    int[] front = {0,1};
    int[] back = {2,3};

    public  MecanumDriveTrain(HardwareMap hardwareMap,String ... motorNames){
        for (int i = 0;i<motorNames.length;i++){
            DcMotor currentMotor = hardwareMap.get(DcMotor.class,motorNames[i]);
            motors.add(currentMotor);
        }

        for(int index:right){
            motors.get(index).setDirection(DcMotorSimple.Direction.REVERSE);
        }
    }

    public void setPower(double power){
        for (DcMotor motor:motors){
            motor.setPower(power);
        }
    }

    public void setPower(double powerR,double powerL){
        for(int index:right){
            motors.get(index).setPower(powerR);
        }
        for(int index:left){
            motors.get(index).setPower(powerL);
        }
    }

    public void rotate(double power){
        for (int index:right){
            motors.get(index).setPower(power);
        }
        for(int index:left){
            motors.get(index).setPower(-power);
        }
    }

    public void slide(double power){
        for(int index:front){
            motors.get(index).setPower(-power);
        }
        for(int index:back){
            motors.get(index).setPower(power);
        }
    }
}