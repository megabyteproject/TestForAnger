package team.megabyte.testforanger;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by sergey on 07/02/16.
 */
public class ResultAdapter extends BaseAdapter{

    int [] resultNumbers;
    Context contexts;

    public ResultAdapter(Context contexts, int[] resultNumbers) {
        this.resultNumbers = resultNumbers;
        this.contexts = contexts;
    }

    @Override
    public int getCount() {
        return resultNumbers.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return resultNumbers[position];
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_list, parent, false);
        TextView textView = (TextView) view.findViewById(R.id.result_list_text);



        Typeface typeAnother = Typeface.createFromAsset(contexts.getAssets(), "fonts/8398.ttf");
        textView.setTypeface(typeAnother);

        String text = "";
        switch (position){
            case 0: text = contexts.getString(R.string.physic_aggression); break;
            case 1: text = contexts.getString(R.string.indirect_aggression); break;
            case 2: text = contexts.getString(R.string.temper); break;
            case 3: text = contexts.getString(R.string.negativism); break;
            case 4: text = contexts.getString(R.string.touchiness); break;
            case 5: text = contexts.getString(R.string.suspicion); break;
            case 6: text = contexts.getString(R.string.verbal_aggression); break;
            case 7: text = contexts.getString(R.string.guilt); break;
        }
        textView.setText(text + ": " + resultNumbers[position] * 20 + "%");
        return view;
    }

}
