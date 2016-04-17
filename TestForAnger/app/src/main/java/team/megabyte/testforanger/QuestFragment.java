package team.megabyte.testforanger;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by sergey on 30/01/16.
 */
public class QuestFragment extends Fragment {

    private EnumSelected selected;
    private int counterQuestions;
    private int sizeArrayQuestions;
    @InjectView(R.id.quest_title)
    TextView quest_title;
    @InjectView(R.id.quest_content)
    TextView quest_content;
    @InjectView(R.id.answer_yes)
    LinearLayout answer_yes;
    @InjectView(R.id.answer_no)
    LinearLayout answer_no;
    @InjectView(R.id.answer_no_icon)
    ImageView answer_no_icon;
    @InjectView(R.id.answer_yes_icon)
    ImageView answer_yes_icon;
    @InjectView(R.id.adView)
    AdView mAdView;

    enum EnumSelected {
        NOT_SELECTED_NOTHING,
        SELECTED_YES,
        SELECTED_NO
    }

    @OnClick(R.id.home_button)
    public void onClickHome(){
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragContainer, new MainFragment())
                .commit();
    }
    @SuppressLint("NewApi")
    @OnClick(R.id.ok)
    public void onClickOk(){
        counterQuestions++;
        if(counterQuestions < sizeArrayQuestions && selected != EnumSelected.NOT_SELECTED_NOTHING) {
            if(EnumSelected.SELECTED_NO == selected) {
                questionAnswers.get(counterQuestions).setAnswer(false);
            }
            quest_title.setText(getString(R.string.title_which_question, counterQuestions + 1, sizeArrayQuestions));
            answer_yes_icon.setBackground(getResources().getDrawable(R.drawable.check_selected));
            answer_no_icon.setBackground(getResources().getDrawable(R.drawable.check_selected));
            quest_content.setText(questionAnswers.get(counterQuestions).getQuestion());
            selected = EnumSelected.NOT_SELECTED_NOTHING;
        } else if (counterQuestions >= sizeArrayQuestions){
//        } else if (counterQuestions >= 1){
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragContainer, ResultTestFragment.instanceFragment(questionAnswers))
                    .commit();
        }
    }

    @SuppressLint("NewApi")
    @OnClick(R.id.answer_yes)
    public void onClickAnswerYes(){
        answer_no_icon.setBackground(getResources().getDrawable(R.drawable.check_selected));
        answer_yes_icon.setBackground(getResources().getDrawable(R.drawable.check_not_selected));
        selected = EnumSelected.SELECTED_YES;
    }
    @SuppressLint("NewApi")
    @OnClick(R.id.answer_no)
    public void onClickAnswerNo(){
        answer_no_icon.setBackground(getResources().getDrawable(R.drawable.check_not_selected));
        answer_yes_icon.setBackground(getResources().getDrawable(R.drawable.check_selected));
        selected = EnumSelected.SELECTED_NO;
    }

    private ArrayList<QuestionAnswer> questionAnswers;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_quest, container, false);
        ButterKnife.inject(this, v);
        selected = EnumSelected.NOT_SELECTED_NOTHING;
        counterQuestions = 0;
        questionAnswers = new ArrayList<QuestionAnswer>();
        String [] array_question = getResources().getStringArray(R.array.string_questions);
        sizeArrayQuestions = array_question.length;
        quest_title.setText(getString(R.string.title_which_question, counterQuestions + 1, sizeArrayQuestions));
        for(int i = 0; i < array_question.length; i++)
            questionAnswers.add(new QuestionAnswer(array_question[i], true));
        quest_content.setText(questionAnswers.get(counterQuestions).getQuestion());
        return v;
    }
    @Override
    public void onResume() {
        super.onResume();
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }
}
