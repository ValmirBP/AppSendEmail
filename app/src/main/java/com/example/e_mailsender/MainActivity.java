package com.example.e_mailsender;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edtTo, edtSub, edtMsg;
    TextView txtTo, txtSub, txtMsg;
    Button btnSend;
    String emailDest, sub, msg;
    Intent intent;
    Boolean ok = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtTo = findViewById(R.id.txtTo); txtMsg = findViewById(R.id.txtMsg);
        txtSub = findViewById(R.id.txtSub); btnSend = findViewById(R.id.btnSend);

// Inserção de um novo tipo de fonte no layout.

        Typeface font = Typeface.createFromAsset(getAssets(),
                "print_bold_tt.ttf");

        txtTo.setTypeface(font);txtMsg.setTypeface(font);
        txtSub.setTypeface(font); btnSend.setTypeface(font);

        edtTo = findViewById(R.id.edtTo); edtSub = findViewById(R.id.edtSub);
        edtMsg = findViewById(R.id.edtMsg);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                intent = new Intent(Intent.ACTION_SEND);
                process();
                infoInst();
            }
        });
    }

    public void process() {
        emailDest = edtTo.getText().toString();
        sub = edtSub.getText().toString();
        msg = edtMsg.getText().toString();
    }

// checar as informações inseridas

    public void infoInst() {

        Drawable icon =
                getResources().getDrawable(R.drawable.ic_warning_icon);
        if (icon != null){
            icon.setBounds(0,0,icon.getIntrinsicWidth(),icon.getIntrinsicHeight());

        }
        ok = true;

        try {

            if (emailDest != null && !emailDest.isEmpty()) {
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{emailDest});
            } else {
                edtTo.setError("Campo obrigatáorio",icon);
                ok = false;
            }
            if (sub != null && !sub.isEmpty()) {
                intent.putExtra(Intent.EXTRA_SUBJECT, sub);
            } else {
                edtSub.setError("Campo obrigatáorio",icon);
                ok = false;

            }
            if (msg != null && !msg.isEmpty()) {
                intent.putExtra(Intent.EXTRA_TEXT, msg);
            } else {
                edtMsg.setError("Campo obrigatáorio",icon);
                ok = false;
            }
            if (ok){
                intent.setType("message/rfc822");
                startActivity(Intent.createChooser(intent, "selecione um app"));
            }
        }catch (Exception e ){
        }
    }
}