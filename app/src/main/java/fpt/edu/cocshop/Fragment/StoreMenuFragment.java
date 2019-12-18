package fpt.edu.cocshop.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import fpt.edu.cocshop.Activity.CheckOutActivity;
import fpt.edu.cocshop.Activity.StoreActivity;
import fpt.edu.cocshop.Adapter.StoreMenuItemAdapter;
import fpt.edu.cocshop.Constant.Constant;
import fpt.edu.cocshop.Custom.CustomDecoration;
import fpt.edu.cocshop.Home_Store_List.HomeStoreListContract;
import fpt.edu.cocshop.Model.CartObj;
import fpt.edu.cocshop.Model.ItemOrder;
import fpt.edu.cocshop.Model.MenuDish;
import fpt.edu.cocshop.Model.Product;
import fpt.edu.cocshop.Model.Store;
import fpt.edu.cocshop.R;
import fpt.edu.cocshop.StoreDetail.StoreDetailContract;
import fpt.edu.cocshop.StoreDetail.StoreDetailPresenter;
import fpt.edu.cocshop.Store_List.ShowEmptyViewNoTask;
import fpt.edu.cocshop.Util.CurrentLocation;
import fpt.edu.cocshop.Util.DoubleHandler;
import fpt.edu.cocshop.Util.PriceExtention;


public class StoreMenuFragment extends Fragment implements StoreDetailContract.View, ShowEmptyViewNoTask {

    private static final String TAG = "StoreMenuFragment";
    private RecyclerView mRcvMenu;
    private StoreMenuItemAdapter mStoreMenuItemAdapter;
    private List<MenuDish> mMenuDishList;
    private Store mStore;
    private View mView;
    private BottomSheetBehavior mSheetBehavior;
    private LinearLayout mCartBottomSheet;
    private TextView mTxtTotalItem, mTxtTotalPrice, mTxtTotalPriceOld, mTxtEmptyView, mTxtUnitPriceOld;
    private CartObj cartObj;
    private FrameLayout mBtnCheckout;
    private StoreDetailPresenter mStoreDetailPresenter;
    private StoreActivity mStoreActivity;
    private Double discount;

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
        mBtnCheckout = getActivity().findViewById(R.id.fl_btn_checkout);
        mSheetBehavior = BottomSheetBehavior.from(mCartBottomSheet);
        mSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        mTxtTotalItem = getActivity().findViewById(R.id.txt_cart_total_item_number);
        mTxtTotalPrice = getActivity().findViewById(R.id.txt_cart_total_price);
        mTxtTotalPriceOld = getActivity().findViewById(R.id.txt_cart_total_price_old);
        mTxtTotalPriceOld.setPaintFlags(mTxtTotalPriceOld.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        mRcvMenu = mView.findViewById(R.id.rcv_store_menu_item);
        mTxtEmptyView = mView.findViewById(R.id.tv_empty_view);
        mTxtUnitPriceOld = getActivity().findViewById(R.id.txt_unit_price_old);
        mRcvMenu.setHasFixedSize(true);
        mStoreActivity = (StoreActivity) getActivity();
        // mRcvMenu.addItemDecoration(new CustomDecoration(ContextCompat.getDrawable(getContext(), R.drawable.custom_horizontal_line)));
        //mRcvMenu.addItemDecoration(new DividerItemDecoration(mRcvMenu.getContext(), DividerItemDecoration.VERTICAL));

        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        mRcvMenu.setLayoutManager(manager);

    }

    private void initData() {
        try {
            mMenuDishList = new ArrayList<>();
            cartObj = new CartObj();
            mStore = new Store();
            updateUIRcvMenu(mMenuDishList);
            mTxtTotalPrice.setText(cartObj.getTotalPrice() + "");
            mTxtTotalItem.setText(cartObj.getTotalQuantity() + "");
            mTxtTotalPriceOld.setText(cartObj.getTotalPriceOld() + "");
            mBtnCheckout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), CheckOutActivity.class);
                    intent.putExtra(Constant.CART_OBJ, cartObj);
                    startActivityForResult(intent, Constant.CHECK_OUT_REQUEST);
                }
            });
            mStoreDetailPresenter = new StoreDetailPresenter(this);
            String storeId = ((StoreActivity) getActivity()).getStore().getId();
            mStoreDetailPresenter.requestDataFromServer(CurrentLocation.latitude, CurrentLocation.longitude, storeId);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == Constant.CHECK_OUT_REQUEST) {
                cartObj = (CartObj) data.getSerializableExtra(Constant.CART_OBJ);
                initCartView(cartObj);
                mStoreMenuItemAdapter.setCartObj(cartObj);
                mStoreMenuItemAdapter.notifyDataSetChanged();
            }
        }
    }

    private void updateUIRcvMenu(final List<MenuDish> mMenuDishList) {

        if (mStoreMenuItemAdapter == null) {
            mStoreMenuItemAdapter = new StoreMenuItemAdapter(getContext(), mMenuDishList, cartObj, null);
            mStoreMenuItemAdapter.expandAllParents();
            mRcvMenu.setAdapter(mStoreMenuItemAdapter);
            mRcvMenu.setLayoutManager(new LinearLayoutManager(getContext()));
            mStoreMenuItemAdapter.setmOnStoreMenuClickListener(new StoreMenuItemAdapter.OnStoreMenuListener() {

                private void init(StoreMenuItemAdapter.ViewHolderItem item, int sign, int parentPosition, int childPosition) {

                    Product dishItem = mMenuDishList.get(parentPosition).getChildList().get(childPosition);
                    ModelMapper modelMapper = new ModelMapper();
                    ItemOrder itemOrder = modelMapper.map(dishItem, ItemOrder.class);
                    if (sign == 1) {
                        cartObj.addToCart(itemOrder, sign);
                        if (cartObj.getTotalQuantity() == 1) {
                            mSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                        }
                        initCartView(cartObj);
                    }
                    if (sign == -1) {
                        cartObj.addToCart(itemOrder, sign);
                        if (cartObj.getTotalQuantity() == 0) {
                            mSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                        }
                        initCartView(cartObj);
                    }

                }

                @Override
                public void onClickAddItem(StoreMenuItemAdapter.ViewHolderItem item, int parentPosition, int childPosition) {
                    init(item, 1, parentPosition, childPosition);
                    mStoreMenuItemAdapter.notifyDataSetChanged();
                    //Toast.makeText(getContext(), item.getmTxtName().getText(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onClickMinusItem(StoreMenuItemAdapter.ViewHolderItem item, int parentPosition, int childPosition) {
                    init(item, -1, parentPosition, childPosition);
                    mStoreMenuItemAdapter.notifyDataSetChanged();
                    //Toast.makeText(getContext(), item.getmTxtName().getText(), Toast.LENGTH_SHORT).show();
                }

            });
        } else {
            mStoreMenuItemAdapter.notifyDataSetChanged();
        }
//        } catch (Exception e) {
//            Log.e(TAG, e.getMessage());
//        }

    }

    private void initCartView(CartObj cartObj) {
        mTxtTotalItem.setText(String.valueOf(cartObj.getTotalQuantity()));
        if (discount != null) {
            mTxtTotalPriceOld.setVisibility(View.VISIBLE);
            mTxtTotalPrice.setVisibility(View.VISIBLE);
            mTxtTotalPriceOld.setText(PriceExtention.longToPrice(cartObj.getTotalPrice(), Constant.NUMBER_COMMA));
            mTxtTotalPrice.setText(PriceExtention.doubleToPrice(Double.parseDouble(DoubleHandler.doubleDisplayDecimalPlaces(cartObj.getTotalPrice() * discount, 2))));
        } else {
            mTxtTotalPrice.setText(PriceExtention.longToPrice(cartObj.getTotalPrice(), Constant.NUMBER_COMMA));
            mTxtTotalPriceOld.setText("0");
            mTxtTotalPriceOld.setVisibility(View.INVISIBLE);
            mTxtUnitPriceOld.setVisibility(View.INVISIBLE);
        }
        if (mTxtTotalItem.getText().toString().matches("0")) {
            mSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        }
    }

    @Override
    public void showProgress() {
        mStoreActivity.showProgress();
    }

    @Override
    public void hideProgress() {
        mStoreActivity.hideProgress();
    }

    @Override
    public void setStoreToRecyclerView(Store Store) {

        if (Store == null) {
            showEmptyView();
        } else {
            hideEmptyView();
        }

        mStoreActivity.setDataToView(Store);
        mMenuDishList.addAll(Store.getMenuDishes());
        if (Store != null) {
            if (Store.getPromotions() != null && Store.getPromotions().size() > 0) {
                discount = Store.getPromotions().get(0).getDiscountPercent();
                if (discount != null) {
                    discount = 1.0 - (discount / 100.0);
                    mStoreMenuItemAdapter.setmDiscount(discount);
                }
            }
        }

        mStoreMenuItemAdapter.notifyParentDataSetChanged(true);
        mStoreMenuItemAdapter.expandAllParents();
    }

    @Override
    public void onResponseFailure(String throwable) {
        Log.e(TAG, throwable);
        Toast.makeText(getContext(), getString(R.string.communication_error), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showEmptyView() {
        mRcvMenu.setVisibility(View.GONE);
        mTxtEmptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmptyView() {
        mRcvMenu.setVisibility(View.VISIBLE);
        mTxtEmptyView.setVisibility(View.GONE);
    }
}
