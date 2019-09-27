package mycom.example.harshu.janavi;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import com.example.harshu.brainykidzapp.R;

import java.util.Locale;

public class SightWordActivity extends AppCompatActivity {
    GridView gridView2;
    String[] listwords;
    TextToSpeech textspeech_obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textspeech_obj = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    textspeech_obj.setLanguage(Locale.US);
                }
            }
        });


        gridView2 = (GridView) findViewById(R.id.gridView);

        listwords = getResources().getStringArray(R.array.sightword_array);

        final ArrayAdapter<String> adapter_sight = new ArrayAdapter<String>(this,
                R.layout.mylist, listwords);
        gridView2.setAdapter(adapter_sight);




        gridView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                String value = adapter_sight.getItem(position);
                Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT).show();
                textspeech_obj.speak(value, TextToSpeech.QUEUE_FLUSH, null);


            }
        });
    }
}
