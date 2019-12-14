package fpt.edu.cocshop.Custom;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class CustomDecoration extends RecyclerView.ItemDecoration {

//    @Override
////    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
////        // only for the last one
////        if (parent.getChildAdapterPosition(view) == parent.getAdapter().getItemCount() - 1) {
////            outRect.bottom = 0/* set your margin here */;
////        }
////    }

    private Drawable mDivider;

    public CustomDecoration(Drawable divider) {
        mDivider = divider;
    }

    @Override
    public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        int dividerLeft = parent.getPaddingLeft();
        int dividerRight = parent.getWidth() - parent.getPaddingRight();

        int childCount = parent.getChildCount();

        View child = parent.getChildAt(childCount - 1);

        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

        int dividerTop = child.getBottom() + params.bottomMargin;
        int dividerBottom = dividerTop + mDivider.getIntrinsicHeight();

        mDivider.setBounds(dividerLeft, dividerTop, dividerRight, dividerBottom);
        mDivider.draw(canvas);

    }
}