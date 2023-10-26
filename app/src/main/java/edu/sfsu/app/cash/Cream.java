package edu.sfsu.app.cash;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import app.cash.paykit.core.CashAppPayFactory;
import app.cash.paykit.core.CashAppPay;
import app.cash.paykit.core.CashAppPayListener;
import app.cash.paykit.core.exceptions.CashAppPayIntegrationException;
import app.cash.paykit.core.models.response.CustomerResponseData;
import app.cash.paykit.core.models.sdk.CashAppPayPaymentAction;

public class Cream implements CashAppPay {
    @Override
    public void authorizeCustomerRequest() throws IllegalArgumentException, CashAppPayIntegrationException {

    }

    @Override
    public void authorizeCustomerRequest(@NonNull CustomerResponseData customerResponseData) throws IllegalArgumentException, RuntimeException {

    }

    @Override
    public void createCustomerRequest(@NonNull CashAppPayPaymentAction cashAppPayPaymentAction, @Nullable String s) {

    }

    @Override
    public void createCustomerRequest(@NonNull List<? extends CashAppPayPaymentAction> list, @Nullable String s) {

    }

    @Override
    public void registerForStateUpdates(@NonNull CashAppPayListener cashAppPayListener) {

    }

    @Override
    public void startWithExistingCustomerRequest(@NonNull String s) {

    }

    @Override
    public void unregisterFromStateUpdates() {

    }

    @Override
    public void updateCustomerRequest(@NonNull String s, @NonNull CashAppPayPaymentAction cashAppPayPaymentAction) {

    }

    @Override
    public void updateCustomerRequest(@NonNull String s, @NonNull List<? extends CashAppPayPaymentAction> list) {

    }
}
