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

    // Setting the power of the drivetrain motor variables.
    // /*
    float FL_power = 0.0;
    float FR_power = 0.0;
    float BL_power = 0.0;
    float BR_power = 0.0;
    // */

    // additional variables used
    float StickX;
    float StickY;

    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {

        // Hardware mapping the Drivetrain's 4 main drive motors, they should be Gobilda 5202 which spin at 435 RPM.
        FL = hardwareMap.dcMotor.get("FL");
        FR = hardwareMap.dcMotor.get("FR");
        BL = hardwareMap.dcMotor.get("BL");
        BR = hardwareMap.dcMotor.get("BR");

        // Reversing 2 of the motors to allow the wheels to all move in the correct direction.
        FL.setDirection(DcMotor.Direction.REVERSE);
        BL.setDirection(DcMotor.Direction.REVERSE);


        // Telemetry that ensures the robot is ready to run the program.
        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        while (opModeIsActive()) {

            // The joystick needed to drive the robot.
            StickX = gamepad1.left_stick_x;
            StickY = gamepad1.left_stick_y;

            // Check the joystick to control the Drivetrain program.
            // Gets the values of the joystick.
            float Yvalue1 = gamepad1.left_stick_y;
            float Xvalue2 = gamepad1.right_stick_x;

            // Calculates the power of all the wheels of the Drivetrain.
            float LF = (Yvalue1 - Xvalue2);
            float RF = (Yvalue1 + Xvalue2);
            float LB = (Yvalue1 - Xvalue2);
            float RB = (Yvalue1 + Xvalue2);

            // Makes sure the power levels are locked in-between the values of 1.00 and -1.00.
            LF = Range.clip(LF, -1, 1);
            RF = Range.clip(RF, -1, 1);
            LB = Range.clip(LF, -1, 1);
            RB = Range.clip(RF, -1, 1);
            
            // Sets the power of the motors to the value of the Joystick.
            FL.setPower(LF);
            FR.setPower(RF);
            BL.setPower(LB);
            BR.setPower(RB);
        }
    }
}

