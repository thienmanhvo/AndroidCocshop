package fpt.edu.cocshop.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import fpt.edu.cocshop.Fragment.OnGoIngFragment;

public class SectionPageMyOrderAdapter extends FragmentStatePagerAdapter {

    private int NUM_PAGE = 3;
    private int currentPosition = -1;

    public SectionPageMyOrderAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return NUM_PAGE;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "On Going";
            case 1:
                return "History";
            case 2:
                return "Cart";
        }
        return null;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return OnGoIngFragment.newInstance();

            case 1:

            case 2:

            default:
                return OnGoIngFragment.newInstance();
        }
    }
}