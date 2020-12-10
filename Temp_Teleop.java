package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Temp_Teleop")
public class Temp_Teleop extends LinearOpMode {
    //drivetrain motors
    // Motors are Gobilda 5202 and rotate at around 435 Revolutions/Rotations Per Minute (RPM)
    DcMotor FL;
    DcMotor FR;
    DcMotor BL;
    DcMotor BR;

    // - drivetrain power variables
    // Setting the power of the drivetrain motor variables.

    double FL_power = 0.0;
    double FR_power = 0.0;
    double BL_power = 0.0;
    double BR_power = 0.0;
    // power determined by the x and y values of the sticks.
    double Yvalue1=0.0;
    double Xvalue1=0.0;
    double Xvalue2 = 0.0;

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

    // buttons that will be used to control the robot's functions
    boolean buttonY2; //
    boolean buttonB2; // Stops Flywheel
    boolean buttonA2; // Starts Flywheel
    boolean buttonX2;
    float RightTrigger; //this is for loading rings and resetting the flipper
    float LeftTrigger;
    boolean LeftBumper;
    boolean RightBumper;
    boolean dpad_right2;
    boolean dpad_left2;
    boolean dpad_up2;
    boolean dpad_down2;

    // additional variables used
    double current_power;
    boolean GOOD = true;

    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {

        // harware mapping
        // - drivetrain
        // Hardware mapping the Drivetrain's 4 main drive motors,
        // they should be Gobilda 5202 which spin at 435 RPM.
        FL = hardwareMap.dcMotor.get("lf");
        FR = hardwareMap.dcMotor.get("rf");
        BL = hardwareMap.dcMotor.get("lb");
        BR = hardwareMap.dcMotor.get("rb");
        // - intake
        Intake = hardwareMap.dcMotor.get("intake");
        // - flipper
        loadingservo = hardwareMap.servo.get("flipper");
        // - flywheel
        Flywheel1 = hardwareMap.dcMotor.get("flyl");
        Flywheel2 = hardwareMap.dcMotor.get("flyr");
        Flywheel2.setDirection(DcMotor.Direction.REVERSE);


//      Reversing 2 of the motors to allow the wheels to all move in the correct direction.
        FL.setDirection(DcMotor.Direction.REVERSE);
        BL.setDirection(DcMotor.Direction.REVERSE);

        // Telemetry that ensures the robot is ready to run the program.
        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        while (opModeIsActive()) {
            // The joystick needed to drive the robot.

            // Check the joystick to control the Drivetrain program.
            // Gets the values of the joystick.
            Yvalue1 = gamepad1.left_stick_y;
            Xvalue1 = gamepad1.left_stick_x;

            Xvalue2 = gamepad1.right_stick_x;

            // check to see if any buttons are pressed
            // --- should be gamepad2


            // Calculates the power of all the wheels of the Drivetrain.
            FL_power = (Yvalue1 - Xvalue1);
            FR_power = (Yvalue1 + Xvalue1);
            BL_power = (Yvalue1 - Xvalue1);
            BR_power = (Yvalue1 + Xvalue1);
/*
            if(Xvalue2 > 0 || Xvalue2 < 0) {
                FL_power = (Xvalue2);
                FR_power = (Xvalue2);
                BL_power = -(Xvalue2);
                BR_power = -(Xvalue2);
            }
*/
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


            buttonA2 = gamepad2.a;
            buttonB2 = gamepad2.b;
            buttonX2 = gamepad2.x;
            buttonY2 = gamepad2.y;
            RightTrigger = gamepad1.right_trigger;
            LeftBumper = gamepad1.left_bumper;
            RightBumper = gamepad1.right_bumper;
            LeftTrigger = gamepad1.left_trigger;
            dpad_up2 = gamepad2.dpad_up;
            dpad_down2 = gamepad2.dpad_down;
            dpad_left2 = gamepad2.dpad_left;
            dpad_right2 = gamepad2.dpad_right;

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
            if (buttonB2) {
                telemetry.addData("Stopping All Flywheel Motors.", " ");
                telemetry.update();
                current_power = Range.clip(current_power, -1, 1);
                Flywheel1.setPower(0.00);
                Flywheel2.setPower(0.00);
            }
            // this button will set the power to what ever "current_power" is
            if (buttonA2) {
                telemetry.addData("All Flywheel Motors Engaged.", " ");
                telemetry.update();
                current_power = Range.clip(current_power, -1, 1);
                Flywheel1.setPower(current_power);
                Flywheel2.setPower(current_power);
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
                telemetry.addData("Motor 2 Power", Flywheel2.getPower());
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
        }
    }
}