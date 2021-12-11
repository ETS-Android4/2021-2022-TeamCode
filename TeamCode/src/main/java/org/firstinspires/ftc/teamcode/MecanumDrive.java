package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;



@TeleOp
public class MecanumDrive extends LinearOpMode{

    //Motors


    //Basic Mecanum drive for joysticks
    @Override
    public void runOpMode() throws InterruptedException {
        // Declare our motors
        DcMotor motorFrontLeft = hardwareMap.dcMotor.get("motorFrontLeft");
        DcMotor motorBackLeft = hardwareMap.dcMotor.get("motorBackLeft");
        DcMotor motorFrontRight = hardwareMap.dcMotor.get("motorFrontRight");
        DcMotor motorBackRight = hardwareMap.dcMotor.get("motorBackRight");
        DcMotor intake = hardwareMap.dcMotor.get("intake");
        DcMotor flyWheel = hardwareMap.dcMotor.get("flyWheel");
        DcMotor slide = hardwareMap.dcMotor.get("slide");
        CRServo slideServo = hardwareMap.crservo.get("slideServo");

        motorFrontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        motorBackRight.setDirection(DcMotorSimple.Direction.REVERSE);
        // Make sure your ID's match your configuration






        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            double vertical = 0.7*gamepad1.left_stick_y;
            double horizontal = -0.7*gamepad1.left_stick_x;
            double pivot = -0.7*gamepad1.right_stick_x;

            motorFrontRight.setPower(vertical - pivot - horizontal);
            motorBackRight.setPower(vertical - pivot + horizontal);
            motorFrontLeft.setPower(vertical + pivot + horizontal);
            motorBackLeft.setPower(vertical + pivot - horizontal);

            //Engage Linear Slide
            if (gamepad1.a) {
                slide.setPower(0.5);
            } else {
                slide.setPower(0);
            }
            if (gamepad1.b) {
                slide.setPower(-0.5);
            } else {
                slide.setPower(0);
            }

            //Intake
            if (gamepad1.x) {
                intake.setPower(-1);
            }

            //Flywheel
            if (gamepad1.y) {
                flyWheel.setPower(-1);
            } else {
                flyWheel.setPower(0);
            }

            if (gamepad1.dpad_up) {
                motorFrontLeft.setPower(1);
            } else if (gamepad1.dpad_down) {
                motorFrontRight.setPower(1);
            } else if (gamepad1.dpad_left) {
                motorBackLeft.setPower(1);
            } else if (gamepad1.dpad_right) {
                motorBackRight.setPower(1);
            } else {
                motorFrontRight.setPower(0);
                motorBackRight.setPower(0);
                motorFrontLeft.setPower(0);
                motorBackLeft.setPower(0);
            }


        }
    }

}