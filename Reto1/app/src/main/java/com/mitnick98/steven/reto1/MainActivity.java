package com.mitnick98.steven.reto1;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
    EditText Pomodoros;
    TextView Descanso;
    TextView Trabajo;
    Button Iniciar;
    TextView Hora;
    final String TAG = MainActivity.class.getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Pomodoros = findViewById(R.id.editTxtPomodoro);
        Descanso = findViewById(R.id.txtDescanso);
        Trabajo = findViewById(R.id.txtRealizado);
        Iniciar  = findViewById(R.id.btnIniciar);
        Hora = findViewById(R.id.txtTime);
        Log.d(TAG,"Se creo");
        final boolean[] fun = {false};
        Iniciar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Log.d(TAG,"on click de Iniciar");
                // Code here executes on main thread after user presses button
                if(!Pomodoros.getText().equals("")){

                    Log.d(TAG,"if de pomodoro");
                    int cantidad = Integer.parseInt(Pomodoros.getText().toString());
                    int minut;
                    CountDownTimer crono;
                    fun[0] = true;
                    for(int a = 1; a <= cantidad ; a++){

                        Hora.setText("");
                        Log.d(TAG,"for de pomodoro "+ cantidad);
                        if(a%2!=0){
                            minut = 25;

                        }
                        else if(a%4==0)
                        {
                            minut = 15;
                        }
                        else{
                            minut = 5;
                        }
                        crono = new CountDownTimer(1000*minut*60,1000) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                                Hora.setText(millisUntilFinished/(3600*1000)+":"+(millisUntilFinished%(3600*1000))/(60*1000)+":"+((millisUntilFinished%(3600*1000))%(60*1000))/(1000));
                                Log.d(TAG,"Cada segundo");
                            }

                            @Override
                            public void onFinish() {
                                Hora.setText("pomodoro nuevo");
                            }

                        };
                        if(!fun[0]) {
                            crono.start();
                            fun[0] = true;
                        }
                        else {

                            if (minut == 25) {
                                Trabajo.setText((Integer.parseInt(Trabajo.getText().toString()) + 1) + "");
                            } else
                                Descanso.setText((Integer.parseInt(Descanso.getText().toString()) + 1) + "");
                        }
                    }

                }
            }
        });


    }
}
