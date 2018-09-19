package br.com.thiago.viajensalura.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import br.com.thiago.viajensalura.R;
import br.com.thiago.viajensalura.ui.adapter.ListaPacotesAdapter;
import br.com.thiago.viajensalura.dao.PacoteDAO;
import br.com.thiago.viajensalura.model.Pacote;

import static br.com.thiago.viajensalura.ui.activity.PacoteActivityConstantes.CHAVE_PACOTE;

public class ListaPacotesActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Pacotes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pacotes);

        setTitle(TITULO_APPBAR);
        configuraLista();

    }

    private void configuraLista() {
        ListView listaDePaotes = findViewById(R.id.lista_pacote_listview);
        final List<Pacote> pacotes = new PacoteDAO().lista();
        listaDePaotes.setAdapter(new ListaPacotesAdapter(pacotes, this));
        listaDePaotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                Pacote pacoteClicado = pacotes.get(posicao);
                transfereParaResumoPacote(pacoteClicado);
            }
        });
    }

    private void transfereParaResumoPacote(Pacote pacote) {
        Intent intent = new Intent(ListaPacotesActivity.this, ResumoPacoteActivity.class);
        intent.putExtra(CHAVE_PACOTE, pacote);
        startActivity(intent);
    }
}
