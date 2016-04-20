package team.megabyte.testforanger;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.kobakei.ratethisapp.RateThisApp;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

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
    @InjectView(R.id.adView)
    AdView mAdView;

    @InjectView(R.id.button_neutral)
    Button buttonNeutral;
    @InjectView(R.id.button_calm)
    Button buttonCalm;
    @InjectView(R.id.button_agressive)
    Button buttonAgressive;

    InterstitialAd mInterstitialAd;

    @OnClick(R.id.home_button)
    public void onClickHome(){
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragContainer, new MainFragment())
                .commit();
    }

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

        Typeface type = Typeface.createFromAsset(getActivity().getAssets(), "fonts/8408.ttf");

        buttonNeutral.setOnClickListener(onClickListener);
        buttonCalm.setOnClickListener(onClickListener);
        buttonAgressive.setOnClickListener(onClickListener);

        counterAnswer = 0;
        for(int  i = 0; i < questionAnswers.size(); i ++)
            if(questionAnswers.get(i).getAnswer())
                counterAnswer += 1;

        if(counterAnswer < 13) {
            reCalm.setVisibility(View.VISIBLE);
            reNeutral.setVisibility(View.GONE);
            reAgressive.setVisibility(View.GONE);

            buttonCalm.setText(getActivity().getResources().getString(R.string.repeat_test));
            buttonCalm.setTypeface(type);
        } else if(counterAnswer < 27) {
            reCalm.setVisibility(View.GONE);
            reNeutral.setVisibility(View.VISIBLE);
            reAgressive.setVisibility(View.GONE);

            buttonNeutral.setText(getActivity().getResources().getString(R.string.repeat_test));
            buttonNeutral.setTypeface(type);
        } else {
            reCalm.setVisibility(View.GONE);
            reNeutral.setVisibility(View.GONE);
            reAgressive.setVisibility(View.VISIBLE);

            buttonAgressive.setText(getActivity().getResources().getString(R.string.repeat_test));
            buttonAgressive.setTypeface(type);
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

        ResultAdapter adapter = new ResultAdapter(getActivity(), resultNumbers);
        resultListView.setDivider(null);
        resultListView.setAdapter(adapter);

        RateThisApp.Config config = new RateThisApp.Config(1, 5);
// Custom title ,message and buttons names
        config.setTitle(R.string.rate_title);
        config.setMessage(R.string.rate_message);
        config.setYesButtonText(R.string.rate_us);
        config.setNoButtonText(R.string.rate_remind);
        config.setCancelButtonText(R.string.rate_no);
        RateThisApp.init(config);


        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId("ca-app-pub-5534695259936833/8187237908");

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                beginPlayingGame();
            }
        });

        requestNewInterstitial();

        return view;
    }

    private void beginPlayingGame() {
        getActivity().getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragContainer, new QuestFragment())
                .commitAllowingStateLoss();
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("SEE_YOUR_LOGCAT_TO_GET_YOUR_DEVICE_ID")
                .build();

        mInterstitialAd.loadAd(adRequest);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            } else {
                beginPlayingGame();
            }

        }
    };

    @Override
    public void onResume() {
        super.onResume();
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Monitor launch times and interval from installation
        RateThisApp.onStart(getActivity());
        // Show a dialog if criteria is satisfied
        RateThisApp.showRateDialogIfNeeded(getActivity());
    }

}