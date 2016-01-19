package org.xwalk.core.demoexpress.basic;


import org.xwalk.core.XWalkActivity;
import org.xwalk.core.XWalkPreferences;
import org.xwalk.core.XWalkView;
import org.xwalk.core.demoexpress.R;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class XWalkViewWithTransparent extends XWalkActivity{
    private XWalkView mXWalkView;
    private Button setTransparent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        XWalkPreferences.setValue(XWalkPreferences.ANIMATABLE_XWALK_VIEW, false);

        setContentView(R.layout.activity_xwalk_view_with_set_transparent);
        mXWalkView = (XWalkView) findViewById(R.id.xwalkview);
        setTransparent = (Button)findViewById(R.id.set_transparent);
    }

    @Override
    protected void onXWalkReady() {
        mXWalkView.load("http://www.baidu.com/more/", null);
        setTransparent.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                mXWalkView.setBackgroundColor(Color.TRANSPARENT);
                mXWalkView.load("http://www.baidu.com/more/", null);
            }
        });

    }

}
