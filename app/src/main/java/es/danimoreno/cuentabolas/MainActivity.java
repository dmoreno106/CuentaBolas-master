package es.danimoreno.cuentabolas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements DialogInterface.OnClickListener {

    private Context context;
    private Button btEasy,btNormal,btHard;
    private Random r;
    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();

    }

    private void initialize()  {

        mediaPlayer = MediaPlayer.create(this, R.raw.portal_radio);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        context=this;
        r= new Random();
        btEasy=findViewById(R.id.btEasy);
        btNormal=findViewById(R.id.btNormal);
        btHard=findViewById(R.id.btHard);

        btEasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,GameActivity.class);
                intent.putExtra("difficulty",1);
                mediaPlayer.stop();
                startActivity(intent);
            }
        });

        btNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,GameActivity.class);
                intent.putExtra("difficulty",2);
                mediaPlayer.stop();
                startActivity(intent);
            }
        });

        btHard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,GameActivity.class);
                intent.putExtra("difficulty",3);
                mediaPlayer.stop();
                startActivity(intent);
            }
        });

    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {

    }
}