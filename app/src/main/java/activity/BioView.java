package activity;

/**
 * Created by mando on 2018-02-26.
 */
        import android.view.View;
        import android.content.Context;
        import android.util.AttributeSet;
        import android.graphics.Canvas;
        import android.graphics.Paint;
        import android.graphics.Rect;
        import android.util.Log;

        import java.util.Date;

public class BioView extends View {
    /** 상수 */
    private static final int mMaxDays = 30;
    private static final int mMaxType = 3;
    private static final long mTDV = (60*60*24*1000);
    private static final double mPI = 3.14159;

    private static final double mBioValues[] = { 23.0, 28.0, 33.0 };
    private static final int mColors[] = { 0xff0000ff, 0xffff00ff, 0xff00ffff };

    /** 멤버변수 */
    private Paint mPaint;
    private Rect mRect;
    private double mStartDays;
    private Date mBirthDate, mTodayDate;

    public BioView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mRect = new Rect();
        mPaint = new Paint();

        mRect.top = 0;
        mRect.bottom = getWidth();
        mRect.left = 0;
        mRect.right = getHeight();

        mTodayDate = new Date();
        Date startDate = new Date(mTodayDate.getYear(), mTodayDate.getMonth(), 1);

        mStartDays = startDate.getTime()/mTDV;
        mBirthDate = new Date();

        mBirthDate.setYear(0);
    }

    public void setBirthDay(int year, int month, int day) {
        mBirthDate.setYear(year);
        mBirthDate.setMonth(month);
        mBirthDate.setDate(day);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int cellWidth = getWidth()/mMaxDays;

        mRect.top = 0;
        mRect.bottom = getWidth();
        mRect.left = 0;
        mRect.right = getHeight();

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(0xFFFFFFFF);

        int x = 0, y = 0, oldY = 0;

        // 세로줄 출력
        for (int i = 0; i <= mMaxDays; i++) {
            x += cellWidth;
            canvas.drawLine(x, mRect.top, x, mRect.bottom, mPaint);
        }

        // 가로줄 출력
        canvas.drawLine(0, mRect.bottom/2, mRect.right, mRect.bottom/2, mPaint);

        // 오늘 날짜 출력
        mPaint.setColor(0xFFFFFF00);
        x = cellWidth * mTodayDate.getDate();
        canvas.drawLine(x, mRect.top, x, mRect.bottom, mPaint);

        // 바이오리듬 출력
        if (mBirthDate.getYear() != 0) {
            Log.e("LOG", "year:" + mBirthDate.getYear() +
                    "month:" + mBirthDate.getMonth() +
                    "day:" + mBirthDate.getDate());

            double startDays = mStartDays;
            double birthDays = mBirthDate.getTime()/mTDV;

            for (int k = 0; k < mMaxType; k++) {
                x = 0;

                mPaint.setColor(mColors[k]);

                for (int i = 0; i <= mMaxDays; i++) {
                    double gab = birthDays - startDays;
                    double p = (int)(Math.sin((gab/mBioValues[k]) * 2.0 * mPI) * 100.0);

                    y = mRect.bottom/2 + (int)(p * ((mRect.bottom/2.0)/100.0));

                    if (i != 0)
                        canvas.drawLine(x, oldY, x + cellWidth, y, mPaint);

                    oldY = y;
                    startDays++;
                    x += cellWidth;
                }
            }
        }

        super.onDraw(canvas);
    }
}
