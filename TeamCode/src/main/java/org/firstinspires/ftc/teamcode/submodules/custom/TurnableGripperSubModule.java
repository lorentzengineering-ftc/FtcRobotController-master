package org.firstinspires.ftc.teamcode.submodules.custom;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.BaseOpMode;
import org.firstinspires.ftc.teamcode.HardwareNaming;
import org.firstinspires.ftc.teamcode.submodules.AsyncSubModule;

public class TurnableGripperSubModule extends AsyncSubModule {
    public TurnableGripperSubModule(BaseOpMode baseOpMode) {
        super(baseOpMode);
    }

    private final Servo turnServo = baseOpMode.hardwareMap.servo.get(HardwareNaming.turnGripper);

    private double turnServoTarget = .825;

    @Override
    public void onUpdate() {
        turnServo.setPosition(turnServoTarget);
    }

    public void turnToIntake(){
        turnServoTarget = .1;
    }

    public void turnToOuttake(){
        turnServoTarget = .825;
    }
}
