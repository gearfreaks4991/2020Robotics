package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;


@TeleOp (name = "Flipper")
public class Flipper extends LinearOpMode {

    // Declare OpMode members.
    Servo loadingservo;
    float RightTrigger; //this is for loading rings and resetting the flipper

    @Override
    public void runOpMode() throws InterruptedException {
//Motor declaration: I called it motor because there is only one motor for this mechanism, so there is no point in naming or numbering it by variety.
        waitForStart();

        while (opModeIsActive()) {

            RightTrigger = gamepad1.right_trigger;


            if (RightTrigger) {
                loadingservo.setPosition(0.00);
                telemetry.addData("Loading.", " ");
                telemetry.update();
                wait(2000);
                loadingservo.setPosition(1.00);
                telemetry.addData("Ready.", " ");
                telemetry.update();
            }
        }
    }
}
