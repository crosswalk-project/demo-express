// Copyright (c) 2014 Intel Corporation. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

package org.xwalk.core.demoexpress.extended;


import org.xwalk.core.XWalkActivity;
import org.xwalk.core.XWalkView;
import org.xwalk.core.demoexpress.R;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.os.Bundle;

public class XWalkViewWithDisableLongClick extends XWalkActivity {
    private XWalkView mXWalkView;
    private Button disableLongClick;
    private TextView msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xwalk_view_with_disable_longclick);
        mXWalkView = (XWalkView) findViewById(R.id.xwalkview);
        disableLongClick = (Button) findViewById(R.id.disable_longclick);
        msg = (TextView) findViewById(R.id.message_tv);
    }

    @Override
    protected void onXWalkReady() {
        msg.setText("LongClick enabled");
        mXWalkView.load("http://www.baidu.com/", null);
        disableLongClick.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Button b = (Button)v;
                String s = b.getText().toString();
                if (s.equals("Disable LongClick")) {
                    disableLongClick.setText("Enable LongClick");
                    msg.setText("LongClick disabled");
                    mXWalkView.setLongClickable(false);
                    mXWalkView.setOnLongClickListener(new OnLongClickListener() {

                        @Override
                        public boolean onLongClick(View v) {
                            // TODO Auto-generated method stub
                            return true;
                        }
                    });
                } else {
                    disableLongClick.setText("Disable LongClick");
                    msg.setText("LongClick enabled");
                    mXWalkView.setLongClickable(true);
                    mXWalkView.setOnLongClickListener(new OnLongClickListener() {

                        @Override
                        public boolean onLongClick(View v) {
                            // TODO Auto-generated method stub
                            return false;
                        }
                    });
                }

            }
        });
    }
}
