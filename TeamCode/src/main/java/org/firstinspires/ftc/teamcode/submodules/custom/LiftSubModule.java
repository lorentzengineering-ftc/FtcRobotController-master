package org.firstinspires.ftc.teamcode.submodules.custom;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.roadrunner.util.NanoClock;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.BaseOpMode;
import org.firstinspires.ftc.teamcode.HardwareNaming;
import org.firstinspires.ftc.teamcode.submodules.BaseSubModule;

public class LiftSubModule extends BaseSubModule {
    private ElapsedTime accelerateTimer = new ElapsedTime();

    public LiftSubModule(BaseOpMode baseOpMode) {
        super(baseOpMode);

        liftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        liftMotor2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        liftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        liftMotor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        liftMotor2.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public int targetPosition = 0;
    public double targetSpeed = .8;

    private DcMotor liftMotor = baseOpMode.hardwareMap.get(DcMotor.class, HardwareNaming.lift);
    private DcMotor liftMotor2 = baseOpMode.hardwareMap.get(DcMotor.class, HardwareNaming.lift2);

    public void setTargetPosition(int targetPosition){
        this.targetPosition = targetPosition;

        accelerateTimer.reset();
    }

    public void setTargetSpeed(double targetSpeed){
        this.targetSpeed = targetSpeed;

        accelerateTimer.reset();
    }

    public void resetLiftEncoders() {
        liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftMotor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        liftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        liftMotor2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void goToPosition(LiftPosition liftPosition, double speed, int offset){
        setTargetPosition(liftPosition.getHeight() + offset);
        setTargetSpeed(speed);
    }

    public void goToPosition(LiftPosition liftPosition, double speed){
        goToPosition(liftPosition, speed, 0);
    }

    public void goToPosition(LiftPosition liftPosition){
        goToPosition(liftPosition, .8);
    }
    

    @Override
    public void onUpdate() {
        double diff = liftMotor.getCurrentPosition() + targetPosition;
        double motorDiff = liftMotor2.getCurrentPosition()  - liftMotor.getCurrentPosition();

        double newSpeed = targetSpeed * (
                Math.min(1, Math.max(-(7.9/7f), diff / 5000f)) * .7 +
                        Math.min(1, Math.max(-.7f, (float) diff / 750f)) * .3);

        if (targetPosition < 500) {
            newSpeed = Math.max(Math.min(0.1, newSpeed), -.8f);
        }

        if (liftMotor.getCurrentPosition() < 500 && targetPosition < 500) {
            newSpeed = Math.max(Math.min(0.1, newSpeed), -.1f);
        }

        double extraSpeed = 0.4 * (Math.min(1, Math.max(-.8f, (float) motorDiff / 5000f)) * .5 +
                Math.min(1, Math.max(-.7f, (float) motorDiff / 350f)) * .5);

        if(motorDiff<0){
            extraSpeed *=.5;
        }

        if (newSpeed < 0) {
            double a = .2;
            newSpeed *= Math.max(Math.min(a, accelerateTimer.seconds()), 0.01) / a;
        }

        FtcDashboard.getInstance().getTelemetry().addData("dif", motorDiff);
        FtcDashboard.getInstance().getTelemetry().addData("pos", liftMotor.getCurrentPosition());
        FtcDashboard.getInstance().getTelemetry().addData("speed", newSpeed);
        FtcDashboard.getInstance().getTelemetry().update();

        liftMotor.setPower(newSpeed);
        liftMotor2.setPower(newSpeed + extraSpeed);
    }
}
