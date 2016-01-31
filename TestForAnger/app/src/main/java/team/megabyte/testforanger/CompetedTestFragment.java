package team.megabyte.testforanger;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by sergey on 30/01/16.
 */
public class CompetedTestFragment extends Fragment {
    private int sizeArrayQuestions;
    private ArrayList<Integer> statisticAnger;
    private int counterAnswer;
    ArrayList<QuestionAnswer> questionAnswers;

    @InjectView(R.id.rlbackground)
    RelativeLayout rlbackground;
    public CompetedTestFragment(ArrayList<QuestionAnswer> questionAnswers) {
        this.questionAnswers = questionAnswers;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_completed_test, container, false);
        ButterKnife.inject(this, view);
        counterAnswer = 0;
        for(int  i = 0; i < questionAnswers.size(); i ++)
            if(questionAnswers.get(i).getAnswer())
                counterAnswer += 1;

        if(counterAnswer < 13) {
            rlbackground.setBackground(getResources().getDrawable(R.drawable.background_wolf_calm));
        } else if(counterAnswer < 27) {
            rlbackground.setBackground(getResources().getDrawable(R.drawable.background_wolf_neutral));
        } else {
            rlbackground.setBackground(getResources().getDrawable(R.drawable.background_wolf_aggressive));
        }

        statisticAnger = new ArrayList<Integer>();
        for(int i = 0; i < 8; i ++)
            statisticAnger.add(0);
        sizeArrayQuestions = questionAnswers.size();

        return view;
    }


}