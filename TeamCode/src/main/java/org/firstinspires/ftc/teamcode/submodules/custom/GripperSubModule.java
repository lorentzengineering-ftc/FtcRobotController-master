package org.firstinspires.ftc.teamcode.submodules.custom;

import static java.lang.Thread.sleep;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.BaseOpMode;
import org.firstinspires.ftc.teamcode.HardwareNaming;
import org.firstinspires.ftc.teamcode.submodules.AsyncSubModule;

public class GripperSubModule extends AsyncSubModule {
    public GripperSubModule(BaseOpMode baseOpMode) {
        super(baseOpMode);
    }

    private final Servo blueServo = baseOpMode.hardwareMap.servo.get(HardwareNaming.gripperBlue);
    private final Servo redServo = baseOpMode.hardwareMap.servo.get(HardwareNaming.gripperRed);

    private double blueServoTarget = 0;
    private double redServoTarget = 1;

    @Override
    public void onUpdate() {
        blueServo.setPosition(blueServoTarget);
        redServo.setPosition(redServoTarget);
    }

    public void open() {
        blueServoTarget = .5;
        redServoTarget = .92;
    }

    public void close() {
        blueServoTarget = 0;
        redServoTarget = 1;
    }

    public void fullIntake(int target) {
        new Thread(() -> {
            baseOpMode.gripper.close();

            sleep(250);
            baseOpMode.lift.goToPosition(LiftPosition.MIN, 1, target);
            baseOpMode.turnableGripper.turnToOuttake();
            baseOpMode.pass.passToOuttake();
        }).start();

        sleep(1000);
    }

    public void fullOuttake(int position) {
        baseOpMode.gripper.open();

        sleep(500);
        new Thread(() -> {
            baseOpMode.pass.passToIntake();
            baseOpMode.lift.goToPosition(LiftPosition.MIN, 1, position);
            baseOpMode.turnableGripper.turnToIntake();

            sleep(150);
            baseOpMode.gripper.close();
            sleep(500);
            baseOpMode.gripper.open();
        }).start();
    }

    public void fullOuttake(LiftPosition autonomousFifth) {
        fullOuttake(autonomousFifth.getHeight());
    }
}
