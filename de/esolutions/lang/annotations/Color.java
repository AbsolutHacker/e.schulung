package de.esolutions.lang.annotations;

public enum Color implements Transmogrifier {


    GREEN(0, 255, 0),
    RED(255, 0, 0),
    BLUE(0, 0, 255) {
        @Override
        public void transmogrify() {
            System.out.println("Nicht immer das, was Du denkst!");
        }
    };

    private final int red;
    private final int green;
    private final int blue;

    private Color(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public java.awt.Color color() {
        return new java.awt.Color(red, green, blue);
    }

    @Override
    public String toString() {
        return super.toString() + "@"  + color();
    }

       @Override
    public void transmogrify() {
        System.out.println("A-ha!");
    }

}
