package cathode.httppostexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button btn_get_res;
    EditText input_id,input_pw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input_id=(EditText)findViewById(R.id.input_id);
        input_pw=(EditText)findViewById(R.id.input_pw);
        btn_get_res=(Button)findViewById(R.id.btn_getres);

        btn_get_res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,result.class);
                intent.putExtra("id",input_id.getText().toString());
                intent.putExtra("pw",input_pw.getText().toString());
                startActivity(intent);
                finish();
            }
        });


    }
}
