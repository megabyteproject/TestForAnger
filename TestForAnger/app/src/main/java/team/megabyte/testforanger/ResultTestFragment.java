package team.megabyte.testforanger;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by sergey on 30/01/16.
 */
public class ResultTestFragment extends Fragment {
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

    @InjectView(R.id.button_neutral)
    Button buttonNeutral;
    @InjectView(R.id.button_calm)
    Button buttonCalm;
    @InjectView(R.id.button_agressive)
    Button buttonAgressive;
    @InjectView(R.id.result_text)
    TextView textResult;

    public static ResultTestFragment instanceFragment(ArrayList<QuestionAnswer> questionAnswers){
        ResultTestFragment competedTestFragment = new ResultTestFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(QUESTIONS_ANSWERS, questionAnswers);
        competedTestFragment.setArguments(bundle);
        return competedTestFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        questionAnswers = getArguments().getParcelableArrayList(QUESTIONS_ANSWERS);

        View view = inflater.inflate(R.layout.fragment_result_test, container, false);
        ButterKnife.inject(this, view);

        buttonNeutral.setOnClickListener(onClickListener);
        buttonCalm.setOnClickListener(onClickListener);
        buttonAgressive.setOnClickListener(onClickListener);

        String[] resultStrings = getResources().getStringArray(R.array.string_results);

        counterAnswer = 0;
        for(int  i = 0; i < questionAnswers.size(); i ++)
            if(questionAnswers.get(i).getAnswer())
                counterAnswer += 1;

        if(counterAnswer < 13) {
            reCalm.setVisibility(View.VISIBLE);
            reNeutral.setVisibility(View.GONE);
            reAgressive.setVisibility(View.GONE);
            textResult.setText(resultStrings[0]);
        } else if(counterAnswer < 27) {
            reCalm.setVisibility(View.GONE);
            reNeutral.setVisibility(View.VISIBLE);
            reAgressive.setVisibility(View.GONE);
            textResult.setText(resultStrings[1]);
        } else {
            reCalm.setVisibility(View.GONE);
            reNeutral.setVisibility(View.GONE);
            reAgressive.setVisibility(View.VISIBLE);
            textResult.setText(resultStrings[2]);
        }
        return view;
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragContainer, CompetedTestFragment.instanceFragment(questionAnswers))
                    .commit();
        }
    };


}