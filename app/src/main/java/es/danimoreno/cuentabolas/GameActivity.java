package es.danimoreno.cuentabolas;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;

public class GameActivity extends AppCompatActivity {

    Game game;
    int difficulty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        int nBolas=getIntent().getIntExtra("nBolas",0);
        int velocidad=getIntent().getIntExtra("velocidad",0);
         game=findViewById(R.id.game);
        difficulty = getIntent().getIntExtra("difficulty", 0);
        game.setContext(this);
        game.setDifficulty(difficulty);
    }
}