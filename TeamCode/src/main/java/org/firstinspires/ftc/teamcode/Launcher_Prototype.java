package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;


import org.firstinspires.ftc.robotcore.external.Telemetry;


@TeleOp(name="Launcher_Prototype")
public class Launcher_Prototype extends LinearOpMode {

    DcMotor Flywheel1;
    DcMotor Flywheel2;


    boolean buttonY;
    boolean buttonB;
    boolean buttonA;
    boolean buttonX;
    boolean DpadUp;
    boolean DpadMiddle;
    boolean DpadBottom;

    double current_power;


    boolean GOOD = true;

    boolean LeftTrigger;
    /*
    float AXIS_Y;
    float AXIS_Y2;
    */

    @Override
    public void runOpMode() throws InterruptedException {

        Flywheel1 = hardwareMap.dcMotor.get("Left");
        Flywheel2 = hardwareMap.dcMotor.get("Right");
        Flywheel2.setDirection(DcMotor.Direction.REVERSE);


        /*
        DriveLeft = hardwareMap.dcMotor.get("DriveLeft");
        DriveRight = hardwareMap.dcMotor.get("DriveRight");
        DriveRight.setDirection(DcMotor.Direction.REVERSE);
        */

        waitForStart();

        while (opModeIsActive()) {

            buttonY = gamepad1.y;
            buttonB = gamepad1.b;
            buttonA = gamepad1.a;
            buttonX = gamepad1.x;


            LeftTrigger = gamepad1.left_bumper;
            DpadUp = gamepad1.dpad_up;
            DpadMiddle = gamepad1.dpad_right;
            DpadBottom = gamepad1.dpad_down;

           /* AXIS_Y = gamepad1.left_stick_y;
            AXIS_Y2 = gamepad1.right_stick_y;
*/


            /*telemetry.addData("LeftJoystick", AXIS_Y);
            telemetry.update();


            telemetry.addData("RightJoystick", AXIS_Y2);
            telemetry.update();

            AXIS_Y = Range.clip(AXIS_Y, -1, 1);

            AXIS_Y2 = Range.clip(AXIS_Y2, -1, 1);
            */

            // The code below is used to start the Flywheel Motor(s) at 100% Throttle when the 'Y' button is pushed on the Controller.
            if (buttonY) {
                telemetry.addData("All Flywheel Motors Engaged.", " ");
                telemetry.update();
                current_power = Range.clip(current_power, -1, 1);
                Flywheel1.setPower(1.00);
                Flywheel2.setPower(1.00);
            }


            if (buttonX && GOOD) {
                GOOD = false;
                telemetry.addData("Current Power Level Is: ", current_power);

                current_power = (current_power + 0.05);

                telemetry.addData("Calculated Power", current_power);

                current_power = Range.clip(current_power, -1, 1);

                telemetry.addData("Clipped Power", current_power);

                telemetry.addData("Motor 2 Power", Flywheel2.getPower());
                telemetry.update();

                Flywheel1.setPower(current_power);
                Flywheel2.setPower(current_power);
                GOOD = true;
                sleep(200);
            }


            if (buttonA && GOOD) {
                GOOD = false;
                telemetry.addData("Current Power Level Is: ", current_power);

                current_power = (current_power - 0.05);

                telemetry.addData("Calculated Power", current_power);

                current_power = Range.clip(current_power, -1, 1);

                telemetry.addData("clipped power", current_power);
                telemetry.update();

                Flywheel1.setPower(current_power);
                Flywheel2.setPower(current_power);
                GOOD = true;
                sleep(200);
            }



            // The code below is used to stop the Flywheel Motor(s) when the 'B' button is pushed on the Controller.
            if (buttonB) {
                telemetry.addData("Stopping All Flywheel Motors.", " ");
                telemetry.update();
                current_power = Range.clip(current_power, -1, 1);
                Flywheel1.setPower(0.00);
                Flywheel2.setPower(0.00);
            }

            if (LeftTrigger) {
                telemetry.addData("All Flywheel Motors Engaged in Reverse. ", " ");
                telemetry.update();
                current_power = Range.clip(current_power, -1, 1);
                Flywheel1.setPower(-1.00);
                Flywheel2.setPower(-1.00);
            }

            if (DpadUp) {
                telemetry.addData("Motors set to Top.", " ");
                telemetry.update();
                current_power = Range.clip(current_power, -1, 1);
                Flywheel1.setPower(0.90);
                Flywheel2.setPower(0.90);
            }

            if (DpadMiddle) {
                telemetry.addData("Motors set to Middle.", " ");
                telemetry.update();
                current_power = Range.clip(current_power, -1, 1);
                Flywheel1.setPower(0.60);
                Flywheel2.setPower(0.60);
            }

            if (DpadBottom) {
                telemetry.addData("Motors set to Bottom.", " ");
                telemetry.update();
                current_power = Range.clip(current_power, -1, 1);
                Flywheel1.setPower(0.30);
                Flywheel2.setPower(0.30);
            }













            //public void subtractpower (float amount_power) {
            //    float target_power1 =  ((currentpower) - (float)amount_power /*the number you want to subtractby */);
            //    float currentpower = ((float) Flywheel1.getPower());
            //    Flywheel1.setPower(target_power1);
            //    Flywheel2.setPower(target_power1);
            //    while (Flywheel1.isBusy() | Flywheel2.isBusy());
            // }


           // public void addpower (float amount_addpower) {
           //     float target_power1 =  ((currentpower) + (float)amount_power /*the number you want to subtractby */);
           //     float currentpower = ((float) Flywheel1.getPower());
           //     Flywheel1.setPower(target_power1);
           //     Flywheel2.setPower(target_power1);
           //     while (Flywheel1.isBusy() | Flywheel2.isBusy());
           // }





           /* DriveLeft.setPower(AXIS_Y);
            DriveRight.setPower(AXIS_Y2);
            */



        }
    }
}