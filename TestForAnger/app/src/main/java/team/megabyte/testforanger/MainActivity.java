package team.megabyte.testforanger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragContainer, new MainFragment())
                .commit();

        throw new RuntimeException("Test crash");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        int count = getFragmentManager().getBackStackEntryCount();
//        if (count == 0) {
//            super.onBackPressed();
//        } else {
//            getFragmentManager().popBackStack();
//        }
    }
}
