package es.danimoreno.cuentabolas;


import android.os.Parcel;
import android.os.Parcelable;

public class Circulo implements Parcelable {
    private int  posX,posY, velocidadX, velocidadY,color;
    private int RADIO=30;

    public Circulo(int posX, int posY, int velocidadX, int velocidadY, int color) {
        this.posX =posX;
        this.posY =posY;
        this.velocidadX = velocidadX;
        this.velocidadY = velocidadY;
        this.color=color;
    }

    protected Circulo(Parcel in) {
        posX = in.readInt();
        posY = in.readInt();
        velocidadX = in.readInt();
        velocidadY = in.readInt();
        color = in.readInt();
        RADIO = in.readInt();
    }

    public static final Creator<Circulo> CREATOR = new Creator<Circulo>() {
        @Override
        public Circulo createFromParcel(Parcel in) {
            return new Circulo(in);
        }

        @Override
        public Circulo[] newArray(int size) {
            return new Circulo[size];
        }
    };

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getVelocidadX() {
        return velocidadX;
    }

    public void setVelocidadX(int velocidadX) {
        this.velocidadX = velocidadX;
    }

    public int getVelocidadY() {
        return velocidadY;
    }

    public void setVelocidadY(int velocidadY) {
        this.velocidadY = velocidadY;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getRADIO() {
        return RADIO;
    }

    public void setRADIO(int RADIO) {
        this.RADIO = RADIO;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        //posX,posY, velocidadX, velocidadY,color
     parcel.writeInt(posX);
     parcel.writeInt(posY);
     parcel.writeInt(velocidadX);
     parcel.writeInt(velocidadY);
     parcel.writeInt(color);
     parcel.writeInt(RADIO);
    }

    @Override
    public String toString() {
        return "Circulo{" +
                "posX=" + posX +
                ", posY=" + posY +
                ", velocidadX=" + velocidadX +
                ", velocidadY=" + velocidadY +
                ", color=" + color +
                ", RADIO=" + RADIO +
                '}';
    }
}



