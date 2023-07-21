package btd.utils;

public enum ImagePath {
    RED_BLOON("bloonsSprites/redBloon.png");

    private String imagePath;

    private ImagePath(String imagePathpath) {
        this.imagePath = imagePathpath;
    }

    public String getImagePath() {
        return imagePath;
    }
}
