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

import org.espiritolivre.revista.android.R;

import android.app.ListActivity;
import android.os.Bundle;
// Comente a linha abaixo se estiver desenvolvendo para 2.x
//import android.os.StrictMode;
import android.content.Intent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ArrayAdapter;


public class FeedReaderActivity extends ListActivity {
    /** Called when the activity is first created. */
	String theLastFeed = "";
	String streamTitle = "";
	private RSSFeed myRSSFeed = null;
	
	//private List<String> item = new ArrayList<String>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedreader);
        
        // As vers�es do Android superiores � 3  implementam um  modo
        // protegido que impede o sistema de acessar  discos e a rede
        // na atividade principal. Assim vamos desabilitar o recurso.
        // O correto, no entanto, seria utilizarmos atividades ass�n-
        // cronas. Note que esta declara��o n�o � necess�ria se   es-
        // tamos desenvolvendo para as vers�es 1.x e 2.x , ou   seja,
        // voc� deve comentar as duas linhas abaixo se   tiver  essas
        // vers�es como alvo.
        
        //StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitNetwork().build();
        //StrictMode.setThreadPolicy(policy);
        
        // Obtendo a URL e o t�tulo do feed
        Bundle extras = getIntent().getExtras();
        // Temos extras?
        if (extras == null) {
        	return;
        }
        // Colocando o t�tulo e a URL em vari�veis
        String theFeedTitle = extras.getString("feedTitle");
        String theFeedURL = extras.getString("feedURL");
        // �ltima verifica��o e exibi��o do feed
        if (theFeedTitle != null && theFeedURL != null) {
        	
        	theLastFeed = theFeedTitle;
        	
        	// Aqui come�a a implementa��o do leitor RSS!
        	// Cr�ditos aos tutoriais de http://android-er.blogspot.com/
        	        	
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
        		
        		// Modifica��o vers�o 2012.0.4
        		myRSSFeed = myRSSHandler.getFeed();
        		// Fim da modifica��o
        		
        	} catch (MalformedURLException e) {
        		e.printStackTrace();
        		
        	} catch (ParserConfigurationException e) {
        		e.printStackTrace();
        		
        	} catch (SAXException e) {
        		e.printStackTrace();
        		
        	} catch (IOException e) {
        		e.printStackTrace();
        		
        	}
        	
        	// Modifica��o vers�o 2012.0.3
        	if (myRSSFeed!=null)
        	 {
        	  TextView feedTitle = (TextView)findViewById(R.id.feedtitle);
        	  TextView feedDescription = (TextView)findViewById(R.id.feeddescription);
        	  TextView feedPubdate = (TextView)findViewById(R.id.feedpubdate);
        	  TextView feedLink = (TextView)findViewById(R.id.feedlink);
        	  feedTitle.setText(myRSSFeed.getTitle());
        	  feedDescription.setText(myRSSFeed.getDescription());
        	  feedPubdate.setText(myRSSFeed.getPubdate());
        	  feedLink.setText(myRSSFeed.getLink());
    		// Fim da modifica��o
        	
        	  ArrayAdapter<RSSItem> adapter =
        			   new ArrayAdapter<RSSItem>(this,
        			     android.R.layout.simple_list_item_1,myRSSFeed.getList());
        			  setListAdapter(adapter);
        	
        }
        }
  }

        @Override
        protected void onListItemClick(ListView l, View v, int position, long id) {
         // TODO Auto-generated method stub
         Intent intent = new Intent(this,ShowDetailsActivity.class);
         Bundle bundle = new Bundle();
         bundle.putString("keyTitle", myRSSFeed.getItem(position).getTitle());
         bundle.putString("keyDescription", myRSSFeed.getItem(position).getDescription());
         bundle.putString("keyLink", myRSSFeed.getItem(position).getLink());
         bundle.putString("keyPubdate", myRSSFeed.getItem(position).getPubdate());
   
         intent.putExtras(bundle);
              startActivity(intent);
        }
        
            
    @Override
    public void finish(){
    	Intent data = new Intent();
    	data.putExtra("returnKey", "�ltimo feed lido: " + theLastFeed);
    	setResult(RESULT_OK, data);
    	super.finish();
    }
}
