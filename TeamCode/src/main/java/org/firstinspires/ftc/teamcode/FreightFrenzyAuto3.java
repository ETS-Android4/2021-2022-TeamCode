package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous(name = "Red Alliance 2 (No Wheel)", group = "Concept")

public class FreightFrenzyAuto3 extends LinearOpMode {
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
                motorFrontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
                motorBackLeft.setDirection(DcMotorSimple.Direction.REVERSE);

                motorFrontRight.setPower(0.2);
                motorFrontLeft.setPower(-0.2);
                motorBackRight.setPower(-0.2);
                motorBackLeft.setPower(0.2);

                sleep(200);

                motorFrontRight.setPower(0.25);
                motorFrontLeft.setPower(0.25);
                motorBackRight.setPower(0.25);
                motorBackLeft.setPower(0.25);

                sleep(2000);

                motorFrontRight.setPower(0);
                motorFrontLeft.setPower(0);
                motorBackRight.setPower(0);
                motorBackLeft.setPower(0);

                sleep(30000);

            }
        }

    }

    public void forward(int ticks) {
        motorFrontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        motorBackRight.setDirection(DcMotorSimple.Direction.REVERSE);

        stop_reset_encoders();

        //All motors spin forward
        motorFrontRight.setTargetPosition(ticks);
        motorFrontLeft.setTargetPosition(ticks);
        motorBackRight.setTargetPosition(ticks);
        motorBackLeft.setTargetPosition(ticks);

        go_to_position();

    }

    public void backward(int ticks) {
        motorFrontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        motorBackRight.setDirection(DcMotorSimple.Direction.REVERSE);

        stop_reset_encoders();

        //All motors spin backward
        motorFrontRight.setTargetPosition(-ticks);
        motorFrontLeft.setTargetPosition(-ticks);
        motorBackRight.setTargetPosition(-ticks);
        motorBackLeft.setTargetPosition(-ticks);

        go_to_position();
    }

    public void right(int ticks) {
        motorFrontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        motorBackRight.setDirection(DcMotorSimple.Direction.REVERSE);

        stop_reset_encoders();

        //Front left and back right go forward. Others go backward
        motorFrontRight.setTargetPosition(ticks);
        motorFrontLeft.setTargetPosition(-ticks);
        motorBackRight.setTargetPosition(ticks);
        motorBackLeft.setTargetPosition(-ticks);

        go_to_position();
    }

    public void left(int ticks) {
        motorFrontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        motorBackRight.setDirection(DcMotorSimple.Direction.REVERSE);

        //Front left and back right go backward. Others go forward
        motorFrontRight.setTargetPosition(-ticks);
        motorFrontLeft.setTargetPosition(ticks);
        motorBackRight.setTargetPosition(-ticks);
        motorBackLeft.setTargetPosition(ticks);
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
    }
}
