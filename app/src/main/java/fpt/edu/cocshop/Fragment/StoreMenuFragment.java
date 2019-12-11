package fpt.edu.cocshop.Fragment;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.ArrayList;
import java.util.List;

import fpt.edu.cocshop.Adapter.StoreMenuItemAdapter;
import fpt.edu.cocshop.Constant.Constant;
import fpt.edu.cocshop.Custom.CustomDecoration;
import fpt.edu.cocshop.Model.Menu;
import fpt.edu.cocshop.Model.MenuItem;
import fpt.edu.cocshop.R;
import fpt.edu.cocshop.Util.PriceExtention;


public class StoreMenuFragment extends Fragment {
    private RecyclerView mRcvMenu;
    private StoreMenuItemAdapter mStoreMenuItemAdapter;
    private List<Menu> mMenuList;
    private View mView;
    private BottomSheetBehavior mSheetBehavior;
    private LinearLayout mCartBottomSheet;
    private TextView mTxtTotalItem, mTxtTotalPrice, mTxtTotalPriceOld;

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
        // mCartBottomSheet = inflater.inflate(R.layout.fragment_cart_bottom_sheet, container, false).findViewById(R.id.ll_cart_bottom_sheet);
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
        //mCartBottomSheet = mView.findViewById(R.id.ll_cart_bottom_sheet);
        mCartBottomSheet = getActivity().findViewById(R.id.ll_cart_bottom_sheet);
        mSheetBehavior = BottomSheetBehavior.from(mCartBottomSheet);
        mSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        mTxtTotalItem = getActivity().findViewById(R.id.txt_cart_total_item_number);
        mTxtTotalPrice = getActivity().findViewById(R.id.txt_cart_total_price);
        mTxtTotalPriceOld = getActivity().findViewById(R.id.txt_cart_total_price_old);
        mTxtTotalPriceOld.setPaintFlags(mTxtTotalPriceOld.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        mRcvMenu = mView.findViewById(R.id.rcv_store_menu_item);
        mRcvMenu.setHasFixedSize(true);
        mRcvMenu.addItemDecoration(new CustomDecoration(ContextCompat.getDrawable(getContext(), R.drawable.custom_horizontal_line)));
        //mRcvMenu.addItemDecoration(new DividerItemDecoration(mRcvMenu.getContext(), DividerItemDecoration.VERTICAL));

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
        updateUIRcvMenu(mMenuList);
        mTxtTotalPrice.setText(0 + "");
        mTxtTotalItem.setText(0 + "");
        mTxtTotalPriceOld.setText(0 + "");

    }


    private void updateUIRcvMenu(final List<Menu> mMenuList) {
        if (mStoreMenuItemAdapter == null) {
            mStoreMenuItemAdapter = new StoreMenuItemAdapter(getContext(), mMenuList);
            mStoreMenuItemAdapter.expandAllParents();
            mRcvMenu.setAdapter(mStoreMenuItemAdapter);
            mRcvMenu.setLayoutManager(new LinearLayoutManager(getContext()));
            mStoreMenuItemAdapter.setmOnStoreMenuClickListener(new StoreMenuItemAdapter.OnStoreMenuListener() {

                private void init(StoreMenuItemAdapter.ViewHolderItem item, int sign) {
                    int oldNum = Integer.parseInt(item.getmTxtNumOfItem().getText().toString());
                    int totalOldItem = Integer.parseInt(PriceExtention.priceToString(mTxtTotalItem.getText().toString(), Constant.NUMBER_COMMA));
                    int newNum = oldNum + (1 * sign);
                    int totalNewItem = totalOldItem + (1 * sign);
                    long totalOldPrice = Long.parseLong(PriceExtention.priceToString(mTxtTotalPrice.getText().toString(), Constant.NUMBER_COMMA));
                    long totalNewPrice = totalOldPrice + (Long.parseLong(PriceExtention.priceToString(item.getmTxtPrice().getText().toString(), Constant.NUMBER_COMMA)) * sign);
                    long totalOldPriceOld = Long.parseLong(PriceExtention.priceToString(mTxtTotalPriceOld.getText().toString(), Constant.NUMBER_COMMA));
                    long totalNewPriceOld = totalOldPriceOld + (Long.parseLong(PriceExtention.priceToString(item.getmTxtPriceOld().getText().toString(), Constant.NUMBER_COMMA)) * sign);

                    item.getmTxtNumOfItem().setText(String.valueOf(newNum));
                    mTxtTotalItem.setText(String.valueOf(totalNewItem));
                    mTxtTotalPrice.setText(PriceExtention.longToPrice(totalNewPrice, Constant.NUMBER_COMMA));
                    mTxtTotalPriceOld.setText(PriceExtention.longToPrice(totalNewPriceOld, Constant.NUMBER_COMMA));

                    if (sign == 1) {
                        if (oldNum == 0) {
                            item.getmTxtNumOfItem().setVisibility(View.VISIBLE);
                            item.getmBtnMinusItem().setVisibility(View.VISIBLE);
                        }
                        if (totalOldItem == 0) {
                            mSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                        }
                    }
                    if (sign == -1) {
                        if (newNum == 0) {
                            item.getmTxtNumOfItem().setVisibility(View.GONE);
                            item.getmBtnMinusItem().setVisibility(View.GONE);
                        }
                        if (totalNewItem == 0) {
                            mSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                        }
                    }

                }

                @Override
                public void onClickAddItem(StoreMenuItemAdapter.ViewHolderItem item) {
                    init(item, 1);
                }

                @Override
                public void onClickMinusItem(StoreMenuItemAdapter.ViewHolderItem item) {
                    init(item, -1);
                }


            });
        } else {
            mStoreMenuItemAdapter.notifyDataSetChanged();
        }
    }
}
