package org.firstinspires.ftc.teamcode.submodules;

import org.firstinspires.ftc.teamcode.BaseOpMode;

public class AsyncSubModule extends BaseSubModule{

    public AsyncSubModule(BaseOpMode baseOpMode) {
        super(baseOpMode);
    }

    public void start(){
        new Thread(this::onUpdate);
    }

    @Override
    public final void update() {
        super.update();
    }
}
