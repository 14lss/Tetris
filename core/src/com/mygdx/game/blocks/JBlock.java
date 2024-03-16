package com.mygdx.game.blocks;

import com.mygdx.game.Block;
import com.mygdx.game.Position;

public class JBlock extends Block {
    public JBlock() {
        id = 2;
        cells = new Position[4][4];
        cells[0] = new Position[]{new Position(0, 0), new Position(1, 0), new Position(1, 1), new Position(1, 2)};
        cells[1] = new Position[]{new Position(0, 1), new Position(0, 2), new Position(1, 1), new Position(2, 1)};
        cells[2] = new Position[]{new Position(1, 0), new Position(1, 1), new Position(1, 2), new Position(2, 2)};
        cells[3] = new Position[]{new Position(0, 1), new Position(1, 1), new Position(2, 0), new Position(2, 1)};
        move(0, 3);
    }
}
