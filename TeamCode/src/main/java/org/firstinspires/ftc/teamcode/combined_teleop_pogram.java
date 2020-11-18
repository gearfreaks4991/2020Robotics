package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="teleop_program")
    public class teleop_program extends LinearOpMode {

        // Declare OpMode members.
        DcMotor FL;
        DcMotor FR;
        DcMotor BL;
        DcMotor BR;
        DcMotor Motor;
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

        double current_power;
        Servo loadingservo;
        boolean GOOD = true;

        int GREAT=0;

        double FL_float = 0;
        double FR_float = 0;
        double BL_float = 0;
        double BR_float = 0;

        //DcMotorController.DeviceMode DevMode;
        private ElapsedTime runtime = new ElapsedTime();

        @Override
        public void runOpMode() throws InterruptedException {

            Flywheel1 = hardwareMap.dcMotor.get("Left");
            Flywheel2 = hardwareMap.dcMotor.get("Right");
            Flywheel2.setDirection(DcMotor.Direction.REVERSE);

            FL = hardwareMap.dcMotor.get("FL");
            FR = hardwareMap.dcMotor.get("FR");
            BL = hardwareMap.dcMotor.get("BL");
            BR = hardwareMap.dcMotor.get("BR");

            FL.setDirection(DcMotor.Direction.REVERSE);
            BL.setDirection(DcMotor.Direction.REVERSE);


//        GrabPlatform1.setPosition(1);
//        GrabPlatform2.setPosition(0);
//        OpenClaw.setPosition(RELEASE);

            telemetry.addData("Status", "Ready to run");    //
            telemetry.update();

            // Wait for the game to start (driver presses PLAY)
            waitForStart();

            while (opModeIsActive()) {
                buttonA = gamepad1.a;
                buttonB = gamepad1.b;
                buttonX = gamepad1.x;
                buttonY = gamepad1.y;
                RightTrigger = gamepad1.right_bumper;
                LeftBumper = gamepad1.left_bumper;
                RightBumper = gamepad1.right_bumper;
                LeftTrigger = gamepad1.left_bumper;
                dpad_up = gamepad1.dpad_up;
                dpad_down = gamepad1.dpad_down;
                dpad_left = gamepad1.dpad_left;
                dpad_right = gamepad1.dpad_right;


                if (buttonX = true) {
                    Motor.setPower(0.80);
                }
                if (buttonX = false) {
                    Motor.setPower(0.00);
                }

                WheelPower();

            }
            stopIsPushed();
        }

        void WheelPower() {
            float LeftYvalue = gamepad1.left_stick_y;
            float LeftXvalue = gamepad1.left_stick_x;
            float RightXvalue = gamepad1.right_stick_x;

            float FLSpeed = (-LeftYvalue + LeftXvalue + RightXvalue);
            float BLSpeed = (-LeftYvalue - LeftXvalue + RightXvalue);
            float FRSpeed = (-LeftYvalue - LeftXvalue - RightXvalue);
            float BRSpeed = (-LeftYvalue + LeftXvalue - RightXvalue);

            FLSpeed = Range.clip(FLSpeed, -1, 1);
            FRSpeed = Range.clip(FRSpeed, -1, 1);
            BLSpeed = Range.clip(BLSpeed, -1, 1);
            BRSpeed = Range.clip(BRSpeed, -1, 1);

            if(Math.abs(FLSpeed)>0) {
                if (Math.abs(FL_float) < Math.abs(FLSpeed)) {
                    FL_float += FLSpeed / 20;
                } else {
                    FL_float = FLSpeed;
                }
                FL.setPower(FL_float);
            } else {
                FL.setPower(0);
                FL_float = 0;
            }

            if(Math.abs(FRSpeed)>0) {
                if (Math.abs(FR_float) < Math.abs(FRSpeed)) {
                    FR_float += FRSpeed / 20;
                } else {
                    FR_float = FRSpeed;
                }
                FR.setPower(FR_float);
            } else {
                FR.setPower(0);
                FR_float = 0;
            }

            if(Math.abs(BLSpeed)>0) {
                if (Math.abs(BL_float) < Math.abs(BLSpeed)) {
                    BL_float += BLSpeed / 20;
                } else {
                    BL_float = BLSpeed;
                }
                BL.setPower(BL_float);
            } else {
                BL.setPower(0);
                BL_float = 0;
            }

            if(Math.abs(BRSpeed)>0) {
                if (Math.abs(BR_float) < Math.abs(BRSpeed)) {
                    BR_float += BRSpeed / 20;
                } else {
                    BR_float = BRSpeed;
                }
                BR.setPower(BR_float);
            } else {
                BR.setPower(0);
                BR_float = 0;
            }

        }

    /*void xRailLower() {
        GOOD=1;
        telemetry.addData("Boldposition", xRailPosition);
        xRailPosition = 0;
        telemetry.addData("Bposition", xRailPosition);    //
        telemetry.update();
        XRail.setTargetPosition(xRailPosition);
        XRail.setPower(1.00);
        //while (XRail.isBusy()){}
        //XRail.setPower(0.0);
        GOOD=0;
    }*/

        void stopIsPushed(){
            FL.setPower(0.00);
            FR.setPower(0.00);
            BL.setPower(0.00);
            BR.setPower(0.00);
            telemetry.addData("Path", "Complete");
            telemetry.update();
            idle();
        }



        if (buttonB) {
            telemetry.addData("Stopping All Flywheel Motors.", " ");
            telemetry.update();
            current_power = Range.clip(current_power, -1, 1);
            Flywheel1.setPower(0.00);
            Flywheel2.setPower(0.00);
        }



        // The code below is used to put the Flywheel Motor(s) at 100% Throttle IN REVERSE when the 'A' button is pushed on the Controller.
          if (buttonA) {
            telemetry.addData("Flywheel Motors Engaged in Reverse. ", " ");
            telemetry.update();
            Flywheel1.setPower(-1.00);
            Flywheel2.setPower(-1.00);
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

    }



//  ColorSensor color;                //for reading color on beacon


//  boolean LEDState = false;//mode of the color sensor;
//Active = true, Passive = false
// Send telemetry message to signify robot waiting;

//     telemetry.addData("Status", "Ready to run");    //
//              telemetry.update();
