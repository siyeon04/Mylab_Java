package mylab.library.entity;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private String name;
    private List<Book> books;

    public Library(String name) {
        this.name = name;
        this.books = new ArrayList<>(); // 빈 리스트로 초기화
    }

    public String getName() { return name; }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("도서가 추가되었습니다: " + book.getTitle());
    }

    // 제목으로 검색 -> 일치하는 첫 번째 도서 반환
    public Book findBookByTitle(String title) {
        for (Book b : books)
            if (b.getTitle().equals(title)) return b;
        return null;
    }
    
    // 저자로 검색 -> 같은 저자의 책이 여러 권 있을 수 있으므로 List로 반환
    public List<Book> findBooksByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        for (Book b : books)
            if (b.getAuthor().equals(author)) result.add(b);
        return result;
    }
    
    // ISBN: 도서의 고유 번호 -> 결과가 항상 1 단일 객체 반환
    public Book findBookByISBN(String isbn) {
        for (Book b : books)
            if (b.getIsbn().equals(isbn)) return b;
        return null;
    }

    // 대출중이면 false 반환
    public boolean checkOutBook(String isbn) {
        Book book = findBookByISBN(isbn);
        return book != null && book.checkOut();
    }

    public boolean returnBook(String isbn) {
        Book book = findBookByISBN(isbn);
        if (book != null) { book.returnBook(); return true; }
        return false;
    }

    public List<Book> getAvailableBooks() {
        List<Book> result = new ArrayList<>();
        for (Book b : books) if (b.isAvailable()) result.add(b);
        return result;
    }

    public List<Book> getAllBooks() { return books; }

    public int getTotalBooks() { return books.size(); }

    public int getAvailableBooksCount() {
        int count = 0;
        for (Book b : books) if (b.isAvailable()) count++;
        return count;
    }

    public int getBorrowedBooksCount() {
        return getTotalBooks() - getAvailableBooksCount();
    }
}