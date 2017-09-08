###### 仿淘宝点击显示物流详情的弹框
**两种方式：

一种是dialog的activity+改变透明度

一种是popwindow+高斯模糊**




![image](https://raw.githubusercontent.com/nuodiehan/mygit/master/imgs/toumingdu.png)
![image](https://raw.githubusercontent.com/nuodiehan/mygit/master/imgs/jieping.jpg )

主要的是：根据坐标画圆点和文字（收、运），贯穿圆点的竖线和右侧的文字


    `    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (logisticsDataList == null || logisticsDataList.size() == 0)
            return;

        List data = logisticsDataList;
        canvas.drawRect(left * 2, top, width + left * 2, heightTotal + top, paint);
        Paint mPaint = new Paint();
        TextPaint textPaint1 = new TextPaint();
        for (int i = 0; i < logisticsDataList.size(); i++) {
            if (i == 0) {
                mPaint.setAntiAlias(true);
                mPaint.setColor(getResources().getColor(R.color.checkColor));
                mPaint.setTextSize(30);
                TextPaint datePaint = new TextPaint();
                datePaint.setColor(getResources().getColor(R.color.checkColor));
                datePaint.setTextSize(20);
                datePaint.setAntiAlias(true);
                datePaint.measureText(((LogisticsData) data.get(i)).getDate());

                TextPaint texttimePaint = new TextPaint();
                texttimePaint.setColor(getResources().getColor(R.color.checkColor));
                texttimePaint.setTextSize(20);
                texttimePaint.setAntiAlias(true);
                texttimePaint.measureText(((LogisticsData) data.get(i)).getTime());
                canvas.drawText(((LogisticsData) data.get(i)).getDate() + "", 5, top + 32, datePaint);
                canvas.drawText(((LogisticsData) data.get(i)).getTime() + "", 5, top + 8, texttimePaint);

                TextPaint textPaint = new TextPaint();
                textPaint.setColor(getResources().getColor(R.color.checkColor));
                textPaint.setTextSize(30.0F);
                textPaint.setAntiAlias(true);


                mPaint.setShader(mLinearGradient);
                canvas.drawCircle(width / 2 + left * 2, top + 5, radius * 2 + 2, mPaint);

                textPaint1.setColor(getResources().getColor(R.color.white));
                textPaint1.setTextSize(26);
                textPaint1.setAntiAlias(true);
                textPaint1.measureText("收");
                canvas.drawText("收", left * 2 - 12, top + 14, textPaint1);
                String[] splitData = splitString(((LogisticsData) data.get(i)).getContext() + "");
                if (splitData != null) {
                    splitPhoneData(splitData, canvas, textPaint, 0, true);
                } else {
                    StaticLayout layout = new StaticLayout(((LogisticsData) data.get(i)).getContext() + "", textPaint,
                            (int) (windowWidth * 0.7), ALIGN_NORMAL, 1.0F, 0.0F, true);
                    canvas.save();
                    canvas.translate(left * 2 + radius * 2 + 10, 0);
                    layout.draw(canvas);
                    canvas.restore();
                }
            } else {
                int heightData = 0;
                for (int j = 0; j < i; j++) {
                    heightData = heightData + heightList.get(j) + interval + (j == 0 ? top : top * 2);
                }
                paint.setColor(getResources().getColor(R.color.normalColor));
                canvas.drawCircle(width / 2 + left * 2, heightData + 44, radius * 2 + 2, mPaint);
                canvas.drawText("运", +left * 2 - 10, heightData + 44 + 8, textPaint1);

                paint.setTextSize(30);
                TextPaint datePaint = new TextPaint();
                datePaint.setColor(getResources().getColor(R.color.normalColor));
                datePaint.setTextSize(20);
                datePaint.setAntiAlias(true);
                datePaint.measureText(((LogisticsData) data.get(i)).getDate());

                TextPaint texttimePaint = new TextPaint();
                texttimePaint.setColor(getResources().getColor(R.color.normalColor));
                texttimePaint.setTextSize(20);
                texttimePaint.setAntiAlias(true);
                texttimePaint.measureText(((LogisticsData) data.get(i)).getTime());
                canvas.drawText(((LogisticsData) data.get(i)).getDate() + "", 5,  20 + heightData + top, datePaint);
                canvas.drawText(((LogisticsData) data.get(i)).getTime() + "", 5, 44 + heightData + top, texttimePaint);
                TextPaint textPaint = new TextPaint();
                textPaint.setColor(getResources().getColor(R.color.normalColor));
                textPaint.setTextSize(30.0F);
                textPaint.setAntiAlias(true);
                String[] splitData = splitString(((LogisticsData) data.get(i)).getContext() + "");
                if (splitData != null) {
                    splitPhoneData(splitData, canvas, textPaint, heightData, false);
                } else {
                    StaticLayout layout = new StaticLayout(((LogisticsData) data.get(i)).getContext() + "", textPaint, (int) (windowWidth * 0.7), ALIGN_NORMAL, 1.0F, 0.0F, true);
                    canvas.save();
                    canvas.translate(left * 2 + radius * 2 + 10, heightData + top);
                    layout.draw(canvas);
                    canvas.restore();
                }

            }
        }
    }`

    
> 


**第一种是全屏的dialog 的activity,滑动是自定义的TabLayout+viewpager**
  >     <!--dialog的activity全屏-->
    <style name="transcutestyle" parent="Dialog_Fullscreen">
        <item name="android:windowFrame">@android:color/transparent</item><!--边框-->
        <item name="android:windowIsFloating">true</item><!--是否浮现在activity之上-->
        <item name="android:windowIsTranslucent">true</item><!--半透明-->
        <item name="android:windowNoTitle">true</item><!--无标题-->
        <item name="android:windowBackground">@android:color/transparent</item><!--背景透明-->
        <item name="android:backgroundDimAmount">0.3</item>
        <item name="android:windowAnimationStyle">@null</item>
    </style>

第二种主要是对popwindow的背景设置为高斯模糊的截屏背景
    
>  Bitmap bitmap = Bitmap.createBitmap(b, 0, statusBarHeight, width, height
                - statusBarHeight);
        view.destroyDrawingCache();
        bitmap = FastBlur.fastBlur(bitmap, radius);
        if (bitmap != null) {
            return bitmap;
        } else {
            return null;
        }

这个demo参考了：https://github.com/aesion/NodeProgressView