package com.hencoder.hencoderpracticedraw2.practice;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.SumPathEffect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice12PathEffectView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Path path = new Path();

    public Practice12PathEffectView(Context context) {
        super(context);
    }

    public Practice12PathEffectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice12PathEffectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        paint.setStyle(Paint.Style.STROKE);

        path.moveTo(50, 100);
        path.rLineTo(50, 100);
        path.rLineTo(80, -150);
        path.rLineTo(100, 100);
        path.rLineTo(70, -120);
        path.rLineTo(150, 80);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 使用 Paint.setPathEffect() 来设置不同的 PathEffect

        //1
        PathEffect pathEffect = new CornerPathEffect(50);
        paint.setPathEffect(pathEffect);

        // 第一处：CornerPathEffect
        canvas.drawPath(path, paint);

        canvas.save();
        canvas.translate(500, 0);

        //2
        pathEffect = new DiscretePathEffect(10f, 10f);
        paint.setPathEffect(pathEffect);

        // 第二处：DiscretePathEffect
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(0, 200);

        //3
        pathEffect = new DashPathEffect(new float[]{10, 5}, 5);
        paint.setPathEffect(pathEffect);

        // 第三处：DashPathEffect
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(500, 200);

        //4
        Path path1 = new Path();
        path1.addCircle(50, 50, 10, Path.Direction.CW);
        pathEffect = new PathDashPathEffect(path1, 50, 0, PathDashPathEffect.Style.TRANSLATE);
        paint.setPathEffect(pathEffect);

        // 第四处：PathDashPathEffect
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(0, 400);
        //5
        paint.setPathEffect(new SumPathEffect(
                new DiscretePathEffect(10f, 10f),
                new DashPathEffect(new float[]{10, 5}, 5)));

        // 第五处：SumPathEffect
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(500, 400);

        //6
        paint.setPathEffect(new ComposePathEffect(
                new DiscretePathEffect(20f, 20f),
                new DashPathEffect(new float[]{10, 5}, 5)));

        // 第六处：ComposePathEffect
        canvas.drawPath(path, paint);
        canvas.restore();
    }
}
