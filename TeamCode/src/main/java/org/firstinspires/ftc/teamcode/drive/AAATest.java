package org.firstinspires.ftc.teamcode.drive;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
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
        Pose2d startPose = new Pose2d(-35, -63, Math.toRadians(90));
        drive.setPoseEstimate(startPose);

        Trajectory traj1 = drive.trajectoryBuilder(startPose)
                .lineTo(new Vector2d(-35,-3))
                .build();

        Trajectory traj2 = drive.trajectoryBuilder(traj1.end())
                .lineToLinearHeading(new Pose2d(-35, -12, Math.toRadians(45)))
                .build();

        Trajectory traj3 = drive.trajectoryBuilder(traj2.end())
                .lineToLinearHeading(new Pose2d(-28, -5.5, Math.toRadians(45)))
                .build();

        Trajectory traj4 = drive.trajectoryBuilder(traj3.end(),true)
                .splineTo(new Vector2d(-65,-12),Math.toRadians(180))
                .build();

        Trajectory traj5 = drive.trajectoryBuilder(new Pose2d(-65, -12, Math.toRadians(0)))
                .splineTo(new Vector2d(-29,-3.5),Math.toRadians(45))
                .build();

        Trajectory traj6 = drive.trajectoryBuilder(traj5.end(),true)
                .splineTo(new Vector2d(-66,-11),Math.toRadians(180))
                .build();




        lift.goToPosition(LiftPosition.HIGH);
        drive.followTrajectory(traj1);
        drive.followTrajectory(traj2);
        drive.followTrajectory(traj3);
        sleep(250);
        gripper.fullOuttake(LiftPosition.AUTONOMOUS_FIFTH);
        drive.followTrajectory(traj4);

        drive.setPoseEstimate(new Pose2d(-65, -12, Math.toRadians(0)));
        gripper.fullIntake(LiftPosition.HIGH.getHeight());
        drive.followTrajectory(traj5);
        sleep(250);
        gripper.fullOuttake(LiftPosition.AUTONOMOUS_FOURTH);
        drive.followTrajectory(traj6);

        drive.setPoseEstimate(new Pose2d(-65, -12, Math.toRadians(0)));
        gripper.fullIntake(LiftPosition.HIGH.getHeight());
        drive.followTrajectory(traj5);
        sleep(250);
        gripper.fullOuttake(LiftPosition.AUTONOMOUS_THIRD);
        drive.followTrajectory(traj6);

        drive.setPoseEstimate(new Pose2d(-65, -12, Math.toRadians(0)));
        gripper.fullIntake(LiftPosition.HIGH.getHeight());
        drive.followTrajectory(traj5);
        sleep(250);
        gripper.fullOuttake(LiftPosition.AUTONOMOUS_SECOND);
        drive.followTrajectory(traj6);








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
