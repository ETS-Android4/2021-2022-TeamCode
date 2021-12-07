

package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous(name = "FullAutonomous2", group = "Concept")

public class FreightFrenzyAuto1 extends LinearOpMode {
    DcMotor motorFrontLeft = hardwareMap.dcMotor.get("motorFrontLeft");
    DcMotor motorFrontRight = hardwareMap.dcMotor.get("motorFrontRight");
    DcMotor motorBackLeft = hardwareMap.dcMotor.get("motorBackLeft");
    DcMotor motorBackRight = hardwareMap.dcMotor.get("motorBackRight");
    DcMotor flyWheel = hardwareMap.dcMotor.get("flyWheel");



    @Override
    public void runOpMode() {
        // The TFObjectDetector uses the camera frames from the VuforiaLocalizer, so we create that
        // first.



        /** Wait for the game to begin **/
        telemetry.addData(">", "Press Play to start op mode");
        telemetry.update();
        waitForStart();
        if (opModeIsActive()) {

            while(opModeIsActive()) {
                opModeIsActive();
                //Run the series of movements

                /*


                 */
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
