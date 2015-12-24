// Copyright (c) 2014 Intel Corporation. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

package org.xwalk.core.demoexpress.basic;


import org.xwalk.core.XWalkActivity;

import org.xwalk.core.XWalkView;
import org.xwalk.core.demoexpress.R;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.os.Bundle;

public class XWalkViewWithZoomInAndOut extends XWalkActivity {
    private XWalkView mXWalkView;
    private Button zoomInBtn;
    private Button zoomOutBtn;
    private SeekBar zoomSeekBar;
    private TextView canZoomText;
    private static final String ZOOMRANGE = "Set zoom range: 0.5~2.0\n";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xwalk_view_with_zoom_in_and_out);
        mXWalkView = (XWalkView) findViewById(R.id.xwalkview);
    }

    @Override
    protected void onXWalkReady() {
        zoomInBtn = (Button) findViewById(R.id.zoomin_btn);
        zoomOutBtn = (Button) findViewById(R.id.zoomout_btn);
        canZoomText = (TextView) findViewById(R.id.zommtv);
        canZoomText.setText(ZOOMRANGE + "Can zoom in: " + mXWalkView.canZoomIn() +
                " Can zoom out: " + mXWalkView.canZoomOut());
        zoomSeekBar = (SeekBar) findViewById(R.id.zoombar);

        zoomInBtn.setOnClickListener(new OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            mXWalkView.zoomIn();
            canZoomText.setText(ZOOMRANGE + "Can zoom in: " + mXWalkView.canZoomIn() +
                    " Can zoom out: " + mXWalkView.canZoomOut());
        }
    });
        zoomOutBtn.setOnClickListener(new OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            mXWalkView.zoomOut();
            canZoomText.setText(ZOOMRANGE + "Can zoom in: " + mXWalkView.canZoomIn() +
                    " Can zoom out: " + mXWalkView.canZoomOut());
        }
    });
        zoomSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress,
                boolean fromUser) {
        // TODO Auto-generated method stub
            if (progress==0) {
                progress=1;
            }
            mXWalkView.zoomBy((float) (progress/10.0));
            canZoomText.setText(ZOOMRANGE + "Can zoom in: " + mXWalkView.canZoomIn() +
                    " Can zoom out: " + mXWalkView.canZoomOut());
        }
    });

        mXWalkView.load("file:///android_asset/zoom.html", null);
    }
}
