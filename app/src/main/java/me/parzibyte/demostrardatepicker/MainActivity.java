package me.parzibyte.demostrardatepicker;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    // Obtener referencia al EditText
    private EditText etFecha;

    // Guardar el último año, mes y día del mes
    private int ultimoAnio, ultimoMes, ultimoDiaDelMes;

    // Crear un listener del datepicker;
    private DatePickerDialog.OnDateSetListener listenerDeDatePicker = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int anio, int mes, int diaDelMes) {
            // Esto se llama cuando seleccionan una fecha. Nos pasa la vista, pero más importante, nos pasa:
            // El año, el mes y el día del mes. Es lo que necesitamos para saber la fecha completa


            // Refrescamos las globales
            ultimoAnio = anio;
            ultimoMes = mes;
            ultimoDiaDelMes = diaDelMes;

            // Y refrescamos la fecha
            refrescarFechaEnEditText(diaDelMes,mes,anio);

        }
    };

    public void refrescarFechaEnEditText(int diaDelMes, int mes, int anio) {
        // Formateamos la fecha pero podríamos hacer cualquier otra cosa ;)
       // String fecha = String.format(new Locale("es"), "%02d-%02d-%02d", ultimoAnio, ultimoMes+1, ultimoDiaDelMes);

        final String fecha = twoDigits(diaDelMes) + "/" + twoDigits(mes+1) + "/" + anio;
        etFecha.setText(fecha);
    }



    private String twoDigits(int n){
        return(n<=9)?("0"+n):String.valueOf(n);
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instanciar objetos
        etFecha = findViewById(R.id.etFecha);

        // Poner último año, mes y día a la fecha de hoy
        final Calendar calendario = Calendar.getInstance();
        ultimoAnio = calendario.get(Calendar.YEAR);
        ultimoMes = calendario.get(Calendar.MONTH);
        ultimoDiaDelMes = calendario.get(Calendar.DAY_OF_MONTH);

        // Refrescar la fecha en el EditText
        refrescarFechaEnEditText(ultimoDiaDelMes,ultimoMes, ultimoAnio);

        // Hacer que el datepicker se muestre cuando toquen el EditText; recuerda
        // que se podría invocar en el click de cualquier otro botón, o en cualquier
        // otro evento

        etFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí es cuando dan click así que mostramos el DatePicker

                // Le pasamos lo que haya en las globales
                DatePickerDialog dialogoFecha = new DatePickerDialog(MainActivity.this, listenerDeDatePicker, ultimoAnio, ultimoMes, ultimoDiaDelMes);
                //Mostrar
                dialogoFecha.show();
            }
        });
    }
}
