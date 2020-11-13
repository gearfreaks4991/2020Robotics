/*
FTC Team 4991 GearFreaks
Author:  Joe Walton
Date: 29 Apr 2020
Modified by:
Modified Date:
*/


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;


@TeleOp(name="test_platform")
public class test_platform extends LinearOpMode {

    // Declare OpMode members.
    DcMotor Left;
    DcMotor Right;
    DcMotor Conveyor;
    DcMotor Kicker;


    boolean CONVEYOR_STATE = false;

    boolean buttonX;
    boolean buttonA;
    boolean buttonB;
    boolean buttonY;

    float AXIS_Y;
    float AXIS_Y2;

    int GOOD = 0;

    @Override
    public void runOpMode() throws InterruptedException {

        Left = hardwareMap.dcMotor.get("DriveLeft");
        Right = hardwareMap.dcMotor.get("DriveRight");
        Right.setDirection(DcMotor.Direction.REVERSE);



        telemetry.addData("Status", "Ready to run");
        telemetry.update();

        Kicker = hardwareMap.dcMotor.get("kicker");

        Kicker.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        Kicker.setMode(DcMotor.RunMode.RUN_USING_ENCODER);



        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        while (opModeIsActive()) {

            // get current button values
            AXIS_Y = gamepad1.left_stick_y;
            AXIS_Y2 = gamepad1.right_stick_y;

            buttonY = gamepad1.y;
            buttonX = gamepad1.x;
            buttonA = gamepad1.a;
            buttonB = gamepad1.b;

            telemetry.addData("LeftJoystick", AXIS_Y);
            telemetry.update();


            telemetry.addData("RightJoystick", AXIS_Y2);
            telemetry.update();

            AXIS_Y = Range.clip(AXIS_Y, -1, 1);

            AXIS_Y2 = Range.clip(AXIS_Y2, -1, 1);


            if (buttonA) {
                telemetry.addData("Starting Conveyor Motor", " ");
                telemetry.update();
                Conveyor.setPower(0.15);

            }
            if (buttonB) {
                telemetry.addData("Stopping Conveyor Motor", " ");
                telemetry.update();
                Conveyor.setPower(0.00);
            }



                if (buttonX) {
                    //set the power of the motor
                    Kicker.setPower(0.20);
                    // set the target position (the upper most position of the motor)
                    Kicker.setTargetPosition(-10);
                    // return to the starting point
                    Kicker.setTargetPosition(0);
                }


                Left.setPower(AXIS_Y);
                Right.setPower(AXIS_Y2);

            }
            // end of OpMode = Game over/stop pushed
/*
        Left.setPower(0.00);
        Right.setPower(0.00);
        telemetry.addData("Program", "Stopped");
        telemetry.update();
*/
        }

    }



