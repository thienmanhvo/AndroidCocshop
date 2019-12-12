package fpt.edu.cocshop.Activity;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

import fpt.edu.cocshop.Adapter.ItemOrderAdapter;
import fpt.edu.cocshop.Constant.Constant;
import fpt.edu.cocshop.Custom.CustomDecoration;
import fpt.edu.cocshop.Model.CartObj;
import fpt.edu.cocshop.R;

public class CheckOutActivity extends AppCompatActivity {

    private RecyclerView mRvDishItem;
    private BottomSheetBehavior mSheetBehavior;
    private LinearLayout mCheckoutBottomSheet;
    private CartObj cartObj;
    private ItemOrderAdapter mItemOrderAdapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        initView();
        initData();
    }

    private void initData() {
        cartObj = (CartObj) getIntent().getSerializableExtra(Constant.CART_OBJ);
        updateUIRcvMenu(cartObj);
    }

    private void initView() {
        mCheckoutBottomSheet = findViewById(R.id.ll_checkout_bottom_sheet);
        mSheetBehavior = BottomSheetBehavior.from(mCheckoutBottomSheet);
        mSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        mRvDishItem = findViewById(R.id.rv_checkout_list_item);
        mRvDishItem.setHasFixedSize(true);
//        mRvDishItem.addItemDecoration(new CustomDecoration(ContextCompat.getDrawable(this, R.drawable.custom_horizontal_line)));
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        mRvDishItem.setLayoutManager(manager);

    }

    private void updateUIRcvMenu(final CartObj cartObj) {
//        try {
        if (mItemOrderAdapter == null) {
            mItemOrderAdapter = new ItemOrderAdapter(this, cartObj);
            mRvDishItem.setAdapter(mItemOrderAdapter);
            //mRvDishItem.setLayoutManager(new LinearLayoutManager();
//            mItemOrderAdapter.setmOnStoreMenuClickListener(new StoreMenuItemAdapter.OnStoreMenuListener() {
//
//                private void init(StoreMenuItemAdapter.ViewHolderItem item, int sign, int parentPosition, int childPosition) {
////                        try {
//                    MenuDishItem dishItem = mMenuDishList.get(parentPosition).getChildList().get(childPosition);
//                    int newNum = dishItem.getQuantityInCart() + (1 * sign);
//                    ModelMapper modelMapper = new ModelMapper();
//                    ItemOrder itemOrder = modelMapper.map(dishItem, ItemOrder.class);
//                    dishItem.setQuantityInCart(newNum);
//                    if (sign == 1) {
//                        cartObj.addToCart(itemOrder, sign);
//                        if (cartObj.getTotalQuantity() == 1) {
//                            mSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
//                        }
//                        initCartView(cartObj);
//                    }
//                    if (sign == -1) {
//                        cartObj.addToCart(itemOrder, sign);
//                        if (cartObj.getTotalQuantity() == 0) {
//                            mSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
//                        }
//                        initCartView(cartObj);
//                    }
//                    item.getmTxtNumOfItem().setText(String.valueOf(newNum));
//
////                        } catch (Exception e) {
////                            Log.e(TAG, e.getMessage());
////                        }
//
//                }
//
//                @Override
//                public void onClickAddItem(StoreMenuItemAdapter.ViewHolderItem item, int parentPosition, int childPosition) {
//                    init(item, 1, parentPosition, childPosition);
//                    mStoreMenuItemAdapter.notifyDataSetChanged();
//                    //Toast.makeText(getContext(), item.getmTxtName().getText(), Toast.LENGTH_SHORT).show();
//                }
//
//                @Override
//                public void onClickMinusItem(StoreMenuItemAdapter.ViewHolderItem item, int parentPosition, int childPosition) {
//                    init(item, -1, parentPosition, childPosition);
//                    mStoreMenuItemAdapter.notifyDataSetChanged();
//                    //Toast.makeText(getContext(), item.getmTxtName().getText(), Toast.LENGTH_SHORT).show();
//                }
//
//            });
        } else {
            mItemOrderAdapter.notifyDataSetChanged();
        }
//        } catch (Exception e) {
//            Log.e(TAG, e.getMessage());
//        }

    }
}
