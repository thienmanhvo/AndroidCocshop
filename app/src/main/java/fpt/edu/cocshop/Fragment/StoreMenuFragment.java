package fpt.edu.cocshop.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fpt.edu.cocshop.Adapter.StoreMenuItemAdapter;
import fpt.edu.cocshop.Model.Menu;
import fpt.edu.cocshop.Model.MenuItem;
import fpt.edu.cocshop.R;


public class StoreMenuFragment extends Fragment {
    private RecyclerView mRcvMenu;
    private StoreMenuItemAdapter mStoreMenuItemAdapter;
    private List<Menu> mMenuList;
    private View mView;


    public StoreMenuFragment() {
        // Required empty public constructor
    }

    public static StoreMenuFragment newInstance() {
        StoreMenuFragment fragment = new StoreMenuFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_store_menu, container, false);
        initView();
        initData();
        return mView;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // This callback will only be called when MyFragment is at least Started.
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }

    private void initView() {
        mRcvMenu = mView.findViewById(R.id.rcv_store_menu_item);
        mRcvMenu.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        mRcvMenu.setLayoutManager(manager);
    }

    private void initData() {
        mMenuList = new ArrayList<>();
        for (int j = 0; j <= 4; j++) {
            Menu menu = new Menu();
            menu.setName("header" + j);
            List<MenuItem> items = new ArrayList<>();
            for (int i = 0; i <= 3; i++) {
                MenuItem item = new MenuItem();
                item.setName("Đùi gà nướng" + " " + i);
                item.setImagePath("https://znews-photo.zadn.vn/w660/Uploaded/Ohunoaa/2016_12_31/d6.jpg");
                item.setPrice(20500);
                items.add(item);
            }
            menu.setItems(items);
            mMenuList.add(menu);
        }
        StoreMenuItemAdapter adapter = new StoreMenuItemAdapter(getContext(), mMenuList);
        mRcvMenu.setAdapter(adapter);
        mRcvMenu.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.expandAllParents();
//        updateUIRcvMenu(mMenuList);


    }


//    private void updateUIRcvMenu(final List<Object> mMenuList) {
//        if (mStoreMenuItemAdapter == null) {
//            mStoreMenuItemAdapter = new StoreMenuItemAdapter(getContext(), mMenuList);
//            mRcvMenu.setAdapter(mStoreMenuItemAdapter);
//            mStoreMenuItemAdapter.setmOnStoreMenuClickListener(new StoreMenuItemAdapter.OnStoreMenuListener() {
//                @Override
//                public void onClickToggleMenuItem(int position) {
//                    int positionTemp = position + 1;
//                    while (true) {
//                        if (positionTemp >= mMenuList.size() || mMenuList.get(positionTemp) instanceof Menu) {
//                            return;
//                        }
//                        StoreMenuItemAdapter.ViewHolderItem vh =(StoreMenuItemAdapter.ViewHolderItem)  mRcvMenu.findViewHolderForAdapterPosition(positionTemp++);
//                        vh.setVisibility(false);
//                    }
//                }
//
//                @Override
//                public void onClickToggleMenuItemShow(int position) {
//                    int positionTemp = position + 1;
//                    while (true) {
//                        if (positionTemp >= mMenuList.size() || mMenuList.get(positionTemp) instanceof Menu) {
//                            return;
//                        }
//                        StoreMenuItemAdapter.ViewHolderItem vh =(StoreMenuItemAdapter.ViewHolderItem)  mRcvMenu.findViewHolderForAdapterPosition(positionTemp++);
//                        vh.setVisibility(true);
//                    }
//                }
//            });
//        } else {
//            mStoreMenuItemAdapter.notifyDataSetChanged();
//        }
//    }
}
