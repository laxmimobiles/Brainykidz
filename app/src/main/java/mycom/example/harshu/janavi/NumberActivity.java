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

public class NumberActivity extends AppCompatActivity {


    GridView gridView2;
    Context context;
    ArrayList<Integer> listNumber;
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
        //listNumber = getResources().getStringArray(R.array.number_array);
        listNumber=new ArrayList<Integer>();
        for(int i=1;i<100;i++){
            listNumber.add(i);
        }

        gridView2 = (GridView) findViewById(R.id.gridView);

        final ArrayAdapter<Integer> adapter_sight = new ArrayAdapter<Integer>(this,
                R.layout.grid_buttons, listNumber);
        gridView2.setAdapter(adapter_sight);




        gridView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                int value = adapter_sight.getItem(position);
                Toast.makeText(getApplicationContext(), String.valueOf(value), Toast.LENGTH_SHORT).show();
                textspeech_obj.speak(String.valueOf(value), TextToSpeech.QUEUE_FLUSH, null);


            }
        });
    }
    }

