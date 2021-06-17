package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
// import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@Autonomous(name="Mechanum_Wheel_Robot_Cool_Turning_Pillar_Test_Very_Tick_Test_Version")
public class Mechanum_Wheels_Robot_Cool_Turning_Pillar_Test_Very_Tick_Test_Version extends Ticks_Autonomous_Base {
    @Override
    public void runOpMode() throws InterruptedException {
        init_motors();

        waitForStart();

        //mcturn(1.00, 480);
       // sleep(500);
        //mcturn(-1.00, 960);
        //sleep(500);
        //mcturn(1.00, 432);

        mcdrive(0.50, 1151);
        sleep(1000);
        /*mcstrafe(0.50, 2500);
        sleep(1000);
        mcdrive(-0.50, 1800);
        sleep(1000);
        mcstrafe(-0.50,2500);
        sleep(1000);
        mcturn(0.50, 3900);
        sleep(1000);
        mcturn(-0.50, 3900);
        sleep(1000);
         */

    }
}
