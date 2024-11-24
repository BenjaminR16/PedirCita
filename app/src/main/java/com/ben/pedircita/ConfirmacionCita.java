package com.ben.pedircita;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ConfirmacionCita extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_confirmacion_cita);


        String dni = getIntent().getStringExtra("dni");
        int day = getIntent().getIntExtra("DAY", 0);
        int month = getIntent().getIntExtra("MONTH", 0);
        int year = getIntent().getIntExtra("YEAR", 0);
        int hour = getIntent().getIntExtra("HOUR", 0);
        int minute = getIntent().getIntExtra("MINUTE", 0);


        TextView textView = findViewById(R.id.textoconfirmacion);
        textView.setText("DNI: " + dni + "\n" + "Fecha: " + day + "/" + month + "/" + year + "\n" + "Hora: " + String.format("%02d:%02d", hour, minute));





        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}