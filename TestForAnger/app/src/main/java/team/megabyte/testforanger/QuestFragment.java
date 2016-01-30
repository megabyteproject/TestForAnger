package team.megabyte.testforanger;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by sergey on 30/01/16.
 */
public class QuestFragment extends Fragment {

    private TextView questTitle;
    private TextView questContent;
    private ImageView answerYesIcon;
    private ImageView answerNoIcon;
    private View answerYes;
    private View answerNo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_quest, container, false);
        answerYesIcon = (ImageView) v.findViewById(R.id.answer_yes_icon);
        answerNoIcon = (ImageView) v.findViewById(R.id.answer_no_icon);
        questTitle = (TextView) v.findViewById(R.id.quest_title);
        questContent = (TextView) v.findViewById(R.id.quest_content);
        answerYes = v.findViewById(R.id.answer_yes);
        answerNo =  v.findViewById(R.id.answer_no);

        return v;
    }
}
