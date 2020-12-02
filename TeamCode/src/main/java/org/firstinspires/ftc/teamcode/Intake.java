package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;


@TeleOp (name = "Intake")
public class Intake extends LinearOpMode {

    // Declare OpMode members.

    DcMotor Intake; //This is using an AndyMark Neverest 40 Motor
    boolean RightBumper; //This is for taking in the rings
    boolean LeftBumper; //This is for pushing out rings

    @Override
    public void runOpMode() throws InterruptedException {
//Motor declaration: I called it motor because there is only one motor for this mechanism, so there is no point in naming or numbering it by variety.
        waitForStart();

        while (opModeIsActive()) {

            RightBumper = gamepad1.right_bumper;
            LeftBumper = gamepad1.left_bumper;


            if (RightBumper == true) { //Taking in rings
                Intake.setPower(0.80);
            }
            if (RightBumper == false) {
                Intake.setPower(0.00);
            }
            if (LeftBumper == true) { //Pushing out rings
                Intake.setPower(-0.80);
            }
            if (LeftBumper == false) {
                Intake.setPower(0.00);
            }
            }
        }
    }