package com.zl.pattern.cloneable;

import java.util.ArrayList;

public class Book implements Cloneable{

    private int price;
    private String title;
    private String content;
    private ArrayList<String> image = new ArrayList<String>();

    public Book() {
        super();
    }

    public Book(int price, String title, String content) {
        super();
        this.price = price;
        this.title = title;
        this.content = content;
    }

    public ArrayList<String> getImage(){
        return image;
    }

    public void addImage(String img){
        this.image.add(img);
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @SuppressWarnings("unchecked")
	@Override
    protected Object clone() throws CloneNotSupportedException {

        Book book = (Book)super.clone();
        book.image = (ArrayList<String>) this.image.clone();
        return book;
    }

    public void showBook(){
        System.out.println("=====Start=====");

        System.out.println("title£º"+title);
        for(String img : image){
            System.out.println("image name:"+img);
        }

        System.out.println("======End======");
    }
}