package com.cly.customview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cly.customview.custom.PieView;
import com.cly.customview.view.BindView;
import com.cly.customview.view.Butterknife;
import com.cly.customview.view.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.pv)
    private PieView viewById;
    @BindView(R.id.et_h)
    private EditText et_h;
    @BindView(R.id.et_w)
    private EditText et_w;
    @BindView(R.id.bu)
    private Button bu;
    @BindView(R.id.et_d)
    private EditText et_d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Butterknife.bind(this);
        viewById.setTextSize(30);
    }


    @OnClick({R.id.bu})
    public void onClick(View view){
        change();
    }

    private void change() {
     String etw=   et_w.getText().toString().trim();
     String eth =  et_h.getText().toString().trim();
     String etd =   et_d.getText().toString().trim();

   viewById.setCircleWidthAndHight(Integer.parseInt(etw), Integer.parseInt(eth),Float.parseFloat(etd));
    }
}
