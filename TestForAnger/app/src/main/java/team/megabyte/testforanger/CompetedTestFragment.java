package team.megabyte.testforanger;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by sergey on 30/01/16.
 */
public class CompetedTestFragment extends Fragment {
    public static String QUESTIONS_ANSWERS = "QUESTIONS_ANSWERS";

    private int sizeArrayQuestions;
    private ArrayList<Integer> statisticAnger;
    private int counterAnswer;
    ArrayList<QuestionAnswer> questionAnswers;

    @InjectView(R.id.reNeutral)
    RelativeLayout reNeutral;
    @InjectView(R.id.reCalm)
    RelativeLayout reCalm;
    @InjectView(R.id.reAgressive)
    RelativeLayout reAgressive;
    @InjectView(R.id.result_list_view)
    ListView resultListView;

    public static CompetedTestFragment instanceFragment(ArrayList<QuestionAnswer> questionAnswers){
        CompetedTestFragment competedTestFragment = new CompetedTestFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(QUESTIONS_ANSWERS, questionAnswers);
        competedTestFragment.setArguments(bundle);
        return competedTestFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        questionAnswers = getArguments().getParcelableArrayList(QUESTIONS_ANSWERS);

        View view = inflater.inflate(R.layout.fragment_completed_test, container, false);
        ButterKnife.inject(this, view);

        counterAnswer = 0;
        for(int  i = 0; i < questionAnswers.size(); i ++)
            if(questionAnswers.get(i).getAnswer())
                counterAnswer += 1;

        if(counterAnswer < 13) {
            reCalm.setVisibility(View.VISIBLE);
            reNeutral.setVisibility(View.GONE);
            reAgressive.setVisibility(View.GONE);
        } else if(counterAnswer < 27) {
            reCalm.setVisibility(View.GONE);
            reNeutral.setVisibility(View.VISIBLE);
            reAgressive.setVisibility(View.GONE);
        } else {
            reCalm.setVisibility(View.GONE);
            reNeutral.setVisibility(View.GONE);
            reAgressive.setVisibility(View.VISIBLE);
        }

        statisticAnger = new ArrayList<Integer>();
        for(int i = 0; i < 8; i ++)
            statisticAnger.add(0);
        sizeArrayQuestions = questionAnswers.size();

        int phisycalAgressive = 0;
        int indirectAgressive = 0;
        int irritability = 0;
        int negativism = 0;
        int resentment = 0;
        int suspicion = 0;
        int verbalAggression = 0;
        int guilt = 0;

        for (int  i = 0; i < questionAnswers.size(); i ++){
            switch (i % 8){
                case 0: if (questionAnswers.get(i).getAnswer()) phisycalAgressive++; break;
                case 1: if (questionAnswers.get(i).getAnswer()) indirectAgressive++; break;
                case 2: if (questionAnswers.get(i).getAnswer()) irritability++; break;
                case 3: if (questionAnswers.get(i).getAnswer()) negativism++; break;
                case 4: if (questionAnswers.get(i).getAnswer()) resentment++; break;
                case 5: if (questionAnswers.get(i).getAnswer()) suspicion++; break;
                case 6: if (questionAnswers.get(i).getAnswer()) verbalAggression++; break;
                case 7: if (questionAnswers.get(i).getAnswer()) guilt++; break;
            }
        }

        int [] resultNumbers = {phisycalAgressive, indirectAgressive, irritability, negativism,
                resentment, suspicion, verbalAggression, guilt};

        ResultAdapter adapter = new ResultAdapter(resultNumbers);
        resultListView.setDivider(null);
        resultListView.setAdapter(adapter);
        return view;
    }


}