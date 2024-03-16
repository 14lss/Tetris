package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;

public class Colors {
    public static final Color darkGray = new Color(26f / 255, 31f / 255, 40f / 255, 1);
    public static final Color green = new Color(47f / 255, 230f / 255, 23f / 255, 1);
    public static final Color red = new Color(232f / 255, 18f / 255, 18f / 255, 1);
    public static final Color orange = new Color(226f / 255, 116f / 255, 17f / 255, 1);
    public static final Color yellow = new Color(237f / 255, 234f / 255, 4f / 255, 1);
    public static final Color purple = new Color(166f / 255, 0, 247f / 255, 1);
    public static final Color cyan = new Color(21f / 255, 204f / 255, 209f / 255, 1);
    public static final Color blue = new Color(13f / 255, 64f / 255, 216f / 255, 1);
    public static final Color lightblue = new Color(59f / 255, 85f / 255, 162f/ 255, 1);
    public static final Color darkblue = new Color(44f / 255,  44f / 255,  127f / 255, 1);


    public static Color[] getColors() {
        return new Color[] {darkGray, green, red, orange, yellow, purple, cyan, blue};
    }
}
