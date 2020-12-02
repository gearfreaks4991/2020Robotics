package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Test_Full_Program_stage1")
public class Test_Full_Program_stage1 extends LinearOpMode {

    // Motors
    DcMotor FL;
    DcMotor FR;
    DcMotor BL;
    DcMotor BR;
    // - motor types and variable names

    // - drivetrain power variables

    // Intake motor
    // - motor type and variable name

    // Flipper motor
    // - motor type and variable name

    // Fly wheel motors
    // motor types and variable names


    // buttons that will be used to control the robot's functions

    // additional variables used


    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {

        // harware mapping
        // - drivetrain
        // - intake
        // - flipper
        // - flywheel

        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        while (opModeIsActive()) {

            // Check the joysticks to control the drivetrain
            // 1. Get the joystick values
            // 2. Calculate the power levels for each mecanum wheel
            // 3. ensure the power levels are within the limits
            // 4. set the power for each wheel

            // check to see if any buttons are pressed
            // --- should be gamepad2

            // intake motor control
            // flipper motor control
            // flywheel motor control
        }
    }
}

