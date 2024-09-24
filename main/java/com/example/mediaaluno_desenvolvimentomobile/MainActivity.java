package com.example.mediaaluno_desenvolvimentomobile;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText cpNome;
    private EditText cpEmail;
    private EditText cpIdade;
    private EditText cpDisciplina;
    private EditText cpNota1;
    private EditText cpNota2;
    private Button btEnviar;
    private Button btLimpar;
    private TextView tvResultado;
    private TextView tvErro;
    private TextView tvHistorico;

    private StringBuilder historicoBuilder = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        cpNome = findViewById(R.id.cpNome);
        cpEmail = findViewById(R.id.cpEmail);
        cpIdade = findViewById(R.id.cpIdade);
        cpDisciplina = findViewById(R.id.cpDisciplina);
        cpNota1 = findViewById(R.id.cpNota1);
        cpNota2 = findViewById(R.id.cpNota2);
        tvResultado = findViewById(R.id.tvResultado);
        tvErro = findViewById(R.id.tvErro);
        tvHistorico = findViewById(R.id.tvHistorico);

        btEnviar = findViewById(R.id.btEnviar);
        btLimpar = findViewById(R.id.btLimpar);

        btEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = cpNome.getText().toString();
                String email = cpEmail.getText().toString();
                String idadeStr = cpIdade.getText().toString();
                String disciplina = cpDisciplina.getText().toString();
                String nota1Str = cpNota1.getText().toString();
                String nota2Str = cpNota2.getText().toString();


                if (TextUtils.isEmpty(nome)) {
                    tvErro.setText("Insira seu Nome!");
                    tvErro.setVisibility(View.VISIBLE);
                    tvResultado.setVisibility(View.GONE);
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    tvErro.setText("Insira um Email Valido!");
                    tvErro.setVisibility(View.VISIBLE);
                    tvResultado.setVisibility(View.GONE);
                    return;
                }

                int idade;
                double nota1, nota2;
                try {
                    idade = Integer.parseInt(idadeStr);
                    if (idade <= 0) {
                        tvErro.setText("Insira uma Idade Válida!");
                        tvErro.setVisibility(View.VISIBLE);
                        tvResultado.setVisibility(View.GONE);
                        return;
                    }
                } catch (NumberFormatException e) {
                    tvErro.setText("Insira uma Idade Válida!");
                    tvErro.setVisibility(View.VISIBLE);
                    tvResultado.setVisibility(View.GONE);
                    return;
                }

                try {
                    nota1 = Double.parseDouble(nota1Str);
                    nota2 = Double.parseDouble(nota2Str);
                    if (nota1 < 0 || nota1 > 10 || nota2 < 0 || nota2 > 10) {
                        tvErro.setText("Notas Devem Estar Entre 0 e 10.");
                        tvErro.setVisibility(View.VISIBLE);
                        tvResultado.setVisibility(View.GONE);
                        return;
                    }
                } catch (NumberFormatException e) {
                    tvErro.setText("As Notas Devem Ser Números Válidos.");
                    tvErro.setVisibility(View.VISIBLE);
                    tvResultado.setVisibility(View.GONE);
                    return;
                }


                double media = (nota1 + nota2) / 2;
                String status = media >= 6 ? "Aprovado!" : "Reprovado!";


                String resumo = String.format("Nome: %s\n" +
                                "Email: %s\n" +
                                "Idade: %d\n" +
                                "Disciplina: %s\n" +
                                "Nota 1º Bimestre: %.2f\n" +
                                "Nota 2º Bimestre: %.2f\n" +
                                "Média: %.2f\n" +
                                "Status: %s\n",
                        nome, email, idade, disciplina, nota1, nota2, media, status);

                tvResultado.setText(resumo);
                tvResultado.setVisibility(View.VISIBLE);
                tvErro.setVisibility(View.GONE);


                historicoBuilder.append(resumo).append("\n \n");
                tvHistorico.setText(historicoBuilder.toString());


                cpNome.setText("");
                cpEmail.setText("");
                cpIdade.setText("");
                cpDisciplina.setText("");
                cpNota1.setText("");
                cpNota2.setText("");
            }
        });

        btLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cpNome.setText("");
                cpEmail.setText("");
                cpIdade.setText("");
                cpDisciplina.setText("");
                cpNota1.setText("");
                cpNota2.setText("");


                tvResultado.setText("");
                tvErro.setText("");
                tvHistorico.setText("");
                tvErro.setVisibility(View.GONE);


                historicoBuilder.setLength(0);
            }
        });
    }
}
