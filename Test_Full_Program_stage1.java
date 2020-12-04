package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Test_Full_Program_stage1")
public class Test_Full_Program_stage1 extends LinearOpMode {

    // Motors are Gobilda 5202 and rotate at around 435 Revolutions/Rotations Per Minute (RPM)
    DcMotor FL;
    DcMotor FR;
    DcMotor BL;
    DcMotor BR;
    // - motor types and variable names
    
    // - drivetrain power variables

    // Intake motor
    // - motor type and variable name
    DcMotor Intake; //This is using an AndyMark Neverest 40 Motor
    // Flipper motor
    // lodingservo is a smart servo used for our flipper on the robot
    Servo loadingservo;
    // Fly wheel motors
    DcMotor Flywheel1;
    DcMotor Flywheel2;
    //both Flywheel1 and Flywheel2 are neverrest 40's


    // Setting the power of the drivetrain motor variables.
    
    double FL_power = 0.0;
    double FR_power = 0.0;
    double BL_power = 0.0;
    double BR_power = 0.0;
    

    // buttons that will be used to control the robot's functions
    boolean buttonY;
    boolean buttonB;
    boolean buttonA;
    boolean buttonX;
    float RightTrigger;//this is for loading rings and resetting the flipper
    float LeftTrigger;
    boolean LeftBumper;
    boolean RightBumper;
    boolean dpad_right;
    boolean dpad_left;
    boolean dpad_up;
    boolean dpad_down;

    float StickX;
    float StickY;


    // additional variables used
    double current_power = 1.0;
    boolean GOOD = true;



    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {

        // harware mapping
        // - drivetrain
        // - intake
        Intake = hardwareMap.dcMotor.get("Intake");
        // - flipper
        loadingservo = hardwareMap.servo.get("flipper");
        // - flywheel
        Flywheel1 = hardwareMap.dcMotor.get("Left");
        Flywheel2 = hardwareMap.dcMotor.get("Right");
        Flywheel2.setDirection(DcMotor.Direction.REVERSE);

        // Hardware mapping the Drivetrain's 4 main drive motors, they should be Gobilda 5202 which spin at 435 RPM.
        FL = hardwareMap.dcMotor.get("FL");
        FR = hardwareMap.dcMotor.get("FR");
        BL = hardwareMap.dcMotor.get("BL");
        BR = hardwareMap.dcMotor.get("BR");

//      Reversing 2 of the motors to allow the wheels to all move in the correct direction.
        FL.setDirection(DcMotor.Direction.REVERSE);
        BL.setDirection(DcMotor.Direction.REVERSE);

        // Telemetry that ensures the robot is ready to run the program.
        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        while (opModeIsActive()) {
            buttonA = gamepad1.a;
            buttonB = gamepad1.b;
            buttonX = gamepad1.x;
            buttonY = gamepad1.y;
            RightTrigger = gamepad1.right_trigger;
            LeftBumper = gamepad1.left_bumper;
            RightBumper = gamepad1.right_bumper;
            LeftTrigger = gamepad1.left_trigger;
            dpad_up = gamepad1.dpad_up;
            dpad_down = gamepad1.dpad_down;
            dpad_left = gamepad1.dpad_left;
            dpad_right = gamepad1.dpad_right;

            // The joystick needed to drive the robot.
            StickX = gamepad1.left_stick_x;
            StickY = gamepad1.left_stick_y;

            // Check the joystick to control the Drivetrain program.
            // Gets the values of the joystick.
            float Yvalue1 = gamepad1.left_stick_y;
            float Xvalue2 = gamepad1.right_stick_x;

            // check to see if any buttons are pressed
            // --- should be gamepad2


            // Calculates the power of all the wheels of the Drivetrain.
             FL_power = (Yvalue1 - Xvalue2);
             FR_power = (Yvalue1 + Xvalue2);
             BL_power = (Yvalue1 - Xvalue2);
             BR_power = (Yvalue1 + Xvalue2);

            // Makes sure the power levels are locked in-between the values of 1.00 and -1.00.
            FL_power = Range.clip(FL_power, -1, 1);
            FR_power = Range.clip(FR_power, -1, 1);
            BL_power = Range.clip(BL_power, -1, 1);
            BR_power = Range.clip(BR_power, -1, 1);

            // Sets the power of the motors to the value of the Joystick.
            FL.setPower(FL_power);
            FR.setPower(FR_power);
            BL.setPower(BL_power);
            BR.setPower(BR_power);




            // intake motor control
            if (RightBumper == true) { //Taking in rings
                Intake.setPower(0.80);
            }
            else{
                Intake.setPower(0.00);
            }


            if (LeftBumper == true) { //Pushing out rings
                Intake.setPower(-0.80);
            }
            else{
                Intake.setPower(0.00);
            }
            // flipper motor control
            if (RightTrigger > 0.1) {
                loadingservo.setPosition(0.00);
                telemetry.addData("Loading.", " ");
                telemetry.update();
                wait(200);
                loadingservo.setPosition(1.00);
                telemetry.addData("Ready.", " ");
                telemetry.update();
            }
            // flywheel motor control

            // this button wll set the power to zero
            if (buttonB) {
                telemetry.addData("Stopping All Flywheel Motors.", " ");
                telemetry.update();
                current_power = Range.clip(current_power, -1, 1);
                Flywheel1.setPower(0.00);
                Flywheel2.setPower(0.00);
            }
            // this button will set the power to what ever "current_power" is
            if (buttonA) {
                telemetry.addData("All Flywheel Motors Engaged.", " ");
                telemetry.update();
                current_power = Range.clip(current_power, -1, 1);
                Flywheel1.setPower(current_power);
                Flywheel2.setPower(current_power);
            }

            //this button will set "current_power" to 100% throttle
            if (dpad_up) {
                telemetry.addData("Motors set to Top Goal.", " ,");
                telemetry.update();
                current_power=(1.00);
                Flywheel1.setPower(current_power);
                Flywheel2.setPower(current_power);
            }
            //this button will set "current_power" to 60% throttle
            if (dpad_right) {
                telemetry.addData("Motors set to Middle Goal.", " ,");
                telemetry.update();
                current_power=(0.60);
                Flywheel1.setPower(current_power);
                Flywheel2.setPower(current_power);
            }

            //this button will set "current_power" to 30% throttle
            if (dpad_down) {
                telemetry.addData("Motors set to Bottom Goal.", " ,");
                telemetry.update();
                current_power=(0.30);
                Flywheel1.setPower(current_power);
                Flywheel2.setPower(current_power);
            }
            
            // this button will add .05% to "current_power"
            if (buttonX && GOOD) {
                GOOD = false;
                telemetry.addData("Current Power Level Is: ", current_power);
                current_power = (current_power + 0.05);
                telemetry.addData("Calculated Power", current_power);
                current_power = Range.clip(current_power, -1, 1);
                telemetry.addData("Clipped Power", current_power);
                telemetry.addData("Motor 2 Power", Flywheel2.getPower());
                telemetry.update();
                GOOD = true;
                sleep(200);
            }
            // this button will take .05% from "current_power"
            if (buttonY && GOOD) {
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
        }
    }
}