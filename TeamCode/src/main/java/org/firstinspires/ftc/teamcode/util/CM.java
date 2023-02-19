package org.firstinspires.ftc.teamcode.util;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

import org.apache.commons.math3.geometry.Point;

public class CM {

    public static Vector2d vector2d(float x, float y) {
        return new Vector2d(toInches(x), toInches(y));
    }

    public static Vector2d vector2d(double x, double y) {
        return new Vector2d(toInches((float) x), toInches((float) y));
    }

    public static Vector2d vector2d(int x, int y) {
        return new Vector2d(toInches(x), toInches(y));
    }

    public static Vector2d vector2d(Vector2d vector2d) {
        return new Vector2d(toInches((float) vector2d.getX()), toInches((float) vector2d.getY()));
    }

    public static Pose2d pose2d(float x, float y, float headingDegrees) {
        return new Pose2d(toInches(x), toInches(y), Math.toRadians(headingDegrees));
    }

    public static Pose2d pose2d(double x, double y, double headingDegrees) {
        return new Pose2d(toInches((float) x), toInches((float) y), Math.toRadians(headingDegrees));
    }

    public static Pose2d pose2d(int x, int y, int headingDegrees) {
        return new Pose2d(toInches(x), toInches(y), Math.toRadians(headingDegrees));
    }

    public static Pose2d pose2d(Pose2d pose2d, boolean convertHeading) {
        return new Pose2d(
                toInches((float) pose2d.getX()),
                toInches((float) pose2d.getY()),
                convertHeading ? Math.toRadians(pose2d.getHeading()) : pose2d.getHeading());
    }

    public static Pose2d pose2d(Pose2d pose2d) {
        return pose2d(pose2d, true);
    }

    public static float toCm(float inches) {
        return inches * 2.54f;
    }

    public static float toInches(float cm) {
        return cm / 2.54f;
    }
}
