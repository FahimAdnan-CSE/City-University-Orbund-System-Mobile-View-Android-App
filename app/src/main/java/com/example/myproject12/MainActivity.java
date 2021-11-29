package com.example.myproject12;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.http.SslError;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static WebView webView;
    private static ProgressBar webViewProgressBar;
    private static ImageView back, forward, refresh, close;
    private  Button buttonexit;






    // add me
    private Button button;
    //
    //  TextView tvstatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);


        ActionBar actionBar=getSupportActionBar(); //how to remove action bar in android studio from specific activity
        // actionBar.isShowing();  //action bar is show in activity
        actionBar.hide();//action bar is hide from activity



        initViews();
        setUpWebView();
        setListeners();

        buttonexit=(Button) findViewById(R.id.exitd);

        buttonexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });




     //bellow this code show that the webview have internet connection or not

       // tvstatus=(TextView)findViewById(R.id.txtviewstatus);
        ConnectivityManager cn=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nf=cn.getActiveNetworkInfo();
        if(nf != null && nf.isConnected()==true )
        {
            //Toast.makeText(this, "Network Available", Toast.LENGTH_LONG).show();
           // tvstatus.setText("Network Available");
            refresh.setVisibility(View.VISIBLE);

        }
        else
        {
            refresh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent= new Intent(MainActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            });

             cutsomdialogbox();


           // Toast.makeText(this, "Network Not Available\nTrun your device internet connection\nThen press Home Icon", Toast.LENGTH_LONG).show();
           // tvstatus.setText("Network Not Available");

            /* this code reload webpage in a certain time
            ha.postDelayed(new Runnable() {
                @Override
                public void run() {
                    webView.loadUrl("https://cityuniversity.orbund.com/einstein-freshair/index.jsp");
                    ha.postDelayed(this, 1000);
                }
            }, 1000);

             */


        }
       //this is the object of webpage reload  Handler ha=new Handler();

 //code end





        //add me this is calling new activat_main2
    //add about button in main activity and then goes it in new activity
        button = (Button) findViewById(R.id.buttonid);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                openActivity();


            }
        });


    } // end oncreat method here




    //add this me  activat_main2 class

        public void openActivity(){
            Intent intent = new Intent(this, Main2Activity.class);
            startActivity(intent);
        }
//



    private void initViews() {
        back = (ImageView) findViewById(R.id.webviewBack);
        forward = (ImageView) findViewById(R.id.webviewForward);
        refresh = (ImageView) findViewById(R.id.webviewReload);
        close = (ImageView) findViewById(R.id.webviewClose);
        webViewProgressBar = (ProgressBar) findViewById(R.id.webViewProgressBar);
    }





    private void setUpWebView() {

// i add
        webView = (WebView) findViewById(R.id.sitesWebView);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);





        //i add
        webView.setInitialScale(1);

        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.setScrollbarFadingEnabled(false);


        //i add for zoom control
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);



      //main
        webView = (WebView) findViewById(R.id.sitesWebView);
        webView.setWebViewClient(new MyWebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        String value = getIntent().getStringExtra("uid");
        final String webViewUrl = "https://cityuniversity.orbund.com/einstein-freshair/index.jsp";
        LoadWebViewUrl(webViewUrl);
    }//setUpWebView end here






    private void setListeners() {
        back.setOnClickListener(this);
        forward.setOnClickListener(this);
        refresh.setOnClickListener(this);
        close.setOnClickListener(this);
    }




    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.webviewBack:
                isWebViewCanGoBack();
                break;
            case R.id.webviewForward:
                if (webView.canGoForward())
                    webView.goForward();
                break;
            case R.id.webviewReload:
                String url = webView.getUrl();
                LoadWebViewUrl(url);
                break;
            case R.id.webviewClose:
                //finish(); // finish the application will be tottaly exit
                //closeContextMenu();
                webView.stopLoading(); // that means the webview will be stop lading the page

                break;
        }
    }





    public void homeclick(View view) {
        Intent intent= new Intent(MainActivity.this,MainActivity.class);
        startActivity(intent);
        finish();


    }




    public class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;

        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            refresh.setVisibility(View.GONE);
            if (!webViewProgressBar.isShown())
                webViewProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            refresh.setVisibility(View.VISIBLE);
            if (webViewProgressBar.isShown())
                webViewProgressBar.setVisibility(View.GONE);
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
            refresh.setVisibility(View.VISIBLE);
            if (webViewProgressBar.isShown())
                webViewProgressBar.setVisibility(View.GONE);


        }

        @Override
        public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
            super.onReceivedHttpError(view, request, errorResponse);
            refresh.setVisibility(View.VISIBLE);
            if (webViewProgressBar.isShown())
                webViewProgressBar.setVisibility(View.GONE);
            Toast.makeText(MainActivity.this, "Fetching data.", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            super.onReceivedSslError(view, handler, error);
            refresh.setVisibility(View.VISIBLE);
            if (webViewProgressBar.isShown())
                webViewProgressBar.setVisibility(View.GONE);

        }

    }





    // To handle "Back" key press event for WebView to go back to previous screen.
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            isWebViewCanGoBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }




    private void isWebViewCanGoBack() {
        if (webView.canGoBack())
            webView.goBack();
        else
            //finish(); //if i use finish the aplication will be full terminated
            onBackPressed(); //if i useing this method by call bellow then application will ne show dialog and say it can be colose yes or no
    }






    private void LoadWebViewUrl(String url) {
        if (isInternetConnected())
            webView.loadUrl(url);
        else {
            refresh.setVisibility(View.VISIBLE);


        }
    }





    public boolean isInternetConnected() {
        // At activity startup we manually check the internet status and change
        // the text status
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;

        else
            return false;

    }






    //make this method for show dialog to close aplication yes or no

    public void onBackPressed() {

        AlertDialog.Builder alertDialogBuilder;

        alertDialogBuilder =new AlertDialog.Builder(MainActivity.this);

        alertDialogBuilder.setIcon(R.drawable.alert);
        alertDialogBuilder.setMessage(R.string.Dialog);
        alertDialogBuilder.setTitle(R.string.Title);
        alertDialogBuilder.setCancelable(false);

        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();

            }
        });

        alertDialogBuilder.setNegativeButton(" No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();

            }
        });

        //alertDialogBuilder.setNeutralButton() here i can not use this object

        AlertDialog alertDialog= alertDialogBuilder.create();
        alertDialog.show();




    }


    public  void cutsomdialogbox()
    {

        final AlertDialog.Builder alert = new  AlertDialog.Builder(MainActivity.this);

        View mview = getLayoutInflater().inflate(R.layout.custom,null);

        final Button retry=(Button) mview.findViewById(R.id.retry);
        final Button cancel=(Button) mview.findViewById(R.id.cancel);

        alert.setView(mview);
        final  AlertDialog alertDialog=alert.create();
        alertDialog.setCanceledOnTouchOutside(false);



        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        alertDialog.show();


    }










}
