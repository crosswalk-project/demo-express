package org.xwalk.embedded.api.sample;

import org.xwalk.core.XWalkPreferences;
import org.xwalk.core.XWalkView;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MultiTextureViewsActivity extends XWalkBaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        XWalkPreferences.setValue(XWalkPreferences.ANIMATABLE_XWALK_VIEW, true);

        RelativeLayout root = new RelativeLayout(this);

        textDes = new TextView(this);
        textDes.setText("This sample demonstrates Multiple TextureViews can be shown in order.");
        root.addView(textDes);

        for (int i = 0; i < 3; i++) {
            XWalkView xWalkView = new XWalkView(this, this);
            xWalkView.setX(i * 100);
            xWalkView.setY((i + 1) * 100);
            xWalkView.load(null, String.format("<html><head><meta name='viewport' content='width=device-width'/></head>"
                    + "<body style='background-color: %s;'><h1>%s</h1></body></html>", i % 2 == 0 ? "white" : "grey", i == 0 ? "A" : i == 1 ?  "B" : "C"));
            root.addView(xWalkView, 200, 200);
        }

        for (int i = 3; i < 6; i++) {
            WebView webView = new WebView(this);
            webView.setX(i * 100);
            webView.setY((i + 1) * 100);
            webView.loadData(String.format("<html><body style='background-color: %s'><h1>%s</h1></body></html>",
                    i % 2 == 0 ? "white" : "grey", i == 3 ? "D" : i == 4 ?  "E" : "F"), "text/html", "utf-8");
            root.addView(webView, 200, 200);
        }

        setContentView(root);
        resizeLoop(root);
    }

    private void resizeLoop(final RelativeLayout views) {
        views.postDelayed(new Runnable() {
            @Override
            public void run() {
                resize(views, 400, 400);
                views.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        resize(views, 200, 200);
                        resizeLoop(views);
                    }
                }, 2000);
            }
        }, 2000);
    }

    private void resize(RelativeLayout views, int width, int height) {
        for (int i = 0; i < views.getChildCount(); i++) {
            View child = views.getChildAt(i);
            child.setLayoutParams(new RelativeLayout.LayoutParams(width, height));
        }
    }
}
