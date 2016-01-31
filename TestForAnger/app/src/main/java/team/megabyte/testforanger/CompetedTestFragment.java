package team.megabyte.testforanger;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * Created by sergey on 30/01/16.
 */
public class CompetedTestFragment extends Fragment {
    private int sizeArrayQuestions;
    private ArrayList<Integer> statisticAnger;
    ArrayList<QuestionAnswer> questionAnswers;
    public CompetedTestFragment(ArrayList<QuestionAnswer> questionAnswers) {
        this.questionAnswers = questionAnswers;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_completed_test, container, false);
        ButterKnife.inject(this, view);
        statisticAnger = new ArrayList<Integer>();
        for(int i = 0; i < 8; i ++)
            statisticAnger.add(0);
        sizeArrayQuestions = questionAnswers.size();

        /// ТУТ ДОПИШИ
        return view;
    }


}