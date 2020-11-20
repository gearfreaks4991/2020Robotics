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
    DcMotor intake;
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
    boolean button2A;
    double current_power;
    Servo loadingservo;
    boolean GOOD = true;

    int GREAT = 0;

    double FL_float = 0;
    double FR_float = 0;
    double BL_float = 0;
    double BR_float = 0;

    //DcMotorController.DeviceMode DevMode;
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {

       intake = hardwareMap.dcMotor.get("intake");
        
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
            
            button2A = gamepad2;

            if (buttonX = true) {
                Motor.setPower(0.80);
            }
            if (buttonX = false) {
                Motor.setPower(0.00);
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

            void stopIsPushed () {
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
            
            if (button2A == true){
                intake.setPower(1.00);
                
            }
            if (button2A == false);
            intake.setPower(0.00);

            }
        }
    }



//  ColorSensor color;                //for reading color on beacon


//  boolean LEDState = false;//mode of the color sensor;
//Active = true, Passive = false
// Send telemetry message to signify robot waiting;

//     telemetry.addData("Status", "Ready to run");    //
//              telemetry.update();

