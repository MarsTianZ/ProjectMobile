package com.example.viewbarang;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class data extends AsyncTask<String, Integer, String> {
    ArrayList<HashMap<String, String>> listItems = new ArrayList<>();

    @Override
    protected String doInBackground(String... strings) {
        String data = "";

        try {
            data = getUrl("http://192.168.1.7/webdasar/PBM/viewbarang.php");

            JSONObject json = new JSONObject(data);
            JSONArray array = json.getJSONArray("orders");
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);

                String id = String.valueOf(obj.getInt("id"));
                String nama = obj.getString("nama");
                String harga = obj.getString("harga");
                String stock = obj.getString("stok");

                HashMap<String, String> item = new HashMap<>();
                item.put("id", id);
                item.put("nama", nama);
                item.put("harga", harga);
                item.put("stok", stock);
                listItems.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("TAG", "doInBackground: " + e.getMessage());
        }

        return data;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

//        MainActivity.mTextView.setText(s);
        ListAdapter adapter = new SimpleAdapter(MainActivity.mContext, listItems, R.layout.list_item, new String[]{"id", "nama", "harga", "stok"}, new int[]{R.id.id, R.id.nama, R.id.harga, R.id.stock});
        MainActivity.mListView.setAdapter(adapter);
    }

    public String getUrl(String urlString) throws IOException {
        String data = "";
        InputStream inStream = null;
        HttpURLConnection connection = null;

        try {
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            inStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
            StringBuilder sb = new StringBuilder();

            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            data = sb.toString();

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("TAG", "getUrl: " + e.getMessage());
        } finally {
//            if (inStream != null) {
            inStream.close();
            connection.disconnect();
//            }
        }
        return data;
    }
}
