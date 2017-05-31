package com.wedoshoes.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.wedoshoes.R;
import com.wedoshoes.WeDoShoesApplication;
import com.wedoshoes.database.LogManager;
import com.wedoshoes.database.prefrences.WeDoPreferencesManager;
import com.wedoshoes.model.commons.EventObject;
import com.wedoshoes.model.user.Donation;
import com.wedoshoes.requester.BackgroundExecutor;
import com.wedoshoes.requester.user.DonationRequester;
import com.wedoshoes.util.EventCenter;
import com.wedoshoes.util.Validator;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

public class DonationActivity extends BaseActivity {

    private static final String TAG = DonationActivity.class.getSimpleName();


    @BindView(R.id.donation_pay_selector) FrameLayout mDonationPay;
    @BindView(R.id.donation_shoe_selector) FrameLayout mDonationShoe;
    @BindView(R.id.name) EditText mNameEditText;
    @BindView(R.id.mobile) EditText mMobileEditText;


    public static Intent createIntent(Context context){
        return  new Intent(context , DonationActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDonationPay.setSelected(true);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_donation;
    }


    @OnClick(R.id.donation_pay_selector)
    public void onDonationPayClick(View view){
        WeDoShoesApplication.getInstance().trackEvent(DonationActivity.class.getSimpleName(),"Pay selected","Donation");
        if(view.isSelected()){
            return;
        }
        view.setSelected(true);
        mDonationShoe.setSelected(false);
    }

    @OnClick(R.id.donation_shoe_selector)
    public void onDonationShoeClick(View view){
        WeDoShoesApplication.getInstance().trackEvent(DonationActivity.class.getSimpleName(),"Shoe selected","Donation");
        if(view.isSelected()){
            return;
        }
        view.setSelected(true);
        mDonationPay.setSelected(false);
    }

    @OnClick(R.id.proceed)
    public void onProceedClick(){
        if(isValidate()) {
            WeDoPreferencesManager preferencesManager = WeDoPreferencesManager.getInstance();
            Donation donation = new Donation(mNameEditText.getText().toString(), preferencesManager.getEmail(), preferencesManager.getCountryCode(), Long.valueOf(mMobileEditText.getText().toString()));
            BackgroundExecutor.getInstance().execute(new DonationRequester(donation));
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventObject eventObject){
        if(eventObject == null){
            return;
        }
        switch (eventObject.getId()){
            case EventCenter.donationRequestSuccess:
                Snackbar.make(findViewById(R.id.donation_container),getString(R.string.donation_success),Snackbar.LENGTH_SHORT).show();
                startActivity(DonateActivity.createIntent(DonationActivity.this));
                overridePendingTransition(R.anim.enter, R.anim.exit);
                finish();
                LogManager.d(TAG, "Donation request success");
                break;
            case EventCenter.donationRequestError:
                Snackbar.make(findViewById(R.id.donation_container),getString(R.string.donation_error),Snackbar.LENGTH_SHORT).show();
                startActivity(DonateActivity.createIntent(DonationActivity.this));
                overridePendingTransition(R.anim.enter, R.anim.exit);
                finish();
                LogManager.d(TAG, "Donation request fail");
                break;
        }
    }

    /**
     * Validate name and mobile
     * @return
     */
    private boolean isValidate(){
        if(TextUtils.isEmpty(mNameEditText.getText().toString().trim())){
            Snackbar.make(mNameEditText,getString(R.string.enter_name),Snackbar.LENGTH_SHORT).show();
            return false;
        }else if(Validator.isValidMobileNumber(mMobileEditText.getText().toString().trim())){
            Snackbar.make(mMobileEditText,getString(R.string.enter_valid_mobile),Snackbar.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    @OnClick(R.id.back)
    public void onBackClick(){
        onBackPressed();
    }


    @OnClick(R.id.btn_skip)
    public void onSkipClick(){
        Intent intent = DashboardActivity.createIntent(this);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.stay, R.anim.push_down_out);
    }


}
