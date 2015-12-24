// Copyright (c) 2014 Intel Corporation. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

package org.xwalk.core.demoexpress.extended;


import org.xwalk.core.XWalkActivity;
import org.xwalk.core.XWalkView;
import org.xwalk.core.demoexpress.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.widget.LinearLayout;
import android.widget.TextView;

public class XWalkViewWithInputConnection extends XWalkActivity {
    private XWalkView mXWalkView;
    private TextView textDes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container);
    }

    @Override
    protected void onXWalkReady() {

        LinearLayout parent = (LinearLayout) findViewById(R.id.container);
        
        textDes = (TextView) findViewById(R.id.description);
        textDes.setText("This sample demostrates XWalkView can use onCreateInputConnection method. Input some words in baidu input box. If it works, then finally you just get 'HAHA'.");

        mXWalkView = new myXWalkView(this, this);
        parent.addView(mXWalkView);
        mXWalkView.load("http://www.baidu.com", null);
    }

    private class myXWalkView extends XWalkView {

        public myXWalkView(Context context, Activity activity) {
            super(context, activity);
            // TODO Auto-generated constructor stub
        }

        @Override
        public InputConnection onCreateInputConnection(EditorInfo ei) {
            // TODO Auto-generated method stub
            InputConnection inputConnection = super.onCreateInputConnection(ei);
            if (inputConnection != null) {
                return new LimitInputConnection(inputConnection, false);
            }
            return null;
        }
    }

    private class LimitInputConnection extends InputConnectionWrapper {

        public LimitInputConnection(InputConnection target, boolean mutable) {
            super(target, mutable);
            // TODO Auto-generated constructor stub
        }

        @Override
        public boolean commitText(CharSequence text, int newCursorPosition) {
            // TODO Auto-generated method stub
            return super.commitText("HAHA", 1);
        }
    }
}

