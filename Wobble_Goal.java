package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;


@TeleOp (name = "Wobble_Goal")
public class Wobble_Goal extends LinearOpMode {

    // Declare OpMode members.
    DcMotor Rotation;
    Servo Arm;
    boolean buttonA;
    boolean buttonB;
    boolean buttonX;
    boolean buttonY;
    boolean dpad_down;
    boolean dpad_up;
    int destination;
    boolean RightBumper;
    boolean LeftBumper;

    @Override
    public void runOpMode() throws InterruptedException {
//Motor declaration: I called it motor because there is only one motor for this mechanism, so there is no point in naming or numbering it by variety.
        Rotation = hardwareMap.dcMotor.get("left");
        Rotation.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Rotation.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Arm = hardwareMap.servo.get("Arm");

        waitForStart();


        while (opModeIsActive()) {
            buttonA = gamepad2.a;
            buttonB = gamepad2.b;
            buttonX = gamepad2.x;
            buttonY = gamepad2.y;
            dpad_down = gamepad2.dpad_down;
            dpad_up = gamepad2.dpad_up;
//            destination = Rotation.getCurrentPosition();

            if (dpad_down){
                Rotation.setPower(.75);
                Rotation.setTargetPosition(0);
                telemetry.addData("retract", " ,");
            }

            //This part is the servo code, where it will rotate the arm grabbing the wobble goal, 90 degrees.

/*Continuous servo: (0-49 is reverse) (50 is resting) (51-100 is forwards)
Smart Servo: 0 is minimum, 1 is max. The parameters can be set however, but 0 will always be min, 1 will always be max.
I chose the max to be 90 degrees, because the servo will only need to go 90 degrees.
*/

/*I plan to use a rack and pinion system to move the wobble goal over the wall.
I need to know the number of ticks for the wall and the pinion gear, so I can figure out how much I need the pinion gear to travel.
*/
/*
This is the circumference of the pinion gear: 2.597244094488189.
Divide the the number of ticks in one rotation by circumference of the wheel. This is the number of ticks in one inch.
(1120/2.597244094488189=431.2263149916629)
The number of ticks in an inch is 431.
I can use this formula to find the number of ticks in the wall that I need to travel.
The wall is 12 inches.
The wall is the number of ticks in an inch multiplied by the inches I need to travel.
(431x12=5172)
The wall is 5172 ticks.
Since the wobble goal is 12.5 inches, I'll need to move the wobble goal up at least 5,387.5 ticks.
I will add another inch in order to give the wobble goal some clearance when it is lifted.
It will take 5,818.5 ticks in order to raise the wobble goal an inch over the field wall.
*/
            //This is bringing the wobble goal up.

            if (buttonA) {
                Rotation.setPower(.65);
                destination+=100; // plus and equal sign adds 100 and stores 100 as its new base value
                Rotation.setTargetPosition(destination); // the target is the new value that is equated
                telemetry.addData("destination", destination);
                telemetry.update();
            }

            //This is resetting the motor to zero, its starting position, so that the wobble goal can be placed down.

            if (buttonB) {
                Rotation.setPower(.65);
                destination-=-100; // minus and equal sign adds 100 and stores 100 as its new base value
                Rotation.setTargetPosition(destination); // the target is the new value that is equated
                telemetry.addData("backwards", destination);
                telemetry.update();
            }
            /*if (buttonX) {
                Rotation.setTargetPosition(192);  //the GoBilda 5202 435rpm motors have 383.6 ticks per rotation.
                 1/4 of that is how much I need to turn. It is now 95.9
                 */
            }
            if (RightBumper) {
                Arm.setPosition(1);
            }
            if (LeftBumper) {
                Arm.setPosition(0);
            }

            if (dpad_up) {
                Rotation.setPower(.75);
                Rotation.setTargetPosition(96);
                telemetry.addData("extend", " ,");
            }



        }
    }


