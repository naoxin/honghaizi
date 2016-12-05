package com.hhzmy.hhzmy;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ehhzmy.hhzmy.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ZhuCeActivity extends AppCompatActivity {

    private TextView zh;
    String phonenum = "";
    private EditText phone;
    private CheckBox checkBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhu_ce);
        zh = (TextView) findViewById(R.id.zhangcheng);
        bian();

        phone = (EditText) findViewById(R.id.pohne);
        checkBox = (CheckBox) findViewById(R.id.checkbox1);

    }
    public  void xiayibu(View view) {
        phonenum=phone.getText().toString().trim();
        if (!isPhoneNumberValid(phonenum)){
            Toast.makeText(ZhuCeActivity.this, "电话格式不正确,请检查！", Toast.LENGTH_SHORT).show();
        } else if(phonenum.length()==0){
            Toast.makeText(ZhuCeActivity.this, "请输入电话号码！", Toast.LENGTH_SHORT).show();
        }else if(!checkBox.isChecked()){
            Toast.makeText(ZhuCeActivity.this, "需要同意", Toast.LENGTH_SHORT).show();
        }else {
//            Intent intent = new Intent(ZhuCeActivity.this,ZhuCeActivity.class);
//            startActivity(intent);
        }
    }


    public static boolean isPhoneNumberValid(String mobiles){
        Matcher m = null;
        if(mobiles.trim().length()>0){
            Pattern p = Pattern.compile("^((13[0-9])|(15[0-3])|(15[7-9])|(18[0,5-9]))\\d{8}$");
            m= p.matcher(mobiles);
        }
        else{
            return false;
        }
        return m.matches();
    }


    public void bian() {
        SpannableString word = new SpannableString("同意苏宁易购会员章程和易付宝协议");

        word.setSpan(new ForegroundColorSpan(Color.RED), 2, 10,
                Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        word.setSpan(new ForegroundColorSpan(Color.RED), 11, word.length(),
                Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        zh.append(word);
    }
}
