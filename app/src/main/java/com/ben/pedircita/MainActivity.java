package com.ben.pedircita;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private DatePicker datePicker;
    private TimePicker timePicker;
    private EditText dni;
    private Button confirmar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        datePicker = findViewById(R.id.date);
        timePicker = findViewById(R.id.hora);
        dni = findViewById(R.id.dni);
        confirmar = findViewById(R.id.enviar);

        //boton que envia la informacion
        confirmar.setOnClickListener(view -> {

            String dniValidar = dni.getText().toString();

            //validar DNI
            if (!isValidDni(dniValidar)) {
                Toast.makeText(this, "Introduce un DNI válido", Toast.LENGTH_SHORT).show();
                return;
            }

            //obtener fecha del datepicker
            int day = datePicker.getDayOfMonth();
            int month = datePicker.getMonth();
            int year = datePicker.getYear();

            //validar si es dia de la semana
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, day);
            int diasemana = calendar.get(Calendar.DAY_OF_WEEK);

            if (diasemana == Calendar.SATURDAY || diasemana == Calendar.SUNDAY) {
                Toast.makeText(this, "La clínica está cerrada los sábados y domingos", Toast.LENGTH_SHORT).show();
                return;
            }


            //obtener hora del timepicker

            int hour = timePicker.getHour();
            int minute = timePicker.getMinute();

            //validar horario
            if (hour < 9 || hour >= 14) {
                Toast.makeText(this, "La clínica abre de 9:00 a 14:00", Toast.LENGTH_SHORT).show();
                return;
            }

            //enviar datos
            Intent intent = new Intent(this, ConfirmacionCita.class);
            intent.putExtra("dni", dniValidar);
            intent.putExtra("DAY", day);
            intent.putExtra("MONTH", month);
            intent.putExtra("YEAR", year);
            intent.putExtra("HOUR", hour);
            intent.putExtra("MINUTE", minute);

            Toast.makeText(this, "Cita confirmada", Toast.LENGTH_SHORT).show();

            startActivity(intent);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    //validar DNI
    private boolean isValidDni(String dni) {
        return dni.matches("\\d{8}[A-Z]");
    }

}