package org.firstinspires.ftc.teamcode.submodules.custom;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.BaseOpMode;
import org.firstinspires.ftc.teamcode.HardwareNaming;
import org.firstinspires.ftc.teamcode.submodules.AsyncSubModule;

public class PassSubModule extends AsyncSubModule {
    public PassSubModule(BaseOpMode baseOpMode) {
        super(baseOpMode);
    }

    private final Servo passServoBlue = baseOpMode.hardwareMap.servo.get(HardwareNaming.passServoBlue);
    private final Servo passServoRed = baseOpMode.hardwareMap.servo.get(HardwareNaming.passServoRed);

    private double passServoBlueTarget = 0;
    private double passServoRedTarget = 1;

    @Override
    public void onUpdate() {
        passServoBlue.setPosition(passServoBlueTarget);
        passServoRed.setPosition(passServoRedTarget);
    }

    public void passToOuttake(){
        passServoBlueTarget = 0.275;
        passServoRedTarget = .78;
    }

    public void passToIntake(){
        passServoBlueTarget = .885;
        passServoRedTarget = .15;
    }
}
