package com.parade.demoproject.view.one;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/***
 *author: parade岁月
 *date:  2020/2/18 15:59
 *description：登录按钮
 */
public class LoginButton extends View {

    int width;
    int height;

    int resetWidth;
    int resetHeight;

    float circleAngle; //圆形矩形的角度大小
    int default_move_distance; //默认需要移动的距离
    int actual_move_distance; //实际需要移动的距离
    int bg_color = Color.parseColor("#EE6383");
    String btnString = "登录";
    int rect_to_angle_duration = 500;
    int rect_to_circle_duration = 1000;
    private Paint paint;
    private Paint textPaint;
    private Paint smilePaint;
    boolean isCircle = false; //是否开始画圆弧
    boolean isEye = false; //是否开始画眼睛
    int startAngle;
    int point_move_up_distance;

    RectF rectf = new RectF();
    RectF textRect = new RectF(); //放文字的矩形
    RectF arcRectf = new RectF(); //笑脸弧度

    AnimatorSet animatorSet = new AnimatorSet();
    ValueAnimator animator_rect_to_angle;
    ValueAnimator animator_rect_to_central_circle;
    ValueAnimator animator_arc_to_rotate;
    ValueAnimator animator_point_move_up;


    OnAnimationButtonClickListener onAnimationButtonClickListener;


    public LoginButton(Context context) {
        super(context);
        //        initPaint();
    }

    public LoginButton(Context context,  AttributeSet attrs) {
        super(context, attrs);
        initPaint();

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onAnimationButtonClickListener!=null){
                    onAnimationButtonClickListener.onAnimationStart();
                }
            }
        });

        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                if (onAnimationButtonClickListener!=null){
                    onAnimationButtonClickListener.onAnimationFinish();
                }
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                if (onAnimationButtonClickListener!=null){
                    onAnimationButtonClickListener.onAnimationCancel();
                }
            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }

    public LoginButton(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //        initPaint();
    }

    public void setAnimationButtonListener(OnAnimationButtonClickListener onAnimationButtonClickListener){
        this.onAnimationButtonClickListener = onAnimationButtonClickListener;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        width = resetWidth = w;
        height = resetHeight = h;

        default_move_distance = (w - h) / 2;

        initAnimation();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        draw_oval_to_circle(canvas);
        drawText(canvas);

        if (isCircle){
            draw_arc_to_smile(canvas);
        }
        if (isEye){
            draw_point_move_up(canvas);
        }

    }




    private void initPaint(){

        paint = new Paint();
        paint.setStrokeWidth(4);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setColor(bg_color);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(40);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setAntiAlias(true);

        smilePaint = new Paint();
        smilePaint.setStyle(Paint.Style.STROKE);
        smilePaint.setColor(Color.WHITE);
        smilePaint.setStrokeWidth(4);
        smilePaint.setAntiAlias(false);


    }

    private void initAnimation(){
        setAnimator_rect_to_angle();
        setAnimator_rect_to_central_circle();
        setAnimator_arc_to_rotate();
        setAnimator_point_move_up();

        animatorSet
                .play(animator_rect_to_central_circle)
                .before(animator_arc_to_rotate)
                .before(animator_point_move_up)
                .after(animator_rect_to_angle);
    }

    /**
     * 设置矩形变成弧度矩形的动画
     */
    private void setAnimator_rect_to_angle(){
        animator_rect_to_angle = ValueAnimator.ofFloat(0,height/2);
        animator_rect_to_angle.setDuration(rect_to_angle_duration);
        animator_rect_to_angle.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                circleAngle = (float) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
    }


    /**
     * 设置弧度矩形到终点的动画
     */
    private void setAnimator_rect_to_central_circle(){
        animator_rect_to_central_circle = ValueAnimator.ofInt(0,default_move_distance);
        animator_rect_to_central_circle.setDuration(rect_to_circle_duration);
        animator_rect_to_central_circle.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {

                actual_move_distance = (int) valueAnimator.getAnimatedValue();

                int alpha = 255 - (actual_move_distance * 255) / default_move_distance;

                textPaint.setAlpha(alpha);

                if (actual_move_distance>=default_move_distance){
                    isCircle = true;
                }

                invalidate();
            }
        });
    }

    /**
     * 加载旋转动画
     */
    private void setAnimator_arc_to_rotate(){
        animator_arc_to_rotate = ValueAnimator.ofInt(0,1440);
        animator_arc_to_rotate.setDuration(2000);
        animator_arc_to_rotate.setInterpolator(new LinearInterpolator());
        animator_arc_to_rotate.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                startAngle = (int) valueAnimator.getAnimatedValue();
                if (startAngle+1>=1440){
                    isEye = true;
                }else{
                    isEye = false;
                }
                invalidate();
            }
        });
    }

    private void setAnimator_point_move_up(){
        animator_point_move_up = ValueAnimator.ofInt(0,20);
        animator_point_move_up.setDuration(1500);
        animator_point_move_up.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                point_move_up_distance = (int) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
    }

    /**
     * 绘制长方形变成圆形
     *
     * @param canvas 画布
     */
    private void draw_oval_to_circle(Canvas canvas) {

        rectf.left = actual_move_distance;
        rectf.top = 0;
        rectf.right = width - actual_move_distance;
        rectf.bottom = height;

        //画圆角矩形
        canvas.drawRoundRect(rectf, circleAngle, circleAngle, paint);

    }

    /**
     * 圆弧旋转最后转为下巴
     * @param canvas
     */
    private void draw_arc_to_smile(Canvas canvas){

        arcRectf.left = width/2-height/4;
        arcRectf.right = width/2+height/4;
        arcRectf.top = height/4;
        arcRectf.bottom = height*3/4;

        canvas.drawArc(arcRectf,startAngle,180,false,smilePaint);
    }

    /**
     * 笑脸眼睛上移
     * @param canvas
     */
    private void draw_point_move_up(Canvas canvas){
        int pointLeftX = width/2-height/4;
        int pointRightX = width/2+height/4;
        canvas.drawPoint(pointLeftX+10,height/2-point_move_up_distance,smilePaint);
        canvas.drawPoint(pointRightX-10,height/2-point_move_up_distance,smilePaint);
    }


    /**
     * 绘制文字
     * @param canvas 画布
     */
    private void drawText(Canvas canvas) {
        textRect.left = 0;
        textRect.top = 0;
        textRect.right = width;
        textRect.bottom = height;
        Paint.FontMetricsInt fontMetrics = textPaint.getFontMetricsInt();
        int baseline = (int) ((textRect.bottom + textRect.top - fontMetrics.bottom - fontMetrics.top) / 2);
        //文字绘制到整个布局的中心位置
        canvas.drawText(btnString, textRect.centerX(), baseline, textPaint);
    }

    /**
     * 开启动画
     */
    public void start(){
        animatorSet.start();
    }

    /**
     * 取消动画
     */
    public void reset(){
        animatorSet.end();
        resetWH();
    }


    public void resetWH(){

        isCircle = false;
        isEye = false;

        width = resetWidth;
        height = resetHeight;
        actual_move_distance = 0;
        circleAngle = 0;
        textPaint.setAlpha(255);
        invalidate();

    }


    public interface OnAnimationButtonClickListener{

        void onAnimationStart(); //动画开始

        void onAnimationFinish(); //动画完成

        void onAnimationCancel(); //动画取消
    }
}
