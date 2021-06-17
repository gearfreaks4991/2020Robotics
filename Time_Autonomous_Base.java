package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
// import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

public abstract class Time_Autonomous_Base extends LinearOpMode {

    DcMotor FLMotor;
    DcMotor FRMotor;
    DcMotor BLMotor;
    DcMotor BRMotor;

    public void init_motors () {
        FLMotor = hardwareMap.dcMotor.get("flmotor");
        FRMotor = hardwareMap.dcMotor.get("frmotor");
        BLMotor = hardwareMap.dcMotor.get("blmotor");
        BRMotor = hardwareMap.dcMotor.get("brmotor");
        FLMotor.setDirection(DcMotor.Direction.REVERSE);
        BLMotor.setDirection(DcMotor.Direction.REVERSE);
    }
    public void mcdrive (double speed, int time) {
        speed = Range.clip(speed, -1, 1);
        FLMotor.setPower(speed);
        FRMotor.setPower(speed);
        BLMotor.setPower(speed);
        BRMotor.setPower(speed);

        sleep(time);
        FLMotor.setPower(0.00);
        FRMotor.setPower(0.00);
        BLMotor.setPower(0.00);
        BRMotor.setPower(0.00);
    }

    public void mcstrafe (double speed, int time) {
        speed = Range.clip(speed, -1, 1);
        FLMotor.setPower(-speed);
        FRMotor.setPower(speed);
        BLMotor.setPower(speed);
        BRMotor.setPower(-speed);

        sleep(time);
        FLMotor.setPower(0.00);
        FRMotor.setPower(0.00);
        BLMotor.setPower(0.00);
        BRMotor.setPower(0.00);
    }
    public void mcturn (double speed, int time) {
        speed = Range.clip(speed, -1, 1);
        FLMotor.setPower(-speed);
        FRMotor.setPower(speed);
        BLMotor.setPower(-speed);
        BRMotor.setPower(speed);

        sleep(time);
        FLMotor.setPower(0.00);
        FRMotor.setPower(0.00);
        BLMotor.setPower(0.00);
        BRMotor.setPower(0.00);
    }


}