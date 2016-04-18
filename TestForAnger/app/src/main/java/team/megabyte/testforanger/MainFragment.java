package team.megabyte.testforanger;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by sergey on 30/01/16.
 */
public class MainFragment extends Fragment {


    @InjectView(R.id.adView)
    AdView mAdView;
    @InjectView(R.id.start)
    Button start;
    @InjectView(R.id.other_test)
    Button otherTest;
    @InjectView(R.id.exit)
    Button exit;

    public MainFragment() {
    }

    @OnClick(R.id.start)
    public void onClickStart(){
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragContainer, new QuestFragment())
                .addToBackStack(null)
                .commit();
    }

    @OnClick(R.id.other_test)
    public void onClickRate(){
        final String appPackageName = getActivity().getPackageName(); // getPackageName() from Context or Activity object
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (android.content.ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }
    }

    @OnClick(R.id.exit)
    public void onClickExit(){
        getActivity().finish();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.inject(this, view);

        Typeface type = Typeface.createFromAsset(getActivity().getAssets(), "fonts/8408.ttf");
        start.setText(getActivity().getResources().getString(R.string.start_test));
        start.setTypeface(type);

        otherTest.setText(getActivity().getResources().getString(R.string.rate));
        otherTest.setTypeface(type);

        exit.setText(getActivity().getResources().getString(R.string.exit));
        exit.setTypeface(type);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }
}
