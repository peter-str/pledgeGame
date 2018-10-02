package com.mygdx.model.maze_algorithm;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import static com.mygdx.game.ResourcePaths.MAZE;

public class LabyrinthErstellen2 {

    private int[][] labyrinth;
    private Cell[][] cells;
    private int höhe;
    private int breite;
    private int totalCells;
    private int visitedCells = 1;
    private StringBuilder sb;
    private final String mapFileEnding = "</data>\n" +
            " </layer>\n" +
            "</map>";

    public LabyrinthErstellen2(int höhe, int breite) {
        this.höhe = höhe;
        this.breite = breite;
        labyrinth = new int[(höhe*2)+1][(breite*2)+1];

        for (int i = 0; i < labyrinth.length; i++) {
            for (int j = 0; j < labyrinth.length; j++) {
                labyrinth[i][j] = 1;
            }

        }

        labyrinth[0][labyrinth.length-2] = 2;
        labyrinth[1][labyrinth.length-2] = 2;
        cells = new Cell[höhe][breite];
        totalCells = höhe*breite;
        initializeCells();
        algorithm();
        createLabyrinth();
        //printLabyrinth();
        sb = new StringBuilder();
        sb.append(getMapFileBeginning(Integer.toString((höhe*2)+1), Integer.toString((breite*2)+1)));
        ausgeben();
        sb.append(mapFileEnding);
        createMapFile(sb.toString());
    }

    public void initializeCells() {
        for(int i = 0; i < höhe; i++) {
            for (int j = 0; j < breite; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
    }

    public void algorithm() {
        Random rand = new Random();
        int x = rand.nextInt(höhe);
        int y = rand.nextInt(breite);

        Stack<Cell> cellstack = new Stack<>();
        Cell currentCell = cells[x][y];

        ArrayList<Vertex> neighborCellList = new ArrayList<>();
        Vertex tmpV;

        while(visitedCells < totalCells) {
            neighborCellList.clear();

            tmpV = new Vertex();
            if(y-1 >= 0 && cells[x][y-1].checkWalls()) {
                tmpV.x1 = x;
                tmpV.y1 = y;
                tmpV.x2 = x;
                tmpV.y2 = y-1;
                tmpV.wall1 = 0;
                tmpV.wall2 = 2;
                neighborCellList.add(tmpV);
            }

            tmpV = new Vertex();
            if(y+1 < breite && cells[x][y+1].checkWalls()) {
                tmpV.x1 = x;
                tmpV.y1 = y;
                tmpV.x2 = x;
                tmpV.y2 = y+1;
                tmpV.wall1 = 2;
                tmpV.wall2 = 0;
                neighborCellList.add(tmpV);
            }

            tmpV = new Vertex();
            if(x-1 >= 0 && cells[x-1][y].checkWalls()) {
                tmpV.x1 = x;
                tmpV.y1 = y;
                tmpV.x2 = x-1;
                tmpV.y2 = y;
                tmpV.wall1 = 3;
                tmpV.wall2 = 1;
                neighborCellList.add(tmpV);
            }

            tmpV = new Vertex();
            if(x+1 < höhe && cells[x+1][y].checkWalls()) {
                tmpV.x1 = x;
                tmpV.y1 = y;
                tmpV.x2 = x+1;
                tmpV.y2 = y;
                tmpV.wall1 = 1;
                tmpV.wall2 = 3;
                neighborCellList.add(tmpV);
            }

            if(neighborCellList.size() >= 1) {
                int r1 = rand.nextInt(neighborCellList.size());
                tmpV = neighborCellList.get(r1);

                cells[tmpV.x1][tmpV.y1].walls[tmpV.wall1] = 0;
                cells[tmpV.x2][tmpV.y2].walls[tmpV.wall2] = 0;

                cellstack.push(currentCell);
                currentCell = cells[tmpV.x2][tmpV.y2];

                x = currentCell.x;
                y = currentCell.y;

                visitedCells++;
            } else {
                currentCell = cellstack.pop();
                x = currentCell.x;
                y = currentCell.y;
            }

        }
    }

    //Fügt dem StringBuilder die Einträge des labyrinth-Arrays hinzu
    public void ausgeben() {
        for(int i = 0; i < labyrinth.length; i++) {
            for(int j = 0; j < labyrinth[i].length; j++) {
                sb.append(labyrinth[i][j]);
                sb.append(",");
            }
            sb.append("\n");
        }
    }

    //Erstellt aus dem cells-Array ein labyrinth-Array
    //Mauern sind im cells-Array kein eigener Array-Eintrag, sondern sind in Cell abgespeichert
    public void createLabyrinth() {
        int a = 0;
        int b = 0;
        for (int i = 0; i < labyrinth.length; i++) {
            for (int j = 0; j < labyrinth[i].length; j++) {
                if(i %2 == 1 && j %2 == 1) {
                    labyrinth[i][j] = 0;

                    if(cells[a][b].walls[0] == 0) {
                        labyrinth[i][j-1] = 0;
                    }

                    if(cells[a][b].walls[1] == 0) {
                        labyrinth[i+1][j] = 0;
                    }

                    if(cells[a][b].walls[2] == 0) {
                        labyrinth[i][j+1] = 0;
                    }

                    if(cells[a][b].walls[3] == 0) {
                        labyrinth[i-1][j] = 0;
                    }

                    b++;
                    if(b == cells[0].length) {
                        a++;
                        b = 0;
                    }
                }
            }
        }
    }

    public void printLabyrinth() {
        for(int i = 0; i < labyrinth.length; i++) {
            for(int j = 0; j < labyrinth.length; j++) {
                System.out.print(labyrinth[i][j] + ",");
            }
            System.out.println();
        }
    }

    public void createMapFile(String content) {
        File file = new File(MAZE);

        try (FileOutputStream fop = new FileOutputStream(file)) {

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
                " <tileset firstgid=\"1\" name=\"rocky01\" tilewidth=\"32\" tileheight=\"32\" tilecount=\"1\" columns=\"1\">\n" +
                "  <image source=\"wall_1.png\" width=\"32\" height=\"32\"/>\n" +
                "  <tile id=\"0\">\n" +
                "   <properties>\n" +
                "    <property name=\"Wand\" value=\"\"/>\n" +
                "   </properties>\n" +
                "  </tile>\n" +
                " </tileset>\n" +
                " <tileset firstgid=\"0\" name=\"BodenStadt\" tilewidth=\"32\" tileheight=\"32\" tilecount=\"1\" columns=\"1\">\n" +
                "  <image source=\"floor_city.png\" width=\"32\" height=\"32\"/>\n" +
                " </tileset>\n" +
                " <tileset firstgid=\"2\" name=\"FinishFlag\" tilewidth=\"32\" tileheight=\"32\" tilecount=\"1\" columns=\"1\">\n" +
                "   <image source=\"finish_flag.png\" width=\"32\" height=\"32\"/>\n" +
                "   <tile id=\"0\">\n" +
                "    <properties>\n" +
                "     <property name=\"Ziel\" value=\"\"/>\n" +
                "    </properties>\n" +
                "   </tile>\n" +
                "  </tileset>\n" +
                " <layer id=\"32\" name=\"Kachelebene 1\" width=\"" + x + "\" height=\"" + y + "\">\n" +
                "  <data encoding=\"csv\">";
    }

    public static void main(String[] args) {
        new LabyrinthErstellen2(10, 10);
    }

}
