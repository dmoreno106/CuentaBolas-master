package es.danimoreno.cuentabolas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class AnswerActivity extends AppCompatActivity {
     int difficulty;
    private boolean resultado;
    private Button btAceptar;
    private EditText etCuantas, etAzul, etRojo, etVerde, etMagenta;
    private Context context;
    private TextView lbAzul, lbRojo, lbVerde, lbMagenta;
    private int nballs;
    int azul = 0, rojo = 0, verde = 0, magenta = 0;

    public static ArrayList<Circulo> balls=new ArrayList<>();
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        Bundle bundle =getIntent().getExtras();
        context=this;

        difficulty = getIntent().getIntExtra("difficulty",0);
        Log.v("xyzyx",String.valueOf(difficulty));
       nballs = getIntent().getIntExtra("nBalls",1);
       for (int i=1 ;i<=nballs;i++){
           Circulo circulo= (Circulo) bundle.get(String.valueOf(i));
          // Log.v("xyzyx",circulo.toString());
           balls.add(circulo);
       }

      initialize();
       cuentaColores();
    }


    public void initialize(){

        mediaPlayer = MediaPlayer.create(this, R.raw.tension);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        etCuantas=findViewById(R.id.etCuantas);
        btAceptar=findViewById(R.id.btAceptar);

        btAceptar.setOnClickListener(view -> {
            if(difficulty>1){
                if(etAzul.getText().toString().isEmpty()||etRojo.getText().toString().isEmpty()||etVerde.getText().toString().isEmpty()||etMagenta.getText().toString().isEmpty()){
                    resultado=false;
                }else{
                    resultado=
                            Integer.parseInt(etAzul.getText().toString())==azul &&
                                    Integer.parseInt(etRojo.getText().toString())==rojo &&
                                    Integer.parseInt(etMagenta.getText().toString())==magenta &&
                                    Integer.parseInt(etVerde.getText().toString())==verde;
                }

            }else {

                if(etCuantas.getText().toString().isEmpty()){
                    resultado=false;
                }else{
                    resultado = (Integer.parseInt(etCuantas.getText().toString()) == nballs);
                }
            }
            Intent intent = new Intent(context, ResultadoActivity.class);
            intent.putExtra("resultado", resultado);
            intent.putExtra("difficulty", difficulty);
            mediaPlayer.stop();
            startActivity(intent);
        });
        if(difficulty>1) {
        lbAzul = findViewById(R.id.tvAzul);
        lbRojo = findViewById(R.id.tvRojo);
        lbVerde = findViewById(R.id.tvVerde);
        lbMagenta = findViewById(R.id.tvMagenta);

        etAzul = findViewById(R.id.etAzul);
        etRojo = findViewById(R.id.etRojo);
        etVerde = findViewById(R.id.etVerde);
        etMagenta = findViewById(R.id.etMagenta);

        etAzul.setVisibility(View.VISIBLE);
        etRojo.setVisibility(View.VISIBLE);
        etVerde.setVisibility(View.VISIBLE);
        etMagenta.setVisibility(View.VISIBLE);
        lbAzul.setVisibility(View.VISIBLE);
        lbRojo.setVisibility(View.VISIBLE);
        lbVerde.setVisibility(View.VISIBLE);
        lbMagenta.setVisibility(View.VISIBLE);
        etCuantas.setVisibility(View.GONE);
        }
    }

    public void cuentaColores() {

        for (Circulo ball : balls) {
            int color = ball.getColor();
            switch (color) {
                case Color.BLUE:
                    azul++;
                    break;
                case Color.RED:
                    rojo++;
                    break;
                case Color.GREEN:
                    verde++;
                    break;
                case Color.MAGENTA:
                    magenta++;
                    break;
            }
        }
    }
}