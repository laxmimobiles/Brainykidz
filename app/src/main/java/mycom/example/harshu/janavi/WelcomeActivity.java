package mycom.example.harshu.janavi;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.harshu.brainykidzapp.R;

import java.util.Locale;

public class WelcomeActivity extends AppCompatActivity {

    Button alphabet ;
    Button sightWord;
    Button advancedWords;
    Button numbers;
    TextToSpeech textspeech_obj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        textspeech_obj = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    textspeech_obj.setLanguage(Locale.US);
                }
            }
        });

        numbers = (Button)findViewById(R.id.number_id);
        numbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textspeech_obj.speak("numbers", TextToSpeech.QUEUE_FLUSH, null);
                Intent intent = new Intent(getApplicationContext(),NumberActivity.class);
                startActivity(intent);
            }
        });
        //control goes to MainActivity.class
        alphabet = (Button)findViewById(R.id.phonic_button);
        alphabet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textspeech_obj.speak("alphabets", TextToSpeech.QUEUE_FLUSH, null);
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        //control goes to SightWordActivity.class
        sightWord = (Button)findViewById(R.id.sight_words_button);
        sightWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textspeech_obj.speak("sightwords", TextToSpeech.QUEUE_FLUSH, null);
                Intent intent = new Intent(getApplicationContext(),SightWordActivity.class);
                startActivity(intent);
            }
        });

        //control goes to AdvancedWordsActivity.class
        advancedWords = (Button)findViewById(R.id.picture_button);
        advancedWords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textspeech_obj.speak("spelling bee", TextToSpeech.QUEUE_FLUSH, null);
                Intent intent = new Intent(getApplicationContext(),WordListActivity.class);
                startActivity(intent);
            }
        });
    }
}
