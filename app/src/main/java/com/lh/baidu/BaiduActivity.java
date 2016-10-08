package com.lh.baidu;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

public class BaiduActivity extends AppCompatActivity implements View.OnClickListener {

    private WebView api_web_view;
    private AlwaysMarqueeTextView api_title_view;
    private TextView api_refresh_view, api_index_view;
    private ImageView api_close_view, api_back_view;
    private WebChromeClient webChromeClient;
    private final String url = "https://www.baidu.com/";
    private String thisUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baidu);
        api_web_view = (WebView) findViewById(R.id.api_web_view);
        api_title_view = (AlwaysMarqueeTextView) findViewById(R.id.api_title_view);
        api_refresh_view = (TextView) findViewById(R.id.api_refresh_view);
        api_index_view = (TextView) findViewById(R.id.api_index_view);
        api_close_view = (ImageView) findViewById(R.id.api_close_view);
        api_back_view = (ImageView) findViewById(R.id.api_back_view);
        api_close_view.setOnClickListener(this);
        api_back_view.setOnClickListener(this);
        api_refresh_view.setOnClickListener(this);
        api_index_view.setOnClickListener(this);
        initData();
    }

    private void initData() {
        api_web_view.loadUrl(url);

        api_web_view.getSettings().setJavaScriptEnabled(true);

        api_web_view.setWebViewClient(new MyWebViewClient());

        webChromeClient = new WebChromeClient() {

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                api_title_view.setText(title);
            }

        };
        api_web_view.setWebChromeClient(webChromeClient);
    }

    private void refresh() {
        api_web_view.loadUrl(thisUrl);
    }

    private void index() {
        //webview清理内存
        api_web_view.clearCache(true);
        //webview清理历史记录
        api_web_view.clearHistory();
        api_web_view.loadUrl(url);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.api_close_view://关闭
                finish();
                break;
            case R.id.api_back_view://返回
                api_web_view.goBack(); // goBack()表示返回WebView的上一页面
                thisUrl = api_web_view.getUrl();
                break;
            case R.id.api_refresh_view://刷新
                refresh();
                break;
            case R.id.api_index_view://回首页
                index();
                break;
        }
    }

    /**
     * 监听 所有点击的链接，如果拦截到我们需要的，就跳转到相对应的页面。
     */
    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.d("android", "url===>" + url);
            thisUrl = url;
            try {
                view.loadUrl(url);
                return true;
            } catch (Exception e) {
                return true;
            }
        }
    }

    @Override
    public void onBackPressed() {
        api_web_view.goBack(); // goBack()表示返回WebView的上一页面
    }

    /**
     * 当前页面暂停后
     */
    @Override
    protected void onPause() {
        //如果当前web服务不是null
        if (webChromeClient != null)
            //通知app当前页面要隐藏它的自定义视图。
            webChromeClient.onHideCustomView();
        //让webview重新加载，用于停掉音视频的声音
        api_web_view.reload();
        //先重载webview再暂停webview，这时候才真正能够停掉音视频的声音，api 2.3.3 以上才能暂停
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
            api_web_view.onPause(); // 暂停网页中正在播放的视频
        super.onPause();
    }

    @Override
    protected void onResume() {
        //重新开始webview，这样做的目的是为了不让webview重复进入的时候出现无法加载url出现空白
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            api_web_view.onResume();
        }
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //webview停止加载
        api_web_view.stopLoading();
        //webview销毁
        api_web_view.destroy();
        //webview清理内存
        api_web_view.clearCache(true);
        //webview清理历史记录
        api_web_view.clearHistory();
    }

}
