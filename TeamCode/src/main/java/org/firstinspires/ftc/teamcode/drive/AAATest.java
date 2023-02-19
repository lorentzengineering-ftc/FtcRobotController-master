package org.firstinspires.ftc.teamcode.drive;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.trajectory.constraints.TrajectoryVelocityConstraint;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.BaseOpMode;
import org.firstinspires.ftc.teamcode.submodules.custom.LiftPosition;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.util.CM;

/*
 * This is an example of a more complex path to really test the tuning.
 */
@Autonomous(group = " Bjorn drive")
public class AAATest extends BaseOpMode {

    private SampleMecanumDrive drive;

    public AAATest() {
        useLoop = false;
    }

    @Override
    public void onInnit() {
        drive = new SampleMecanumDrive(hardwareMap);

        lift.resetLiftEncoders();
        gripper.fullIntake(0);
    }

    @Override
    public void onStart() {
        Pose2d startPose = CM.pose2d(-90, -160, 90);
        drive.setPoseEstimate(startPose);

        TrajectorySequence seq1 = drive.trajectorySequenceBuilder(startPose)
                .addTemporalMarker(() -> {
                    gripper.fullIntake(LiftPosition.HIGH.getHeight());
                })
                .waitSeconds(.3)
                .lineToLinearHeading(CM.pose2d(-90, -10, 90))
                .lineToLinearHeading(CM.pose2d(-90, -30, 90))
                .turn(toRad(-45))
                .lineToLinearHeading(CM.pose2d(-75, -15, 45))
                .forward(0.5)
                .waitSeconds(1)
                .addTemporalMarker(() -> {
                    gripper.fullOuttake(LiftPosition.AUTONOMOUS_FIFTH);
                })
                .waitSeconds(.5)
                .back(.5)
                .lineToLinearHeading(CM.pose2d(-100, -35, 0))
                .lineToLinearHeading(CM.pose2d(-163, -35, 0))
                .addTemporalMarker(() -> {
                    gripper.fullIntake(LiftPosition.HIGH.getHeight());
                })
//                .setReversed(true)
/*                .splineToSplineHeading(CM.pose2d(-100, -30, 180), 0)
                .splineToSplineHeading(CM.pose2d(-110, -30, 180), 0)
                .splineToSplineHeading(CM.pose2d(-140, -30, 180), 0)*/
                .build();

        drive.followTrajectorySequence(seq1);

        requestOpModeStop();
    }

    @Override
    public void onStop() {

    }

    @Override
    public void onLoop() {

    }

    public float toInch(float cm) {
        return cm * 0.393701f;
    }

    public float toCm(float inch) {
        return inch * 2.54f;
    }

    public float toRad(float deg) {
        return (float) Math.toRadians(deg);
    }
}
