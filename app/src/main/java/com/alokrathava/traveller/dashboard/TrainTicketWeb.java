package com.alokrathava.traveller.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.alokrathava.traveller.R;

public class TrainTicketWeb extends AppCompatActivity {

    ProgressBar pb_per;
    WebView mWebView;
    View view;

    private androidx.appcompat.widget.Toolbar mtoolbar;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_train_ticket_web );


        mtoolbar = findViewById ( R.id.toolbar );
        setSupportActionBar ( mtoolbar );


        WebView myWebView = findViewById ( R.id.webview );
        myWebView.setWebViewClient ( new WebViewClient ( ) );
        myWebView.loadUrl ( "https://www.irctc.co.in/nget/train-search" );

        WebSettings webSettings = myWebView.getSettings ( );
        webSettings.setJavaScriptEnabled ( true );


    }

    @Override
    public void onBackPressed () {
        if (mWebView.canGoBack ( )) {
            mWebView.goBack ( );
        } else {
            startActivity ( new Intent ( TrainTicketWeb.this , Dashboard.class ) );
        }
    }
}