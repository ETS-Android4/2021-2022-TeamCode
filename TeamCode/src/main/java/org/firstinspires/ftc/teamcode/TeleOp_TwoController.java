package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;




@TeleOp(name = "Freight Frenzy TeleOp(Two Controllers)", group = "Concept")
public class TeleOp_TwoController extends LinearOpMode{

    //Motors
    DcMotor motorFrontLeft;
    DcMotor motorBackLeft;
    DcMotor motorFrontRight;
    DcMotor motorBackRight;
    DcMotor intake;
    DcMotor flyWheel;
    DcMotor flyWheel2;
    DcMotor slide;
    Servo slideServo;
    DcMotor[] driveMotors;

    //Basic Mecanum drive for joysticks
    @Override
    public void runOpMode() throws InterruptedException {
        // Declare our motors
        int slideMax = 9000;
        motorFrontLeft = hardwareMap.dcMotor.get("motorFrontLeft");
        motorBackLeft = hardwareMap.dcMotor.get("motorBackLeft");
        motorFrontRight = hardwareMap.dcMotor.get("motorFrontRight");
        motorBackRight = hardwareMap.dcMotor.get("motorBackRight");
        intake = hardwareMap.dcMotor.get("intake");
        flyWheel = hardwareMap.dcMotor.get("flyWheel");
        slide = hardwareMap.dcMotor.get("slide");
        slideServo = hardwareMap.servo.get("slideServo");
        driveMotors = new DcMotor[4];
        flyWheel2 = hardwareMap.dcMotor.get("flyWheel2");


        motorFrontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        motorBackRight.setDirection(DcMotorSimple.Direction.REVERSE);
        // Make sure your ID's match your configuration


        slideServo.setPosition(0.02);
//        slide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        slide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
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
                slide.setPower(1);
            } else if (gamepad1.b) {
                slide.setPower(-1);
            } else {
                slide.setPower(0);
            }

            if (gamepad2.y) {
                slideServo.setPosition(0.8);
            } else if (gamepad2.x) {
                slideServo.setPosition(0);
            }

            if (gamepad2.a && slide.getCurrentPosition()<slideMax) {
                slide.setPower(1);
            } else if (gamepad2.b) {
                slide.setPower(-1);
            } else {
                slide.setPower(0);
            }

            if (gamepad2.dpad_up) {
                slideServo.setPosition(0.2);
            }

            //Intake
            if (gamepad1.x) {
                intake.setPower(-1);
            } else {
                intake.setPower(0);
            }

            //Flywheel
            if (gamepad2.left_bumper) {
                flyWheel.setPower(-1);
            } else if (gamepad2.left_trigger>0.8) {
                flyWheel.setPower(1);
            } else if (gamepad2.right_bumper){
                flyWheel2.setPower(-1);
            } else if (gamepad2.right_trigger>0.8) {
                flyWheel2.setPower(1);
            } else {
                flyWheel2.setPower(0);
                flyWheel.setPower(0);
            }


            //Box
            if (gamepad1.left_bumper) {
                slideServo.setPosition(slideServo.getPosition() + 0.05);
                sleep(100);
            } else if (gamepad1.right_bumper) {
                slideServo.setPosition(0.02);
            }

            telemetry.addData("Slide", slide.getCurrentPosition());
            telemetry.update();


        }

    }
    //This method will lift the slide and tilt the box up
    public void liftAndTilt(int height) {
        if (slide.getCurrentPosition()<=height) {
            slide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            slide.setTargetPosition(height);
            slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            slide.setPower(0.4);
            while (slide.isBusy()) {
                telemetry.addData("Slide position", slide.getCurrentPosition());
                telemetry.update();
            }
            slide.setPower(0);
            slideServo.setPosition(0.1);
            return;


        }
    }

    public void returnLiftAndTilt() {
        slideServo.setPosition(0);

        slide.setTargetPosition(0);
        slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slide.setPower(-0.4);
        while (slide.isBusy()) {
            telemetry.addData("Slide position", slide.getCurrentPosition());
            telemetry.update();
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