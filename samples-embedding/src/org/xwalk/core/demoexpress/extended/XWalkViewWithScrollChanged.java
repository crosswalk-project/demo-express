package org.xwalk.core.demoexpress.extended;


import android.os.Bundle;
import android.widget.TextView;

import org.xwalk.core.XWalkActivity;
import org.xwalk.core.XWalkView;
import org.xwalk.core.demoexpress.R;

public class XWalkViewWithScrollChanged extends XWalkActivity implements ScrollChangedXWalkView.ScrollChangedListener{

    private TextView tv;

    @Override
    protected void onXWalkReady() {
        tv = (TextView)findViewById(R.id.scroll_changed_label);

        XWalkView view = (XWalkView)findViewById(R.id.scroll_changed_xwalk_view);
        ScrollChangedXWalkView mScrollChangedXWalkView = (ScrollChangedXWalkView)view;
        mScrollChangedXWalkView.setScrollChangedListener(this);
        view.load("http://www.baidu.com", null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xwalk_view_with_scroll_changed);
    }

    @Override
    public void informScrollChanged(String msg) {
        if(null != tv){
            tv.setText(msg);
        }
    }
}
