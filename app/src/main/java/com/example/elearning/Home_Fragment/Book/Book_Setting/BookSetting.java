package com.example.elearning.Home_Fragment.Book.Book_Setting;

public class BookSetting {

    public BookSetting(String book1Id,String book1Name){
        this.book1Id=book1Id;
        this.book1Name=book1Name;
    }

    private String book1Id=null;
    private String book1Name=null;

    public String getBook1Id() {
        return book1Id;
    }

    public void setBook1Id(String book1Id) {
        this.book1Id = book1Id;
    }

    public String getBook1Name() {
        return book1Name;
    }

    public void setBook1Name(String book1Name) {
        this.book1Name = book1Name;
    }

}
