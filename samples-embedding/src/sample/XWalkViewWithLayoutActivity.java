// Copyright (c) 2014 Intel Corporation. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

package org.xwalk.embedded.api.sample;

import org.xwalk.core.XWalkNavigationHistory;
import org.xwalk.core.XWalkNavigationItem;
import org.xwalk.core.XWalkPreferences;
import org.xwalk.core.XWalkView;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class XWalkViewWithLayoutActivity extends XWalkBaseActivity {
    private final static float ANIMATION_FACTOR = 0.6f;
    private Button mRunAnimationButton;
    private ImageButton mButton;
    private boolean isPaused;

    private ImageButton mNextButton;
    private ImageButton mPrevButton;

    String url, originalUrl, title;
    TextView text1, text2, text3;

    private void startAnimation() {
        
        AnimatorSet combo = new AnimatorSet();

        float targetAlpha = mXWalkView.getAlpha() == 1.f ? ANIMATION_FACTOR : 1.f;
        float targetScaleFactor = mXWalkView.getScaleX() == 1.f ? ANIMATION_FACTOR : 1.f;

        ObjectAnimator fade = ObjectAnimator.ofFloat(mXWalkView,
                "alpha", mXWalkView.getAlpha(), targetAlpha);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(mXWalkView,
                "scaleX", mXWalkView.getScaleX(), targetScaleFactor);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(mXWalkView,
                "scaleY", mXWalkView.getScaleY(), targetScaleFactor);

        combo.setDuration(400);
        combo.playTogether(fade, scaleX, scaleY);
        combo.start();
    }

    private void showNavigationItemInfo(XWalkNavigationItem navigationItem){
        url = navigationItem.getUrl();
        originalUrl = navigationItem.getOriginalUrl();
        title = navigationItem.getTitle();

        text1.setText(title);
        text2.setText(url);
        text3.setText(originalUrl);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // ANIMATABLE_XWALK_VIEW preference key MUST be set before XWalkView creation.
        XWalkPreferences.setValue(XWalkPreferences.ANIMATABLE_XWALK_VIEW, true);
        setContentView(R.layout.xwview_layout);
        mRunAnimationButton = (Button) findViewById(R.id.run_animation);
        mRunAnimationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimation();
            }
        });

        mXWalkView = (XWalkView) findViewById(R.id.xwalkview1);
        
        isPaused = false;
        mButton = (ImageButton) findViewById(R.id.pause);
        mButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mXWalkView != null) {
                    if (!isPaused) {
                        // Pause JS timer
                        mXWalkView.pauseTimers();
                        isPaused = true;
                        mButton.setImageResource(android.R.drawable.ic_media_play);
                    } else {
                        // Resume JS timer
                        mXWalkView.resumeTimers();
                        isPaused = false;
                        mButton.setImageResource(android.R.drawable.ic_media_pause);
                    }
                }
            }
        });
        
        mPrevButton = (ImageButton) findViewById(R.id.prev);
        mNextButton = (ImageButton) findViewById(R.id.next);

        text1 = (TextView) super.findViewById(R.id.text1);
        text2 = (TextView) super.findViewById(R.id.text2);
        text3 = (TextView) super.findViewById(R.id.text3);

        mPrevButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go backward
                if (mXWalkView != null &&
                        mXWalkView.getNavigationHistory().canGoBack()) {
                    mXWalkView.getNavigationHistory().navigate(
                            XWalkNavigationHistory.Direction.BACKWARD, 1);
                }
                XWalkNavigationItem navigationItem = mXWalkView.getNavigationHistory().getCurrentItem();
                showNavigationItemInfo(navigationItem);
            }
        });

        mNextButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go forward
                if (mXWalkView != null &&
                        mXWalkView.getNavigationHistory().canGoForward()) {
                    mXWalkView.getNavigationHistory().navigate(
                            XWalkNavigationHistory.Direction.FORWARD, 1);
                }
                XWalkNavigationItem navigationItem = mXWalkView.getNavigationHistory().getCurrentItem();
                showNavigationItemInfo(navigationItem);
            }
        });

        textDes = (TextView) super.findViewById(R.id.xwalk_des);
        textDes.setText("This sample demonstrates the basic functions of XWalkView. contains navigate forward and backward, pause timers, and scale the view.");
        mXWalkView.load("file:///android_asset/window_navigate.html", null);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        // Reset the preference for animatable XWalkView.
        XWalkPreferences.setValue(XWalkPreferences.ANIMATABLE_XWALK_VIEW, false);
    }
}
