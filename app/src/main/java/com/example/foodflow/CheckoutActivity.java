package com.example.foodflow;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CheckoutActivity extends AppCompatActivity {

    EditText etAddress;

    RadioGroup radioPayment;
    RadioButton radioCash;
    RadioButton radioOnline;

    TextView tvCheckoutSubtotal;
    TextView tvCheckoutDelivery;
    TextView tvCheckoutTotal;

    Button btnPlaceOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_checkout);

        etAddress = findViewById(R.id.etAddress);

        radioPayment = findViewById(R.id.radioPayment);
        radioCash = findViewById(R.id.radioCash);
        radioOnline = findViewById(R.id.radioOnline);

        tvCheckoutSubtotal = findViewById(R.id.tvCheckoutSubtotal);
        tvCheckoutDelivery = findViewById(R.id.tvCheckoutDelivery);
        tvCheckoutTotal = findViewById(R.id.tvCheckoutTotal);

        btnPlaceOrder = findViewById(R.id.btnPlaceOrder);

        updateOrderSummary();

        btnPlaceOrder.setOnClickListener(v -> placeOrder());
    }

    private void updateOrderSummary() {

        CartManager cartManager = CartManager.getInstance();

        int subtotal = cartManager.getCartTotal();

        int deliveryFee = subtotal > 0 ? 40 : 0;

        int total = subtotal + deliveryFee;

        tvCheckoutSubtotal.setText("₹" + subtotal);

        tvCheckoutDelivery.setText("₹" + deliveryFee);

        tvCheckoutTotal.setText("₹" + total);
    }

    private void placeOrder() {

        String address = etAddress.getText().toString().trim();

        if (address.isEmpty()) {
            etAddress.setError("Please enter delivery address");
            etAddress.requestFocus();
            return;
        }

        int selectedPaymentId =
                radioPayment.getCheckedRadioButtonId();

        if (selectedPaymentId == -1) {
            Toast.makeText(
                    this,
                    "Please select a payment method",
                    Toast.LENGTH_SHORT
            ).show();
            return;
        }

        String paymentMethod;

        if (selectedPaymentId == R.id.radioCash) {
            paymentMethod = "Cash on Delivery";
        } else {
            paymentMethod = "Online Payment";
        }

        int total = CartManager.getInstance().getCartTotal() + 40;

        Toast.makeText(
                this,
                "Order placed successfully! Total: ₹" + total
                        + " • " + paymentMethod,
                Toast.LENGTH_LONG
        ).show();

        CartManager.getInstance().clearCart();

        finish();
    }
}