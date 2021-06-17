package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
// import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;



@Autonomous(name="Mechanum_Wheel_Robot_Cool_Turning_Pillar_Test")
public class Mechanum_Wheel_Robot_Cool_Turning_Pillar_Test extends Time_Autonomous_Base {
    @Override
    public void runOpMode() throws InterruptedException {
        init_motors();

        waitForStart();

        //mcturn(1.00, 480);
       // sleep(500);
        //mcturn(-1.00, 960);
        //sleep(500);
        //mcturn(1.00, 432);

        mcdrive(0.50, 2400);
        sleep(1000);
        mcstrafe(0.50, 2500);
        sleep(1000);
        mcdrive(-0.50, 1800);
        sleep(1000);
        mcstrafe(-0.50,2500);
        sleep(1000);
        mcturn(0.50, 3900);
        sleep(1000);
        mcturn(-0.50, 3900);
        sleep(1000);

    }
}
