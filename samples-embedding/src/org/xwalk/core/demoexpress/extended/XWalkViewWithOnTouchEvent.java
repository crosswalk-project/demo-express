package org.xwalk.core.demoexpress.extended;

import android.os.Bundle;
import android.widget.TextView;

import org.xwalk.core.XWalkActivity;
import org.xwalk.core.XWalkView;
import org.xwalk.core.demoexpress.R;

public class XWalkViewWithOnTouchEvent extends XWalkActivity implements MyXWalkView.TouchEventListener {

    private TextView tv;

    @Override
    protected void onXWalkReady() {
        tv = (TextView)findViewById(R.id.touch_event_label);

        XWalkView view = (XWalkView)findViewById(R.id.touch_event_xwalk_view);
        MyXWalkView mMyXWalkView = (MyXWalkView)view;
        mMyXWalkView.setTouchEventListener(this);
        view.load("http://www.baidu.com", null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xwalk_view_with_on_touch_event);
    }

    @Override
    public void onTouchEventInvoked(String msg){
        if(null != tv) {
            tv.setText(msg);
        }
    }
}
