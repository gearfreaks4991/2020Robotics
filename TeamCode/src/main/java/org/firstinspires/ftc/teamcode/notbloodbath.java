package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

@Autonomous (name="notbloodbath")
public class notbloodbath extends LinearOpMode {
    DcMotor DriveLeft;
    DcMotor DriveRight;

    int temp_current_left_position;
    int temp_current_right_position;

    @Override
    public void runOpMode() throws InterruptedException {
        DriveLeft = hardwareMap.dcMotor.get("Drive_left");
        DriveLeft.setDirection(DcMotor.Direction.REVERSE);
        DriveRight = hardwareMap.dcMotor.get("Drive_Right");

        DriveLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        DriveRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();
            /*
            the position is based of of the Andymark 40:1 DCMotors which require 1120 ticks
            per revolution and a 4" wheel
            */

        //DriveLeft.setPower(1.00);
        //DriveRight.setPower(1.00);
        //DriveLeft.setTargetPosition(1783);
        //DriveRight.setTargetPosition(1783);
        //DriveLeft.setPower(0.00);
        //DriveRight.setPower(0.00);
        //i got this number by dividing 20"(the distance i want to travel)
        // by 12.5663706" circumference of my wheel and multiplying that by 1120

        //DriveLeft.setPower(1.00);
        //DriveRight.setPower(1.00);
        //DriveLeft.setTargetPosition(2674);
        //DriveRight.setTargetPosition(2674);
        //DriveLeft.setPower(0.00);
        //DriveRight.setPower(0.00);
        //i got this number by dividing 10"(the distance i want to travel)
        // by 12.5663706" circumference of my wheel and multiplying that by 1120
        //DriveLeft.setPower(1.00);
        //DriveRight.setPower(1.00);
        //DriveLeft.setTargetPosition(3724);
        //DriveRight.setTargetPosition(-1050);
        //DriveRight.setTargetPosition(1624);
        //DriveLeft.setPower(0.00);
        //DriveRight.setPower(0.00);
        //with this one i made the distance between my two wheels 15"
        //then i found the circumference of that and divided it by four giving me 11.7809725
        // i then divided that by 12.5663706 and multiplied the sum by 1120
        //that way when i turn it will be a 90 degree turn

        temp_current_left_position = DriveLeft.getCurrentPosition();
        temp_current_right_position = DriveRight.getCurrentPosition();
        telemetry.addData(" Left Starting Position: ", temp_current_left_position);
        telemetry.addData("Right Starting Position: ", temp_current_right_position);

        drive(20);
        temp_current_left_position = DriveLeft.getCurrentPosition();
        temp_current_right_position = DriveRight.getCurrentPosition();
        telemetry.addData(" Left after drive 20'': ", temp_current_left_position);
        telemetry.addData("Right after drive 20'': ", temp_current_right_position);

        wait(2000);

        turndegrees(90);
        temp_current_left_position = DriveLeft.getCurrentPosition();
        temp_current_right_position = DriveRight.getCurrentPosition();
        telemetry.addData(" Left after 90* turn: ", temp_current_left_position);
        telemetry.addData("Right after 90* turn: ", temp_current_right_position);

        wait(2000);

        drive(10);
        temp_current_left_position = DriveLeft.getCurrentPosition();
        temp_current_right_position = DriveRight.getCurrentPosition();
        telemetry.addData(" Left after drive 10'': ", temp_current_left_position);
        telemetry.addData("Right after drive 10'': ", temp_current_right_position);

        wait(2000);

        turndegrees(112.4);
        /* because i want my inner ange of the top portion of the triangle to be 67.6* i needed to turn
        180* - 67.6* which came out to 112.4
        */

        temp_current_left_position = DriveLeft.getCurrentPosition();
        temp_current_right_position = DriveRight.getCurrentPosition();
        telemetry.addData(" Left after new turn: ", temp_current_left_position);
        telemetry.addData("Right after new turn: ", temp_current_right_position);

        wait(2000);

        drive(22.36);
        /* this distance is the hypotenuse of a triangle with a 20" Length, 10" With and 90* turn
         */
        temp_current_left_position = DriveLeft.getCurrentPosition();
        temp_current_right_position = DriveRight.getCurrentPosition();
        telemetry.addData(" Left after drive 22'': ", temp_current_left_position);
        telemetry.addData("Right after drive 22'': ", temp_current_right_position);

        wait(2000);

        telemetry.addData("Program", "Stopped");
        telemetry.update();


    }

    public void drive (double inches) {
        float distance =  (((float) inches) * (float) 89.127);
        int distance_int = Math.round(distance);
        int target_position_right;
        int target_position_left;
        target_position_right = (DriveRight.getCurrentPosition() + distance_int);
        target_position_left = (DriveLeft.getCurrentPosition() + distance_int);
        DriveRight.setPower(1.00);
        DriveLeft.setPower(1.00);
        DriveRight.setTargetPosition(target_position_right);
        DriveLeft.setTargetPosition(target_position_left);
        DriveRight.setPower(0.00);
        DriveLeft.setPower(0.00);
        while (DriveRight.isBusy() ||  DriveLeft.isBusy());

    }
    /*public void turn (int inches ) {
        int distance =  (inches* 89.127);
        int target_position_right;
        int target_position_left;
        target_position_right = (DriveRight.getCurrentPosition() - distance);
        target_position_left = (DriveLeft.getCurrentPosition() + distance);
        DriveRight.setPower(1.00);
        DriveLeft.setPower(1.00);
        DriveRight.setTargetPosition(target_position_right);
        DriveLeft.setTargetPosition(target_position_left);
        DriveRight.setPower(0.00);
        DriveLeft.setPower(0.00);
    }
   */
    public void turndegrees (double dge ) {
        float degrees = (((float)47.1238 / (360 / (float)dge)) * 1120);
        int degrees_int = Math.round(degrees);
        int target_position_right;
        int target_position_left;
        target_position_right = (DriveRight.getCurrentPosition() - degrees_int);
        target_position_left = (DriveLeft.getCurrentPosition() + degrees_int);
        DriveRight.setPower(1.00);
        DriveLeft.setPower(1.00);
        DriveRight.setTargetPosition(target_position_right);
        DriveLeft.setTargetPosition(target_position_left);
        DriveRight.setPower(0.00);
        DriveLeft.setPower(0.00);
        while (DriveRight.isBusy() ||  DriveLeft.isBusy());
    }
}
