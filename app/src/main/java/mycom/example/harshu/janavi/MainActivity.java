package mycom.example.harshu.janavi;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import com.example.harshu.brainykidzapp.R;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private GridView gridView;
   private  Context context;
    private ArrayList<String> listItem;
    private TextToSpeech textspeech_obj;

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

        gridView = (GridView) findViewById(R.id.gridView);
        listItem = new ArrayList<String>();
       // listItem = getResources().getStringArray(R.array.alphabet_array);
        for(char i='A';i<='Z';i++){
           listItem.add(String.valueOf(i));
        }

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.grid_buttons, listItem);
        gridView.setAdapter(adapter);




        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                String value = adapter.getItem(position);
                Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT).show();
                textspeech_obj.speak(value, TextToSpeech.QUEUE_FLUSH, null);


            }
        });



    }
}

