package com.example.elearning.Home_Fragment.Home.Unknown_Word;

public class UnknownWord {
    public UnknownWord(String Word, String Chinese){
        this.Word=Word;
        this.Chinese=Chinese;
    }

    String Word=null;
    String Chinese=null;

    public String getWord() {
        return Word;
    }

    public void setWord(String word) {
        Word = word;
    }

    public String getChinese() {
        return Chinese;
    }

    public void setChinese(String chinese) {
        Chinese = chinese;
    }
}
