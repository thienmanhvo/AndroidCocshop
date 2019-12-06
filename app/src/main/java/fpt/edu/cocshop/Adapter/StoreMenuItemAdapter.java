package fpt.edu.cocshop.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bignerdranch.expandablerecyclerview.ChildViewHolder;
import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.ParentViewHolder;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import fpt.edu.cocshop.Model.Brand;
import fpt.edu.cocshop.Model.Menu;
import fpt.edu.cocshop.Model.MenuItem;
import fpt.edu.cocshop.R;

public class StoreMenuItemAdapter extends ExpandableRecyclerAdapter<Menu, MenuItem, StoreMenuItemAdapter.ViewHolderHeader, StoreMenuItemAdapter.ViewHolderItem> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private LayoutInflater mInflater;

    public interface OnStoreMenuListener {
        void onClickToggleMenuItem(int position);

        void onClickToggleMenuItemShow(int position);
    }

    private Context mContext;
    private List<Menu> mListItem;
    private OnStoreMenuListener mOnStoreMenuClickListener;

    public void setmOnStoreMenuClickListener(OnStoreMenuListener mOnStoreMenuClickListener) {
        this.mOnStoreMenuClickListener = mOnStoreMenuClickListener;
    }

    public StoreMenuItemAdapter(Context mContext, List<Menu> mListItem) {
        super(mListItem);
        this.mContext = mContext;
        this.mListItem = mListItem;
        mInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public ViewHolderHeader onCreateParentViewHolder(@NonNull ViewGroup parentViewGroup, int viewType) {
        View v = mInflater.inflate(R.layout.item_menu_header, parentViewGroup, false);
        return new ViewHolderHeader(v);
    }

    @NonNull
    @Override
    public ViewHolderItem onCreateChildViewHolder(@NonNull ViewGroup childViewGroup, int viewType) {
        View v = mInflater.inflate(R.layout.item_menu_list, childViewGroup, false);
        return new ViewHolderItem(v);
    }

    @Override
    public void onBindParentViewHolder(@NonNull ViewHolderHeader parentViewHolder, int parentPosition, @NonNull Menu parent) {
        parentViewHolder.bind(parent, parentPosition);
    }

    @Override
    public void onBindChildViewHolder(@NonNull ViewHolderItem childViewHolder, int parentPosition, int childPosition, @NonNull MenuItem child) {
        childViewHolder.bind(child);
    }

    protected class ViewHolderHeader extends ParentViewHolder {
        private ImageView mImgToggle, mImgShowItem;
        private TextView mTxtName;
        private RelativeLayout mRlHeader;

        public ViewHolderHeader(@NonNull View itemView) {
            super(itemView);
            mTxtName = itemView.findViewById(R.id.txt_menu_name);
            mImgToggle = itemView.findViewById(R.id.btn_store_toggle_menu);
            mImgShowItem = itemView.findViewById(R.id.btn_store_show_item);
            mRlHeader = itemView.findViewById(R.id.rl_store_menu);
        }

        public void bind(Menu menu, final int position) {
            mTxtName.setText(menu.getName());
            mRlHeader.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (isExpanded()) {
                        mImgToggle.setImageResource(R.drawable.ic_navigate_next);
                        collapseView();
                    } else {
                        mImgToggle.setImageResource(R.drawable.ic_keyboard_arrow_up);
                        expandView();
                    }
                    //mOnStoreMenuClickListener.onClickToggleMenuItem(position);
                }
            });
        }

        @Override
        public boolean shouldItemViewClickToggleExpansion() {
            return false;
        }
    }

    public class ViewHolderItem extends ChildViewHolder {
        private ImageView mImgDescription;
        private TextView mTxtName;
        private TextView mTxtPriceOld, mTxtPrice;

        public ViewHolderItem(@NonNull View itemView) {
            super(itemView);
            mImgDescription = itemView.findViewById(R.id.img_menu_item_avatar);
            mTxtName = itemView.findViewById(R.id.txt_menu_item_name);
            mTxtPrice = itemView.findViewById(R.id.txt_menu_item_price);
            mTxtPriceOld = itemView.findViewById(R.id.txt_menu_item_price_old);
            mTxtPriceOld.setPaintFlags(mTxtPriceOld.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }

        public void bind(MenuItem item) {
            mTxtName.setText(item.getName());
            mTxtPrice.setText(String.valueOf(item.getPrice()));
            mTxtPriceOld.setText(String.valueOf(item.getPrice()));
            Picasso.get()
                    .load(item.getImagePath())
                    .error(R.drawable.ic_launcher_background)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(mImgDescription, new Callback() {
                        @Override
                        public void onSuccess() {
                        }

                        @Override
                        public void onError(Exception e) {
                            Log.e("PICASSO", e.getMessage());
                        }
                    });
        }

    }
}
