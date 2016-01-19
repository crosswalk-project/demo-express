package org.xwalk.core.demoexpress.extended;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.xwalk.core.XWalkActivity;
import org.xwalk.core.demoexpress.R;

public class XWalkViewWithSizeChanged extends XWalkActivity implements SizeChangedXWalkView.SizeChangedListener{

    private SizeChangedXWalkView mXWalkView;

    private TextView tv;

    private Button hb;

    private final static String HB_BUTTON_TEXT = "Small View";
    private final static String FB_BUTTON_TEXT = "Big View";

    private final static int SMALL_VIEW_HEIGHT = 200;
    private final static int BIG_VIEW_HEIGHT = 600;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xwalk_view_with_size_changed);
    }

    @Override
    protected void onXWalkReady() {
        mXWalkView = (SizeChangedXWalkView) findViewById(R.id.size_changed_xwalk_view);
        mXWalkView.setSizeChangedListener(this);
        mXWalkView.load("http://www.baidu.com", null);
        tv = (TextView)findViewById(R.id.size_changed_tip_label);

        hb = (Button)findViewById(R.id.half_button);
        hb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hb.getText().equals(HB_BUTTON_TEXT)){
                    mXWalkView.getLayoutParams().height = SMALL_VIEW_HEIGHT;
                    hb.setText(FB_BUTTON_TEXT);
                    mXWalkView.requestLayout();
                }else{
                    mXWalkView.getLayoutParams().height = BIG_VIEW_HEIGHT;
                    hb.setText(HB_BUTTON_TEXT);
                    mXWalkView.requestLayout();
                }

            }
        });
    }

    @Override
    public void informSizeChanged(String msg) {
        if(null != tv){
            tv.setText(msg);
        }
    }
}
