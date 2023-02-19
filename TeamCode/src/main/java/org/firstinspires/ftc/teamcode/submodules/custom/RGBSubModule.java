package org.firstinspires.ftc.teamcode.submodules.custom;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver.BlinkinPattern;

import org.firstinspires.ftc.teamcode.BaseOpMode;
import org.firstinspires.ftc.teamcode.HardwareNaming;
import org.firstinspires.ftc.teamcode.submodules.BaseSubModule;

public class RGBSubModule extends BaseSubModule {
    public RGBSubModule(BaseOpMode baseOpMode) {
        super(baseOpMode);
    }

    private boolean liftDown = false;
    private boolean junctionFound = false;
    private boolean junctionAligned = false;

    private final RevBlinkinLedDriver blinkinLedDriver = baseOpMode.hardwareMap.get(RevBlinkinLedDriver.class, HardwareNaming.rgb);

    private final BlinkinPattern liftDownPattern = BlinkinPattern.RAINBOW_PARTY_PALETTE;
    private final BlinkinPattern liftUpPattern = BlinkinPattern.RED;
    private final BlinkinPattern junctionFoundPattern = BlinkinPattern.YELLOW;
    private final BlinkinPattern junctionAlignedPattern = BlinkinPattern.GREEN;

    @Override
    public void onUpdate() {
        if (liftDown) {
            blinkinLedDriver.setPattern(liftDownPattern);
        } else {
            blinkinLedDriver.setPattern(liftUpPattern);
        }

        if (junctionFound) {
            blinkinLedDriver.setPattern(junctionFoundPattern);
        }

        if (junctionAligned) {
            blinkinLedDriver.setPattern(junctionAlignedPattern);
        }
    }

    public void setLiftDown(boolean liftDown) {
        this.liftDown = liftDown;
    }

    public void setJunctionFound(boolean junctionFound) {
        this.junctionFound = junctionFound;
    }

    public void setJunctionAligned(boolean junctionAligned) {
        this.junctionAligned = junctionAligned;
    }
}
