package com.t3h.buoi6;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.widget.AppCompatButton;

public class AnswerButton extends AppCompatButton implements View.OnClickListener {
    private AnswerButton btn;

    public AnswerButton(Context context) {
        super(context);
        init();
    }

    public AnswerButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AnswerButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (getText().toString().isEmpty() == true) {
            return;
        }
        btn.setVisibility(VISIBLE);
        btn.setText(getText().toString());
        setText("");
    }

    public void setResource(boolean isCorrect){
        if (isCorrect == true) {
            setBackgroundResource(R.drawable.ic_tile_true);
        }else{
            setBackgroundResource(R.drawable.ic_tile_false);
        }

    }

    public void setText(AnswerButton btn){
        this.btn = btn;
        setText(btn.getText());
        btn.setVisibility(INVISIBLE);
    }
}
