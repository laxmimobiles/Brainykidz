package mycom.example.harshu.janavi;

import android.os.AsyncTask;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.harshu.brainykidzapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class AdvancedWordsActivity extends AppCompatActivity {

    private String TAG = AdvancedWordsActivity.class.getSimpleName();
    ListView listView;
    String[] listwords;
    TextToSpeech textspeech_obj;
    ArrayAdapter<String> adapter_sight;
    Words words;
    ArrayList<Words> wordsArrayList;
    WordAdapter wordAdapter;
    String definition,definition2;
    String message;
    String category;
    Button speakerButton;
    TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_list);
        speakerButton = findViewById(R.id.speaker);

        wordsArrayList= new ArrayList<Words>();
        Bundle bundle = getIntent().getExtras();
        message = bundle.getString("mywordpass");
       // Words wordOne = new Words();
       // wordOne.setWord1(message);


        Log.v("word",message);
        new CallbackTask().execute(dictionaryEntries());

    }

    private String dictionaryEntries() {
        final String language = "en";
        final String word = message;
        final String word_id = word.toLowerCase(); //word id is case sensitive and lowercase is required
        return "https://od-api.oxforddictionaries.com:443/api/v1/entries/" + language + "/" + word_id;
    }

    private class CallbackTask extends AsyncTask<String, Integer, String> {

        protected String doInBackground(String... params) {
            HttpHandler sh = new HttpHandler();

            String jsonString = "";
            try {

                jsonString = sh.makeHttpRequest(createUrl(params[0]));
            } catch (IOException e) {
                return null;
            }
            if (jsonString != null) {
                try {

                    new Words(message);
                    JSONObject jsonObj = new JSONObject(jsonString);


                    JSONArray resultarrayObj = jsonObj.getJSONArray("results");
                    JSONObject lexical = resultarrayObj.getJSONObject(0);
                    JSONArray lexicalArray = lexical.getJSONArray("lexicalEntries");

                               for(int i=0; i < lexicalArray.length();i++) {
                            //publishProgress((int) (((i+1) / (float) lexicalArray.length()) * 100));
                            JSONObject lexicalArrayJSONObject = lexicalArray.getJSONObject(i);
                            JSONArray entryArray = lexicalArrayJSONObject.getJSONArray("entries");
                            category = lexicalArrayJSONObject.getString("lexicalCategory");

                            JSONObject entryArrayJSONObject = entryArray.getJSONObject(0);
                            JSONArray sensesArray = entryArrayJSONObject.getJSONArray("senses");

                            JSONObject sensesObject = sensesArray.getJSONObject(0);
                            definition = sensesObject.getJSONArray("definitions").getString(0);
                            Log.v("def", definition);
                            Log.v("cat", category);
                            // String example1 = sensesObject.getJSONArray("examples").getString(0);
                            //String example2 = sensesObject.getJSONArray("examples").getString(1);


                            wordsArrayList.add(new Words(category,definition));
                        }
                        //new Words(message);
                        // adding a pokemon to our pokemon list



                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                }

            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server.",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }

            return null;

        }
        private URL createUrl(String stringUrl) {
            URL url = null;
            try {
                url = new URL(stringUrl);
            } catch (MalformedURLException exception) {
                return null;
            }
            return url;
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            new Words(message);
            LexicalAdapter lexicalAdapter = new LexicalAdapter(AdvancedWordsActivity.this,wordsArrayList,message);
            listView = findViewById(R.id.listofwords);
            listView.setAdapter(lexicalAdapter);


            // System.out.println(result);
        }
    }




}
