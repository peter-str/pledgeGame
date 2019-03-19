package com.mygdx.game;

public class TutorialTexts {

    private TutorialTexts() {}

    private static final String BREAK = "\n";

    //Algorithmus Fenster
    public static final String ALGO_WINDOW_HEADLINE = "Aktueller Algorithmus - Wiederhole fortlaufend: ";

    //TUTORIAL LEVEL 1
    private static final String L1_1 = "Du erarbeitest nun einen Algorithmus, mit dem du aus jedem \n" +
            "dunklen Labyrinth entkommen kannst! ";
    private static final String L1_2 = "Alles was du dafür später brauchst, ist deine Berührungsanzeige ";
    private static final String L1_3 = "und deinen Kompass, der deine Drehungen zählt. ";
    private static final String L1_4 = "Fürs Erste läufst du einfach nur geradeaus, benutze den \"Play\"- oder \"1 Schritt\"-Button. ";
    public static final String LEVEL1 = L1_1 + BREAK + L1_2 + BREAK + L1_3 + BREAK + BREAK + L1_4;

    //TUTORIAL LEVEL 2
    private static final String L2_1 = "Dieser Algorithmus kommt offensichtlich schnell an seine Grenzen, ";
    private static final String L2_2 = "sobald eine Abzweigung kommt. \nWas ist zu tun? ";
    private static final String L2_3 = "Sobald eine Mauer vor mir ist, drehe ich mich nach ...? ";
    public static final String LEVEL2 = L2_1 + BREAK + L2_2 + BREAK + BREAK + L2_3 + BREAK;

    //TUTORIAL LEVEL 3
    private static final String L3_1 = "Der aktuelle Algorithmus lässt uns nur hin und her laufen... ";
    private static final String L3_2 = "Welche Regel musst du hinzufügen, um ins Ziel zu gelangen? ";
    public static final String LEVEL3 = L3_1 + BREAK + BREAK + L3_2 + BREAK;

    //TUTORIAL LEVEL 4
    private static final String L4_1 = "Mit dem aktuellen Algorithmus läufst du im Kreis. ";
    private static final String L4_2 = "";
    private static final String L4_3 = "Was würdest du verändern? ";
    public static final String LEVEL4 = L4_1 + BREAK + BREAK + L4_3 + BREAK;

    //TUTORIAL LEVEL 5
    private static final String L5_1 = "Erneut läufst du nur im Kreis... ";
    private static final String L5_2 = "Du musst noch eine Sache verändern, dann hast du es geschafft! ";
    private static final String L5_3 = "Wie oft solltest du dich nach links drehen, um ins Ziel zu kommen? ";
    public static final String LEVEL5 = L5_1 + BREAK + L5_2 + BREAK + BREAK + L5_3 + BREAK;

    //TUTORIAL LEVEL 6
    private static final String L6_1 = "Der Algorithmus ist nun komplett. ";
    private static final String L6_2 = "Hier kannst du ihn nun selbst mal anwenden. ";
    private static final String L6_3 = "Folge den rot markierten Anweisungen im unteren Fenster. \nBenutze die Pfeiltasten. ";
    public static final String LEVEL6 = L6_1 + BREAK + L6_2 + BREAK + L6_3;

}
