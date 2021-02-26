package com.example.android.demoapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.android.demoapp.Config.Config;
import com.example.android.demoapp.R;
import com.paypal.pyplcheckout.BuildConfig;
import com.paypal.pyplcheckout.exception.CheckoutCancelReason;
import com.paypal.pyplcheckout.interfaces.PayPalCheckoutCompleteListener;
import com.paypal.pyplcheckout.merchantIntegration.CheckoutConfig;
import com.paypal.pyplcheckout.merchantIntegration.PayPalWebViewUtils;
import com.paypal.pyplcheckout.merchantIntegration.RunTimeEnvironment;

import java.util.HashMap;


public class MakePaymentActivity extends AppCompatActivity {



    private WebView mWebview;
    private ProgressBar mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_payment);

        mWebview = (WebView) findViewById(R.id.wvPayment);
        mProgress = (ProgressBar) findViewById(R.id.progressBarPayment);
        mWebview.getSettings().setJavaScriptEnabled(true); //set up javascript to use Paypal Button
        mWebview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);


        mWebview.setWebViewClient(new WebViewClient() {
                                      @Override
                                      public void onPageStarted(WebView view, String url, Bitmap favicon) {
                                          super.onPageStarted(view, url, favicon);
                                          mWebview.setVisibility(View.GONE);
                                          mProgress.setVisibility(View.VISIBLE);
                                          if (url.equals(".com")) {
                                              Toast.makeText(MakePaymentActivity.this, "Payment is cancelled", Toast.LENGTH_SHORT).show();
                                              finish();
                                          } else if (url.equals(".done")) {
                                              //TODO
                                              Toast.makeText(MakePaymentActivity.this, "Payment is success", Toast.LENGTH_SHORT).show();
                                              UploadInvoice();
                                          }
                                      }

                                      @Override
                                      public void onPageFinished(WebView view, String url) {
                                          super.onPageFinished(view, url);
                                          mWebview.setVisibility(View.VISIBLE);
                                          mProgress.setVisibility(View.GONE);
                                      }
                                  }
        );
        mWebview.loadUrl("http://germanyshoppingsquare.com/server/ungdungchaua/paypal");
   /*        mWebview = (WebView) findViewById(R.id.wvPayment);
        mProgress = (ProgressBar) findViewById(R.id.progressBarPayment);
        mWebview.getSettings().setJavaScriptEnabled(true); //set up javascript to use Paypal Button
        mWebview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        mWebview.setWebViewClient(new WebViewClient()
                                  {
                                      @Override
                                      public void onPageStarted(WebView view, String url, Bitmap favicon) {
                                          super.onPageStarted(view, url, favicon);
                                          mWebview.setVisibility(View.GONE);
                                          mProgress.setVisibility(View.VISIBLE);
                                          if(url.equals(".com")){
                                              Toast.makeText(MakePaymentActivity.this, "Payment is cacelled", Toast.LENGTH_SHORT).show();
                                              finish();
                                          }
                                          else if (url.equals(".done")){
                                              //TODO
                                              Toast.makeText(MakePaymentActivity.this, "Payment is success", Toast.LENGTH_SHORT).show();
                                              UploadInvoice();
                                          }
                                      }
                                      @Override
                                      public void onPageFinished(WebView view, String url) {
                                          super.onPageFinished(view, url);
                                          mWebview.setVisibility(View.VISIBLE);
                                          mProgress.setVisibility(View.GONE);
                                      }
                                  }
        );
        mWebview.loadUrl("https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=E4UR246M8N38E");*/
/*
        // Register the activity context.
        final CheckoutConfig checkoutConfig = CheckoutConfig.getInstance();


        // Set the client ID of the app.
        checkoutConfig.setClientId("AR-GPLgh6bpcJD-6UKeaWxTQ7a1SOIorsSoqC2W0Q3oRDbDXQsjcrxbmVej8ylid0vq7u5gZCnOvGnx0");
        checkoutConfig.setMerchantRedirectUrlScheme(BuildConfig.LIBRARY_PACKAGE_NAME);

        // Set environment to LIVE or SANDBOX. This step is mandatory.
        checkoutConfig.setPayPalEnvironment(RunTimeEnvironment.SANDBOX);

        checkoutConfig.setPayPalCheckoutCompleteListener(new PayPalCheckoutCompleteListener() {
            @Override
            public void onCheckoutComplete(HashMap params) {

                // Return params will contain all the required information for the merchant to finish    the transaction.
                Log.i("CheckoutFinishedWith>>", params.toString());
                Toast.makeText(MakePaymentActivity.this, params.toString(), Toast.LENGTH_LONG).show();

                // Here is a sample of what return params consist of
                */
/***
                 {
                 return_uri=https://sampleurl.com/checkouts/?from_cart=true&key=Key&token=EC-token&PayerID=payerID&opType=payment,
                 token=EC-token,
                 PayerID=payerID
                 paymentId=paymentId
                 }
                 ***//*

            }

            // In addition to the onApprove delegate you can also provide a onCancel delegate that is called when the user cancels the checkout process.
            @Override
            public void onCheckoutCancelled(CheckoutCancelReason cancelReason, String reason) {
                Log.i("Checkout Canceled>>", "Checkout Canceled");
            }
        });


        // MainActivity.class
        WebView webView = new WebView(this);

        //SampleWebViewIntercept is your webViewClient.
        webView.setWebViewClient(new SampleWebViewIntercept());

        // Provide a Service with overrden methods to handle checkout operations.
    }

    // SampleWebViewIntercept.class
    public class SampleWebViewIntercept extends WebViewClient {

        @Override
        public void onPageStarted(WebView view,
                                  String url,
                                  Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            // This is needed in case the user is integrated with Checkout.js
            PayPalWebViewUtils.getInstance().loadScript(view);
        }

        // Include this for integrating with Checkout.js.
        @Override
        public void onPageFinished(WebView view, String url) {

            super.onPageFinished(view, url);

            // This will load a script to handle the Checkout.js integration.
            PayPalWebViewUtils.getInstance().loadScript(view);

        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {

            return PayPalWebViewUtils.getInstance().shouldOverrideUrlLoading(view, request.getUrl().toString());

        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError er) {
            handler.proceed();
        }

*/


    }

    void UploadInvoice(){ }


}