package com.zwy.kutils.widget.loadingdialog.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class DividerItemDecoration extends RecyclerView.ItemDecoration {


    /**
     * 画笔
     */
    private Paint mPaint;
    /**
     * 分割线drawable图
     */
    private Drawable mDivider;
    /**
     * 分割线默认高度 默认为1px
     */
    private int mDividerHeight = 1;
    /**
     * 分割线方向 true 为竖向false为横向
     * LinearLayoutManager.VERTICAL或LinearLayoutManager.HORIZONTAL
     */
    private boolean isVertical;
    /**
     * 是否底部添加分割线
     */
    private boolean isMarginTopShow;
    /**
     * 是否中间内容添加分割线 默认显示
     */
    private boolean isMiddleShow = true;
    /**
     * 是否底部添加分割线
     */
    private boolean isMarginBottomshow;
    /**
     * 分割线属性 我们通过获取系统属性中的listDivider来添加，在系统中的AppTheme中设置
     */
    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};


    /**
     * 默认分割线：高度为1px，颜色为灰色,可以通过style的 底部和顶部不显示
     * <item name="android:listDivider">@drawable/shape_divider_bg</item>
     * 修改颜色
     *
     * @param context
     * @param isVertical 列表方向
     */
    public DividerItemDecoration(Context context, boolean isVertical) {
        this(context, 0, isVertical);
    }

    /**
     * 自定义分割线 底部和顶部不显示
     *
     * @param context
     * @param isVertical 列表方向
     * @param drawableId 分割线图片
     */
    public DividerItemDecoration(Context context, boolean isVertical, int drawableId) {
        this(context, isVertical, drawableId, false, true, false);
    }

    /**
     * 默认分割线：高度为1px，颜色为灰色 底部和顶部不显示
     *
     * @param context
     * @param isVertical 列表方向
     */
    public DividerItemDecoration(Context context, int dividerHeight, boolean isVertical) {
        this(context, dividerHeight, isVertical, false, true, false);
    }

    /**
     * 自定义分割线 底部和顶部不显示
     *
     * @param context
     * @param isVertical    列表方向
     * @param dividerHeight 分割线高度
     * @param dividerColor  分割线颜色
     */
    public DividerItemDecoration(Context context, int dividerHeight, boolean isVertical, int dividerColor) {
        this(context, dividerHeight, isVertical, dividerColor, false, true, false);
    }

    /**
     * 自定义分割线
     *
     * @param context
     * @param isVertical         列表方向
     * @param isMarginTopShow    第一个条item顶部是否需要分割线
     * @param isMarginBottomshow 最后一个条item底部是否需要分割线
     */
    public DividerItemDecoration(Context context, int dividerHeight, boolean isVertical, boolean isMarginTopShow, boolean isMarginBottomshow) {
        this(context, dividerHeight, isVertical, isMarginTopShow, true, isMarginBottomshow);
    }

    /**
     * 自定义分割线
     *
     * @param context
     * @param isVertical         列表方向
     * @param drawableId         分割线图片
     * @param isMarginTopShow    第一个条item顶部是否需要分割线
     * @param isMiddleShow       item列表中间是否需要分割线
     * @param isMarginBottomshow 最后一个条item底部是否需要分割线
     */
    public DividerItemDecoration(Context context, boolean isVertical, int drawableId, boolean isMarginTopShow, boolean isMiddleShow, boolean isMarginBottomshow) {
        this(context, 0, isVertical, isMarginTopShow, isMiddleShow, isMarginBottomshow);
        mDivider = ContextCompat.getDrawable(context, drawableId);
        this.mDividerHeight = mDivider.getIntrinsicHeight();
    }

    /**
     * 默认分割线：高度为1px，颜色为灰色
     *
     * @param context
     * @param isVertical         列表方向
     * @param isMarginTopShow    第一个条item顶部是否需要分割线
     * @param isMiddleShow       item列表中间是否需要分割线
     * @param isMarginBottomshow 最后一个条item底部是否需要分割线
     */
    public DividerItemDecoration(Context context, int dividerHeight, boolean isVertical, boolean isMarginTopShow, boolean isMiddleShow, boolean isMarginBottomshow) {
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();
        this.isVertical = isVertical;
        if (dividerHeight == 0) {
            mDivider.getIntrinsicHeight();
        } else {
            this.mDividerHeight = dividerHeight;
        }
        this.isMarginTopShow = isMarginTopShow;
        this.isMiddleShow = isMiddleShow;
        this.isMarginBottomshow = isMarginBottomshow;
    }

    /**
     * 自定义分割线
     *
     * @param context
     * @param isVertical         列表方向
     * @param dividerHeight      分割线高度
     * @param dividerColor       分割线颜色
     * @param isMarginTopShow    第一个条item顶部是否需要分割线
     * @param isMiddleShow       item列表中间是否需要分割线
     * @param isMarginBottomshow 最后一个条item底部是否需要分割线
     */
    public DividerItemDecoration(Context context, int dividerHeight, boolean isVertical, int dividerColor, boolean isMarginTopShow, boolean isMiddleShow, boolean isMarginBottomshow) {
        this(context, dividerHeight, isVertical, isMarginTopShow, isMiddleShow, isMarginBottomshow);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(dividerColor);
        mPaint.setStyle(Paint.Style.FILL);
    }


    /**
     * 绘制分割线
     * 此方法需要我们计算出绘制的分割线的【位置和范围】，并绘制在Canvas上。主要的逻辑就是通过parent获取到child，然后从child中获取到Item的四个边的位置，从而计算出位置和范围。
     */
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if (isVertical) {
            drawVertical(c, parent);
        } else {
            drawHorizontal(c, parent);
        }
    }


    /**
     * 预留分割线的空间，
     * 主要是为了在每个Item的某一位置预留出分割线的空间 ，
     * 从而让Decoration绘制在预留的空间内，
     * 注意这里只是预留了空间还没有绘制内容的
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        /**
         *  outRect.set(left, top, right, bottom);
         *  在Item的四周设定距离
         *  所以当Orientation为垂直时，我们只需要在每个Item的下方预留出分割线的高度就可以了
         *  同理当Orientation为水平时，我们只需要在每个Item的右方预留出分割线的宽度就可以了
         *  但通常我们使用分割线的style都是统一的，这样我们在attrs中只需要定义一个即可，即共同使用Height
         */
        super.getItemOffsets(outRect, view, parent, state);
        /**item位置*/
        int position = parent.getChildAdapterPosition(view);
        /**item总数*/
        int count = parent.getAdapter().getItemCount();
        /**列表方向*/
        if (isVertical) {
            /**竖向列表*/
            if (position == 0) {
                if (isMarginTopShow) {
                    /**第一个view，可以在顶部加一个margin值*/
                    outRect.set(0, mDividerHeight, 0, mDividerHeight);
                } else {
                    if (isMiddleShow) {
                        outRect.set(0, 0, 0, mDividerHeight);
                    }
                }
            } else if (position >= count - 1) {
                if (isMarginBottomshow) {
                    /**最后一个view，可以在底部加一个margin值*/
                    outRect.set(0, 0, 0, mDividerHeight);
                }
            } else {
                if (isMiddleShow) {
                    /**画横线，就是往下偏移一个分割线的高度*/
                    outRect.set(0, 0, 0, mDividerHeight);
                }
            }
        } else {
            /**横向列表*/
            if (position == 0) {
                if (isMarginTopShow) {
                    /**第一个view，可以在顶部加一个margin值*/
                    outRect.set(mDividerHeight, 0, mDividerHeight, 0);
                } else {
                    if (isMiddleShow) {
                        outRect.set(0, 0, mDividerHeight, 0);
                    }
                }
            } else if (position >= count - 1) {
                if (isMarginBottomshow) {
                    /**最后一个view，可以在底部加一个margin值*/
                    outRect.set(0, 0, mDividerHeight, 0);
                }
            } else {
                if (isMiddleShow) {
                    /**画竖线，就是往右偏移一个分割线的宽度*/
                    outRect.set(0, 0, mDividerHeight, 0);
                }
            }
        }
    }

    /**
     * 绘制横向 item 分割线 这里的parent其实是显示在屏幕显示的这部分
     */
    private void drawHorizontal(Canvas canvas, RecyclerView parent) {
        /**
         当orientation为 Horizontal 时，Item的分割线为多条竖直的条形
         所以，分割线的Top和Bottom就比较容易确定
         top = parent.top = parent.paddingTop
         bottom = parent.getHeight() - parent.getPaddingBottom()
         分割线的 left 和 right 则需要计算出有多少个Item
         根据Item的位置获取到child的位置坐标
         所以分割线的left = child的右边的坐标 + child的外边距的距离
         left = child.right + parms.rightMargin
         然后根据左边 + 分割线的宽度 得到右边的坐标
         right = left + mDivider.getIntrinsicHeight()
         为了统一分割线的间隔，故共同使用Height的数值作为间隔的距离
         */
        final int top = parent.getPaddingTop();
        final int bottom = parent.getHeight() - parent.getPaddingBottom();
        final int childSize = parent.getChildCount();
        for (int i = 0; i < childSize; i++) {
            final View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = child.getRight() + layoutParams.rightMargin;
            final int right = left + mDividerHeight;

            /**横向列表*/
            if (i == 0) {
                if (isMarginTopShow) {
                    /**第一个view，可以在顶部加一个margin值*/
                    int leftF = child.getLeft() - layoutParams.leftMargin - mDividerHeight;
                    int rightF = child.getLeft() - layoutParams.leftMargin;
                    if (mDivider != null) {
                        mDivider.setBounds(leftF, top, rightF, bottom);
                        mDivider.draw(canvas);
                        if (isMiddleShow) {
                            mDivider.setBounds(left, top, right, bottom);
                            mDivider.draw(canvas);
                        }
                    }
                    if (mPaint != null) {
                        canvas.drawRect(leftF, top, rightF, bottom, mPaint);
                        if (isMiddleShow) {
                            canvas.drawRect(left, top, right, bottom, mPaint);
                        }
                    }
                } else {
                    if (isMiddleShow) {
                        if (mDivider != null) {
                            mDivider.setBounds(left, top, right, bottom);
                            mDivider.draw(canvas);
                        }
                        if (mPaint != null) {
                            canvas.drawRect(left, top, right, bottom, mPaint);
                        }
                    }
                }
            } else {
                if (isMiddleShow || i >= childSize - 1) {
                    /**画竖线，就是往右偏移一个分割线的宽度*/
                    if (mDivider != null) {
                        mDivider.setBounds(left, top, right, bottom);
                        mDivider.draw(canvas);
                    }
                    if (mPaint != null) {
                        canvas.drawRect(left, top, right, bottom, mPaint);
                    }
                }
            }


        }

    }

    /**
     * 绘制纵向 item 分割线 这里的parent其实是显示在屏幕显示的这部分
     */
    private void drawVertical(Canvas canvas, RecyclerView parent) {
        /**
         当orientation为 Vertical 时，Item的分割线为多条水平的条形
         所以，分割线的Left和Right就比较容易确定
         Left = parent.left = parent.paddingLeft
         right = parent.getWidth() - parent.getPaddingRight
         分割线的 Top 和 Bottom 则需要计算出有多少个Item
         根据Item的位置获取到child的位置坐标
         所以分割线的Top = child的下边的坐标 + child的外边距的距离
         top = child.getBottom() + parms.bottomMargin
         然后根据上边 + 分割线的高度 得到右边的坐标
         bottom = top + mDivider.getIntrinsicHeight()
         为了统一分割线的间隔，故共同使用Height的数值作为间隔的距离
         */
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();
        final int childSize = parent.getChildCount();
        for (int i = 0; i < childSize; i++) {
            final View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getBottom() + layoutParams.bottomMargin;
            final int bottom = top + mDividerHeight;
            if (i == 0) {
                if (isMarginTopShow) {
                    /**第一个view，可以在顶部加一个margin值*/
                    int topF = child.getTop() - layoutParams.topMargin - mDividerHeight;
                    int bottomF = child.getTop() - layoutParams.topMargin;
                    if (mDivider != null) {
                        mDivider.setBounds(left, topF, right, bottomF);
                        mDivider.draw(canvas);
                        if (isMiddleShow) {
                            mDivider.setBounds(left, top, right, bottom);
                            mDivider.draw(canvas);
                        }
                    }
                    if (mPaint != null) {
                        canvas.drawRect(left, topF, right, bottomF, mPaint);
                        if (isMiddleShow) {
                            canvas.drawRect(left, top, right, bottom, mPaint);
                        }
                    }
                } else {
                    if (isMiddleShow) {
                        if (mDivider != null) {
                            mDivider.setBounds(left, top, right, bottom);
                            mDivider.draw(canvas);
                        }
                        if (mPaint != null) {
                            canvas.drawRect(left, top, right, bottom, mPaint);
                        }
                    }
                }
            } else {
                if (isMiddleShow || i >= childSize - 1) {
                    if (mDivider != null) {
                        mDivider.setBounds(left, top, right, bottom);
                        mDivider.draw(canvas);
                    }
                    if (mPaint != null) {
                        canvas.drawRect(left, top, right, bottom, mPaint);
                    }
                }
            }
        }
    }

}