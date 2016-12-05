package com.hhzmy.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.ehhzmy.hhzmy.R;
import com.hhzmy.hhzmy.ZhuCeActivity;
import com.hhzmy.view.Code;

/**
 * Created by liyan on 2016/11/8.
 */

public class Fragment4 extends Fragment {

    private EditText ed;
    ImageView vc_image; //图标
    Button vc_shuaixi, vc_ok; //确定和刷新验证码
    String getCode = null; //获取验证码的值
    EditText vc_code; //文本框的值
    private ImageView ce;

    private Button mBtnPassword;
    private EditText mEtPassword;
    private boolean mbDisplayFlg = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment4, null);

        mEtPassword = (EditText)view.findViewById(R.id.edit2);
        mBtnPassword = (Button)view.findViewById(R.id.btnPassword);
        mBtnPassword.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Log.d("AndroidTest", "mbDisplayFlg = " + mbDisplayFlg);
                if (!mbDisplayFlg) {
                    // display password text, for example "123456"
                    mEtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    // hide password, display "."
                    mEtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                mbDisplayFlg = !mbDisplayFlg;
                mEtPassword.postInvalidate();
            }
        });


        ed = (EditText) view.findViewById(R.id.edit);
        ed.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s == null || s.length() == 0)
                    return;
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < s.length(); i++) {
                    if (i != 3 && i != 8 && s.charAt(i) == ' ') {
                        continue;
                    } else {
                        sb.append(s.charAt(i));
                        if ((sb.length() == 4 || sb.length() == 9)
                                && sb.charAt(sb.length() - 1) != ' ') {
                            sb.insert(sb.length() - 1, ' ');
                        }
                    }
                }
                if (!sb.toString().equals(s.toString())) {
                    int index = start + 1;
                    if (sb.charAt(start) == ' ') {
                        if (before == 0) {
                            index++;
                        } else {
                            index--;
                        }
                    } else {
                        if (before == 1) {
                            index--;
                        }
                    }
                    ed.setText(sb.toString());
                    ed.setSelection(index);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        vc_image = (ImageView) view.findViewById(R.id.vc_image);
        vc_image.setImageBitmap(Code.getInstance().getBitmap());
        vc_code = (EditText) view.findViewById(R.id.edit3);

        getCode = Code.getInstance().getCode(); //获取显示的验证码
        Log.e("info", getCode + "----");
        vc_shuaixi = (Button) view.findViewById(R.id.vc_shuaixi);
        vc_shuaixi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vc_image.setImageBitmap(Code.getInstance().getBitmap());
                getCode = Code.getInstance().getCode();
            }
        });

        vc_ok = (Button) view.findViewById(R.id.lu);
        vc_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String v_code = vc_code.getText().toString().trim();
                if (v_code == null || v_code.equals("")) {
                    Toast.makeText(getActivity(), "没有填写验证码", Toast.LENGTH_SHORT).show();
                } else if (!v_code.equals(getCode)) {
                    Toast.makeText(getActivity(), "验证码填写不正确", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "操作成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                }
            }
        });

        ce = (ImageView) view.findViewById(R.id.ce);
        ce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), ZhuCeActivity.class);
                getActivity().startActivity(intent);
            }
        });


        return view;

    }

}
