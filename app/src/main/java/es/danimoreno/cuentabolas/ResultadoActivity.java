package es.danimoreno.cuentabolas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ResultadoActivity extends AppCompatActivity {

    TextView tvResult, tvBolas, tvHabia,
            tvBlueResult, tvRedResult, tvGreenResult, tvMagentaResult;
    MediaPlayer mediaPlayer;
    int difficulty;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    ArrayList<Circulo> balls = Game.balls;
    Button btCamara,btFinish;
    boolean resultado;
    ImageView ivCamara;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        resultado = getIntent().getBooleanExtra("resultado", false);
        difficulty = getIntent().getIntExtra("difficulty", 0);
        Log.v("xyzyx",String.valueOf(difficulty));
        initialize();
    checkResult(resultado);
    }
    private void checkResult(boolean result) {
        if (result){
            mediaPlayer = MediaPlayer.create(this, R.raw.winner);
            mediaPlayer.start();
            tvResult.setText(R.string.tv_success);
            tvResult.setBackgroundColor(Color.GREEN);
        } else {
            mediaPlayer = MediaPlayer.create(this, R.raw.lose);
            mediaPlayer.start();
            tvResult.setText(R.string.tv_fail);
            tvResult.setBackgroundColor(Color.RED);
        }

        if (difficulty != 0){
            Log.v("xyzyx",String.valueOf(difficulty));
            resultado(difficulty);
        }

    }

    private void resultado(int difficulty) {
        if (difficulty == 1){
            tvBolas.setText(balls.size()+" Bolas");
            tvBolas.setVisibility(View.VISIBLE);
            tvHabia.setVisibility(View.VISIBLE);
            Log.v("xyzyx",String.valueOf(difficulty));
        } else {
            tvBlueResult.setVisibility(View.VISIBLE);
            tvRedResult.setVisibility(View.VISIBLE);
            tvGreenResult.setVisibility(View.VISIBLE);
            tvMagentaResult.setVisibility(View.VISIBLE);


            tvBlueResult.append(" \t"+BolasPorColor(Color.BLUE));
            tvRedResult.append(" \t"+BolasPorColor(Color.RED));
            tvGreenResult.append(" \t"+BolasPorColor(Color.GREEN));
            tvMagentaResult.append(" \t"+BolasPorColor(Color.MAGENTA));
        }
    }

    private String BolasPorColor(int color) {
        int nBalls = 0;
        for (Circulo ball: balls) {
            if (ball.getColor() == color){
                nBalls++;
            }
        }
        return String.valueOf(nBalls);
    }





    private void initialize() {
        tvResult = findViewById(R.id.tvResult);
        tvHabia = findViewById(R.id.tvHabia);
        tvBolas = findViewById(R.id.tvBolas);
        tvBlueResult = findViewById(R.id.tvBlueResult);
        tvRedResult = findViewById(R.id.tvRedResult);
        tvGreenResult = findViewById(R.id.tvGreenResult);
        tvMagentaResult = findViewById(R.id.tvMagentaResult);

        ivCamara = findViewById(R.id.ivPhoto);
        btFinish=findViewById(R.id.btFinish);
        btCamara = findViewById(R.id.btPhoto);

        btCamara.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Camera")
                    .setMessage(R.string.tx_player_picture)
                    .setNegativeButton("Deny", (dialogInterface, which) -> {


                        builder.create().cancel();
                    })
                    .setPositiveButton("Accept", (dialogInterface, which) -> {
                        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                        }
                    });
            builder.create().show();
        });


        btFinish.setOnClickListener(view -> {
            finish();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ivCamara.setImageBitmap(imageBitmap);
        }
        ivCamara.setVisibility(View.VISIBLE);

        btCamara.setText("Change photo");
        if (resultado){
            tvResult.setText(R.string.tv_victory_pic);
        } else {
            tvResult.setText(R.string.tv_lose_pic);
        }
    }
}