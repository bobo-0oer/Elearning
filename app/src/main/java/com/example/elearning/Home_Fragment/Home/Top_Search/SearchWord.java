package com.example.elearning.Home_Fragment.Home.Top_Search;

public class SearchWord {
    public SearchWord(String Word, String Chinese){
        this.searchWord=Word;
        this.searchChinese=Chinese;
    }

    String searchWord=null;
    String searchChinese=null;

    public String getSearchWord() {
        return searchWord;
    }

    public void setSearchWord(String searchWord) {
        this.searchWord = searchWord;
    }

    public String getSearchChinese() {
        return searchChinese;
    }

    public void setSearchChinese(String searchChinese) {
        this.searchChinese = searchChinese;
    }
}
