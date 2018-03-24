package app.codebrew.mycensus.Tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;

import app.codebrew.mycensus.R;


public class FamilyDetailsTab extends Fragment {

    public static ArrayList<EditText[]> familyMembers = new ArrayList();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_family_details, container, false);
        familyMembers.add(new EditText[]{(EditText)rootView.findViewById(R.id.first_name_et), (EditText)rootView.findViewById(R.id.last_name_et)});
        Button button = (Button)rootView.findViewById(R.id.btn_add);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout name = (LinearLayout)rootView.findViewById(R.id.family_cl);
                LinearLayout newmember = new LinearLayout(getContext());
                newmember.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                newmember.setOrientation(LinearLayout.HORIZONTAL);

                EditText firstname = new EditText(getContext());
                EditText lastname = new EditText(getContext());

                int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, getResources().getDisplayMetrics());

                firstname.setLayoutParams(new LinearLayout.LayoutParams(height, ViewGroup.LayoutParams.WRAP_CONTENT));
                firstname.setTextSize(14);
                firstname.setHint("First Name");

                lastname.setLayoutParams(new LinearLayout.LayoutParams(height, ViewGroup.LayoutParams.WRAP_CONTENT));
                lastname.setTextSize(14);
                lastname.setHint("Last Name");

                newmember.addView(firstname);
                newmember.addView(lastname);

                familyMembers.add(new EditText[]{firstname, lastname});

                name.addView(newmember);



            }
        });
        return rootView;


    }

}
