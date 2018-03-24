package app.codebrew.mycensus.Tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.codebrew.mycensus.R;


public class EmploymentDetailsTab extends Fragment {

    public EmploymentDetailsTab() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_employment_details, container, false);
        return rootView;
    }
}
