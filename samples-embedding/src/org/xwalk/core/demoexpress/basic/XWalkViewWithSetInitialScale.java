// Copyright (c) 2014 Intel Corporation. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

package org.xwalk.core.demoexpress.basic;


import org.xwalk.core.XWalkActivity;
import org.xwalk.core.XWalkView;
import org.xwalk.core.demoexpress.R;

import android.R.integer;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;

public class XWalkViewWithSetInitialScale extends XWalkActivity {
    private final static String TAG = XWalkViewWithSetInitialScale.class.getName();
    private final static int sizeX = 500;
    private final static int sizeY = 500;
    private XWalkView mXWalkView;
    private Button setInitialScale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xwalk_view_with_set_initial_scale);
        mXWalkView = (XWalkView) findViewById(R.id.xwalkview);
        setInitialScale = (Button) findViewById(R.id.set_initialscale);
    }

    @Override
    protected void onXWalkReady() {
        final String pageTemplate = "<html><head>"
                                + "<meta name='viewport' content='initial-scale=1.0' />"
                                + "</head><body style='margin:0; padding:0'>"
                                + "<div style='background:green;width:" + sizeX
                                + "px;height:" + sizeY
                                + "px'>If you set InitialScale 100 percent, the green piece will spans this 500x500px size perfectly. If you set 150 percent, then green piece will be scaled."
                                + " This green piece width is " + sizeX
                                + "px; height is " + sizeY
                                + "px</div>"
                                + "</body></html>";
        mXWalkView.setInitialScale(100);
        mXWalkView.load(null, pageTemplate);
        setInitialScale.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Button b = (Button)v;
                String s = b.getText().toString();
                if (s.equals("SetInitialScale 150")) {
                    setInitialScale.setText("SetInitialScale 100");
                    mXWalkView.setInitialScale(150);
                    mXWalkView.load(null, pageTemplate);
                } else {
                    setInitialScale.setText("SetInitialScale 150");
                    mXWalkView.setInitialScale(100);
                    mXWalkView.load(null, pageTemplate);
                }

            }
        });
    }
}