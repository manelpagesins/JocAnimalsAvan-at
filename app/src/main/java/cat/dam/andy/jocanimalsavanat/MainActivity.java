package cat.dam.andy.jocanimalsavanat;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //Objectes del xml
        LinearLayout TaulaEsquerra;
        LinearLayout TaulaDreta;
        ImageButton MajorQue;
        ImageButton Igual;
        ImageButton MenorQue;
        TextView tvResultatUser;
        TextView seguent;
        TextView punts;

    //Variables en static
        static int nTaulaEsquerra = 0;
        static int nTaulaDreta = 0;
        static  ArrayList<ImageView> ImatgesTaulaEsquerra = new ArrayList<ImageView>();
        static  ArrayList<ImageView> ImatgesTaulaDreta = new ArrayList<ImageView>();
        static int PuntsPlayer = 0;


    /**
     * Funcio amb la que podrem colocar el numero de animals proporcionat dins de les seves respactives taules
     */
    public void ColocarAnimals(LinearLayout TaulaEsquerra, LinearLayout TaulaDreta){

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(150, 150);

        //Faig un removeAllViews per si ja hi havien Imageviews existentes de un altre jugada
        TaulaEsquerra.removeAllViews();
        TaulaDreta.removeAllViews();

        //Bucle per entrar les imatges a la taula esquerra
        for(int i = 0; i < nTaulaEsquerra; i++){

            ImatgesTaulaEsquerra.add(new ImageView(this));
            ImatgesTaulaEsquerra.get(i).setId(i);
            ImatgesTaulaEsquerra.get(i).setImageResource(R.drawable.lleo);
            ImatgesTaulaEsquerra.get(i).setLayoutParams(layoutParams);

            if(ImatgesTaulaEsquerra.get(i).getParent() != null){
                ((ViewGroup)ImatgesTaulaEsquerra.get(i).getParent()).removeView(ImatgesTaulaEsquerra.get(i));
            }
            TaulaEsquerra.addView(ImatgesTaulaEsquerra.get(i));
        }

        //Bucle per entrar les imatges a la taula dreta
        for(int i = 0; i < nTaulaDreta; i++){

            ImatgesTaulaDreta.add(new ImageView(this));
            ImatgesTaulaDreta.get(i).setId(i);
            ImatgesTaulaDreta.get(i).setImageResource(R.drawable.dofi);
            ImatgesTaulaDreta.get(i).setLayoutParams(layoutParams);

            if(ImatgesTaulaDreta.get(i).getParent() != null){
                ((ViewGroup)ImatgesTaulaDreta.get(i).getParent()).removeView(ImatgesTaulaDreta.get(i));
            }
            TaulaDreta.addView(ImatgesTaulaDreta.get(i));

        }


        }

    /**
     * Funcio on es realitzara tots els processos per poder començar el joc
     */
    public static void PreJoc(LinearLayout TaulaEsquerra, LinearLayout TaulaDreta){

        Random rand = new Random();

        int numRand1 = 0,  numRand2 = 0;


        boolean sortir = true;

        //Bucle per tal de no aconseguir un valor 0 i aixi poder mostrar els animals
        while(sortir){
            numRand1 = rand.nextInt(7);
            numRand2 = rand.nextInt(7);

            if(numRand1 != 0 && numRand2 != 0){
                sortir = false;
            }
        }

        nTaulaEsquerra = numRand1;
        nTaulaDreta = numRand2;

    }

    /**
     * Funcio en la que podrem saber el resultat del joc
     * @param numeroButto - numero per identificar el butto que s'ha clicat
     * @return String
     */
    public static String Resultat(int numeroButto){

        String paraulaClau = "";

        int conclusio = 0;

        //Condicional per saber el resultat entre quina taula te mes animals o si son iguals
        if(nTaulaEsquerra > nTaulaDreta){
            conclusio = 1;
        }
        else if(nTaulaEsquerra < nTaulaDreta){
            conclusio = 3;
        }
        else{
            conclusio = 2;
        }

        //Condicional per saber si la resposta escullida per el usuari es encertada o no
        if(numeroButto == conclusio){
            paraulaClau = "HAS ENCERATAT!";
            PuntsPlayer++;
        }
        else{
            paraulaClau = "HAS FALLAT!";
        }

        return paraulaClau;

    }

    /**
     * Funcio que conte totes les altres funcions per tal de simular una sessio de joc
     * @param TaulaEsquerra - LinearLayout de la taula Esquerra
     * @param TaulaDreta - LinearLayout de la taula Dreta
     */
    public void SessioDeJoc(LinearLayout TaulaEsquerra, LinearLayout TaulaDreta){


                PreJoc(TaulaEsquerra, TaulaDreta);
                ColocarAnimals(TaulaEsquerra, TaulaDreta);

                MajorQue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int numeroEleccio = 1;
                        tvResultatUser.setText(Resultat(numeroEleccio));
                        tvResultatUser.setVisibility(View.VISIBLE);
                        seguent.setVisibility(View.VISIBLE);
                    }
                });

                Igual.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int numeroEleccio = 2;
                        tvResultatUser.setText(Resultat(numeroEleccio));
                        tvResultatUser.setVisibility(View.VISIBLE);
                        seguent.setVisibility(View.VISIBLE);
                    }
                });

                MenorQue.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        int numeroEleccio = 3;
                        tvResultatUser.setText(Resultat(numeroEleccio));
                        tvResultatUser.setVisibility(View.VISIBLE);
                        seguent.setVisibility(View.VISIBLE);

                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TaulaEsquerra = (LinearLayout) findViewById(R.id.TaulaEsquerra);
        TaulaDreta = (LinearLayout) findViewById(R.id.TaulaDreta);
        MajorQue = (ImageButton) findViewById(R.id.MajorQue);
        Igual = (ImageButton) findViewById(R.id.Igual);
        MenorQue = (ImageButton) findViewById(R.id.MenorQue);
        tvResultatUser = (TextView) findViewById(R.id.tvResultatUser);
        seguent = (TextView) findViewById(R.id.seguent);
        punts  = (TextView) findViewById(R.id.PuntsJugador);

        SessioDeJoc(TaulaEsquerra, TaulaDreta);

        String tv_punts = Integer.toString(PuntsPlayer) + " PUNTS";

        punts.setText(tv_punts);

        seguent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvResultatUser.setVisibility(View.INVISIBLE);
                seguent.setVisibility(View.INVISIBLE);
                SessioDeJoc(TaulaEsquerra, TaulaDreta);
                String tv_punts = Integer.toString(PuntsPlayer) + " PUNTS";
                punts.setText(tv_punts);
            }
        });


    }


}