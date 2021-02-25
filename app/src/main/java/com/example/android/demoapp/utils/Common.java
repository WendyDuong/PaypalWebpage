package com.example.android.demoapp.utils;

import com.example.android.demoapp.Retrofit.IAppChauAAPI;
import com.example.android.demoapp.Retrofit.RetrofitClient;
import com.example.android.demoapp.Retrofit.RetrofitScalarsClient;

public class Common {
/*
    public static final String BASE_URL ="http://10.0.2.2/germanyshoppingsquare.000webhostapp.com/";
    public static final String API_TOKEN_URL ="http://10.0.2.2/germanyshoppingsquare.000webhostapp.com/braintree/main.php";
*/
/*
    public static final String BASE_URL ="https://germanyshoppingsquare.000webhostapp.com/";
*/
    public static final String BASE_URL ="http://www.germanyshoppingsquare.com/";
    public static final String API_TOKEN_URL = BASE_URL + "braintree/main.php";
    public static final String API_SEND_PAYMENT = BASE_URL + "braintree/checkout.php";


    public static IAppChauAAPI getAPI()
    {
        return RetrofitClient.getClient(BASE_URL).create(IAppChauAAPI.class);
    }

    public static IAppChauAAPI getScalarsAPI()
    {
        return RetrofitScalarsClient.getScalarsClient(BASE_URL).create(IAppChauAAPI.class);
    }
}
