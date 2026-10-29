
package com.example.kostki;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private EditText LiczbaKostek;
    private Button ButtonLosuj;
    private TextView WynikiText;
    private Button RosnacoButton;
    private Button MalejacoButton;
    private GridLayout GridKostki;
    ArrayList<Integer> wynikiKostek = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        LiczbaKostek = findViewById(R.id.editTextLiczbaKostek);
        ButtonLosuj = findViewById(R.id.buttonLosuj);
        WynikiText = findViewById(R.id.textViewWyniki);
        RosnacoButton = findViewById(R.id.buttonRosnaco);
        MalejacoButton = findViewById(R.id.buttonMalejaco);
        GridKostki = findViewById(R.id.gridKostki);

        ButtonLosuj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tekst = LiczbaKostek.getText().toString().trim();

                if (tekst.isEmpty()) {
                    Toast.makeText(MainActivity.this,
                            "Podaj liczbę kostek!",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                int liczba;

                try {
                    liczba = Integer.parseInt(tekst);
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this,
                            "Podaj prawidłową liczbę!",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if (liczba < 1 || liczba > 100) {
                    Toast.makeText(MainActivity.this,
                            "Podaj liczbę kostek od 1 do 100!",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                wynikiKostek.clear();
                GridKostki.removeAllViews();

                Random generator = new Random();
                String wyniki = "";

                for (int i = 0; i < liczba; i++) {
                    int wynik = generator.nextInt(6) + 1;
                    wynikiKostek.add(wynik);

                    wyniki += wynik + ", ";

                    TextView kostka = new TextView(MainActivity.this);
                    kostka.setText(String.valueOf(wynik));
                    GridKostki.addView(kostka);
                }

                WynikiText.setText(wyniki);
            }
        });

        RosnacoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (wynikiKostek.isEmpty()) {
                    Toast.makeText(MainActivity.this,
                            "Najpierw wylosuj kostki!",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                for (int i = 0; i < wynikiKostek.size(); i++) {
                    for (int j = 0; j < wynikiKostek.size() - i - 1; j++) {
                        if (wynikiKostek.get(j) > wynikiKostek.get(j + 1)) {
                            int temp = wynikiKostek.get(j);
                            wynikiKostek.set(j, wynikiKostek.get(j + 1));
                            wynikiKostek.set(j + 1, temp);
                        }
                    }
                }

                pokazWyniki();
            }
        });

        MalejacoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (wynikiKostek.isEmpty()) {
                    Toast.makeText(MainActivity.this,
                            "Najpierw wylosuj kostki!",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                for (int i = 0; i < wynikiKostek.size(); i++) {
                    for (int j = 0; j < wynikiKostek.size() - i - 1; j++) {
                        if (wynikiKostek.get(j) < wynikiKostek.get(j + 1)) {
                            int temp = wynikiKostek.get(j);
                            wynikiKostek.set(j, wynikiKostek.get(j + 1));
                            wynikiKostek.set(j + 1, temp);
                        }
                    }
                }

                pokazWyniki();
            }
        });
    }

    private void pokazWyniki() {
        String tekst = "";

        for (int i = 0; i < wynikiKostek.size(); i++) {
            tekst += wynikiKostek.get(i);

            if (i < wynikiKostek.size() - 1) {
                tekst += ", ";
            }
        }

        WynikiText.setText(tekst);
    }
}
