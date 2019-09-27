package mycom.example.harshu.janavi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.harshu.brainykidzapp.R;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Words> implements Adapter {

    private Context context;

    public WordAdapter(Activity context, ArrayList<Words> wordsArrayList) {
        super(context, 0, wordsArrayList);
        this.context=context;
    }




    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null)
            listItem = LayoutInflater.from(getContext()).inflate(R.layout.mylist, parent, false);

        Words words = getItem(position);

        TextView word1 = (TextView) listItem.findViewById(R.id.textView);
        word1.setText(words.getWord1());
        final String myword =words.getWord1();

        word1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent wordIntent = new Intent(context,AdvancedWordsActivity.class);
                                            wordIntent.putExtra("mywordpass",myword);
                                            context.startActivity(wordIntent);

                                        }
                                    }

        );
        return listItem;
    }
}
