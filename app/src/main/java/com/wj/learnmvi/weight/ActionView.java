package com.wj.learnmvi.weight;

/**
 * Author:WJ
 * Date:2024/1/18 9:42
 * Describe：
 */
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.View;

import com.wj.learnmvi.bean.Action;

import java.util.List;

public class ActionView extends View {
   public static final int TEXT_TYPE = 1;
   public static final int INPUT_TYPE = 2;

    private List<Action> actions;

    public ActionView(Context context) {
        super(context);
    }

    public ActionView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ActionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (actions == null) return;

        Paint paint = new Paint();
        paint.setTextSize(30);
        paint.setAntiAlias(true);
        paint.setColor(getResources().getColor(android.R.color.black));

        float x = 0;
        float y = 0;

        for (Action action : actions) {
            switch (action.getType()) {
                case TEXT_TYPE:
                    canvas.drawText(action.getText(), x, y, paint);
                    y += paint.getFontSpacing();
                    break;
                case INPUT_TYPE:
                    canvas.drawRect(x, y, x + 200, y + 2, paint);
                    y += 10;
                    break;
            }
        }
    }
}
