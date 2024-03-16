package com.mygdx.game.blocks;

import com.mygdx.game.Block;
import com.mygdx.game.Position;

public class OBlock extends Block {
    public OBlock() {
        id = 4;
        cells = new Position[4][4];
        cells[0] = new Position[]{new Position(0, 0), new Position(0, 1), new Position(1, 0), new Position(1, 1)};
        move(0, 4);
    }
}
