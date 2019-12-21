package com.example.salario;
//@Authors David Pereira, Brehme Firmino

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    EditText salBruto;
    Float salarioLiquido, salDesc, salarioBruto, inss;
    TextView res, perc;
    Button btnCalc, btnZerar;
    String autentica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        salBruto = (EditText) findViewById(R.id.salBruto);
        btnCalc = (Button) findViewById(R.id.btnCalc);
        btnZerar = (Button) findViewById(R.id.btnZerar);
        res = (TextView) findViewById(R.id.res);
        perc = (TextView) findViewById(R.id.perc);


        //Criando a Mascara de Editext

        SimpleMaskFormatter number = new SimpleMaskFormatter("NNNN.NN");//Numero de Carateres a ser computado, os N é
        // o lugar dos caracteres com limite de 9 digitos
        MaskTextWatcher mtw = new MaskTextWatcher(salBruto, number);//Criando o objeto com a mascara
        salBruto.addTextChangedListener(mtw);//adicionando mascara



        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salario();

            }

        });




        btnZerar.setOnClickListener(new View.OnClickListener() {//Resertando os valores
            @Override
            public void onClick(View view) {
                if (salBruto.getText().length() != 0) {
                    salBruto.setText("");


                    res.setTextColor(Color.BLACK);//Colocando a cor Preta
                    perc.setText("Percentual de Desc. (%)");
                    res.setText("Salário á receber: R$");
                }
                else {

                    res.setTextColor(Color.RED);//Colocando a cor Preta
                    res.setText("Nenhum dado a resetar");
                }
            }
        });


    }

    public void salario() {//Função Salario,  funcoes devem ficar fora do metodo Oncreate

        if (salBruto.getText().length() != 0) {//Verificando se Existe ALGUM valor dentro do Editext

            salarioBruto = Float.parseFloat(salBruto.getText().toString());//Convertendo o EdiText em Float
            String formato = "R$ #,##0.00";//Var com o Formato Moeda
            DecimalFormat arredondar = new DecimalFormat(formato);//Arredonda resultados em valor moeda

            if (salarioBruto <= 1400) {
                inss = 0.08f;//Tem que colocar o f após os numeros para a variavel reconhecer Float
                salDesc = salarioBruto * inss;
                salarioLiquido = salarioBruto - salDesc;

                res.setTextColor(Color.BLACK);
                perc.setText("O Desconto de INSS é de 8%");
                res.setText("Salário à receber: " + arredondar.format(salarioLiquido));

            }else if ((salarioBruto > 1400) && (salarioBruto < 2333)) {

                inss = 0.09f;//Tem que colocar o f após os numeros para a variavel reconhecer Float
                salDesc = salarioBruto * inss;
                salarioLiquido = salarioBruto - salDesc;

                res.setTextColor(Color.BLACK);//Colocando a cor Preta
                perc.setText("O Desconto de INSS é de  9%");
                res.setText("Salário à receber: " + arredondar.format(salarioLiquido));

            }else{
                inss = 0.11f;//Tem que colocar o f após os numeros para a variavel reconhecer Float
                salDesc = salarioBruto * inss;
                salarioLiquido = salarioBruto - salDesc;

                res.setTextColor(Color.BLACK);//Colocando a cor Preta
                perc.setText("O Desconto de INSS é de 11%");
                res.setText("Salário à receber: " + arredondar.format(salarioLiquido));
                }

        }else{
             res.setTextColor(Color.RED);//Colocando a cor Vermelha
             res.setText("Por Favor, digite o valor do Salário.");
            }


    }
}


