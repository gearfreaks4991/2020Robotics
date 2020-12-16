package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Test_Full_Program_stage1")
public class Temp_Tele extends LinearOpMode {

    //-------------------------------- Drivetrain Motors --------------------------------\\

    // Motors are Gobilda 5202 and rotate at around 435 Revolutions/Rotations Per Minute (RPM).
    DcMotor FL;
    DcMotor FR;
    DcMotor BL;
    DcMotor BR;

    //-------------------------------- Intake Motors --------------------------------\\

    // The motor for the Intake is an AndyMark Neverest 40 Motor.
    DcMotor Intake;

    //-------------------------------- Flipper Servo --------------------------------\\

    // Loadingservo is a smart servo used for our flipper on the robot.
    Servo loadingservo;

    //-------------------------------- Flywheel Motors --------------------------------\\

    // Both Flywheel 1 and Flywheel 2 use AndyMark Neverest 40 Motors.
    DcMotor Flywheel1;
    DcMotor Flywheel2;

    //-------------------------------- Variables --------------------------------\\

    // Setting the power of the drivetrain motor variables.
    double FL_power = 0.0;
    double FR_power = 0.0;
    double BL_power = 0.0;
    double BR_power = 0.0;


    // Buttons that will be used to control the robot's functions.
    boolean buttonY2; // This button is used to decrease the Flywheel Motors' power by 0.05%.
    boolean buttonB2; // This button is used to bring both of the Flywheel's motors to a complete stop.
    boolean buttonA2; // This button is used to set the Flywheel's power to what the variable current_power's value is.
    boolean buttonX2; // This button is used to increase the Flywheel Motors' power by 0.05%.
    float RightTrigger; // This is for loading rings and resetting the flipper.
    // float LeftTrigger;
    boolean LeftBumper; // This is used to start the intake motors to pick up the Rings.
    boolean RightBumper; // This is used to load the rings into the storage so they are ready to be loaded into the Flywheel.
    boolean dpad_right2; // This is used to set the Flywheel Motors to aim at the Middle Goal.
    // boolean dpad_left2;
    boolean dpad_up2; // This is used to set the Flywheel Motors to aim at the Top Goal.
    boolean dpad_down2; // This is used to set the Flywheel Motors to aim at the Bottom Goal.

    // The X and Y values of the Joystick.
    float StickX;
    float StickY;


    //-------------------------------- Misc. Variables --------------------------------\\

    // Current_Power is the variable used to define the power of the Flywheel Motors.
    double current_power = 1.0;

    // The boolean below is used to tell if the power has been increased/decreased in the part of the code which increases and decreases the flywheel motor's power by 0.05%.
    boolean FlywheelState = true;


    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {


        //-------------------------------- Hardware Mapping --------------------------------\\


        // Hardware mapping the Drivetrain's 4 main drive motors, they should be Gobilda 5202 which spin at 435 RPM.
        FL = hardwareMap.dcMotor.get("FL");
        FR = hardwareMap.dcMotor.get("FR");
        BL = hardwareMap.dcMotor.get("BL");
        BR = hardwareMap.dcMotor.get("BR");

        // Reversing 2 of the Drivetrain motors to allow the wheels to all move in the correct direction.
        FL.setDirection(DcMotor.Direction.REVERSE);
        BL.setDirection(DcMotor.Direction.REVERSE);



        // - Intake Motor, uses a single AndyMark Neverest 40 Motor.
        Intake = hardwareMap.dcMotor.get("Intake");


        // - Flipper Servo.
        loadingservo = hardwareMap.servo.get("flipper");


        // - Flywheel Motors, which are both AndyMark Neverest 40 Motors.
        Flywheel1 = hardwareMap.dcMotor.get("Left");
        Flywheel2 = hardwareMap.dcMotor.get("Right");

        // Reversing one of the Flywheel motors to allow them both to spin in the same direction.
        Flywheel2.setDirection(DcMotor.Direction.REVERSE);


        //-------------------------------- Robot Start Telemetry & WaitForStart Code. --------------------------------\\

        telemetry.addData("Status", "Ready to run");
        telemetry.update();


        // Wait for the game to start (driver presses PLAY).
        waitForStart();


        while (opModeIsActive()) {

            ///-------------------------------- Defining Buttons --------------------------------\\

            buttonA2 = gamepad2.a;
            buttonB2 = gamepad2.b;
            buttonX2 = gamepad2.x;
            buttonY2 = gamepad2.y;
            RightTrigger = gamepad1.right_trigger;
            LeftBumper = gamepad1.left_bumper;
            RightBumper = gamepad1.right_bumper;
            // LeftTrigger = gamepad1.left_trigger;
            dpad_up2 = gamepad2.dpad_up;
            dpad_down2 = gamepad2.dpad_down;
            // dpad_left2 = gamepad2.dpad_left;
            dpad_right2 = gamepad2.dpad_right;


            // The joystick values needed to drive the robot.
            StickX = gamepad1.left_stick_x;
            StickY = gamepad1.left_stick_y;

            //-------------------------------- Drivetrain Calculations & Code --------------------------------\\

            // Gets the values of the joystick.
            float Yvalue1 = gamepad1.left_stick_y;
            float Xvalue2 = gamepad1.right_stick_x;

            // check to see if any buttons are pressed.


            // Calculates the power of all the wheels of the Drivetrain.
            FL_power = (Yvalue1 - Xvalue2);
            FR_power = (Yvalue1 + Xvalue2);
            BL_power = (Yvalue1 - Xvalue2);
            BR_power = (Yvalue1 + Xvalue2);

            // Makes sure the power levels are locked in-between the values of 1.00% (100%) power and -1.00% (100%) power.
            FL_power = Range.clip(FL_power, -1, 1);
            FR_power = Range.clip(FR_power, -1, 1);
            BL_power = Range.clip(BL_power, -1, 1);
            BR_power = Range.clip(BR_power, -1, 1);

            // Sets the power of the motors to the value of the Joystick.
            FL.setPower(FL_power);
            FR.setPower(FR_power);
            BL.setPower(BL_power);
            BR.setPower(BR_power);


            //-------------------------------- Intake Motor Control Code --------------------------------\\


            // Taking in the Rings.
            if (RightBumper == true) {
                Intake.setPower(0.80);
            }
            else{
                Intake.setPower(0.00);
            }

            // Pushing out the Rings.
            if (LeftBumper == true) {
                Intake.setPower(-0.80);
            }
            else{
                Intake.setPower(0.00);
            }

            //-------------------------------- Flipper Code --------------------------------\\
            // This portion of the code will load the rings into the Flywheel to fire them to the set goal.
            if (RightTrigger > 0.1) {
                loadingservo.setPosition(0.00);
                telemetry.addData("Loading Rings into Flywheel.", " ");
                telemetry.update();
                wait(200);
                loadingservo.setPosition(1.00);
                telemetry.addData("Returning to resting position.", " ");
                telemetry.update();
            }

            //-------------------------------- Flywheel Motor Code --------------------------------\\
            // This button wll set the Flywheel Motors' power to zero.
            if (buttonB2) {
                telemetry.addData("Stopping All Flywheel Motors.", " ");
                telemetry.update();
                current_power = Range.clip(current_power, -1, 1);
                Flywheel1.setPower(0.00);
                Flywheel2.setPower(0.00);
            }

            // This button will set the Flywheel Motors' power to what the value of "current_power" is.
            if (buttonA2) {
                telemetry.addData("All Flywheel Motors Engaged.", " ");
                telemetry.update();
                current_power = Range.clip(current_power, -1, 1);
                Flywheel1.setPower(current_power);
                Flywheel2.setPower(current_power);
            }

            // This button will set the Flywheel Motors' to 100% throttle.
            if (dpad_up2) {
                telemetry.addData("Flywheel Motors set to Top Goal.", " ,");
                telemetry.update();
                current_power=(1.00);
                Flywheel1.setPower(current_power);
                Flywheel2.setPower(current_power);
            }

            // This button will set "current_power" to 60% throttle.
            if (dpad_right2) {
                telemetry.addData("Flywheel Motors set to Middle Goal.", " ,");
                telemetry.update();
                current_power=(0.60);
                Flywheel1.setPower(current_power);
                Flywheel2.setPower(current_power);
            }

            // This button will set "current_power" to 30% throttle.
            if (dpad_down2) {
                telemetry.addData("Flywheel Motors set to Bottom Goal.", " ,");
                telemetry.update();
                current_power=(0.30);
                Flywheel1.setPower(current_power);
                Flywheel2.setPower(current_power);
            }

            // This button will increase .05% to "current_power".
            if (buttonX2 && FlywheelState) {
                FlywheelState = false;
                telemetry.addData("Current Flywheel Power Level Is: ", current_power);
                current_power = (current_power + 0.05);
                telemetry.addData("Calculated Flywheel Power", current_power);
                current_power = Range.clip(current_power, -1, 1);
                telemetry.addData("Clipped Flywheel Power", current_power);
                telemetry.addData("Flywheel Motor 2 Power", Flywheel2.getPower());
                telemetry.update();
                FlywheelState = true;
                sleep(200);
            }

            // This button will decrease .05% from "current_power".
            if (buttonY2 && FlywheelState) {
                FlywheelState = false;
                telemetry.addData("Current Flywheel Power Level Is: ", current_power);
                current_power = (current_power - 0.05);
                telemetry.addData("Calculated Flywheel Power", current_power);
                current_power = Range.clip(current_power, -1, 1);
                telemetry.addData("clipped Flywheel power", current_power);
                telemetry.update();
                FlywheelState = true;
                sleep(200);
            }




        }
    }
}