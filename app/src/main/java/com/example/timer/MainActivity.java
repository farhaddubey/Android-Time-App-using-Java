import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    public class BG extends AsyncTask<String, Void, String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d("HarryBG", "onPreExecute: ran");
        }

        @Override
        protected String doInBackground(String... urls) {
            Log.d("HarryBG", "doInBackground: ran");
            String result = "";
            URL url;
            HttpURLConnection conn;
            try {
                url = new URL(urls[0]);
                conn = (HttpURLConnection) url.openConnection();
                InputStream in = conn.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();
                while (data != -1) {
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }
            }
            catch(Exception e){
                e.printStackTrace();
                return "Something went wrong";
            }
            return result;


        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("HarryBG", "onPostExecute: ran");
            Log.d("HarryBG", s);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        BG myTask = new BG();
//        myTask.execute("https://www.codewithharry.com/");

        final TextView textView = (TextView) findViewById(R.id.textView2);



        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://www.google.com";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        textView.setText("Response is: "+ response.substring(0,500));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText("That didn't work!");
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
    public void leLo(View view){
        Toast.makeText(this, "Selfie le li maine", Toast.LENGTH_SHORT).show();
    }
}