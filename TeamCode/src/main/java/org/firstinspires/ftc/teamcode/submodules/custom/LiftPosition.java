package org.firstinspires.ftc.teamcode.submodules.custom;

public enum LiftPosition {

    /**
     * The position of the lift when it needs to score at a ground junction
     */
    GROUND_JUNCTION(-1),

    /**
     * The position of the lift when it needs to pick up a cone
     */
    GROUND_PICKUP(0),

    /**
     * The position of the lift when it needs to score at a first level junction
     */
    LOW(13000),

    /**
     * The position of the lift when it needs to score at a second level junction
     */
    MID(39000),

    /**
     * The position of the lift when it needs to score at a third level junction
     */
    HIGH(62500),


    OP_HIGH(62500),

    /**
     * The position of the lift when it needs to pick up a cone from the first level
     */
    AUTONOMOUS_FIRST(0),

    /**
     * The position of the lift when it needs to pick up a cone from the second level
     */
    AUTONOMOUS_SECOND(2500),

    /**
     * The position of the lift when it needs to pick up a cone from the third level
     */
    AUTONOMOUS_THIRD(9000),

    /**
     * The position of the lift when it needs to pick up a cone from the fourth level
     */
    AUTONOMOUS_FOURTH(12000),

    /**
     * The position of the lift when it needs to pick up a cone from the fifth level
     */
    AUTONOMOUS_FIFTH(15000),

    /**
     * The position of the lift when it is completely down
     */
    MIN(0),

    /**
     * The position of the lift when it is completely up
     */
    MAX(2310),

    AUTONOMOUS_TAKE(30000);

    /**
     * A zero position for the lift
     */




    final Integer height;

    LiftPosition(Integer height) {
        this.height = height;
    }

    /**
     * Gets the encoder ticks for a specific position
     * @return the encoder ticks for a specific position
     */
    public Integer getHeight() {
        return height;
    }
}
