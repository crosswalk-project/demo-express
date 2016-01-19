package org.xwalk.core.demoexpress.extended;

import android.os.Bundle;

import org.xwalk.core.XWalkActivity;
import org.xwalk.core.XWalkView;
import org.xwalk.core.demoexpress.R;

public class XWalkViewWithDispatchDraw extends XWalkActivity {

    @Override
    protected void onXWalkReady() {
        XWalkView mXWalkView = (XWalkView) findViewById(R.id.xwalk_view);
        mXWalkView.load("http://www.baidu.com", null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xwalk_view_with_dispatch_draw);
    }
}
