package com.mygdx.model.maze_algorithm;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class LabyrinthErstellen {

    private int höhe;
    private int breite;
    private int x = 0;
    private int y = 0;
    private int[][] waagerecht;
    private StringBuilder sb;
    private final String mapFileEnding = "</data>\n" +
            " </layer>\n" +
            "</map>";

    public LabyrinthErstellen(int höhe, int breite) {
        this.höhe = höhe;
        this.breite = breite;
        this.waagerecht = new int[höhe][breite];
        sb = new StringBuilder();
        sb.append(getMapFileBeginning(Integer.toString(höhe), Integer.toString(breite)));
        getRandomX();
        getRandomY();
        waagerecht[x][y] = 1;
        fuellen();
        pruefen();
        ausgeben();
        sb.append(mapFileEnding);
        //System.out.println(sb.toString());
        createMapFile(sb.toString());
    }

    public void fuellen() {
        for(int i = 1; i < höhe-1; i++) {
            for(int j = 1; j < breite-1; j++) {
                double z = Math.random();
                if(z > 0.5) {
                    waagerecht[i][j] = 1;
                }
            }
        }
    }

    public void pruefen() {
        for(int i = 2; i < höhe-2; i++) {
            for(int j = 2; j < breite-2; j++) {
                int counter = 0;
                if(waagerecht[i-1][j] == 0)
                    counter++;
                if(waagerecht[i][j+1] == 0)
                    counter++;
                if(waagerecht[i+1][j] == 0)
                    counter++;
                if(waagerecht[i][j-1] == 0)
                    counter++;
                if(counter == 4) {
                    int random4 = (int) (Math.random() *4) + 1;
                    if(random4 == 1) {
                        waagerecht[i-1][j] = 1;
                    } else if(random4 == 2) {
                        waagerecht[i][j+1] = 1;
                    } else if(random4 == 3) {
                        waagerecht[i+1][j] = 1;
                    } else if(random4 == 4) {
                        waagerecht[i][j-1] = 1;
                    }

                    random4 = 5 - random4;
                    if(random4 == 1) {
                        waagerecht[i-1][j] = 1;
                    } else if(random4 == 2) {
                        waagerecht[i][j+1] = 1;
                    } else if(random4 == 3) {
                        waagerecht[i+1][j] = 1;
                    } else if(random4 == 4) {
                        waagerecht[i][j-1] = 1;
                    }
                }

                if(counter == 3) {
                    int random4 = (int) (Math.random() *4) + 1;
                    if(random4 == 1) {
                        waagerecht[i-1][j] = 1;
                    } else if(random4 == 2) {
                        waagerecht[i][j+1] = 1;
                    } else if(random4 == 3) {
                        waagerecht[i+1][j] = 1;
                    } else if(random4 == 4) {
                        waagerecht[i][j-1] = 1;
                    }
                }
            }
        }
    }

    public void getRandomX() {
        x = (int) (Math.random() * höhe-2)+1;
    }

    public void getRandomY() {
        y = (int) (Math.random() * breite-2)+1;
    }

    public int getX() {
        return y;
    }

    public int getY() {
        return x;
    }

    public void ausgeben() {
        for(int i = 0; i < waagerecht.length; i++) {
            for(int j = 0; j < waagerecht[i].length; j++) {
                sb.append(waagerecht[i][j]);
                sb.append(",");
            }
            sb.append("\n");
        }
    }

    public void createMapFile(String con) {
        File file = new File("core\\assets\\maps\\newfile.tmx");
        String content = con;

        try (FileOutputStream fop = new FileOutputStream(file)) {

            // if file doesn't exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            // get the content in bytes
            byte[] contentInBytes = content.getBytes();

            fop.write(contentInBytes);
            fop.flush();
            fop.close();

            System.out.println("Done");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getMapFileBeginning(String x, String y) {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<map version=\"1.0\" tiledversion=\"2018.07.13\" orientation=\"orthogonal\" renderorder=\"right-down\" width=\"10\" height=\"10\" tilewidth=\"32\" tileheight=\"32\" infinite=\"0\" nextlayerid=\"33\" nextobjectid=\"1\">\n" +
                " <tileset firstgid=\"0\" name=\"rocky01\" tilewidth=\"32\" tileheight=\"32\" tilecount=\"1\" columns=\"1\">\n" +
                "  <image source=\"textures/wall_1.png\" width=\"32\" height=\"32\"/>\n" +
                "  <tile id=\"0\">\n" +
                "   <properties>\n" +
                "    <property name=\"Wand\" value=\"\"/>\n" +
                "   </properties>\n" +
                "  </tile>\n" +
                " </tileset>\n" +
                " <tileset firstgid=\"1\" name=\"BodenStadt\" tilewidth=\"32\" tileheight=\"32\" tilecount=\"1\" columns=\"1\">\n" +
                "  <image source=\"textures/floor_city.png\" width=\"32\" height=\"32\"/>\n" +
                " </tileset>\n" +
                " <layer id=\"32\" name=\"Kachelebene 1\" width=\"" + x + "\" height=\"" + y + "\">\n" +
                "  <data encoding=\"csv\">\n";
    }

    public static void main(String[] args) {
        LabyrinthErstellen a = new LabyrinthErstellen(20, 20);
    }
}
