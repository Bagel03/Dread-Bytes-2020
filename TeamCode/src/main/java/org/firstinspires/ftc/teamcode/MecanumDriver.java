package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "MecanumDriver (Blocks to Java)", group = "")
public class MecanumDriver extends LinearOpMode {

    private DcMotor driveleftfront;
    private DcMotor driverightfront;
    private DcMotor Driveleftback;
    private DcMotor driverightback;

    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {
        float control_pad_1_left_X;
        double control_pad_1_left_Y;
        float control_pad_1_right_X;
        double front_left_motor_power;
        double front_right_motor_power;
        double rear_left_motor_power;
        double rear_right_motor_power;

        driveleftfront = hardwareMap.get(DcMotor.class, "drive left front");
        driverightfront = hardwareMap.get(DcMotor.class, "drive right front");
        Driveleftback = hardwareMap.get(DcMotor.class, "Drive left back");
        driverightback = hardwareMap.get(DcMotor.class, "drive right back");

        // Put initialization blocks here.
        waitForStart();
        if (opModeIsActive()) {
            // Put run blocks here.
            while (opModeIsActive()) {
                control_pad_1_left_X = gamepad1.left_stick_x;
                control_pad_1_left_Y = -gamepad1.left_stick_y;
                control_pad_1_right_X = gamepad1.right_stick_x;
                front_left_motor_power = control_pad_1_left_X + control_pad_1_left_Y;
                front_left_motor_power = front_left_motor_power + control_pad_1_right_X;
                front_right_motor_power = control_pad_1_left_Y - control_pad_1_left_X;
                front_right_motor_power = front_right_motor_power - control_pad_1_right_X;
                rear_left_motor_power = control_pad_1_left_Y - control_pad_1_left_X;
                rear_left_motor_power = rear_left_motor_power + control_pad_1_right_X;
                rear_right_motor_power = control_pad_1_left_Y + control_pad_1_left_X;
                rear_right_motor_power = rear_right_motor_power - control_pad_1_right_X;
                driveleftfront.setPower(front_left_motor_power);
                driverightfront.setPower(front_right_motor_power);
                Driveleftback.setPower(rear_left_motor_power);
                driverightback.setPower(rear_right_motor_power);
                // Put loop blocks here.
                telemetry.addData("front left power", front_left_motor_power);
                telemetry.addData("front right power", front_right_motor_power);
                telemetry.addData("back left power", rear_left_motor_power);
                telemetry.addData("back right power", rear_right_motor_power);
                telemetry.update();
            }
        }
    }
}
