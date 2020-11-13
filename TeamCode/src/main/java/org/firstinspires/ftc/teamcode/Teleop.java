package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;


import org.firstinspires.ftc.robotcore.external.Telemetry;

    @TeleOp(name="Teleop")
    public class Teleop extends LinearOpMode {


        DcMotor Motor;
        Servo Rotation;
        Servo Arm;
        Servo loadingservo;


        DcMotor Flywheel1;
        DcMotor Flywheel2;


        boolean buttonY;
        boolean buttonB;
        boolean buttonA;
        boolean buttonX;
        boolean dpad_up;
        boolean dpad_down;
        boolean RightBumper;
        boolean RightTrigger;
        boolean LeftTrigger;
        boolean LeftBumper;
        boolean dpad_right;
        boolean dpad_left;


        boolean buttonY2;
        boolean buttonB2;
        boolean buttonA2;
        boolean buttonX2;
        boolean dpad_up2;
        boolean dpad_down2;
        boolean RightBumper2;
        boolean RightTrigger2;
        boolean LeftTrigger2;
        boolean LeftBumper2;
        boolean dpad_right2;
        boolean dpad_left2;


        float TargetPosition;

        double current_power;

        boolean GOOD = true;



        @Override
        public void runOpMode() throws InterruptedException {

            Flywheel1 = hardwareMap.dcMotor.get("Left");
            Flywheel2 = hardwareMap.dcMotor.get("Right");
            Flywheel2.setDirection(DcMotor.Direction.REVERSE);

            Motor = hardwareMap.dcMotor.get("Left");
            Motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            Motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            Arm = hardwareMap.servo.get("Arm");
            Rotation = hardwareMap.servo.get("Rotation");

            waitForStart();

            while (opModeIsActive()) {

                buttonY = gamepad1.y;
                buttonB = gamepad1.b;
                buttonA = gamepad1.a;
                buttonX = gamepad1.x;
                RightTrigger = gamepad1.right_bumper;
                LeftBumper = gamepad1.left_bumper;
                RightBumper = gamepad1.right_bumper;
                LeftTrigger = gamepad1.left_bumper;
                dpad_up = gamepad1.dpad_up;
                dpad_down = gamepad1.dpad_down;
                dpad_left = gamepad1.dpad_left;
                dpad_right = gamepad1.dpad_right;

                buttonY2 = gamepad2.y;
                buttonB2 = gamepad2.b;
                buttonA2 = gamepad2.a;
                buttonX2 = gamepad2.x;
                RightTrigger2 = gamepad2.right_bumper;
                LeftBumper2 = gamepad2.left_bumper;
                RightBumper2 = gamepad2.right_bumper;
                LeftTrigger2 = gamepad2.left_bumper;
                dpad_up2 = gamepad2.dpad_up;
                dpad_down2 = gamepad2.dpad_down;
                dpad_left2 = gamepad2.dpad_left;
                dpad_right2 = gamepad2.dpad_right;




                    // Code for controller 1.

                    if (dpad_up) {

                        telemetry.addData("Motors set to Top Goal.", " ,");
                        telemetry.update();

                        Flywheel1.setPower(1.00);
                        Flywheel2.setPower(1.00);
                    }

                    if (dpad_right) {

                        telemetry.addData("Motors set to Middle Goal.", " ,");
                        telemetry.update();

                        Flywheel1.setPower(0.60);
                        Flywheel2.setPower(0.60);
                    }


                    if (dpad_down) {

                        telemetry.addData("Motors set to Bottom Goal.", " ,");
                        telemetry.update();

                        Flywheel1.setPower(0.30);
                        Flywheel2.setPower(0.30);
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


                    if (buttonB) {
                        telemetry.addData("Stopping All Flywheel Motors.", " ");
                        telemetry.update();
                        current_power = Range.clip(current_power, -1, 1);
                        Flywheel1.setPower(0.00);
                        Flywheel2.setPower(0.00);
                    }


                    if (buttonY) {
                        telemetry.addData("All Flywheel Motors Engaged.", " ");
                        telemetry.update();
                        current_power = Range.clip(current_power, -1, 1);
                        Flywheel1.setPower(1.00);
                        Flywheel2.setPower(1.00);
                    }

                    if (LeftBumper) {
                        loadingservo.setPosition(0.00);
                        telemetry.addData("Closing.", " ");
                        telemetry.update();
                    }


                    if (RightBumper) {
                        loadingservo.setPosition(1.00);
                        telemetry.addData("Opening.", " ");
                        telemetry.update();
                    }


                    /*  // Code for controller 2.

                    if (RightBumper2) {
                        Arm.setPosition(1.00);
                        telemetry.addData("letting go", " ,");
                        telemetry.update();
                    }

                    if (buttonY2) {
                        Rotation.setPosition(1.00);
                        telemetry.addData("Rotating Arm", " ,");
                        telemetry.update();
                    }


                    if (buttonX2) {
                        Rotation.setPosition(0.00);
                        telemetry.addData("Rotating Arm", " ,");
                        telemetry.update();
                    }


                    if (buttonB2) {
                        Motor.setPower(-0.65);
                        Motor.setTargetPosition(0);
                        telemetry.addData("Lowering", " ,");
                        telemetry.update();
                    }

                    //This is resetting the motor to zero, its starting position, so that the wobble goal can be placed down.

                    if (LeftBumper2) {
                        Arm.setPosition(0.00);
                        telemetry.addData("grabbing", " ,");
                        telemetry.update();
                    }


                    if (buttonA2) {
                        Motor.setTargetPosition(5819);
                        telemetry.addData("Lifting", " ,");
                        telemetry.update();
                     }
                   */
                }
            }
        }
