package com.devjurnal.orderapps;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // TODO (1) global variable
    int quantity=0;
    int price=0;
    Button show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        show = findViewById(R.id.btnShow);

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText edtName = findViewById(R.id.edtName);

                // Mengambil Nilai dari Edit Text dengan GetText
                String name = edtName.getText().toString();

                //price = CalculatePrice();

                String orderSummary = "Nama : " +name
                        +"\nTotal Harga : " +price;
                DisplaySummary(price, orderSummary);
            }
        });

    }

    public void decrement(View view) {
        //quantity = quantity -1 ;
        quantity -= 1;
        //Log.d("Decrement", String.valueOf(quantity));
        display(quantity);
    }

    public void increment(View view) {
        //quantity = quantity + 1;
        quantity += 1;
        display(quantity);
    }

    // method/fungsi Display
    public void display(int quantity) {
        TextView tvQuantity = (TextView) findViewById(R.id.tvQuantity);
        tvQuantity.setText("" +quantity);

    }


    public void submitOrder(View view) {
        String topping = "";

        // Mengambil hasil checkbox
        CheckBox whippedCreamCB = findViewById(R.id.cbWCream);
        boolean hasWhippedCream = whippedCreamCB.isChecked();

        CheckBox Choco = findViewById(R.id.cbChoco);
        boolean hasChoco = Choco.isChecked();
        if (hasWhippedCream == true && hasChoco == true){
            topping = "Whipped Cream & Chocolate";
        } else if (hasChoco == true){
            topping = "Chocolate";
        } else if ( hasWhippedCream == true ){
            topping = "Whipped Cream";
        }   else {
            topping = "tanpa topping";
        }
        // Layout Binding => menghubungkan layout & java
        EditText edtName = findViewById(R.id.edtName);

        // Mengambil Nilai dari Edit Text dengan GetText
        String name = edtName.getText().toString();

        price = CalculatePrice(hasWhippedCream,hasChoco);

        String orderSummary = CreateOrderSummary(price, name, topping);
        DisplaySummary(price, orderSummary);

        Intent implicitIntent = new Intent(Intent.ACTION_SENDTO);
        // membuka aplikasi lain yang dapat digunakan untuk mengirim email
        // ke emailtujuan@email.com
        implicitIntent.setData(Uri.parse("mailto: emailtujuan@email.com"));
        // subject Email yang akan dikirim
        implicitIntent.putExtra(Intent.EXTRA_SUBJECT,
                "Rekap Pesanan dari "+name);
        // Isi dari Email menggunakan data dari variabel orderSummary
        implicitIntent.putExtra(Intent.EXTRA_TEXT, orderSummary);

        if (implicitIntent.resolveActivity(getPackageManager())!= null){
            startActivity(implicitIntent);
        }
    }// end of SubmitOrder Function

    /*
    // menghitung Harga tanpa topping
    public int CalculatePrice(){
        price = quantity * 5000;
        return price;
    }
    */
    public int CalculatePrice(boolean hasWhippedCream, boolean hasChoco){
        int hargaAwal = 5000;
        if(hasWhippedCream){
            hargaAwal = hargaAwal+500;
        }

        if (hasChoco)
        {
            hargaAwal = hargaAwal+500;
        }

        return quantity *hargaAwal;
    }

    // Menampilkan Rekap Pesanan
    public void DisplaySummary(int price, String Summary){
        TextView tvSummary = findViewById(R.id.tvSummary);
        tvSummary.setText(Summary);
    }

    private String CreateOrderSummary(int price, String name , String topping){
        String priceMessage = "Nama : " +name
                +"\nQUantity : " +quantity
                +"\nTopping : " +topping
                +"\nTotal Harga : Rp " +price;
        return priceMessage;
    }
}
