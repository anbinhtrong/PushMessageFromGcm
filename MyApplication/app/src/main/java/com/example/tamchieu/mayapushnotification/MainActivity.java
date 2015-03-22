package com.example.tamchieu.mayapushnotification;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.io.IOException;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    Button btnRegId;
    EditText etRegId;
    GoogleCloudMessaging gcm;
    String regid;
    String PROJECT_NUMBER = "551222489279";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRegId = (Button)findViewById(R.id.btnRegId);
        etRegId = (EditText)findViewById(R.id.edit_text_id);

        btnRegId.setOnClickListener(this);

        Button helloBtn = (Button)findViewById(R.id.btnHello);

        helloBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog ThongBao  = new AlertDialog.Builder(MainActivity.this).create();
                ThongBao.setTitle("Thông Báo");
                ThongBao.setMessage("你好");
                ThongBao.show();
            }
        });
    }

    public void getRegId(){
        new AsyncTask<Void, Void, String>(){

            @Override
            protected String doInBackground(Void... params) {
                String msg = "";
                try{
                    if(gcm == null){
                        gcm = GoogleCloudMessaging.getInstance(getApplicationContext());
                    }
                    regid = gcm.register(PROJECT_NUMBER);
                    msg = "Device registered, registration ID=" + regid;
                    Log.i("GCM", "!!!!! " + regid);
                }catch (IOException ex){
                    msg = "Error: " + ex.getMessage();
                }
                return msg;
            }

            @Override
            protected void onPostExecute(String msg){
                etRegId.setText(msg + "\n");
            }
        }.execute(null, null, null);
    }

    public void onClick(View v){
        getRegId();
    }
}
