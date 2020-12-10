package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import utils.Odometry;

@Autonomous(name = "driveToRings1 (Blocks to Java)", group = "")
public class driveToRings1 extends LinearOpMode {

    private DcMotor Driveleftback;
    private DcMotor driverightback;
    private DcMotor driverightfront;
    private DcMotor driveleftfront;

    private Odometry odometry = new Odometry(0, 0, 0, 16, 16, 1.5f);

    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {
        double front_left_motor_power;
        double front_right_motor_power;
        double rear_left_motor_power;
        double rear_right_motor_power;
        int front_left_POS;
        int front_right_POS;
        int back_left_POS;
        int back_right_POS;

        Driveleftback = hardwareMap.get(DcMotor.class, "Drive left back");
        driverightback = hardwareMap.get(DcMotor.class, "drive right back");
        driverightfront = hardwareMap.get(DcMotor.class, "drive right front");
        driveleftfront = hardwareMap.get(DcMotor.class, "drive left front");

        // Put initialization blocks here.
        waitForStart();
        if (opModeIsActive()) {
            // Put run blocks here.
            Driveleftback.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            driverightback.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            driverightfront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            driveleftfront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            Driveleftback.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            driveleftfront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            driverightfront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            driverightback.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Driveleftback.setTargetPosition(500);
            driveleftfront.setTargetPosition(500);
            driverightfront.setTargetPosition(-500);
            driverightback.setTargetPosition(-500);

//            float[] changes = odometry.getVelocities(put motor powers here)
//            float[] position = odometry.calculate(changes[0], changes[1], changes[2])
            while (opModeIsActive()) {
                Driveleftback.setPower(0.1);
                driveleftfront.setPower(0.1);
                driverightfront.setPower(-0.1);
                driverightback.setPower(-0.1);
                rear_right_motor_power = driverightback.getPower();
                rear_left_motor_power = Driveleftback.getPower();
                front_right_motor_power = driverightfront.getPower();
                front_left_motor_power = driveleftfront.getPower();
                front_left_POS = driveleftfront.getCurrentPosition();
                front_right_POS = driverightfront.getCurrentPosition();
                back_left_POS = Driveleftback.getCurrentPosition();
                back_right_POS = driverightback.getCurrentPosition();
                // Put loop blocks here.
                telemetry.addData("front left power", front_left_motor_power);
                telemetry.addData("front right power", front_right_motor_power);
                telemetry.addData("back left power", rear_left_motor_power);
                telemetry.addData("back right power", rear_right_motor_power);
                telemetry.addData("front left POS", front_left_POS);
                telemetry.addData("front right POS", front_right_POS);
                telemetry.addData("back right POS", back_right_POS);
                telemetry.addData("back left POS", back_left_POS);
                telemetry.update();
            }
        }
    }
}
