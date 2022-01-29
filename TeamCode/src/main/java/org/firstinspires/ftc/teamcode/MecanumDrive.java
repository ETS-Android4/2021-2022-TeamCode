package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;




@TeleOp
public class MecanumDrive extends LinearOpMode{

    //Motors
    DcMotor motorFrontLeft = hardwareMap.dcMotor.get("motorFrontLeft");
    DcMotor motorBackLeft = hardwareMap.dcMotor.get("motorBackLeft");
    DcMotor motorFrontRight = hardwareMap.dcMotor.get("motorFrontRight");
    DcMotor motorBackRight = hardwareMap.dcMotor.get("motorBackRight");
    DcMotor intake = hardwareMap.dcMotor.get("intake");
    DcMotor flyWheel = hardwareMap.dcMotor.get("flyWheel");
    DcMotor slide = hardwareMap.dcMotor.get("slide");
    Servo slideServo = hardwareMap.servo.get("slideServo");
    DcMotor[] driveMotors = new DcMotor[4];

    //Basic Mecanum drive for joysticks
    @Override
    public void runOpMode() throws InterruptedException {
        // Declare our motors



        motorFrontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        motorBackRight.setDirection(DcMotorSimple.Direction.REVERSE);
        // Make sure your ID's match your configuration


        slideServo.setPosition(0.02);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            double vertical = 0.7 * gamepad1.left_stick_y;
            double horizontal = -0.7 * gamepad1.left_stick_x;
            double pivot = -0.7 * gamepad1.right_stick_x;

            motorFrontRight.setPower(vertical - pivot - horizontal);
            motorBackRight.setPower(vertical - pivot + horizontal);
            motorFrontLeft.setPower(vertical + pivot + horizontal);
            motorBackLeft.setPower(vertical + pivot - horizontal);

            //Engage Linear Slide
            if (gamepad1.a) {
                liftAndTilt();
            } else if (gamepad1.b) {
                returnLiftAndTilt();
            }

            //Intake
            if (gamepad1.x) {
                intake.setPower(-1);
            } else {
                intake.setPower(0);
            }

            //Flywheel
            if (gamepad1.y) {
                flyWheel.setPower(-1);
            } else {
                flyWheel.setPower(0);
            }


            //Box
            if (gamepad1.left_bumper) {
                slideServo.setPosition(slideServo.getPosition() + 0.05);
                sleep(100);
            } else if (gamepad1.right_bumper) {
                slideServo.setPosition(0.02);
            }


        }

    }
    //This method will lift the slide and tilt the box up
    public void liftAndTilt() {
        slide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slide.setTargetPosition(800);
        slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slide.setPower(0.4);
        while (slide.isBusy()) {
            continue;
        }
        slide.setPower(0);

        //slideServo.setPosition(0.4);
    }

    public void returnLiftAndTilt() {
        slideServo.setPosition(0);

        slide.setTargetPosition(0);
        slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slide.setPower(-0.4);
        while (slide.isBusy()) {
            continue;
        }
        slide.setPower(0);
    }
    //for future use
//    for (int i = 0; i<driveMotors.length; i++) {
//        driveMotors[i].setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//    }
//            for (int i = 0; i<driveMotors.length; i++) {
//        driveMotors[i].setTargetPosition(800);
//    }
//            for (int i = 0; i<driveMotors.length; i++) {
//        driveMotors[i].setMode(DcMotor.RunMode.RUN_TO_POSITION);
//    }

}