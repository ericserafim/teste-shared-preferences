package cordefundosalvo.ericserafim.com.cordefundosalvo;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {

    private RadioGroup rgCores;
    private RadioButton rbSelecionado;
    private Button botaoSalvar;
    private RelativeLayout layout;

    private static final String ARQUIVO_PREFERENCIAS = "ArquivoPreferencias";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = (RelativeLayout) findViewById(R.id.layoutId);
        rgCores = (RadioGroup) findViewById(R.id.rgCores);
        botaoSalvar = (Button) findViewById(R.id.botaoSalvarId);

        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idRbSelecionado = rgCores.getCheckedRadioButtonId();

                if (idRbSelecionado > 0) {
                    rbSelecionado = (RadioButton) findViewById(idRbSelecionado);
                    SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_PREFERENCIAS, MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    String corEscolhida = rbSelecionado.getText().toString();
                    editor.putString("cor", corEscolhida);
                    editor.commit();

                    setBackgroud(corEscolhida);
                }
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_PREFERENCIAS, MODE_PRIVATE);
        if (sharedPreferences.contains("cor")) {
            String cor = sharedPreferences.getString("cor", "branco");
            setBackgroud(cor);
        }
    }

    private void setBackgroud(String cor) {
        switch (cor){
            case "Azul":
                layout.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBlue));
                break;
            case "Laranja":
                layout.setBackgroundColor(ContextCompat.getColor(this, R.color.colorOrange));
                break;
            case "Verde":
                layout.setBackgroundColor(ContextCompat.getColor(this, R.color.colorGreen));
                break;
            default:
                layout.setBackgroundColor(ContextCompat.getColor(this, android.R.color.white));
                break;
        }

    }
}
