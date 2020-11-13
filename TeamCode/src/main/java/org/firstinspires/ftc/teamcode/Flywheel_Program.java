package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;


@TeleOp(name="Flywheel_Program")
public class Flywheel_Program extends LinearOpMode {

    DcMotor Flywheel1;
    DcMotor Flywheel2;

    boolean buttonY;
    boolean buttonB;
    boolean buttonA;

    @Override
    public void runOpMode() throws InterruptedException {

        // Hardware Mapping the 2 DcMotors.
        Flywheel1 = hardwareMap.dcMotor.get("Left");
        Flywheel2 = hardwareMap.dcMotor.get("Right");
        Flywheel2.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();

        while (opModeIsActive()) {

            buttonY = gamepad1.y;
            buttonB = gamepad1.b;
            buttonA = gamepad1.a;

            if (buttonY) {
                telemetry.addData("Flywheel Motors Engaged.", " ");
                telemetry.update();
                Flywheel1.setPower(1.00);
                Flywheel2.setPower(1.00);
            }



            // The code below is used to stop the Flywheel Motor(s) when the 'B' button is pushed on the Controller.
            if (buttonB) {
                telemetry.addData("Flywheel Motors Disengaged. ", " ");
                telemetry.update();
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

        }
    }
}