package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@Autonomous(name="blue_power_shot")
public class blue_power_shot extends LinearOpMode {

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
    Servo Loadingservo;

    //-------------------------------- Flywheel Motors --------------------------------\\
    // Both Flywheel 1 and Flywheel 2 use AndyMark Neverest 40 Motors.
    DcMotor Flywheel1;
    DcMotor Flywheel2;

    //-----------------------------Wobble goal servo and motor--------------------------------------\\

    //This is the motor for rotating the wobble goal arm. It will rotate downwards 90 degrees for grabbing.
    DcMotor Rotation;
    //This servo is for grabbing and letting go of the wobble goals.
    Servo Claw;

    @Override
    public void runOpMode() throws InterruptedException {


        //-------------------------------- Hardware Mapping --------------------------------\\


        // Hardware mapping the Drivetrain's 4 main drive motors, they should be Gobilda 5202 which spin at 435 RPM.
        FL = hardwareMap.dcMotor.get("lf");
        FR = hardwareMap.dcMotor.get("rf");
        BL = hardwareMap.dcMotor.get("lb");
        BR = hardwareMap.dcMotor.get("rb");

        // Reversing 2 of the Drivetrain motors to allow the wheels to all move in the correct direction.
        FL.setDirection(DcMotor.Direction.REVERSE);
        BL.setDirection(DcMotor.Direction.REVERSE);



        // - Intake Motor, uses a single AndyMark Neverest 40 Motor.
        Intake = hardwareMap.dcMotor.get("intake");


        // - Flipper Servo.
        Loadingservo = hardwareMap.servo.get("flipper");


        // - Flywheel Motors, which are both AndyMark Neverest 40 Motors.
        Flywheel1 = hardwareMap.dcMotor.get("flyl");
        Flywheel2 = hardwareMap.dcMotor.get("flyr");

        // Reversing one of the Flywheel motors to allow them both to spin in the same direction.
        Flywheel2.setDirection(DcMotor.Direction.REVERSE);

        // - Wobble Goal Mechanism Motors.
        Rotation = hardwareMap.dcMotor.get("rotation");
        Rotation.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Rotation.setTargetPosition(0);
        Rotation.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        Claw = hardwareMap.servo.get("claw");

//        Rotation.setTargetPosition(0);


        //-------------------------------- Robot Start Telemetry & WaitForStart Code. --------------------------------\\

        telemetry.addData("Status", "Ready to run");
        telemetry.update();


        // Wait for the game to start (driver presses PLAY).
        waitForStart();



        while (RunOpMode()) {

            //-------------------------------- Variables and Step-By-Step Code --------------------------------\\


            int right = 1
            int left = 0
            // check to see if any buttons are pressed.

            //Step 1  -  Drive Forward 60 inches (12.566 is the circumfernce.) (4.77 rotations for 60 inches) (1829.77 ticks in 60 inches)
            FL.setPower(1.00);
            FR.setPower(1.00);
            BR.setPower(1.00);
            BL.setPower(1.00);
            FL.setTargetPosition(1830);
            FR.setTargetPosition(1830);
            BR.setTargetPosition(1830);
            BL.setTargetPosition(1830);
            while (FL.isBusy());

            //Step 3  -  Strafe Right
            mcstrafe(9.00,right); //This is a placeholder amount of inches until it is measured

            //Step 4  -  Stop (#) inches from right wall (lined up for the first goal)
                //This will be checked in person and should work because of the class

            //Step 5  -  Start Flywheel
            Flywheel1.setPower(1.00);
            FLywheel2.setPower(1.00);
            //Step 6  -  Load Flywheel using Flipper
            Loadingservo.setPosition(0.00);
            sleep(500);
            Loadingservo.setPosition(1.00);
            //Step 7  -  Strafe to and Shoot at Middle Goal
            mcstrafe(4.00,right);
            Loadingservo.setPosition(0.00);
            sleep(500);
            Loadingservo.setPosition(1.00);
            //Step 8  -  Strafe to and Shoot at Right Goal
            mcstrafe(4.00,right);
            Loadingservo.setPosition(0.00);
            sleep(500);
            Loadingservo.setPosition(1.00);
            Flywheel1.setPower(0.00);
            FLywheel2.setPower(0.00);
            /*Step 9  -  Park on white line - I need to go 9 inches forward
            (12.566 inches is the circumfernce.) (9/12.566=0.716) (0.716x383.6=274.74 ticks) (I rounded to 275)
           */
            FL.setPower(1.00);
            FR.setPower(1.00);
            BR.setPower(1.00);
            BL.setPower(1.00);
            FL.setTargetPosition(275);
            FR.setTargetPosition(275);
            BR.setTargetPosition(275);
            BL.setTargetPosition(275);
        }
        public void mcstrafe(double inches, int direction) {


            float distance = (((float) inches) * (float) 30.5251030464);
            int distance_int = Math.round(distance);


            int target_position_FL;
            int target_position_FR;
            int target_position_BL;
            int target_position_BR;

            if(direction == 1){
                target_position_FL = (FL.getCurrentPosition() + distance_int);
                target_position_FR = (FR.getCurrentPosition() - distance_int);
                target_position_BL = (BL.getCurrentPosition() + distance_int);
                target_position_BR = (BR.getCurrentPosition() - distance_int);

                FL.setTargetPosition(target_position_FL);
                FR.setTargetPosition(target_position_FR);
                BL.setTargetPosition(target_position_BL);
                BR.setTargetPosition(target_position_BR);

            }

            if (direction == 0){
                target_position_FL = (FL.getCurrentPosition() - distance_int);
                target_position_FR = (FR.getCurrentPosition() + distance_int);
                target_position_BL = (BL.getCurrentPosition() - distance_int);
                target_position_BR = (BR.getCurrentPosition() + distance_int);

                FL.setTargetPosition(target_position_FL);
                FR.setTargetPosition(target_position_FR);
                BL.setTargetPosition(target_position_BL);
                BR.setTargetPosition(target_position_BR);
            }


            FL.setPower(1.00);
            FR.setPower(1.00);
            BL.setPower(1.00);
            BR.setPower(1.00);



            FL.setPower(0.00);
            FR.setPower(0.00);
            BL.setPower(0.00);
            BR.setPower(0.00);

            while(FL.isBusy());


        }



    }