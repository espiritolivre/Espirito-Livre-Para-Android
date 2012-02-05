package org.espiritolivre.revista.android;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class FeedReaderActivity extends FragmentActivity {

    String theLastFeed = "";
    String streamTitle = "";
    private RSSFeed myRSSFeed = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedreader);
        
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_bg));
        

        // As versoes do Android superiores a 3  implementam um  modo
        // protegido que impede o sistema de acessar  discos e a rede
        // na atividade principal. Assim vamos desabilitar o recurso.
        // O correto, no entanto, seria utilizarmos atividades assin-
        // cronas. Note que esta declaracao nao e necessaria se   es-
        // tamos desenvolvendo para as versoes 1.x e 2.x , ou   seja,
        // voce deve comentar as duas linhas abaixo se   tiver  essas
        // versoes como alvo.

        //StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitNetwork().build();
        //StrictMode.setThreadPolicy(policy);

        // Obtendo a URL e o titulo do feed
        Bundle extras = getIntent().getExtras();
        // Temos extras?
        if (extras == null) {
            return;
        }
        // Colocando o titulo e a URL em variaveis
        String theFeedTitle = extras.getString("feedTitle");
        String theFeedURL = extras.getString("feedURL");
        // ultima verificacao e exibicao do feed
        if (theFeedTitle != null && theFeedURL != null) {

            theLastFeed = theFeedTitle;

            // Aqui comeca a implementacao do leitor RSS!
            // Creditos aos tutoriais de http://android-er.blogspot.com/

            // No manipulador a seguir vamos obter o feed RSS de fr_URL

            try {
                URL rssURL = new URL(theFeedURL);
                SAXParserFactory mySAXParserFactory = SAXParserFactory.newInstance();
                SAXParser mySAXParser = mySAXParserFactory.newSAXParser();
                XMLReader myXMLReader = mySAXParser.getXMLReader();
                RSSHandler myRSSHandler = new RSSHandler();
                myXMLReader.setContentHandler(myRSSHandler);
                InputSource myInputSource = new InputSource(rssURL.openStream());
                myXMLReader.parse(myInputSource);

                // Modificacao versao 2012.0.4
                myRSSFeed = myRSSHandler.getFeed();
                // Fim da modificacao

            } catch (MalformedURLException e) {
                e.printStackTrace();

            } catch (ParserConfigurationException e) {
                e.printStackTrace();

            } catch (SAXException e) {
                e.printStackTrace();

            } catch (IOException e) {
                e.printStackTrace();

            }

            // Modificacao versao 2012.0.3
            if (myRSSFeed != null) {
                TextView feedTitle = (TextView) findViewById(R.id.feedtitle);
                TextView feedDescription = (TextView) findViewById(R.id.feeddescription);
                TextView feedPubdate = (TextView) findViewById(R.id.feedpubdate);
                TextView feedLink = (TextView) findViewById(R.id.feedlink);
                feedTitle.setText(myRSSFeed.getTitle());
                feedDescription.setText(myRSSFeed.getDescription());
                feedPubdate.setText(myRSSFeed.getPubdate());
                feedLink.setText(myRSSFeed.getLink());
                // Fim da modificacao

                ArrayAdapter<RSSItem> adapter =
                        new ArrayAdapter<RSSItem>(this,
                                android.R.layout.simple_list_item_1, myRSSFeed.getList());
                
                ListView feedlist = (ListView) findViewById(R.id.feedlist);
                feedlist.setAdapter(adapter);
                feedlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {
				        Intent intent = new Intent(FeedReaderActivity.this, ShowDetailsActivity.class);
				        Bundle bundle = new Bundle();
				        bundle.putString("keyTitle", myRSSFeed.getItem(position).getTitle());
				        bundle.putString("keyDescription", myRSSFeed.getItem(position).getDescription());
				        bundle.putString("keyLink", myRSSFeed.getItem(position).getLink());
				        bundle.putString("keyPubdate", myRSSFeed.getItem(position).getPubdate());

				        intent.putExtras(bundle);
				        startActivity(intent);
					}
                	
				});

            }
        }
    }

    @Override
    public void finish() {
    	// TODO: Não tem mais a mensaagem do último feed lido. Melhor gravar os que foram lidos.
        Intent data = new Intent();
        data.putExtra("returnKey", "Último feed lido: " + theLastFeed);
        setResult(RESULT_OK, data);
        super.finish();
    }
}
