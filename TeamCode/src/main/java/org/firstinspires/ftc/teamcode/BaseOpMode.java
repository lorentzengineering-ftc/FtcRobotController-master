package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.submodules.BaseSubModule;
import org.firstinspires.ftc.teamcode.submodules.custom.GripperSubModule;
import org.firstinspires.ftc.teamcode.submodules.custom.LiftSubModule;
import org.firstinspires.ftc.teamcode.submodules.custom.PassSubModule;
import org.firstinspires.ftc.teamcode.submodules.custom.TurnableGripperSubModule;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public abstract class BaseOpMode extends LinearOpMode {

    public LiftSubModule lift;
    public GripperSubModule gripper;
    public TurnableGripperSubModule turnableGripper;
    public PassSubModule pass;


    protected boolean useLoop = true;

    @Override
    public final void runOpMode() throws InterruptedException {
        try {
            constructSubModules();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        handleInnit();

        waitForStart();

        if (!useLoop) {
            new Thread(this::handleLoop).start();
        }

        handleStart();

        if (!useLoop) {
            handleLoop();
        }

        handleStop();
    }

    private void constructSubModules() throws IllegalAccessException {
        System.out.println("newInstanceConstructing");
        for (Field field : this.getClass().getFields()) {
            System.out.println(field.getType().toString());
            if (BaseSubModule.class.isAssignableFrom(field.getType())) {
                try {
                    System.out.println("type" + field.getType().toString());
                    Object newInstance = field.getType()
                            .getConstructor(BaseOpMode.class)
                            .newInstance(this);
                    System.out.println("newInstance = " + newInstance);
                    field.set(this, newInstance);
                } catch (InstantiationException | InvocationTargetException | NoSuchMethodException ignored) {
                    throw new RuntimeException(ignored);
                }
            }
        }
    }

    public void handleInnit() {
        onInnit();
    }

    public void handleStart() {
        onStart();
    }

    public void handleLoop() {
        while (opModeIsActive()) {
            onLoop();

            try {
                update();
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void handleStop() {
        onStop();
    }

    public void update() throws IllegalAccessException {
        for (Field field : this.getClass().getFields()) {
            Object o = field.get(this);
            if (o instanceof BaseSubModule) {
                BaseSubModule baseSubModule = (BaseSubModule) o;

                baseSubModule.update();
            }
        }
    }

    public abstract void onInnit();

    public abstract void onStart();

    public abstract void onStop();

    public abstract void onLoop();
}
