package cathode.httppostexample;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;

import org.apache.http.client.methods.HttpPost;

import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class result extends AppCompatActivity {

    TextView txt_result;
    String id,pw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        txt_result=(TextView)findViewById(R.id.txt_result);
        Intent intent = getIntent();

        id=intent.getStringExtra("id");
        pw=intent.getStringExtra("pw");

        new getResponse().execute(id,pw);




    }


    class getResponse extends AsyncTask<String, Void, Void> {




        @Override
        protected Void doInBackground(String... params) {
            /*
            * params[0] : id
            * params[1] : pw
            *
            * */
            HttpClient client = new DefaultHttpClient();
            String url= "http://222.99.71.114/exam.php" ;
            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

            nameValuePairs.add(new BasicNameValuePair("id",params[0]));
            nameValuePairs.add(new BasicNameValuePair("pw",params[1]));

            HttpPost post = new HttpPost(url);
            UrlEncodedFormEntity ent =null;
            try {
                ent = new UrlEncodedFormEntity(nameValuePairs, HTTP.UTF_8);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            HttpResponse responsePost = null;
            post.setEntity(ent);
            try {
                responsePost = client.execute(post);
            } catch (IOException e) {
                e.printStackTrace();
            }
            HttpEntity resEntity = responsePost.getEntity();
            if(resEntity!=null){
                try {
                    final String res = EntityUtils.toString(resEntity);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            txt_result.setText(res);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            return null;
        }
    }

}
