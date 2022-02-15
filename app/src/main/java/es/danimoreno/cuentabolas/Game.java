package es.danimoreno.cuentabolas;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

public class Game extends View {

    private  Paint text;
    private int nBolas,velocidadX,velocidadY,difficulty,centroX,centroY;
    private int RADIO=60;
    private int textPosition=getHeight();
    private Context context;
    private Random r;
    int[] colores = { Color.RED, Color.GREEN, Color.MAGENTA,Color.BLUE};
    public static ArrayList<Circulo> balls;
    MediaPlayer mediaPlayer;

    public Game(Context context, AttributeSet attrs) {
        super(context, attrs);
        text = new Paint();
        text.setColor(Color.BLACK);
        text.setTextSize(100);
        mediaPlayer=MediaPlayer.create(context,R.raw.old_computer);
        mediaPlayer.start();
    }



    public void creaBolas(){

        r= new Random();
        switch (difficulty){
            case 1:
                nBolas = r.nextInt(5)+3;
                break;
            case 2:
                nBolas=  r.nextInt(6)+4;
                break;
            case 3:
                nBolas =  (r.nextInt(7))+6;
                break;
        }

        int posX,posY;
        balls=new ArrayList<>();
        for (int i=0;i<nBolas;i++){
            switch (difficulty){
                case 1:
                    velocidadX = r.nextInt(5)+10;
                    velocidadY = r.nextInt(5)+10;
                    break;
                case 2:
                    velocidadX = (r.nextInt(5))+15;
                    velocidadY = (r.nextInt(5))+15;
                    break;
                case 3:
                    velocidadX = r.nextInt(5)+20;
                    velocidadY = r.nextInt(5)+20;
                    break;
            }


            posX=(r.nextInt(1000))+RADIO;
            posY=(r.nextInt(1000))+RADIO;
            Circulo circulo=new Circulo(posX,posY,velocidadX,velocidadY,colores[r.nextInt(colores.length)]);
        balls.add(circulo);
        }
    }



    private void texAnimation(Canvas c, int screenHeight) {
        textPosition += 20;
        if (textPosition < screenHeight+50){
            c.drawText(getContext().getString(R.string.ready), centroX-250, textPosition, text);
        }else {
            mediaPlayer.stop();
        }

    }

    private void pintaBolas(Canvas c) {
        for (Circulo ball:balls) {

            ball.setPosX(ball.getPosX()+ball.getVelocidadX());
            ball.setPosY(ball.getPosY()+ ball.getVelocidadY());

            if (ball.getPosX() >= getWidth()) {
                ball.setPosX(getWidth());
                ball.setVelocidadX(-ball.getVelocidadX());
            }

            if (ball.getPosX() <= RADIO) {
                ball.setPosX(RADIO);
                ball.setVelocidadX(-ball.getVelocidadX());
            }

            if (ball.getPosY() >= getHeight()) {
                ball.setPosY(getHeight());
                ball.setVelocidadY(-ball.getVelocidadY());
            }

            if (ball.getPosY() <= RADIO) {
                ball.setPosY(RADIO);
                ball.setVelocidadY(-ball.getVelocidadY());
            }

            Paint p = new Paint();
            p.setColor(ball.getColor());
            c.drawCircle(ball.getPosX(), ball.getPosY(), RADIO, p);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldW, int oldH) {
        centroX = w / 2;
        centroY = h / 2;
    }


    protected void onDraw(Canvas c) {
        final int screenHeight = getHeight();
        if (textPosition< screenHeight+500){
            texAnimation(c, screenHeight);
        } else if (difficulty != 0){
            if (balls == null){
                creaBolas();
                new CountDownTimer(10000, 1) {
                    long time=100;
                    @Override
                    public void onTick(long l) {
                            time = l/1000;
                    }

                    @Override
                    public void onFinish() {
                        mediaPlayer.stop();
                        finishGame();
                    }
                }.start();

                switch (difficulty){
                    case 1:
                        mediaPlayer = MediaPlayer.create(context, R.raw.raining_teddy_bears);
                        break;

                    case 2:
                        mediaPlayer = MediaPlayer.create(context, R.raw.mantis_lords);
                        break;

                    case 3:
                        mediaPlayer = MediaPlayer.create(context, R.raw.megalovania);
                        break;
                }

                mediaPlayer.start();
            }
            pintaBolas(c);

        } else {
            String sorry = "Critical Error";
            text.setTextSize(50);
            c.drawText(sorry, centroX-200, centroY, text);
        }
        postInvalidateDelayed(1);
    }

    private void finishGame(){
        Intent intent = new Intent(context, AnswerActivity.class);
        intent.putExtra("nBalls", balls.size());
        intent.putExtra("difficulty", difficulty);

            int contador=1;
            for (Circulo circulo:balls) {
                intent.putExtra(String.valueOf(contador), (Parcelable) circulo);
            }


        context.startActivity(intent);
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
}
