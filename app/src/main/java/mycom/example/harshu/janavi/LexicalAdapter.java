package mycom.example.harshu.janavi;

import android.app.Activity;
import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.harshu.brainykidzapp.R;

import java.util.ArrayList;
import java.util.Locale;

public class LexicalAdapter extends ArrayAdapter<Words> {
    private Context context;
    public String messageword;
    TextToSpeech textspeech_obj;

    public LexicalAdapter(Activity context, ArrayList<Words> wordsArrayList,String message) {
        super(context, 0, wordsArrayList);
        this.messageword=message;
        this.context=context;
    }




    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null)
            listItem = LayoutInflater.from(getContext()).inflate(R.layout.activity_advanced_words, parent, false);

        Words words = getItem(position);
        TextView word0 = (TextView) listItem.findViewById(R.id.word_item0);
        String wordColor = "Word: ";
        word0.setText("Word: "+messageword);
        TextView word1 = (TextView) listItem.findViewById(R.id.word_item1);
        word1.setText("Category: "+words.getWord2());
        TextView word2 = (TextView) listItem.findViewById(R.id.word_item2);
        word2.setText("Definition: "+words.getWord3());
       // final String myword =words.getWord1();
        textspeech_obj = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    textspeech_obj.setLanguage(Locale.US);
                }
            }
        });
        Button button= listItem.findViewById(R.id.speaker);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textspeech_obj.speak(messageword, TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        return listItem;
    }
}
