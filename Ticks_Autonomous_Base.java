package org.firstinspires.ftc.teamcode;

// import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

public abstract class Ticks_Autonomous_Base extends LinearOpMode {

    DcMotor FLMotor;
    DcMotor FRMotor;
    DcMotor BLMotor;
    DcMotor BRMotor;

    public void init_motors () {
        FLMotor = hardwareMap.dcMotor.get("flmotor");
        FLMotor.setTargetPosition(0);
        FLMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FLMotor.setDirection(DcMotor.Direction.REVERSE);

        FRMotor = hardwareMap.dcMotor.get("frmotor");
        FRMotor.setTargetPosition(0);
        FRMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        BLMotor = hardwareMap.dcMotor.get("blmotor");
        BLMotor.setTargetPosition(0);
        BLMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BLMotor.setDirection(DcMotor.Direction.REVERSE);

        BRMotor = hardwareMap.dcMotor.get("brmotor");
        BRMotor.setTargetPosition(0);
        BRMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }
    public void mcdrive (double speed, int ticks) {
        speed = Range.clip(speed, -1, 1);
        FLMotor.setTargetPosition(ticks);
        FLMotor.setPower(speed);
        FRMotor.setTargetPosition(ticks);
        FRMotor.setPower(speed);
        BRMotor.setTargetPosition(ticks);
        BLMotor.setPower(speed);
        BRMotor.setTargetPosition(ticks);
        BRMotor.setPower(speed);

        while (FLMotor.isBusy());
        FLMotor.setPower(0.00);
        FRMotor.setPower(0.00);
        BLMotor.setPower(0.00);
        BRMotor.setPower(0.00);
    }

    public void mcstrafe (double speed, int ticks) {
        speed = Range.clip(speed, -1, 1);
        FLMotor.setPower(-speed);
        FRMotor.setPower(speed);
        BLMotor.setPower(speed);
        BRMotor.setPower(-speed);

        while (FLMotor.isBusy());
        FLMotor.setPower(0.00);
        FRMotor.setPower(0.00);
        BLMotor.setPower(0.00);
        BRMotor.setPower(0.00);
    }
    public void mcturn (double speed, int ticks) {
        speed = Range.clip(speed, -1, 1);
        FLMotor.setPower(-speed);
        FRMotor.setPower(speed);
        BLMotor.setPower(-speed);
        BRMotor.setPower(speed);

        while (FLMotor.isBusy());
        FLMotor.setPower(0.00);
        FRMotor.setPower(0.00);
        BLMotor.setPower(0.00);
        BRMotor.setPower(0.00);
    }


}