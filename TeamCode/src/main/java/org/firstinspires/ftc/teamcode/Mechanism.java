/*
FTC Team 4991 GearFreaks
Author:  Joe Walton
Date: 29 Apr 2020
Modified by:
Modified Date:
*/
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;


@TeleOp (name = "Mechanism")
public class Mechanism extends LinearOpMode {

    // Declare OpMode members.
    DcMotor Motor;

    boolean buttonA;

    int GOOD = 0;

    @Override
    public void runOpMode() throws InterruptedException {
//Motor declaration: I called it motor because there is only one motor for this mechanism, so there is no point in naming or numbering it by variety.
        Motor = hardwareMap.dcMotor.get("Motor");
        Motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        waitForStart();

        while (opModeIsActive()) {
            buttonA = gamepad1.a;

            /*This portion of the code is for having the motors go to the target position of one rotation of the gear.
            I will fill in the target position space once i get the circumference, in inches, of the gear.
            I set the motor speed slow so that the motor moves the gear slowly, and doesn't damage anything.
            */

/*1120 ticks in a 40:1 Neverest motor rotation.
Divide the number of inches in the entire gear by the circumference of gear part with teeth. This is the percent of the gear that has teeth.
I will take the percentage and multiply it by the amount of ticks in one rotation of the motor to get the total number of ticks I need to travel.
The amount of inches in the small gear: 4. The number of inches before the small gear will slip: 3.5 inches.
The equations: 3.5 / 4 = .875. 1120 x .875 = 1280 ticks. I will need to travel 980 ticks.
*/
            if (buttonA) {
                Motor.setPower(.65);
                Motor.setTargetPosition(980);
                telemetry.addData("Button A is Pushed", " ,");
                telemetry.update();
                Motor.setPower(.65);
                Motor.setTargetPosition(0);
            }
        }
    }
}