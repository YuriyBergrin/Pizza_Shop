package com.bergrin.pizzashop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private TextView countTextView;
    private TextView priceTextView;
    private EditText userNameEditText;
    private int count = 1;
    private Spinner spinner;
    private ArrayList spinnerArrayList;
    private ArrayAdapter spinnerAdapter;
    private HashMap goodsMap;
    private String goodsName;
    private double price;
    private ImageView goodsImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        priceTextView = findViewById(R.id.priceLabel);
        goodsImageView = findViewById(R.id.goodsImageView);
        countTextView = findViewById(R.id.quantity);
        userNameEditText = findViewById(R.id.userNameEditText);

        createSpinner();
        createMap();
    }

    public void createSpinner() {
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        spinnerArrayList = new ArrayList();
        spinnerArrayList.add("Маргарита");
        spinnerArrayList.add("Мясная");
        spinnerArrayList.add("С анчоусами");
        spinnerArrayList.add("Сырная");
        spinnerArrayList.add("Вегетрианская");

        spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerArrayList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
    }

    public void createMap() {
        goodsMap = new HashMap();
        goodsMap.put(spinnerArrayList.get(0), 340.0);
        goodsMap.put(spinnerArrayList.get(1), 540.0);
        goodsMap.put(spinnerArrayList.get(2), 640.0);
        goodsMap.put(spinnerArrayList.get(3), 240.0);
        goodsMap.put(spinnerArrayList.get(4), 300.0);
    }

    public void increaseQuantity(View view) {
        count = Integer.parseInt(countTextView.getText().toString());
        count = count + 1;
        countTextView.setText(String.valueOf(count));
        priceTextView.setText("" + count * price);
    }

    public void decreaseQuantity(View view) {
        count = Integer.parseInt(countTextView.getText().toString());
        count = count - 1;
        if (count < 0) {
            count = 0;
        }
        countTextView.setText(String.valueOf(count));
        priceTextView.setText("" + count * price);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        goodsName = spinner.getSelectedItem().toString();
        price = (double) goodsMap.get(goodsName);
        priceTextView.setText("" + count * price);

        switch (goodsName) {
            case "Маргарита":
                goodsImageView.setImageResource(R.drawable.margarita);
                break;
            case "Мясная":
                goodsImageView.setImageResource(R.drawable.meat);
                break;
            case "С анчоусами":
                goodsImageView.setImageResource(R.drawable.anchous);
                break;
            case "Сырная":
                goodsImageView.setImageResource(R.drawable.cheese);
                break;
            case "Вегетрианская":
                goodsImageView.setImageResource(R.drawable.vegan);
                break;

            default:
                goodsImageView.setImageResource(R.drawable.margarita);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void addToCart(View view) {
        Order order = new Order();
        order.userName = userNameEditText.getText().toString();
        order.goodsName = goodsName;
        order.quantity = count;
        order.price = price;
        order.orderPrice = count * price;

        Intent orderIntent = new Intent(MainActivity.this, OrderActivity.class);
        orderIntent.putExtra("userName", order.userName);
        orderIntent.putExtra("goodsName", order.goodsName);
        orderIntent.putExtra("quantity", order.quantity);
        orderIntent.putExtra("orderPrice", order.orderPrice);
        orderIntent.putExtra("price", order.price);
        startActivity(orderIntent);

    }
}
