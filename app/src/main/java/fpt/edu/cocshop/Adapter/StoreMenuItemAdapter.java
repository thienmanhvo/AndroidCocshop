package fpt.edu.cocshop.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import fpt.edu.cocshop.Model.Brand;
import fpt.edu.cocshop.Model.Menu;
import fpt.edu.cocshop.Model.MenuItem;
import fpt.edu.cocshop.R;

public class StoreMenuItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    public interface OnStoreMenuListener {
        void onClick(MenuItem brand);
    }

    private Context mContext;
    private List<Object> mListItem;
    private StoreMenuItemAdapter.OnStoreMenuListener mOnStoreMenuClickListener;

    public void setmOnFoodPicksClickListener(StoreMenuItemAdapter.OnStoreMenuListener mOnStoreMenuClickListener) {
        this.mOnStoreMenuClickListener = mOnStoreMenuClickListener;
    }

    public StoreMenuItemAdapter(Context mContext, List<Object> mListItem) {
        this.mContext = mContext;
        this.mListItem = mListItem;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == TYPE_HEADER) {
            View v = inflater.inflate(R.layout.item_menu_header, parent, false);
            v.setBackgroundColor(Color.argb(100,200, 200, 200));
            return new ViewHolderHeader(v);
        } else {
            View v = inflater.inflate(R.layout.item_menu_list, parent, false);
            return new ViewHolderItem(v);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolderHeader) {
            // VHHeader VHheader = (VHHeader)holder;
            Menu currentItem = (Menu) mListItem.get(position);
            ViewHolderHeader vHHeader = (ViewHolderHeader) holder;
            vHHeader.mTxtName.setText(currentItem.getName());
        } else if (holder instanceof ViewHolderItem) {
            MenuItem currentItem = (MenuItem) mListItem.get(position);
            ViewHolderItem VHitem = (ViewHolderItem) holder;
            VHitem.mTxtName.setText(currentItem.getName());
            VHitem.mTxtPrice.setText(String.valueOf(currentItem.getPrice()));
            VHitem.mTxtPriceOld.setText(String.valueOf(currentItem.getPrice()));
            Picasso.get()
                    .load(currentItem.getImagePath())
                    .error(R.drawable.ic_launcher_background)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(((ViewHolderItem) holder).mImgDescription, new Callback() {
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

    @Override
    public int getItemCount() {
        return mListItem != null ? mListItem.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;
        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return mListItem.get(position) instanceof Menu;
    }

    protected class ViewHolderHeader extends RecyclerView.ViewHolder {

        private TextView mTxtName;

        public ViewHolderHeader(@NonNull View itemView) {
            super(itemView);
            mTxtName = itemView.findViewById(R.id.txt_menu_name);
        }

    }

    protected class ViewHolderItem extends RecyclerView.ViewHolder {
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

    }
}
