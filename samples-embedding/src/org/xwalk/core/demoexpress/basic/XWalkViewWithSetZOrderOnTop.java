package org.xwalk.core.demoexpress.basic;


import org.xwalk.core.XWalkActivity;
import org.xwalk.core.XWalkPreferences;
import org.xwalk.core.XWalkView;
import org.xwalk.core.demoexpress.R;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class XWalkViewWithSetZOrderOnTop extends XWalkActivity{
    private XWalkView mXWalkView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        XWalkPreferences.setValue(XWalkPreferences.ANIMATABLE_XWALK_VIEW, false);
        
        setContentView(R.layout.activity_xwalk_view_with_set_zorder_ontop);
        mXWalkView = (XWalkView) findViewById(R.id.xwalkview_transparent);
    }

    @Override
    protected void onXWalkReady() {
        mXWalkView.setZOrderOnTop(true);
        mXWalkView.setBackgroundColor(0);    	
        mXWalkView.load("http://www.baidu.com/more/", null);
    }
}
