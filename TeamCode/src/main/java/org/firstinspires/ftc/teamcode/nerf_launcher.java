package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotorController;

@TeleOp(name = "nerf_launcher")
public class nerf_launcher extends LinearOpMode {

    DcMotor onlymotor;
    boolean buttonA;

    @Override
    public void runOpMode() throws InterruptedException {
        onlymotor = hardwareMap.dcMotor.get("Left");
        onlymotor.setTargetPosition(0);
        onlymotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        onlymotor.setDirection(DcMotor.Direction.REVERSE);
        waitForStart();

        while (opModeIsActive()) {
            buttonA = gamepad1.a;
// this allows the program to dectect weather or not the A button
// on gamepad one has been pressed and do the function underneath it
            if (buttonA) {
                telemetry.addData("A pressed.", " ");
                telemetry.update();
                float distance = (((float) 7.85) * (float) 142.675);
                int distance_int = Math.round(distance);
                int target_position_launch;
                telemetry.addData("1 current position.", onlymotor.getCurrentPosition());
                telemetry.update();
                target_position_launch = (onlymotor.getCurrentPosition() + distance_int);
                onlymotor.setPower(1.00);
                onlymotor.setTargetPosition(target_position_launch);
                telemetry.addData("2 current position.", onlymotor.getCurrentPosition());
                telemetry.update();
                while (onlymotor.isBusy()) ;
                onlymotor.setPower(0.00);
                telemetry.addData("surprize!.", " ");
                telemetry.update();
            }

        }
    /*i based this section of program of of an Andymark 40:1 motor and a 2.5" diameter gear
        i then found that the ticks per rotation on an andymark 40:1 is 1120 per rotation
        based of this i th then divided my gear circumference by the ticks and found how many ticks
        are in an inch which turned out to be 142.675
     */
        /*public void launch ( double inches){
            float distance = (((float) inches) * (float) 142.675);
            int distance_int = Math.round(distance);
            int target_position_launch;
            target_position_launch = (onlymotor.getCurrentPosition() + distance_int);
            onlymotor.setPower(1.00);
            onlymotor.setTargetPosition(target_position_launch);
            onlymotor.setPower(0.00);
            telemetry.addData("surprize!.", " ");
            telemetry.update();
           while (onlymotor.isBusy());
*/

    }

}