package com.bradleyramunas.calhacks50.data.api.question;


import com.google.gson.annotations.Expose;

public class QuestionJson {
    @Expose
    public int id;

    @Expose
    public String text;

    @Expose
    public String category;

    @Expose
    public String question;

    @Expose
    public String answer;
}
