package com.wj.learnmvi.weight;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by client on 15-11-27.
 * <p>
 * ViewGroup and View:
 * ViewGroup responsible for child's location
 * View responsible for self draw
 */
public class FlowLayout extends ViewGroup {

    private static final String TAG = "logcatFlowLayout";
    private List<List<View>> mAllViews = new ArrayList<>();
    private List<Integer> mLineHeight = new ArrayList<>();
    private boolean mEdge;

    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }


    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 1.generate a LayoutParams for child
     * 2.child will get this Params from getLayoutParmas
     */
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    /**
     * 1.measure every child's size and caculate self size
     * 2.measure child's size through child's measure(also:parent tell child to measure self)
     * 3.
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int sizeWith = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

        int width = 0;
        int height = 0;
        int lineWidth = 0;
        int lineHeight = 0;
        final int childCount = getChildCount();
        final int childrenWidth = sizeWith - getPaddingLeft() - getPaddingRight();

        List<View> lineViews = new ArrayList<>();
        mAllViews.clear();
        mLineHeight.clear();

        for (int i = 0; i < childCount; i++) {

            View child = getChildAt(i);
            if (child.getVisibility() == GONE) {
                continue;
            }
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();

            int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
            if (lineWidth + childWidth > childrenWidth) {

                width = Math.max(lineWidth, childWidth);
                height += lineHeight;

                mAllViews.add(lineViews);
                lineViews = new ArrayList<>();
                mLineHeight.add(lineHeight);
                lineHeight = childHeight;
                lineWidth = 0;
            }

            lineWidth += childWidth;
            lineHeight = Math.max(lineHeight, childHeight);
            lineViews.add(child);
        }
        width = Math.max(width, lineWidth);
        height += lineHeight + getPaddingTop() + getPaddingBottom();
        mAllViews.add(lineViews);
        mLineHeight.add(lineHeight);

        setMeasuredDimension(modeWidth == MeasureSpec.EXACTLY ? sizeWith : width,
                modeHeight == MeasureSpec.EXACTLY ? sizeHeight : height);
    }

    /**
     * put child at suitable position and tell to layout self
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int lineHeight = 0;
        final int leftStart = getPaddingLeft();
        int left = leftStart;
        int top = getPaddingTop();

        List<View> lineViews;
        final int lineNums = mAllViews.size();
        for (int i = 0; i < lineNums; i++) {

            lineViews = mAllViews.get(i);
            lineHeight = mLineHeight.get(i);
            final int itemCount = lineViews.size();
            for (int j = 0; j < itemCount; j++) {

                View child = lineViews.get(j);
                if (child.getVisibility() == View.GONE) {
                    continue;
                }
                MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
                int lc = left + ((j == 0 && mEdge) ? 0 : lp.leftMargin);
                int rc = lc + child.getMeasuredWidth();
                int tc = top + lp.topMargin;
                int bc = tc + child.getMeasuredHeight();

                child.layout(lc, tc, rc, bc);
                left = rc + lp.rightMargin;

            }
            left = leftStart;
            top += lineHeight;
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    /**
     * 是否边不处理边距
     *
     * @param edge
     */
    public void isEdge(boolean edge) {
        this.mEdge = edge;
    }


}
