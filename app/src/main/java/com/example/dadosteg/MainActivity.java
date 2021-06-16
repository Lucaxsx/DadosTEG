package com.example.dadosteg;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private final ImageView[] at = new ImageView[4];
    private final ImageView[] def = new ImageView[4];
    private final Button[] atButton = new Button[4];
    private final Button[] defButton = new Button[4];
    private ImageButton lanzar, atacar, defender;
    private int dadosAtacante = 1;
    private int dadosDefensor = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        atButton[0] = findViewById(R.id.atUno);
        atButton[1] = findViewById(R.id.atDos);
        atButton[2] = findViewById(R.id.atTres);
        atButton[3] = findViewById(R.id.atCuatro);
        atacar = findViewById(R.id.atacar);
        lanzar = findViewById(R.id.reset);
        defender = findViewById(R.id.defender);
        defButton[0] = findViewById(R.id.defUno);
        defButton[1] = findViewById(R.id.defDos);
        defButton[2] = findViewById(R.id.defTres);
        defButton[3] = findViewById(R.id.defCuatro);

        at[0] = findViewById(R.id.aDado1);
        at[1] = findViewById(R.id.aDado2);
        at[2] = findViewById(R.id.aDado3);
        at[3] = findViewById(R.id.aDado4);
        at[1].setVisibility(View.INVISIBLE);
        at[2].setVisibility(View.INVISIBLE);
        at[3].setVisibility(View.INVISIBLE);

        def[0] = findViewById(R.id.dDado1);
        def[1] = findViewById(R.id.dDado2);
        def[2] = findViewById(R.id.dDado3);
        def[3] = findViewById(R.id.dDado4);
        def[1].setVisibility(View.INVISIBLE);
        def[2].setVisibility(View.INVISIBLE);
        def[3].setVisibility(View.INVISIBLE);
    //      Agregar evento a los botones.
        for(int i=0; i<4; i++){
            atButton[i].setOnClickListener(new Click());
            defButton[i].setOnClickListener(new Click());
        }
        atacar.setOnClickListener(new Click());
        lanzar.setOnClickListener(new Click());
        defender.setOnClickListener(new Click());
    }
    //      Vuelve a pantalla completa en caso de haberse minimizado.
    @Override
    protected void onResume() {
        super.onResume();
        View decor = getWindow().getDecorView();
        decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    //      Vuelve a pantalla completa en caso de interactuar con la barra de notificación.
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(!hasFocus){
            View dec = getWindow().getDecorView();
            dec.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        }
    }


    public void lanzar(int cantDados, char bando){
        int[] dados = new int[cantDados];
        for(int i=0; i<dados.length; i++) dados[i] = (int) (Math.random()*6)+1;
        dados = ordenar(dados);
        mostrarDados(dados, bando);
    }
    //      Asigna '?' a los dados presentes en pantalla.
    public void reset(int dAt, int dDef){
        for(int i=0; i<dAt; i++){
            at[i].setImageResource(asignar(0));
        }
        for(int i=0; i<dDef; i++){
            def[i].setImageResource(asignar(0));
        }
    }
    //      Función que devuelve el Array ordenado de mayor a menor. Método por Selección.
    public int[] ordenar(int[] arrayInt){
        int temp, c;
        for(int i=0; i<arrayInt.length; i++){
            c=i;
            for(int j=i+1; j<arrayInt.length; j++){
                if(arrayInt[c]<arrayInt[j]){
                    c = j;
                }
            }
            if(c!=i) {
                temp = arrayInt[i];
                arrayInt[i] = arrayInt[c];
                arrayInt[c] = temp;
            }
        }
        return arrayInt;
    }

    public void mostrarDados(int[] dados, char bando){
        if(bando == 'a') {
            for (int x = 0; x < dados.length; x++) {
                switch (x) {
                    case 0:
                        at[0].setImageResource(asignar(dados[x]));
                        break;
                    case 1:
                        at[1].setImageResource(asignar(dados[x]));
                        break;
                    case 2:
                        at[2].setImageResource(asignar(dados[x]));
                        break;
                    case 3:
                        at[3].setImageResource(asignar(dados[x]));
                        break;
                }
            }
        }else{
            for (int x = 0; x < dados.length; x++) {
                switch (x) {
                    case 0:
                        def[0].setImageResource(asignar(dados[x]));
                        break;
                    case 1:
                        def[1].setImageResource(asignar(dados[x]));
                        break;
                    case 2:
                        def[2].setImageResource(asignar(dados[x]));
                        break;
                    case 3:
                        def[3].setImageResource(asignar(dados[x]));
                        break;
                }
            }
        }
    }

    //  Función que retorna la vista asociada al número del dado.
    public int asignar(int num){
        switch(num){
            case 1:
                return R.drawable.dadouno;
            case 2:
                return R.drawable.dadodos;
            case 3:
                return R.drawable.dadotres;
            case 4:
                return R.drawable.dadocuatro;
            case 5:
                return R.drawable.dadocinco;
            case 6:
                return R.drawable.dadoseis;
            default:
                return R.drawable.dadovacio;
        }
    }

    //      Método para mostrar los dados
    public void mostrar(int dados, ImageView[] array, Button[] boton){
        for(int i=0; i<4; i++){
            if(i!=dados-1) boton[i].setTextColor(Color.WHITE);
            else boton[i].setTextColor(Color.BLACK);

            if(i<=dados-1){
                array[i].setVisibility(View.VISIBLE);
            }else{
                array[i].setImageResource(asignar(0));
                array[i].setVisibility(View.INVISIBLE);
            }
        }
    }
    //      Clase interna para manejar los eventos de los botones.
    class Click implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.atUno:
                    dadosAtacante=1;
                    mostrar(dadosAtacante, at, atButton);
                    break;
                case R.id.atDos:
                    dadosAtacante=2;
                    mostrar(dadosAtacante, at, atButton);
                    break;
                case R.id.atTres:
                    dadosAtacante=3;
                    mostrar(dadosAtacante, at, atButton);
                    break;
                case R.id.atCuatro:
                    dadosAtacante=4;
                    mostrar(dadosAtacante, at, atButton);
                    break;
                case R.id.atacar:
                    lanzar(dadosAtacante, 'a');
                    break;
                case R.id.reset:
                    reset(dadosAtacante, dadosDefensor);
                    break;
                case R.id.defender:
                    lanzar(dadosDefensor, 'd');
                    break;
                case R.id.defUno:
                    dadosDefensor=1;
                    mostrar(dadosDefensor, def, defButton);
                    break;
                case R.id.defDos:
                    dadosDefensor=2;
                    mostrar(dadosDefensor, def, defButton);
                    break;
                case R.id.defTres:
                    dadosDefensor=3;
                    mostrar(dadosDefensor, def,defButton);
                    break;
                case R.id.defCuatro:
                    dadosDefensor=4;
                    mostrar(dadosDefensor, def, defButton);
                    break;
            }

        }
    }

}