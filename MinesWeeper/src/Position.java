public class Position {

    private int value;

    private String imageEmoji;

    public Position(int value, String imageEmoji) {
        this.value = value;
        this.imageEmoji = imageEmoji;
    }

    public int getValue() {
        return value;
    }

    public String getImageEmoji() {
        return imageEmoji;
    }

    public void update(int value, String imageEmoji){
        this.value = value;
        this.imageEmoji=imageEmoji;
    }
}
