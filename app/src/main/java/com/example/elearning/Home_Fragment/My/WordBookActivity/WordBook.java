package com.example.elearning.Home_Fragment.My.WordBookActivity;

public class WordBook {

    public WordBook(int bookId,String bookName){
        this.bookId=bookId;
        this.bookName=bookName;
    }

    private int bookId=0;
    private String bookName=null;

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}
