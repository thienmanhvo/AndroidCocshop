package fpt.edu.cocshop.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import fpt.edu.cocshop.Fragment.HomeFragment;

public class MainAdapter  extends FragmentStatePagerAdapter {
    private static int NUM_PAGE = 4;

    public MainAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
//            case 0:
//                return HomeFragment.newInstance();
//            case 1:
////                return HistoryFragment.newInstance();
////            case 2:
////                return AlbumFragment.newInstance();
////            case 3:
////                return UserFragment.newInstance();
            default:
                return HomeFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        return NUM_PAGE;
    }
}
