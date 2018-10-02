package com.mygdx.game;

public class TutorialTexts {

    private static final String BREAK = "\n";

    //Algorithmus Fenster
    public static final String ALGO_WINDOW_HEADLINE = "Aktueller Algorithmus - Wiederhole fortlaufend: ";

    //TUTORIAL LEVEL 1
    private static final String L1_1 = "Am Anfang laufen wir einfach ein Feld nach vorne.";
    private static final String L1_2 = "Das wiederholen wir fortlaufend, bis eine Mauer uns den Weg versperrt.";
    public static final String LEVEL1 = L1_1 + BREAK + L1_2;

    //TUTORIAL LEVEL 2
    private static final String L2_1 = "Dieser Algorithmus kommt offensichtlicherweise schnell an seine Grenzen, ";
    private static final String L2_2 = "sobald eine Abzweigung kommt. Was musst du tun, um ins Ziel zu kommen? ";
    private static final String L2_3 = "Sobald eine Mauer vor mir ist, drehe ich mich nach ...? ";
    public static final String LEVEL2 = L2_1 + BREAK + L2_2 + BREAK + BREAK + L2_3;

    //TUTORIAL LEVEL 3
    private static final String L3_1 = "Benutzt du hier den aktuellen Algorithmus, wirst du feststellen, dass du ";
    private static final String L3_2 = "lediglich den mittleren Gang auf und ab gehst, aber nicht ins Ziel gelangst. ";
    private static final String L3_3 = "Welche Regel musst du hinzufuegen, um ins Ziel zu gelangen? ";
    public static final String LEVEL3 = L3_1 + BREAK + L3_2 + BREAK + BREAK + L3_3;

    //TUTORIAL LEVEL 4
    private static final String L4_1 = "Der aktuelle Algorithmus laesst dich hier im Kreis um die Saeule laufen. ";
    private static final String L4_2 = "Das Ziel soll nun sein, den ersten Gang vor der Saeule zu nehmen. ";
    private static final String L4_3 = "Wie musst du den bestehenden Algorithmus anpassen? ";
    public static final String LEVEL4 = L4_1 + BREAK + L4_2 + BREAK + BREAK + L4_3;

    //TUTORIAL LEVEL 5
    private static final String L5_1 = "Fuehrst du den Algorithmus nun aus, wirst du merken, dass er dich wieder ";
    private static final String L5_2 = "im Kreis fuehrt. Du musst also noch eine letzte Anpassung durchfuehren, ";
    private static final String L5_3 = "damit der Algorithmus allgemeingueltig ist. ";
    private static final String L5_4 = "Wie oft solltest du dich nach links drehen, um ins Ziel zu kommen? ";
    public static final String LEVEL5 = L5_1 + BREAK + L5_2 + BREAK + L5_3 + BREAK + BREAK + L5_4;

}
