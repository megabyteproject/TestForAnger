package team.megabyte.testforanger;

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

    public ResultAdapter(int[] resultNumbers) {
        this.resultNumbers = resultNumbers;
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
        String text = "";
        switch (position){
            case 0: text = "Физическая агрессия"; break;
            case 1: text = "Косвенная агрессия"; break;
            case 2: text = "Раздражительность"; break;
            case 3: text = "Негативизм"; break;
            case 4: text = "Обидчивость"; break;
            case 5: text = "Подозрительность"; break;
            case 6: text = "Вербальная агрессия"; break;
            case 7: text = "Чувство вины"; break;
        }
        textView.setText(text + ": " + resultNumbers[position] * 20 + "%");
        return view;
    }

}
