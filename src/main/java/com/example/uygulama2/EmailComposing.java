package com.example.uygulama2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EmailComposing extends AppCompatActivity {
    private String SENDTO;
    private String TOPIC;
    private String CONTENT;
    private String CC = "";
    private String BCC = "";
    private EditText sendTo;
    private EditText emailTopic;
    private EditText emailContent;
    private EditText emailCC;
    private EditText emailBCC;
    private TextView attachmentText;
    private Uri URI = null;
    private static final int PICK_FROM_GALLERY = 101;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_composing);

        final Button sendButton = findViewById(R.id.emailSendBttn);
        final Button attachments = findViewById(R.id.attachmentsBttn);
        sendTo = findViewById(R.id.editSendAnddress);
        emailTopic = findViewById(R.id.editTopic);
        emailContent = findViewById(R.id.editContent);
        emailCC = findViewById(R.id.editCC);
        emailBCC = findViewById(R.id.editBCC);
        attachmentText = findViewById(R.id.attachmentsShow);
        sendButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendEmail();
            }
        });
        attachments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFolder();
            }
        });
    }
    protected void openFolder(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.putExtra("return-data", true);
        startActivityForResult(Intent.createChooser(intent, "Complete action using"), PICK_FROM_GALLERY);

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_FROM_GALLERY && resultCode == RESULT_OK) {
            URI = data.getData();
            attachmentText.setText(URI.getLastPathSegment());
            attachmentText.setVisibility(View.VISIBLE);
        }
    }
    protected void sendEmail(){
        try {
            SENDTO = sendTo.getText().toString();
            TOPIC = emailTopic.getText().toString();
            CONTENT = emailContent.getText().toString();
            CC = emailCC.getText().toString();
            BCC =emailBCC.getText().toString();
            final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
            emailIntent.setType("text/plain");
            emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, SENDTO);
            emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, TOPIC);
            emailIntent.putExtra(Intent.EXTRA_CC, CC);
            emailIntent.putExtra(Intent.EXTRA_BCC, BCC);

            if (URI != null) {
                emailIntent.putExtra(Intent.EXTRA_STREAM, URI);
            }
            emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, CONTENT);
            this.startActivity(Intent.createChooser(emailIntent, "Sending email..."));
        } catch (Throwable t) {
            Toast.makeText(this, "Request failed try again: "+ t.toString(), Toast.LENGTH_LONG).show();
        }
    }

   /*
        Intent selectorIntent = new Intent(Intent.ACTION_SENDTO , Uri.fromParts(
                "mailto", SENDTO, null));
        selectorIntent.setData(Uri.parse("mailto:"));


        final Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setDataAndType(Uri.parse("mailto:"),"text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, SENDTO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_BCC, BCC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, TOPIC);
        emailIntent.putExtra(Intent.EXTRA_TEXT, CONTENT);
        emailIntent.setSelector( selectorIntent );
        startActivity(Intent.createChooser(emailIntent, "Send email..."));
*/

}
