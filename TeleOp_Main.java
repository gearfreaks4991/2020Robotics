package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="TeleOp Main")
public class TeleOp_Main extends LinearOpMode {

    // Drivetrain motors - all GoBilda 5202 series 435 rpm
    DcMotor FL;
    DcMotor FR;
    DcMotor BL;
    DcMotor BR;

    // drivetrain power variables
    double FL_power = 0.0;
    double FR_power = 0.0;
    double BL_power = 0.0;
    double BR_power = 0.0;

    // Intake motor - GoBilda 5202 series 435 rpm (need to confirm)
    DcMotor Intake;

    // used to store the state of the intake (0=off, 1=on)
    boolean Intake_Setting = false;

    // Flipper motor - SRS (Smart Robotics Servo) set to 180 degrees
    Servo Flipper;

    // Fly wheel motors - GoBilda 5202 series 435 rpm
    DcMotor Flywheel_Left;
    DcMotor Flywheel_Right;


    // buttons that will be used to control the robot's functions
    float Yvalue1;   //
    float Xvalue1;   //
    float Xvalue2;   //
    boolean buttonA; // Turns flywheel on
    boolean buttonB; // Turns flywheel off
    boolean buttonX; // moves intake motor forward
    boolean buttonY; // moves intake motor reverse

    boolean buttonA2; // Turns flywheel on
    boolean buttonB2; // Turns flywheel off
    boolean buttonX2; // moves intake motor forward
    boolean buttonY2; // moves intake motor reverse

    boolean dpad_up;
    boolean dpad_down;
    boolean dpad_right;
    boolean dpad_left;

    boolean dpad_right2;
    boolean dpad_left2;
    boolean dpad_up2;
    boolean dpad_down2;

    boolean RightBumper;
    boolean LeftBumper;

    float RightTrigger;
    float LeftTrigger; // push a ring into the flywheel, then reset
    float RightTrigger2;
    float LeftTrigger2; // push a ring into the flywheel, then reset


    // additional variables used
    double current_power;
    boolean GOOD = true;
    String INTAKE_STATUS="";
    String FLYWHEEL_STATUS="";



    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {

        FL = hardwareMap.dcMotor.get("lf");
        FR = hardwareMap.dcMotor.get("rf");
        BL = hardwareMap.dcMotor.get("lb");
        BR = hardwareMap.dcMotor.get("rb");

        FL.setDirection(DcMotor.Direction.REVERSE);
        BL.setDirection(DcMotor.Direction.REVERSE);


// WARNING: potential problem, port 1 on control hub may be reversed!
        BR.setDirection(DcMotor.Direction.REVERSE);

        Intake = hardwareMap.dcMotor.get("intake");

        Flipper = hardwareMap.servo.get("flipper");

        Flywheel_Left = hardwareMap.dcMotor.get("flyl");
        Flywheel_Right = hardwareMap.dcMotor.get("flyr");
        Flywheel_Right.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        while (opModeIsActive()) {

            // Check the joysticks to control the drivetrain
            // 1. Get the joystick values
            Yvalue1 = -gamepad1.left_stick_y;
            Xvalue1 = gamepad1.left_stick_x;
            Xvalue2 = gamepad1.right_stick_x;

            // 2. Calculate the power levels for each mecanum wheel
            FL_power = (Yvalue1 + Xvalue1 - Xvalue2);
            BL_power = (Yvalue1 - Xvalue1 + Xvalue2);
            FR_power = (Yvalue1 - Xvalue1 + Xvalue2);
            BR_power = (Yvalue1 + Xvalue1 - Xvalue2);

            if(Xvalue2 != 0) {
                FL_power = Xvalue2;
                BL_power = Xvalue2;
                FR_power = -(Xvalue2);
                BR_power = -(Xvalue2);
            }

            // 3. ensure the power levels are within the limits
            FL_power = Range.clip(FL_power, -1, 1);
            FR_power = Range.clip(FR_power, -1, 1);
            BL_power = Range.clip(BL_power, -1, 1);
            BR_power = Range.clip(BR_power, -1, 1);

            // 4. set the power for each wheel
            FL.setPower(FL_power);
            FR.setPower(FR_power);
            BL.setPower(BL_power);
            BR.setPower(BR_power);

            // check to see if any buttons are pressed
            // --- should be gamepad2
            buttonA = gamepad1.a;
            buttonB = gamepad1.b;
            buttonX = gamepad1.x;
            buttonY = gamepad1.y;
            buttonA2 = gamepad2.a;
            buttonB2 = gamepad2.b;
            buttonX2 = gamepad2.x;
            buttonY2 = gamepad2.y;

            LeftBumper = gamepad1.left_bumper;
            RightBumper = gamepad1.right_bumper;
            LeftTrigger = gamepad1.left_trigger;
            RightTrigger = gamepad1.right_trigger;

            LeftTrigger2 = gamepad2.left_trigger;
            RightTrigger2 = gamepad2.right_trigger;

            dpad_up = gamepad1.dpad_up;
            dpad_down = gamepad1.dpad_down;
            dpad_left = gamepad1.dpad_left;
            dpad_right = gamepad1.dpad_right;
            dpad_up2 = gamepad2.dpad_up;
            dpad_down2 = gamepad2.dpad_down;
            dpad_left2 = gamepad2.dpad_left;
            dpad_right2 = gamepad2.dpad_right;

            // controls the intake motor buttonX: forward direction
            // first push turns on, second push turns motor off.
            // --- May have to add a wait/sleep
            // intake motor control
            if (RightBumper == true) { //Taking in rings
                Intake.setPower(1.00);
            }
            else{
                Intake.setPower(0.00);
            }


            if (LeftBumper == true) { //Pushing out rings
                Intake.setPower(-1.00);
            }
            else{
                Intake.setPower(0.00);
            }

            // flipper motor control
            if (RightTrigger2 > 0.1) {
                Flipper.setPosition(0.00);
                telemetry.addData("Loading.", " ");
                telemetry.update();
                sleep(200);
                Flipper.setPosition(1.00);
                telemetry.addData("Ready.", " ");
                telemetry.update();
            }
            // flywheel motor control

            // this button wll set the power to zero
            if (buttonB2) {
                telemetry.addData("Stopping All Flywheel Motors.", " ");
                telemetry.update();
                current_power = Range.clip(current_power, -1, 1);
                Flywheel_Left.setPower(0.00);
                Flywheel_Right.setPower(0.00);
            }
            // this button will set the power to what ever "current_power" is
            if (buttonA2) {
                telemetry.addData("All Flywheel Motors Engaged.", " ");
                telemetry.update();
                current_power = Range.clip(current_power, -1, 1);
                Flywheel_Left.setPower(current_power);
                Flywheel_Right.setPower(current_power);
            }
            // The code below is used to set "current_power" to 100%
            // Throttle IN REVERSE when the 'A' button is pushed on the Controller.
            if (dpad_left2) {
                telemetry.addData("Flywheel Motors Engaged in Reverse. ", " ");
                telemetry.update();
                current_power=(-1.00);
            }
            //this button will set "current_power" to 100% throttle
            if (dpad_up2) {
                telemetry.addData("Motors set to Top Goal.", " ,");
                telemetry.update();
                current_power=(1.00);
            }
            //this button will set "current_power" to 60% throttle
            if (dpad_right2) {
                telemetry.addData("Motors set to Middle Goal.", " ,");
                telemetry.update();
                current_power=(0.60);
            }
            //this button will set "current_power" to 30% throttle
            if (dpad_down2) {
                telemetry.addData("Motors set to Bottom Goal.", " ,");
                telemetry.update();
                current_power=(0.30);
            }
            // this button will add .05% to "current_power"
            if (buttonX2 && GOOD) {
                GOOD = false;
                telemetry.addData("Current Power Level Is: ", current_power);
                current_power = (current_power + 0.05);
                telemetry.addData("Calculated Power", current_power);
                current_power = Range.clip(current_power, -1, 1);
                telemetry.addData("Clipped Power", current_power);
                telemetry.addData("Motor 2 Power", Flywheel_Right.getPower());
                telemetry.update();
                GOOD = true;
                sleep(200);
            }
            // this button will take .05% from "current_power"
            if (buttonY2 && GOOD) {
                GOOD = false;
                telemetry.addData("Current Power Level Is: ", current_power);
                current_power = (current_power - 0.05);
                telemetry.addData("Calculated Power", current_power);
                current_power = Range.clip(current_power, -1, 1);
                telemetry.addData("clipped power", current_power);
                telemetry.update();
                GOOD = true;
                sleep(200);
            }


            telemetry.addData("Flywheel: ", FLYWHEEL_STATUS);

            telemetry.update();
        }
    }
}
