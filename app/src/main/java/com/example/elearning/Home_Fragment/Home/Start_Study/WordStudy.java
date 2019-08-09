package com.example.elearning.Home_Fragment.Home.Start_Study;

public class WordStudy {

    public WordStudy(String word,String voice,String phonetic,String knownTime,String lastLearnTime,String chinese,String exampleSentence){
        this.word=word;
        this.voice=voice;
        this.phonetic=phonetic;
        this.knownTime=knownTime;
        this.lastLearnTime=lastLearnTime;
        this.chinese=chinese;
        this.exampleSentence=exampleSentence;

    }

    String word=null;
    String voice=null;
    String phonetic=null;
    String knownTime=null;
    String lastLearnTime=null;
    String chinese=null;
    String exampleSentence=null;

    public String getExampleSentence() {
        return exampleSentence;
    }

    public void setExampleSentence(String exampleSentence) {
        this.exampleSentence = exampleSentence;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getVoice() {
        return voice;
    }

    public void setVoice(String voice) {
        this.voice = voice;
    }

    public String getPhonetic() {
        return phonetic;
    }

    public void setPhonetic(String phonetic) {
        this.phonetic = phonetic;
    }

    public String getKnownTime() {
        return knownTime;
    }

    public void setKnownTime(String knownTime) {
        this.knownTime = knownTime;
    }

    public String getLastLearnTime() {
        return lastLearnTime;
    }

    public void setLastLearnTime(String lastLearnTime) {
        this.lastLearnTime = lastLearnTime;
    }

    public String getChinese() {
        return chinese;
    }

    public void setChinese(String chinese) {
        this.chinese = chinese;
    }


}
