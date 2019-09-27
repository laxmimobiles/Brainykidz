package mycom.example.harshu.janavi;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.harshu.brainykidzapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class WordListActivity extends AppCompatActivity {

    ArrayList wordArray;
    private String TAG = WordListActivity.class.getSimpleName();
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_list);
        wordArray = new ArrayList<Words>();
        new CallbackTask().execute(wordlist());
    }
    private String wordlist() {
        final String language = "en";
        final String filters = "domains=Art";
        return "https://od-api.oxforddictionaries.com:443/api/v1/wordlist/" + language + "/" + filters;
    }

    private class CallbackTask extends AsyncTask<String, Integer, String> {

        protected String doInBackground(String... params) {
            HttpHandler sh = new HttpHandler();
            //TODO: replace with your own app id and app key


            String jsonString = "";
            try {
                jsonString = sh.makeHttpRequest(createUrl(params[0]));
            } catch (IOException e) {
                return null;
            }
            if (jsonString != null) {
                try {
                    //TODO: Create a new JSONObject
                    JSONObject jsonObj = new JSONObject(jsonString);

                    // TODO: Get the JSON Array node
                    JSONArray resultarrayObj = jsonObj.getJSONArray("results");
                    for(int i=0; i<resultarrayObj.length();i++){
                        JSONObject resultobject = resultarrayObj.getJSONObject(i);
                        String myword = resultobject.getString("word");
                        wordArray.add(new Words(myword));

                    }

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

            WordAdapter wordAdapter = new WordAdapter(WordListActivity.this,wordArray);
            listView = findViewById(R.id.listofwords);
           listView.setAdapter(wordAdapter);



            // System.out.println(result);
        }
    }
}
