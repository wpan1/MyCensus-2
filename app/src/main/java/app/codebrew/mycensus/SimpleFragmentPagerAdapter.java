package app.codebrew.mycensus;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import app.codebrew.mycensus.Tabs.EmploymentDetailsTab;
import app.codebrew.mycensus.Tabs.FamilyDetailsTab;
import app.codebrew.mycensus.Tabs.MedicationDetailsTab;
import app.codebrew.mycensus.Tabs.PersonalDetailsTab;

/**
 * Created by wpan1 on 24/3/18.
 */
public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public SimpleFragmentPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    // This determines the fragment for each tab
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new PersonalDetailsTab();
        } else if (position == 1){
            return new FamilyDetailsTab();
        } else if (position == 2){
            return new EmploymentDetailsTab();
        } else {
            return new MedicationDetailsTab();
        }
    }

    // This determines the number of tabs
    @Override
    public int getCount() {
        return 4;
    }

    // This determines the title for each tab
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        switch (position) {
            case 0:
                return mContext.getString(R.string.personal_details_title);
            case 1:
                return mContext.getString(R.string.family_details_title);
            case 2:
                return mContext.getString(R.string.employment_details_title);
            case 3:
                return mContext.getString(R.string.medical_details_title);
            default:
                return null;
        }
    }

}