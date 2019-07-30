package com.t3h.buoi6;

import androidx.annotation.DrawableRes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Question {
    Random random = new Random();
    private int image;
    private String answer;

    public Question(@DrawableRes int image, String answer) {
        this.image = image;
        this.answer = answer;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getPickAnswer(){
        String pickAnswer = answer;
        for (int i = 0; i < 16 - answer.length(); i++) {
            int v = random.nextInt(26)+65;
            pickAnswer += (char) v;
        }
        String[] arr = pickAnswer.split("");
        List<String> result = Arrays.asList(arr);
        Collections.shuffle(result);

        pickAnswer = "";
        for (String var : result
             ) {
            pickAnswer+= var;
        }
        return pickAnswer;
    }
}
