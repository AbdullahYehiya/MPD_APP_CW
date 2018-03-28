package com.example.abdullahyehiya.mpd_app_cw;

/**
 * Created by abdullahyehiya on 02/03/2018.
 * S1512605
 * Abdullah Yehiya
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;



public class RssReader extends AsyncTask<Void,Void,Void> {

    Context mContext;
    String address = "http://www.trafficscotland.org/rss/feeds/currentincidents.aspx";
    ProgressDialog progressDialog;
    ArrayList<RssItems> rssItems;
    RecyclerView recyclerView;
    URL url;

    public RssReader(Context context,RecyclerView recyclerView) {
        this.recyclerView=recyclerView;
        this.mContext = context;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Fetching Data.....");
    }

    @Override
    protected void onPreExecute() {
        progressDialog.show();
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        progressDialog.dismiss();
        RssAdapter adapter=new RssAdapter(mContext,rssItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected Void doInBackground(Void... params) {
        ProcessXml(Getdata());
        return null;
    }


    private void ProcessXml(Document data) {
        if (data != null) {
            rssItems=new ArrayList<>();
            Element root=data.getDocumentElement();
            Node channel=root.getChildNodes().item(1);
            NodeList items=channel.getChildNodes();
            for (int i=0;i<items.getLength();i++){
                Node currentchild=items.item(i);
                if (currentchild.getNodeName().equalsIgnoreCase("item")){
                    RssItems item=new RssItems();
                    NodeList itemchilds=currentchild.getChildNodes();
                    for (int j=0;j<itemchilds.getLength();j++){
                        Node current=itemchilds.item(j);
                        if (current.getNodeName().equalsIgnoreCase("title")){
                            item.setTitle(current.getTextContent());
                        }else if (current.getNodeName().equalsIgnoreCase("description")){
                            item.setDescription(current.getTextContent());
                        }else if (current.getNodeName().equalsIgnoreCase("pubDate")) {
                            item.setPubDate(current.getTextContent());
                        }else if (current.getNodeName().equalsIgnoreCase("link")){
                            item.setLink(current.getTextContent());
                        }else if (current.getNodeName().equalsIgnoreCase("georss:point")){
                            item.setCoordinates(current.getTextContent());
                        }
                        //Log.d("textcontent",current.getTextContent());
                    }
                    rssItems.add(item);
                    //Log.d("itemTitle",item.getTitle());
                    //Log.d("itemDescription",item.getDescription());
                    //Log.d("itemLink",item.getLink());
                    //Log.d("itempubDate",item.getPubDate());
                    //Log.d("item")

                    //Log.d("coordinates",item.getCoordinates());
                }

            }
            //Log.d("Root", data.getDocumentElement().getNodeName());
        }
    }

    public Document Getdata() {
        try {
            url = new URL(address);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream inputStream = connection.getInputStream();
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document xmlDoc = builder.parse(inputStream);
            return xmlDoc;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
