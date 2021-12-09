package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous(name = "FreightFrenzyAuto2", group = "Concept")
/*

Assuming that both the right motors have been reversed, all the motors spin backward with power 1
 */
public class FreightFrenzyAuto2 extends LinearOpMode {
    DcMotor motorFrontLeft;
    DcMotor motorFrontRight;
    DcMotor motorBackLeft;
    DcMotor motorBackRight;
    DcMotor flyWheel;



    @Override
    public void runOpMode() {
        // The TFObjectDetector uses the camera frames from the VuforiaLocalizer, so we create that
        // first.
        motorFrontLeft = hardwareMap.dcMotor.get("motorFrontLeft");
        motorFrontRight = hardwareMap.dcMotor.get("motorFrontRight");
        motorBackLeft = hardwareMap.dcMotor.get("motorBackLeft");
        motorBackRight = hardwareMap.dcMotor.get("motorBackRight");
        flyWheel = hardwareMap.dcMotor.get("flyWheel");


        /** Wait for the game to begin **/
        telemetry.addData(">", "Press Play to start op mode");
        telemetry.update();
        waitForStart();
        if (opModeIsActive()) {

            while(opModeIsActive()) {
                opModeIsActive();
                //Run the series of movements

                /*
                    Goal for both alliances:
                    Spin duck off carousel
                    Park inside the warehouse(place where the blocks are)

                    Plan for red alliance:
                    Go forward slightly out of start position.
                    Turn right slightly
                    Back up into the wheel
                    Forward slightly
                    Turn right until parallel with the wall
                    Go forward until inside the warehouse



                 */
                forward(100);
                sleep(500);
                backward(100);
                sleep(500);
                left(100);
                sleep(500);
                //Write all code above this
                sleep(30000);
            }

        }

    }

    public void forward(int ticks) {
        //Forward: Set all wheels to go forward. With the right motors reversed, the direction
        //should be negative
        motorFrontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        motorBackRight.setDirection(DcMotorSimple.Direction.REVERSE);

        stop_reset_encoders();

        motorFrontRight.setTargetPosition(-ticks);
        motorFrontLeft.setTargetPosition(-ticks);
        motorBackRight.setTargetPosition(-ticks);
        motorBackLeft.setTargetPosition(-ticks);

        motorFrontRight.setPower(0.3);
        motorFrontLeft.setPower(0.3);
        motorBackRight.setPower(0.3);
        motorBackLeft.setPower(0.3);

        go_to_position();

    }

    public void backward(int ticks) {


        motorFrontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        motorBackRight.setDirection(DcMotorSimple.Direction.REVERSE);

        stop_reset_encoders();

        motorFrontRight.setTargetPosition(ticks);
        motorFrontLeft.setTargetPosition(ticks);
        motorBackRight.setTargetPosition(ticks);
        motorBackLeft.setTargetPosition(ticks);

        motorFrontRight.setPower(0.3);
        motorFrontLeft.setPower(0.3);
        motorBackRight.setPower(0.3);
        motorBackLeft.setPower(0.3);

        go_to_position();

    }



    public void right(int ticks) {
        //Left:
        motorFrontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        motorBackRight.setDirection(DcMotorSimple.Direction.REVERSE);

        stop_reset_encoders();

        motorFrontRight.setTargetPosition(ticks);
        motorFrontLeft.setTargetPosition(-ticks);
        motorBackRight.setTargetPosition(-ticks);
        motorBackLeft.setTargetPosition(ticks);

        motorFrontRight.setPower(0.3);
        motorFrontLeft.setPower(0.3);
        motorBackRight.setPower(0.3);
        motorBackLeft.setPower(0.3);

        go_to_position();
    }

    public void left(int ticks) {
        //Left:
        motorFrontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        motorBackRight.setDirection(DcMotorSimple.Direction.REVERSE);

        stop_reset_encoders();

        motorFrontRight.setTargetPosition(-ticks);
        motorFrontLeft.setTargetPosition(ticks);
        motorBackRight.setTargetPosition(ticks);
        motorBackLeft.setTargetPosition(-ticks);

        motorFrontRight.setPower(0.3);
        motorFrontLeft.setPower(0.3);
        motorBackRight.setPower(0.3);
        motorBackLeft.setPower(0.3);

        go_to_position();
    }

    public void stop_reset_encoders() {
        motorFrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void go_to_position() {


        motorFrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorFrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorBackRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorBackLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (motorFrontRight.isBusy()) {
            telemetry.addData("Running encoder ", "");
        }

        motorFrontRight.setPower(0);
        motorFrontLeft.setPower(0);
        motorBackRight.setPower(0);
        motorBackLeft.setPower(0);
    }
}
