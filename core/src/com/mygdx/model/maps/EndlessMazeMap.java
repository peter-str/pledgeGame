package com.mygdx.model.maps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.mygdx.enums.MapEnum;
import com.mygdx.game.PledgeGame;
import com.mygdx.model.difficulties.Difficulty;
import com.mygdx.screens.MainMenuScreen;

import static com.mygdx.game.ResourcePaths.MAZE;

public class EndlessMazeMap extends AbstractMap {

    public EndlessMazeMap(final PledgeGame game, MapEnum nextMap) {
        super(game, nextMap);
        tiledMap = new TmxMapLoader().load(MAZE);
    }

    @Override
    public void showWinningMessage() {
        if (finished) {
            finished = false;
            Gdx.input.setInputProcessor(gameScreenObserver.getStage());
            dialog = new Dialog("Ziel erreicht", game.uiSkin, "dialog") {
                public void result(Object obj) {
                    if (obj.equals("menu")) {
                        game.setScreen(new MainMenuScreen(game, false));
                        dispose();
                    }
                }
            };
            dialog.text("Super, du hast den Ausgang aus dem Labyrinth gefunden! \n");
            dialog.button("Hauptmenue", "menu");
            dialog.show(gameScreenObserver.getStage());
        }
    }

    @Override
    public int getStartX() {
        return 32;
    }

    @Override
    public int getStartY() {
        return 32;
    }

    @Override
    public boolean getTutorialFlagOfNextLevel() {
        return false;
    }

    @Override
    public MapEnum getNextMap() {
        return nextMap;
    }

    @Override
    public void triggerGoalAchievedMethod() {
        super.goalAchieved(nextMap, true);
    }

    @Override
    public Difficulty getDifficulty() {
        return null;
    }
}
