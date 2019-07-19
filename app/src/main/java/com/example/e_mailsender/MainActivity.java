package com.example.e_mailsender;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText edtTo, edtSub, edtMsg;
    Button btnSend;
    String emailDest, assunto, msg;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        edtTo = findViewById(R.id.edtTo);
        edtSub = findViewById(R.id.edtSub);
        edtMsg = findViewById(R.id.edtMsg);

        btnSend = findViewById(R.id.btnSend);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                process();

                intent = new Intent(Intent.ACTION_SEND);

                intent.putExtra(Intent.EXTRA_EMAIL,new String[]{emailDest});
                intent.putExtra(Intent.EXTRA_SUBJECT,assunto);
                intent.putExtra(Intent.EXTRA_TEXT,msg);

                intent.setType("message/rfc822");

                startActivity(Intent.createChooser(intent,"selecione um app"));
            }
        });
    }

    public void process()

    {
        emailDest = edtTo.getText().toString();
        assunto = edtSub.getText().toString();
        msg = edtMsg.getText().toString();
    }
}