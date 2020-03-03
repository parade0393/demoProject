package com.parade.demoproject.view.one;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;

import com.parade.demoproject.R;
import com.parade.demoproject.util.KeyBoardUtil;

/***
 *author: parade岁月
 *date:  2020/3/2 15:01
 *description：自定义背景
 */
public class CodeInputView extends View {

    private int mWidth;
    private int mHeight;
    private int itemWidth;
    private int gapWidth;
    private int testSize;
    private int textColor = Color.CYAN;
    private int itemCount = 4;
    private Drawable itemBackground;
    private InputMode inputType;

    private StringBuilder codeBuilder;
    private Paint textPaint;
    //关键点2
    private PointF[] itemPoints;

    private OnTextChangListener listener;

    public void setListener(OnTextChangListener listener) {
        this.listener = listener;
    }

    public CodeInputView(Context context) {
        this(context, null);
    }

    public CodeInputView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CodeInputView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CodeInputView);

            itemWidth = typedArray.getDimensionPixelSize(R.styleable.CodeInputView_itemWidth, -1);
            gapWidth = typedArray.getDimensionPixelSize(R.styleable.CodeInputView_gapWidth, 10);
            itemBackground = typedArray.getDrawable(R.styleable.CodeInputView_itemBackground);
            testSize = typedArray.getDimensionPixelSize(R.styleable.CodeInputView_ciTextSize, 24);
            textColor = typedArray.getColor(R.styleable.CodeInputView_ciTextColor, textColor);
            itemCount = typedArray.getInt(R.styleable.CodeInputView_itemCount, itemCount);
            if (itemCount < 2)
                throw new IllegalArgumentException("item count must more than 1!");
            inputType = InputMode.formMode(typedArray.getInt(R.styleable.CodeInputView_ciInputType, InputMode.INPUT_TYPE_TEXT.getMode()));

            typedArray.recycle();
        }
        if (codeBuilder == null) {
            codeBuilder = new StringBuilder();
        }

        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(testSize);
        textPaint.setColor(textColor);
        textPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        textPaint.setTextAlign(Paint.Align.CENTER);//关键点1：设置画笔绘制文本时参考点的水平对齐方式
        //关键点2：
        setFocusableInTouchMode(true);//使控件获得焦点，才会触摸式弹出键盘
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        switch (mode) {
            case MeasureSpec.UNSPECIFIED:
            case MeasureSpec.AT_MOST:
                //没有指定宽度 宽度等于单个的宽度*个数+总间距
                mWidth = itemWidth * itemCount + gapWidth * (itemCount - 1);
                break;
            case MeasureSpec.EXACTLY:
                mWidth = MeasureSpec.getSize(widthMeasureSpec);
                itemWidth = mWidth - (gapWidth * (itemCount - 1)) / itemCount;
                break;
        }
        mHeight = MeasureSpec.getSize(heightMeasureSpec);

        calculateStartAndEndPoint(itemCount);
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawLine(canvas);
    }

    private void drawLine(Canvas canvas) {
        if (codeBuilder == null)
            return;

        int inputLength = codeBuilder.length();
        //画背景
        for (int i = 0; i < itemCount; i++) {
            if (itemBackground != null) {
                //绘制背景区域
                itemBackground.setBounds((int) itemPoints[i].x, 0, (int) itemPoints[i].y, mHeight);
                //关键点4：设置背景的状态，索引等于字符串长度，代表当前item处于FOCUSED状态，否则处于普通状态
                //这样可以使用xml文件Selector来的定义不同状态下的背景
                itemBackground.setState(i == inputLength ? FOCUSED_STATE_SET : EMPTY_STATE_SET);
                itemBackground.draw(canvas);//把背景画上去
            }
        }

        //画字体
        Paint.FontMetricsInt fontMetricsInt = textPaint.getFontMetricsInt();
        //关键点5：基线的y坐标(固定套路)
        int baseline = mHeight / 2 + (fontMetricsInt.bottom - fontMetricsInt.top) / 2 - fontMetricsInt.bottom;
        for (int i = 0; i < inputLength; i++) {
            if (inputType == InputMode.INPUT_TYPE_NUMBER_PASSWORD || inputType == InputMode.NPUT_TYPE_PASSWORD) {
                canvas.drawCircle(itemPoints[i].y - itemWidth / 2, mHeight / 2, testSize / 4, textPaint);
            } else {
                canvas.drawText(codeBuilder.toString(), i, i + 1, itemPoints[i].y - itemWidth / 2, baseline, textPaint);
            }
        }
    }

    private void calculateStartAndEndPoint(int textCount) {
        itemPoints = new PointF[textCount];
        for (int i = 0; i < textCount; i++) {
            itemPoints[i] = new PointF((gapWidth + itemWidth) * i, (gapWidth + itemWidth) * i + itemWidth);
            //            itemPoints[i] = new PointF(i * gapWidth + i * itemWidth, i * gapWidth + (i + 1) * itemWidth);
        }
    }

    public enum InputMode {

        INPUT_TYPE_NUMBER(0),
        INPUT_TYPE_TEXT(1),
        INPUT_TYPE_TEXT_CAP_CHARACTERS(2),
        NPUT_TYPE_PASSWORD(3),
        INPUT_TYPE_NUMBER_PASSWORD(4);

        private int mode;

        InputMode(int mode) {
            this.mode = mode;
        }

        public int getMode() {
            return mode;
        }

        static InputMode formMode(int mode) {
            for (InputMode m : values()) {
                if (mode == m.mode) {
                    return m;
                }
            }
            throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            //            requestFocus();//关键点6：只有请求获取焦点，才会弹出软键盘
            KeyBoardUtil.showKeyboard(getContext(), this);
            return true;
        }
        return false;
    }

    /**
     * //关键点7：定义软键盘类型
     * 创建一个新的InputConnection以便当前视图可以使用InputMethod，此方法默认实现返回null，
     * 因此它不支持输入法。因此如果当前视图想要使用输入法，则必须重写此方法，只有具有焦点和需要文本输入的视图
     * 才需要主动调用此方法
     * Create a new InputConnection for an InputMethod to interact
     * with the view.  The default implementation returns null, since it doesn't
     * support input methods.  You can override this to implement such support.
     * This is only needed for views that take focus and text input.
     * <p>
     * 当实现此方法后，最好也重写onCheckIsTextEditor方法来表明你将返回一个非空的InputConnection
     * <p>When implementing this, you probably also want to implement
     * {@link #onCheckIsTextEditor()} to indicate you will return a
     * non-null InputConnection.</p>
     * <p>
     * 另外，你必须指定EditorInfo的一些参数，以便系统输入法引擎可以参考这些参数来决定软键盘的信息
     * <p>Also, take good care to fill in the {@link android.view.inputmethod.EditorInfo}
     * object correctly and in its entirety, so that the connected IME(Input Method Engine) can rely
     * on its values. For example, {@link android.view.inputmethod.EditorInfo#initialSelStart}
     * and  {@link android.view.inputmethod.EditorInfo#initialSelEnd} members
     * must be filled in with the correct cursor position for IMEs to work correctly
     * with your application.</p>
     * <p>
     * 配置有关连接的属性信息
     *
     * @param outAttrs Fill in with attribute information about the connection.
     */
    @Override
    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        BaseInputConnection bic = new BaseInputConnection(this, false);
        outAttrs.actionLabel = null;
        if (inputType == InputMode.INPUT_TYPE_NUMBER) {
            outAttrs.inputType = InputType.TYPE_CLASS_NUMBER;
        } else if (inputType == InputMode.INPUT_TYPE_TEXT) {
            outAttrs.inputType = InputType.TYPE_CLASS_TEXT;
        } else if (inputType == InputMode.INPUT_TYPE_TEXT_CAP_CHARACTERS) {
            outAttrs.inputType = InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS;
        } else if (inputType == InputMode.NPUT_TYPE_PASSWORD) {
            outAttrs.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD;
        } else if (inputType == InputMode.INPUT_TYPE_NUMBER_PASSWORD) {
            outAttrs.inputType = InputType.TYPE_NUMBER_VARIATION_PASSWORD;
        }

        outAttrs.imeOptions = EditorInfo.IME_ACTION_NEXT;
        return bic;
    }

    /**
     * 关键点8
     * 检查视图是否用来文本编辑，如果是系统就会自动弹出软键盘，并且这样做是有意义的
     * 子类如果重写了onCreateInputConnection并且返回了一个非空的，那么子类也应该重写此方法
     * Check whether the called view is a text editor, in which case it
     * would make sense to automatically display a soft input window for
     * it.  Subclasses should override this if they implement
     * {@link #onCreateInputConnection(EditorInfo)} to return true if
     * a call on that method would return a non-null InputConnection, and
     * they are really a first-class editor that the user would normally
     * start typing on when the go into a window containing your view.
     * <p>
     * 默认返回false，但这并不表示onCreateInputConnection不会被调用或者使用者不可以编辑你的视图
     * 此方法只是用来告诉系统当前视图的主要目的
     * <p>The default implementation always returns false.  This does
     * <em>not</em> mean that its {@link #onCreateInputConnection(EditorInfo)}
     * will not be called or the user can not otherwise perform edits on your
     * view; it is just a hint to the system that this is not the primary
     * purpose of this view.
     *
     * @return Returns true if this view is a text editor, else false.
     */
    @Override
    public boolean onCheckIsTextEditor() {
        return true;
    }

    /**
     * 关键点9 后续研究监听软键盘的更好解决方案
     * 键盘回调函数，默认实现了ENTER键的动作
     * Default implementation of {@link KeyEvent.Callback#onKeyDown(int, KeyEvent)
     * KeyEvent.Callback.onKeyDown()}: perform press of the view
     * when {@link KeyEvent#KEYCODE_DPAD_CENTER} or {@link KeyEvent#KEYCODE_ENTER}
     * is released, if the view is enabled and clickable.
     * 按压软键盘的按键通常不会触发此监听，尽管某些情况下有些人会选择实现此方法监听软键盘的键盘监听
     * 但是不要依靠此来监听软键盘按键
     * Key presses in software keyboards will generally NOT trigger this
     * listener, although some may elect to do so in some situations. Do not
     * rely on this to catch software key presses.
     *
     * @param keyCode a key code that represents the button pressed, from
     *                {@link android.view.KeyEvent}
     * @param event   the KeyEvent object that defines the button action
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (codeBuilder == null)
            codeBuilder = new StringBuilder();
        if (keyCode == KeyEvent.KEYCODE_DEL) {
            //删除
            deleteLast();
        } else if (keyCode >= KeyEvent.KEYCODE_0 && keyCode <= KeyEvent.KEYCODE_9) {
            //纯数字
            appendText(String.valueOf(event.getNumber()));
        } else if (((inputType == InputMode.INPUT_TYPE_TEXT || inputType == InputMode.INPUT_TYPE_TEXT_CAP_CHARACTERS || inputType == InputMode.NPUT_TYPE_PASSWORD))
                && keyCode >= KeyEvent.KEYCODE_A && keyCode <= KeyEvent.KEYCODE_Z) {
            String text = String.valueOf((char) event.getUnicodeChar());
            appendText(inputType == InputMode.INPUT_TYPE_TEXT_CAP_CHARACTERS ? text.toUpperCase() : text);
        }

        if (codeBuilder.length() >= itemCount || keyCode == KeyEvent.KEYCODE_ENTER) {
            KeyBoardUtil.hideKeyboard(getContext(), this);
        }
        return super.onKeyDown(keyCode, event);
    }

    private void deleteLast() {
        if (codeBuilder == null || codeBuilder.length() == 0)
            return;
        codeBuilder.deleteCharAt(codeBuilder.length() - 1);
        if (listener != null) {
            listener.afterTextChanged(this,codeBuilder.toString());
        }
        invalidate();//重绘
    }

    private void appendText(String text) {
        if (codeBuilder == null)
            codeBuilder = new StringBuilder();
        if (codeBuilder.length() >= itemCount)
            return;
        codeBuilder.append(text);
        if (listener != null) {
            listener.afterTextChanged(this,codeBuilder.toString());
        }

        invalidate();
    }


    public interface OnTextChangListener {
        void afterTextChanged(View view,String text);
    }

    public boolean isComplete() {
        return codeBuilder != null && codeBuilder.length() == itemCount;
    }

    public String getText() {
        return codeBuilder == null ? "" : codeBuilder.toString();
    }
}
