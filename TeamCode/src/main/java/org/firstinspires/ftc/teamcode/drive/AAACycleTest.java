package org.firstinspires.ftc.teamcode.drive;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.BaseOpMode;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

/*
 * This is an example of a more complex path to really test the tuning.
 */
@Autonomous(group = " Bjorn drive")
public class AAACycleTest extends BaseOpMode {

    private SampleMecanumDrive drive;

    public AAACycleTest() {
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
        double x = .35;
        double y = 0;
        Pose2d startPose = new Pose2d(-65, -12, Math.toRadians(0));
        drive.setPoseEstimate(startPose);

        Trajectory traj1 = drive.trajectoryBuilder(new Pose2d(-65,-12, Math.toRadians(0)))
                .splineTo(new Vector2d(-29.5,-3), Math.toRadians(45))
                .build();

        Trajectory traj2 = drive.trajectoryBuilder(traj1.end(),true)
                .splineTo(new Vector2d(-66,-11), Math.toRadians(180))
                .build();








        drive.followTrajectory(traj1);
        drive.followTrajectory(traj2);

        drive.setPoseEstimate(startPose);

        drive.followTrajectory(traj1);
        drive.followTrajectory(traj2);

        drive.setPoseEstimate(startPose);

        drive.followTrajectory(traj1);
        drive.followTrajectory(traj2);


















        /*TrajectorySequence seq1 = drive.trajectorySequenceBuilder(startPose)
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
                .setReversed(true)
               .splineToSplineHeading(CM.pose2d(-100, -30, 180), 0)
                .splineToSplineHeading(CM.pose2d(-110, -30, 180), 0)
                .splineToSplineHeading(CM.pose2d(-140, -30, 180), 0)
                .build();

        drive.followTrajectorySequence(seq1);

        requestOpModeStop();*/
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
