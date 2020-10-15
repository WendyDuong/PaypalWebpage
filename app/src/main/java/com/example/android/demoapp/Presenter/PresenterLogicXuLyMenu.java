package com.example.android.demoapp.Presenter;

import com.example.android.demoapp.model.ModelDangNhap;
import com.facebook.AccessToken;



/**
 * Created by Lenovo S410p on 6/25/2016.
 */
public class PresenterLogicXuLyMenu implements IPresenterXuLyMenu {

    @Override
    public AccessToken LayTokenDungFacebook() {
        ModelDangNhap modelDangNhap = new ModelDangNhap();
        AccessToken accessToken = modelDangNhap.LayTokenFacebookHienTai();

        return accessToken;
    }
}
