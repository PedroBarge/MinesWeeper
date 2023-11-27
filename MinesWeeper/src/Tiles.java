enum Tiles {
    TILE_BOMB_EXPLODE(-3, "\t🟥"),
    TILE_BOMB(-2, "\t💣"),
    TILE_PLAYER_ATACK(-1, "\t🟧"),
    DEFAULT(0, "\t🟩"),
    //——————————————————————————————————————————
    TILE_ONE(1, "\t1️⃣"),
    TILE_TWO(2, "\t2️⃣"),
    TILE_TREE(3, "\t3️⃣"),
    TILE_FOUR(4, "\t4️⃣"),
    TILE_FIVE(5, "\t5️⃣"),
    TILE_SIX(6, "\t6️⃣"),
    TILE_SEVEN(7, "\t7️⃣"),
    TILE_EIGHT(8, "\t8️⃣");


    private final int index;
    private final String tileImage;

    Tiles(int index, String tileImage) {
        this.index = index;
        this.tileImage = tileImage;
    }

    public int getIndex() {
        return index;
    }

    public String getTileImage() {
        return tileImage;
    }

}
