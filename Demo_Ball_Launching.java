package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.ColorSensor;

@TeleOp(name="Demo_Ball_Launching")
public class Demo_Ball_Launching extends LinearOpMode {

    //-------------------------------- Drivetrain Motors --------------------------------\\
    // Motors are Neverest 60:1 Motors and rotate with 1680 Ticks per Rotation.
    DcMotor LeftMotor;
    DcMotor RightMotor;
    DcMotor LaunchingMotor;

    //-----------------------------Touch Sensor for Ball Loading--------------------------------------\\

    //This is sensing if a ball is ready to be loaded.
    ColorSensor Color1;

    //-------------------------------- Variables --------------------------------\\



    // Buttons that will be used to control the robot's functions.

    //Gamepad1 Variables
    float left_y_value;
    float right_y_value;
    double LeftMotorPower;
    double RightMotorPower;
    boolean dpad_down;
    boolean dpad_up;
    boolean AutoMode = false;



    @Override
    public void runOpMode() throws InterruptedException {


        //-------------------------------- Hardware Mapping --------------------------------\\


        // Hardware mapping the Drivetrain's 2 drive motors, they are Neverest 60 Motors and go 1680 ticks per rotation.
        LeftMotor = hardwareMap.dcMotor.get("LeftMotor");
        RightMotor = hardwareMap.dcMotor.get("RightMotor");
        LaunchingMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LaunchingMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        LaunchingMotor.setTargetPosition(210);
        sleep(500);
        LaunchingMotor.setPower(0.00);
        // Reversing 1 of the Drivetrain motors to allow the wheels to both move in the correct direction.
        LeftMotor.setDirection(DcMotor.Direction.REVERSE);
        // - Color Sensor.
        Color1 = hardwareMap.colorSensor.get("ColorSensor");

        while (opModeIsActive()) {



            ///-------------------------------- Defining Buttons and Drivetrain code --------------------------------\\

            left_y_value = gamepad1.left_stick_y;
            right_y_value = gamepad1.right_stick_y;
            LeftMotorPower = left_y_value;
            RightMotorPower = right_y_value;
            LeftMotorPower = Range.clip(LeftMotorPower, -1, 1);
            RightMotorPower = Range.clip(RightMotorPower, -1, 1);
            LeftMotor.setPower(LeftMotorPower);
            RightMotor.setPower(RightMotorPower);


            ButtonA1 = gamepad1.a;
            dpad_down = gamepad1.dpad_down;
            dpad_up = gamepad1.dpad_up;

            //-------------------------------- Ball Launching Code --------------------------------\\

            if (dpad_down) {
                AutoMode = true
                telemetry.addData("AutoMode True", AutoMode;
                telemetry.update();
            }
            if (dpad_up) {
                AutoMode = false
                telemetry.addData("AutoMode False", AutoMode;
                telemetry.update();
            }
            if (AutoMode = false && ButtonA1) {
                sleep(1500);
                LaunchingMotor.setTargetPosition(350); /*Moving 5/24 of a rotation (349.44 ticks)
                (I rounded up to account for the last tick needed in the rotation) */
                LaunchingMotor.setPower(0.50);
                sleep(2000);
                LaunchingMotor.setPower(0.00);
                sleep(1500);
                LaunchingMotor.setTargetPosition(1330); //Moving 2/3 (16/24) of a rotaion (1120 ticks) + initialization movement (210 ticks)
                LaunchingMotor.setPower(0.50);
                sleep(2000);
                LaunchingMotor.setPower(0.00);
            }
            if (AutoMode = true) {
                if (Color1.red()>3 && Color1.blue()>3 && Color1.green()>3) {
                telemetry.addData("Red Color", Color1.red();
                telemetry.addData("Blue Color", Color1.blue();
                telemetry.addData("Green Color", Color1.green();
                telemetry.update();
                    sleep(1500);
                    LaunchingMotor.setTargetPosition(350); /*Moving 5/24 of a rotation (349.44 ticks)
                (I rounded up to account for the last tick needed in the rotation) */
                    LaunchingMotor.setPower(0.50);
                    sleep(2000);
                    LaunchingMotor.setPower(0.00);
                    sleep(1500);
                    LaunchingMotor.setTargetPosition(1330); //Moving 2/3 (16/24) of a rotaion (1120)
                    LaunchingMotor.setPower(0.50);
                    sleep(2000);
                    LaunchingMotor.setPower(0.00);
                }
            }
    }
}