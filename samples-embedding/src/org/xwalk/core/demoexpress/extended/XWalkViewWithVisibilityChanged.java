package org.xwalk.core.demoexpress.extended;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.xwalk.core.XWalkActivity;
import org.xwalk.core.demoexpress.R;

public class XWalkViewWithVisibilityChanged extends XWalkActivity implements VisibilityChangedXWalkView.VisibilityChangedListener{

    private VisibilityChangedXWalkView mXWalkView;

    private TextView tv;

    private Button hb;

    private final static String HB_BUTTON_TEXT = "Show View";
    private final static String FB_BUTTON_TEXT = "Hide View";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xwalk_view_with_visibility_changed);
    }

    @Override
    public void informVisibilityChanged(String msg) {
        if(null != tv){
            tv.setText(msg);
        }
    }

    @Override
    protected void onXWalkReady() {
        mXWalkView = (VisibilityChangedXWalkView) findViewById(R.id.visibility_changed_xwalk_view);
        mXWalkView.setSizeChangedListener(this);
        mXWalkView.load("http://www.baidu.com", null);
        tv = (TextView)findViewById(R.id.visibility_changed_tip_label);

        hb = (Button)findViewById(R.id.visibility_button);
        hb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hb.getText().equals(HB_BUTTON_TEXT)){
                    hb.setText(FB_BUTTON_TEXT);
                    mXWalkView.setVisibility(View.VISIBLE);
                    mXWalkView.requestLayout();
                }else{
                    hb.setText(HB_BUTTON_TEXT);
                    mXWalkView.setVisibility(View.INVISIBLE);
                    mXWalkView.requestLayout();
                }
            }
        });
    }
}
