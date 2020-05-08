package com.bergrin.pizzashop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {
    private String userName;
    private String goodsName;
    private int quantity;
    private double orderPrice;
    private double price;

    private TextView orderTextView;

    private String[] addresses = {"bergrin1991@gmail.com"};
    private String subject = "Pizza order";
    private String emailText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Intent receivedOrderIntent = getIntent();

        orderTextView = findViewById(R.id.orderTextView);

        userName = receivedOrderIntent.getStringExtra("userName");
        goodsName = receivedOrderIntent.getStringExtra("goodsName");
        quantity = receivedOrderIntent.getIntExtra("quantity", 0);
        orderPrice = receivedOrderIntent.getDoubleExtra("orderPrice", 0);
        price = receivedOrderIntent.getDoubleExtra("price", 0);

        emailText =  "Покупатель: " + userName + "\n" +
                "Наименование: " + goodsName + "\n" +
                "Кол-во: " + quantity + "\n" +
                "Цена за ед.: " + price + "\n" +
                "Стоимость заказа: " + orderPrice + "\n";

        orderTextView.setText(emailText);
    }

    public void submitOrder(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, emailText);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


}
