package com.mygdx.game;

public class ResourcePaths {

    private ResourcePaths() {}

    private static boolean createJarFile = false;

    //PledgeGame Class (package game)
    public static final String UIPATH = (createJarFile) ? "skins/uiskin.json" : "core/assets/skins/uiskin.json";
    public static final String NEUTRAL = (createJarFile) ? "collision/neutral2.png" : "core/assets/collision/neutral2.png";
    public static final String WALL = (createJarFile) ? "collision/wall.png" : "core/assets/collision/wall.png";

    //GameScreen Class
    public static final String COMPASS = (createJarFile) ? "maps/textures/compass.png" : "core/assets/maps/textures/compass.png";

    //maps package
    public static final String TUTMAP1 = (createJarFile) ? "maps/TutorialMap1.tmx" : "core/assets/maps/TutorialMap1.tmx";
    public static final String TUTMAP2 = (createJarFile) ? "maps/TutorialMap2.tmx" : "core/assets/maps/TutorialMap2.tmx";
    public static final String TUTMAP3 = (createJarFile) ? "maps/TutorialMap3.tmx" : "core/assets/maps/TutorialMap3.tmx";
    public static final String TUTMAP4 = (createJarFile) ? "maps/TutorialMap4.tmx" : "core/assets/maps/TutorialMap4.tmx";
    public static final String TUTMAP5 = (createJarFile) ? "maps/TutorialMap5.tmx" : "core/assets/maps/TutorialMap5.tmx";
    public static final String TUTMAP6 = (createJarFile) ? "maps/TutorialMap6.tmx" : "core/assets/maps/TutorialMap6.tmx";

    public static final String INTRODUCTIONMAP1 = (createJarFile) ? "maps/Introduction1.tmx" : "core/assets/maps/Introduction1.tmx";
    public static final String INTRODUCTIONMAP2 = (createJarFile) ? "maps/Introduction2.tmx" : "core/assets/maps/Introduction2.tmx";
    public static final String INTRODUCTIONMAP3 = (createJarFile) ? "maps/Introduction3.tmx" : "core/assets/maps/Introduction3.tmx";

    //maze algorithm outpout
    public static final String MAZE = (createJarFile) ? "randomMap.tmx" : "core/assets/maps/randomMap.tmx";

    //Player class
    public static final String PLAYER_TEXTURE = (createJarFile) ? "player/texture.atlas" : "core/assets/player/texture.atlas";

    //Difficulty Textures (difficulties package)
    public static final String EASY = (createJarFile) ? "fov_high_50.png" : "core/assets/fov_high_50.png";
    public static final String MEDIUM = (createJarFile) ? "fov_medium_75.png" : "core/assets/fov_medium_75.png";
    public static final String HIGH = (createJarFile) ? "fov_low.png" : "core/assets/fov_low.png";
}
