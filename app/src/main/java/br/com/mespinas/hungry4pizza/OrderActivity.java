package br.com.mespinas.hungry4pizza;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class OrderActivity extends AppCompatActivity {

    private TextView tvUserGreeting;

    private CheckBox cbTuna;
    private CheckBox cbBacon;
    private CheckBox cbPepperoni;
    private CheckBox cbMozzarella;

    private RadioGroup rgPizzaSize;

    private Spinner spPaymentMethod;

    private CheckBox cbFilledEdge;
    private CheckBox cbExtraFilling;
    private CheckBox cbSoda;
    private CheckBox cbDessert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        tvUserGreeting = (TextView) findViewById(R.id.tvUserGreeting);

        cbTuna = (CheckBox) findViewById(R.id.cbTuna);
        cbBacon = (CheckBox) findViewById(R.id.cbBacon);
        cbPepperoni = (CheckBox) findViewById(R.id.cbPepperoni);
        cbMozzarella = (CheckBox) findViewById(R.id.cbMozzarella);

        rgPizzaSize = (RadioGroup) findViewById(R.id.rgPizzaSize);

        spPaymentMethod = (Spinner) findViewById(R.id.spPaymentMethod);

        cbFilledEdge = (CheckBox) findViewById(R.id.cbFilledEdge);
        cbExtraFilling = (CheckBox) findViewById(R.id.cbExtraFilling);
        cbSoda = (CheckBox) findViewById(R.id.cbSoda);
        cbDessert = (CheckBox) findViewById(R.id.cbDessert);

        //Get the username sent by the Login activity
        Bundle bundle = getIntent().getExtras();
        String user = bundle.getString("user");

        tvUserGreeting.setText("Olá " + user);
    }

    public void calculate(View view) {
        int selectedId = rgPizzaSize.getCheckedRadioButtonId();
        int pizzaSizePrice = 0;
        double price = 0;

        switch (selectedId) {
            case R.id.rbSmall:
                pizzaSizePrice = 10;
                break;
            case R.id.rbMedium:
                pizzaSizePrice = 12;
                break;
            case R.id.rbLarge:
                pizzaSizePrice = 15;
                break;
        }

        if(cbFilledEdge.isChecked())
            price += 5;

        if(cbExtraFilling.isChecked())
            price += 10;

        if(cbSoda.isChecked())
            price += 7;

        if(cbDessert.isChecked())
            price += 13;

        if(cbTuna.isChecked())
            price += 3 + pizzaSizePrice;

        if(cbBacon.isChecked())
            price += 3 + pizzaSizePrice;

        if(cbPepperoni.isChecked())
            price += 3 + pizzaSizePrice;

        if(cbMozzarella.isChecked())
            price += 3 + pizzaSizePrice;

        String paymentMethod = (String) spPaymentMethod.getSelectedItem();

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Confirmação");
        alert.setMessage("Valor: " + price + "\nPagamento: " + paymentMethod);

        alert.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.i("Pedido", "Confirmação de Pedido");
                Toast.makeText(OrderActivity.this, "Pedido Confirmado!", Toast.LENGTH_SHORT).show();
            }
        });

        alert.setNegativeButton("NÃO", null);

        alert.show();
    }
}
