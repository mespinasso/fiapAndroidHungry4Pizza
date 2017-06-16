package br.com.mespinas.hungry4pizza;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText etLogin;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Getting view components
        etLogin = (EditText) findViewById(R.id.etLogin);
        etPassword = (EditText) findViewById(R.id.etPassword);
    }

    //Fired by onClick event
    public void login(View view) {
        String login = etLogin.getText().toString();
        String password = etPassword.getText().toString();

        if(login.equalsIgnoreCase("fiap") && password.equals("123")) {
            Intent intent = new Intent(this, OrderActivity.class);
            intent.putExtra("user", login);

            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Usuário ou senha inválidos", Toast.LENGTH_SHORT).show();
        }
    }
}
