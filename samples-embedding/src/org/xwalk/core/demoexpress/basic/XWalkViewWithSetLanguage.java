// Copyright (c) 2014 Intel Corporation. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

package org.xwalk.core.demoexpress.basic;


import org.xwalk.core.XWalkActivity;
import org.xwalk.core.XWalkView;
import org.xwalk.core.demoexpress.R;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class XWalkViewWithSetLanguage extends XWalkActivity {
    public final static String DEFAULT_AL = "Set Default Language";
    public final static String ZHCN = "zh-cn";
    public final static String ALTER_AL = "Set Alternative Language";
    public final static String ENUS = "en-us";
    public final static String MESSAGE_TITLE = "Accept Language: ";
    private XWalkView mXWalkView;
    private Button setLanguage;
    private TextView msg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xwalk_view_with_set_accept_language);
        mXWalkView = (XWalkView) findViewById(R.id.xwalkview);
        setLanguage = (Button) findViewById(R.id.set_language);
        msg = (TextView) findViewById(R.id.message_tv);
    }

    @Override
    protected void onXWalkReady() {
        msg.setText(MESSAGE_TITLE + ENUS);
        mXWalkView.load("http://www.huawei.com", null);
        setLanguage.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Button b = (Button)v;
                String s = b.getText().toString();
                if (s.equals(ALTER_AL)) {
                    setLanguage.setText(DEFAULT_AL);
                    msg.setText(MESSAGE_TITLE + ZHCN);
                    mXWalkView.setAcceptLanguages(ZHCN);
                    mXWalkView.load("http://www.huawei.com", null);
                } else {
                    setLanguage.setText(ALTER_AL);
                    msg.setText(MESSAGE_TITLE + ENUS);
                    mXWalkView.setAcceptLanguages(ENUS);
                    mXWalkView.load("http://www.huawei.com", null);
                }
            }
        });
    }
}

