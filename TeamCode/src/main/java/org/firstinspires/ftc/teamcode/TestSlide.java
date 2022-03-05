package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;




@TeleOp(name = "testbruh", group = "Concept")
public class TestSlide extends LinearOpMode{

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
        slide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
           if(gamepad1.a) {
               slide.setPower(0.5);
           } else if (gamepad1.b) {
               slide.setPower(-0.5);
           } else {
               slide.setPower(0);
           }
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