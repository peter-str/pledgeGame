package com.mygdx.model.maps;

import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.mygdx.model.difficulties.Difficulty;
import com.mygdx.model.difficulties.DifficultyEasy;
import com.mygdx.game.PledgeGame;
import com.mygdx.enums.MapEnum;

public class Map1 extends AbstractMap {
    public Map1(final PledgeGame game, MapEnum nextMap) {
        super(game, nextMap);
        tiledMap = new TmxMapLoader().load("core/assets/maps/map4.tmx");
    }

    @Override
    public int getStartX() {
        return 5*32;//20
    }

    @Override
    public int getStartY() {
        return 32;//14
    }

    @Override
    public boolean getTutorialFlagOfNextLevel() {
        return false;
    }

    @Override
    public Difficulty getDifficulty() {
        return new DifficultyEasy(false);
    }

    @Override
    public MapEnum getNextMap() {
        return nextMap;
    }

    @Override
    public void triggerGoalAchievedMethod() {
        super.goalAchieved(nextMap, false);
    }
}
