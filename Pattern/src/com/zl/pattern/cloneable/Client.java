package com.zl.pattern.cloneable;

public class Client {
	public static void main(String[] args) {
        try {
            Book book1 = new Book(50," È1","ƒ⁄»›");
            book1.addImage("Õº1");
            book1.showBook();

            Book book2 = (Book) book1.clone();
            book2.showBook();

            book2.setTitle(" È2");
            book2.addImage("Õº2");
            book2.showBook();

            book1.showBook();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

    }
}
