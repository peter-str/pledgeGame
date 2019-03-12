package com.mygdx.model.maze_algorithm;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import static com.mygdx.game.ResourcePaths.FINISH_TEX;
import static com.mygdx.game.ResourcePaths.FLOOR_TEX;
import static com.mygdx.game.ResourcePaths.WALL_TEX;

public class MazeCreatorClass {

    private int[][] labyrinth;
    private Cell[][] cells;
    private int height;
    private int width;
    private int totalCells;
    private int visitedCells = 1;
    private Texture wall;
    private Texture floor;
    private Texture finish;

    public MazeCreatorClass(int height, int width) {
        this.height = height;
        this.width = width;
        wall = new Texture(Gdx.files.internal(WALL_TEX));
        floor = new Texture(Gdx.files.internal(FLOOR_TEX));
        finish = new Texture(Gdx.files.internal(FINISH_TEX));

        labyrinth = new int[(height * 2) + 1][(width * 2) + 1];

        for (int i = 0; i < labyrinth.length; i++) {
            for (int j = 0; j < labyrinth[i].length; j++) {
                labyrinth[i][j] = 1;
            }

        }

        labyrinth[0][labyrinth.length - 2] = 2;
        cells = new Cell[height][width];
        totalCells = height * width;

        initializeCells();
        algorithm();
        createLabyrinth();
        //printLabyrinth();
    }

    public TiledMap getTiledMap() {
        TiledMap map = new TiledMap();
        MapLayers layers = map.getLayers();
        TextureRegion wallRegion = new TextureRegion();
        wallRegion.setRegion(wall);
        TextureRegion floorRegion = new TextureRegion();
        floorRegion.setRegion(floor);
        TextureRegion finishRegion = new TextureRegion();
        finishRegion.setRegion(finish);

        TiledMapTileLayer layer1 = new TiledMapTileLayer((height * 2) + 1, (width * 2) + 1, 32, 32);

        TiledMapTileLayer.Cell wallCell = new TiledMapTileLayer.Cell();
        wallCell.setTile(new StaticTiledMapTile(wallRegion));
        wallCell.getTile().getProperties().put("Wand", null);

        TiledMapTileLayer.Cell floorCell = new TiledMapTileLayer.Cell();
        floorCell.setTile(new StaticTiledMapTile(floorRegion));

        TiledMapTileLayer.Cell finishCell = new TiledMapTileLayer.Cell();
        finishCell.setTile(new StaticTiledMapTile(finishRegion));
        finishCell.getTile().getProperties().put("Ziel", null);

        for (int i = 0; i < labyrinth.length; i++) {
            for (int j = 0; j < labyrinth[i].length; j++) {
                //System.out.print(labyrinth[i][j]);
                if (labyrinth[i][j] == 1) {
                    layer1.setCell(j, Math.abs(i-(labyrinth.length-1)), wallCell);
                }
                if (labyrinth[i][j] == 0) {
                    layer1.setCell(j, Math.abs(i-(labyrinth.length-1)), floorCell);
                }

                if (labyrinth[i][j] == 2) {
                    layer1.setCell(j, Math.abs(i-(labyrinth.length-1)), finishCell);
                }
            }
            //System.out.println();
        }

        layers.add(layer1);

        return map;
    }

    private void initializeCells() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
    }

    private void algorithm() {
        Random rand = new Random();
        int x = rand.nextInt(height);
        int y = rand.nextInt(width);

        Stack<Cell> cellstack = new Stack<>();
        Cell currentCell = cells[x][y];

        ArrayList<Vertex> neighborCellList = new ArrayList<>();
        Vertex tmpV;

        while (visitedCells < totalCells) {
            neighborCellList.clear();

            tmpV = new Vertex();
            if (y - 1 >= 0 && cells[x][y - 1].checkWalls()) {
                tmpV.x1 = x;
                tmpV.y1 = y;
                tmpV.x2 = x;
                tmpV.y2 = y - 1;
                tmpV.wall1 = 0;
                tmpV.wall2 = 2;
                neighborCellList.add(tmpV);
            }

            tmpV = new Vertex();
            if (y + 1 < width && cells[x][y + 1].checkWalls()) {
                tmpV.x1 = x;
                tmpV.y1 = y;
                tmpV.x2 = x;
                tmpV.y2 = y + 1;
                tmpV.wall1 = 2;
                tmpV.wall2 = 0;
                neighborCellList.add(tmpV);
            }

            tmpV = new Vertex();
            if (x - 1 >= 0 && cells[x - 1][y].checkWalls()) {
                tmpV.x1 = x;
                tmpV.y1 = y;
                tmpV.x2 = x - 1;
                tmpV.y2 = y;
                tmpV.wall1 = 3;
                tmpV.wall2 = 1;
                neighborCellList.add(tmpV);
            }

            tmpV = new Vertex();
            if (x + 1 < height && cells[x + 1][y].checkWalls()) {
                tmpV.x1 = x;
                tmpV.y1 = y;
                tmpV.x2 = x + 1;
                tmpV.y2 = y;
                tmpV.wall1 = 1;
                tmpV.wall2 = 3;
                neighborCellList.add(tmpV);
            }

            if (neighborCellList.size() >= 1) {
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

    //Erstellt aus dem cells-Array ein labyrinth-Array
    //Mauern sind im cells-Array kein eigener Array-Eintrag, sondern sind in Cell abgespeichert
    private void createLabyrinth() {
        int a = 0;
        int b = 0;
        for (int i = 0; i < labyrinth.length; i++) {
            for (int j = 0; j < labyrinth[i].length; j++) {
                if (i % 2 == 1 && j % 2 == 1) {
                    labyrinth[i][j] = 0;

                    if (cells[a][b].walls[0] == 0) {
                        labyrinth[i][j - 1] = 0;
                    }

                    if (cells[a][b].walls[1] == 0) {
                        labyrinth[i + 1][j] = 0;
                    }

                    if (cells[a][b].walls[2] == 0) {
                        labyrinth[i][j + 1] = 0;
                    }

                    if (cells[a][b].walls[3] == 0) {
                        labyrinth[i - 1][j] = 0;
                    }

                    b++;
                    if (b == cells[0].length) {
                        a++;
                        b = 0;
                    }
                }
            }
        }
    }

    private void printLabyrinth() {
        for (int i = 0; i < labyrinth.length; i++) {
            for (int j = 0; j < labyrinth[i].length; j++) {
                System.out.print(labyrinth[i][j] + ",");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        new MazeCreatorClass(6, 6);
    }

}
