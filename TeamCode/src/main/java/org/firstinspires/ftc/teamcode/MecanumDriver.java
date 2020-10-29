package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "MecanumDriver (Blocks to Java)", group = "")
public class MecanumDriver extends LinearOpMode {

    private DcMotor driveLeftFront;
    private DcMotor driveRightFront;
    private DcMotor driveLeftBack;
    private DcMotor driveRightBack;


    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {
        float controlPad1LeftX;
        double controlPad1LeftY;
        float controlPad1RightX;
        double frontLeftMotorPower;
        double frontRightMotorPower;
        double rearLeftMotorPower;
        double rearRightMotorPower;

        driveLeftFront = hardwareMap.get(DcMotor.class, "drive left front");
        driveRightFront = hardwareMap.get(DcMotor.class, "drive right front");
        driveLeftBack = hardwareMap.get(DcMotor.class, "Drive left back");
        driveRightBack = hardwareMap.get(DcMotor.class, "drive right back");

        // Put initialization blocks here.
        waitForStart();
        if (opModeIsActive()) {
            // Put run blocks here.
            while (opModeIsActive()) {
                controlPad1LeftX = gamepad1.left_stick_x;
                controlPad1LeftY = -gamepad1.left_stick_y;
                controlPad1RightX = gamepad1.right_stick_x;
                frontLeftMotorPower = controlPad1LeftX + controlPad1LeftY;
                frontLeftMotorPower = frontLeftMotorPower + controlPad1RightX;
                frontRightMotorPower = controlPad1LeftY - controlPad1LeftX;
                frontRightMotorPower = frontRightMotorPower - controlPad1RightX;
                rearLeftMotorPower = controlPad1LeftY - controlPad1LeftX;
                rearLeftMotorPower = rearLeftMotorPower + controlPad1RightX;
                rearRightMotorPower = controlPad1LeftY + controlPad1LeftX;
                rearRightMotorPower = rearRightMotorPower - controlPad1RightX;
                driveLeftFront.setPower(frontLeftMotorPower);
                driveRightFront.setPower(frontRightMotorPower);
                driveLeftBack.setPower(rearLeftMotorPower);
                driveRightBack.setPower(rearRightMotorPower);
                // Put loop blocks here.
                telemetry.addData("front left power", frontLeftMotorPower);
                telemetry.addData("front right power", frontRightMotorPower);
                telemetry.addData("back left power", rearLeftMotorPower);
                telemetry.addData("back right power", rearRightMotorPower);
                telemetry.update();
            }
        }
    }
}
