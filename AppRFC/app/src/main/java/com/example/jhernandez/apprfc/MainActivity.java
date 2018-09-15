package com.example.jhernandez.apprfc;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText nombre;
    private EditText aPaterno;
    private EditText aMaterno;
    /*private Date fNacimiento;*/
    private Button btnGenerar;
    private TextView rfc;
    private Button btnLimpiar;

    private ImageButton btnFecha;
    private EditText fNacimiento;
    private int dia, mes, anio;

    private String SuRFC = "";
    private String vocales[] = {"a","e","i","o","u","A","E","I","O","U"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre = (EditText)findViewById(R.id.nombre);
        aPaterno = (EditText)findViewById(R.id.aPaterno);
        aMaterno = (EditText)findViewById(R.id.aMaterno);
        btnGenerar = (Button)findViewById(R.id.btnGenerar);

        btnFecha = (ImageButton) findViewById(R.id.btnFecha);
        fNacimiento = (EditText)findViewById(R.id.fNacimiento);

        rfc         = (TextView)findViewById(R.id.rfc);
        btnLimpiar  = (Button)findViewById(R.id.btnLimpiar);

        btnGenerar.setOnClickListener(this);
        btnLimpiar.setOnClickListener(this);
        btnFecha.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        try{
        if(v==btnFecha){
            final Calendar c = Calendar.getInstance();
            dia = c.get(Calendar.DAY_OF_MONTH);
            mes = c.get(Calendar.MONTH);
            anio = c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                    fNacimiento.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
                }
            }
            ,anio,mes,dia);
            datePickerDialog.show();
        }

            if(v == btnLimpiar){
                nombre.setText("");
                aPaterno.setText("");
                aMaterno.setText("");
                fNacimiento.setText("");
                rfc.setText("");
            }


            if(v == btnGenerar){

                String p1 = aPaterno.getText().toString();

                //Extrae la Primera Letra
                SuRFC = p1.substring(0,1);

                String temp = "";
                Boolean end = true;

                for (int i = 1; i < p1.length(); i++){
                    for (int a=0; a<vocales.length;a++){
                        temp = p1.charAt(i)+"";
                        if (temp.equalsIgnoreCase(vocales[a])){
                            if(end){
                                SuRFC = SuRFC + "" + vocales[a];
                                end = false;
                            }
                        }
                    }
                }

                //Primera letra del Segundo Apellido
                String p2 = aMaterno.getText().toString();

                SuRFC = SuRFC + "" + p2.substring(0,1);

                //Primera letra del primer nombre
                String p3 = nombre.getText().toString();

                SuRFC = SuRFC + "" + p3.substring(0,1);

                //Fecha
                String txt = fNacimiento.getText().toString();
                String[] parts = txt.split("/");



                String f3=parts[2].substring(parts[2].length()-2, parts[2].length());

                if(parts[0].length()<2){
                    parts[0] = "0" + parts[0];
                }

                if(parts[1].length()<2){
                    parts[1] = "0" + parts[1];
                }

                SuRFC = SuRFC + "" + parts[0] + "" + parts[1] + "" + f3;

                rfc.setText("" + SuRFC.toUpperCase());

                //Toast.makeText(MainActivity.this,"Ve"+parts[0], Toast.LENGTH_SHORT).show();

            }


        }catch (Exception e){

        }
    }
}
