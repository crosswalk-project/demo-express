package org.xwalk.core.demoexpress.extended;


import android.os.Bundle;
import android.widget.TextView;

import org.xwalk.core.XWalkActivity;
import org.xwalk.core.demoexpress.R;

public class XWalkViewWithFocusChanged  extends XWalkActivity implements MyXWalkView.FocusChangedListener {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xwalk_view_with_focus_changed);
    }

    @Override
    protected void onXWalkReady() {

        MyXWalkView mXWalkView = (MyXWalkView) findViewById(R.id.focus_xwalk_view);
        mXWalkView.setFocuseChangedListener(this);
        mXWalkView.load("http://www.baidu.com", null);
        tv = (TextView)findViewById(R.id.focus_tip_label);
    }

    @Override
    public void informFocuseChanged(String msg){
        if(null != tv){
            tv.setText(msg);
        }
    }
}
