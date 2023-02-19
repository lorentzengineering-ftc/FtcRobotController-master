package org.firstinspires.ftc.teamcode.submodules;

import org.firstinspires.ftc.teamcode.BaseOpMode;

public class BaseSubModule {
    public BaseOpMode baseOpMode;

    public BaseSubModule(BaseOpMode baseOpMode) {
        this.baseOpMode = baseOpMode;
    }

    public void update() {
        onUpdate();
    }

    public void onUpdate() {

    }

    public void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
