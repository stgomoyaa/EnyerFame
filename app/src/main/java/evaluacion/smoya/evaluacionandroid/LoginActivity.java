package evaluacion.smoya.evaluacionandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import kotlinx.coroutines.Delay;

public class LoginActivity extends AppCompatActivity {
    // Variables de instancia
    Button btnLogin;
    EditText txtUsuario;
    EditText txtPass;
    ProgressBar progressBar;
    Handler handler = new Handler();
    int progreso = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.SplashTheme);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Asigno los valores a botones
        btnLogin = findViewById(R.id.btnLogin);
        txtUsuario = findViewById(R.id.txtUsuario);
        txtPass = findViewById(R.id.txtPass);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        // Comienzo a cargar
        new Thread(new Runnable() {
            public void run() {
                while (progreso < 100) {
                    progreso += 4;
                    // Actualizo la barra de progreso
                    handler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progreso);
                        }
                    });
                    try {
                        // Espero 200 milisegundos.
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();


    }

    public void btnLoginClick(View view) {
        if(txtUsuario.getText().toString().equals("") | txtPass.getText().toString().equals("")){
            Toast msjError = Toast.makeText(LoginActivity.this, "⚠No dejes campos vacíos porfavor.", Toast.LENGTH_LONG);
            msjError.show();
        }else if(txtUsuario.getText().toString().equals("admin") && txtPass.getText().toString().equals("123")){
            progressBar.setVisibility(View.VISIBLE);
            if(progreso == 100){
                Intent intent = new Intent (this, MainActivity.class);
                startActivity(intent);
                progressBar.setVisibility(View.GONE);
            }

        }

    }




    public void btnRegisterClick(View view) {
        Intent intent = new Intent (this, RegisterActivity.class);
        startActivity(intent);
    }
}