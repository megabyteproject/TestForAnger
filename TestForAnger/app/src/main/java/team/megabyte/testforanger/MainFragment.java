package team.megabyte.testforanger;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by sergey on 30/01/16.
 */
public class MainFragment extends Fragment {


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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.inject(this, view);

        return view;
    }


}
