package com.example.bookStore.controller;

import com.example.bookStore.model.entities.*;
import com.example.bookStore.service.BookService;
import com.example.bookStore.service.CategoryService;
import com.example.bookStore.validation.AddressValidation;
import com.example.bookStore.validation.BookValidation;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Controller
@RequestMapping("bookStore")
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/books")
    public String showBooksPage(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "Admin_view/all-Books";
    }

    @GetMapping("/deleteBook/{id}")
    private String deleteBook(@PathVariable int id) {
        bookService.deleteBook(id);
        return "redirect:/bookStore/books";
    }

    @GetMapping("/editBookPage/{id}")
    private String showUpdateBookPage(Model model, @PathVariable int id) {
        Optional<Book> book = bookService.getBook(id);
        List<Category> names = categoryService.getAllCategoriesNames();

        String base64Image = Base64.getEncoder().encodeToString(book.get().getImage());

        model.addAttribute("book", book.get());
        model.addAttribute("image",base64Image);

        model.addAttribute("categoriesNames", names);
        return "Admin_view/edit-book";
    }


    @PostMapping("/editBook")
    private String updateBook(@ModelAttribute("book") @Valid Book book,Model model,BindingResult bindingResult, @RequestParam("imageFile") MultipartFile imageFile) throws Exception{
        if (bindingResult.hasErrors()) {
            // There are validation errors, return to the form page
            return "/bookStore/editBookPage?id="+book.getId();
        }

        if (!imageFile.isEmpty()) {
            byte[] imageData = imageFile.getBytes();
            book.setImage(imageData);
        }
        return update(book,model);
    }


    @GetMapping("/showAddBookPage")
    private String showAddBookPage(Model model,Book book) {
        if(book==null)
            model.addAttribute("book",new Book());
        else{
            model.addAttribute("book",book);
        }
        List<Category> names = categoryService.getAllCategoriesNames();
        model.addAttribute("categoriesNames", names);
        return "Admin_view/add-book";
    }

    @PostMapping("/addBook")
    private String addBook(@ModelAttribute("book") @Valid Book book,Model model, BindingResult bindingResult, @RequestParam("imageFile") MultipartFile imageFile) throws IOException {

        if (bindingResult.hasErrors()) {
            // There are validation errors, return to the form page
            return "/bookStore/showAddBookPage";
        }

        if (!imageFile.isEmpty()) {
            byte[] imageData = imageFile.getBytes();
            book.setImage(imageData);
        }

         return saveBook(book,model);
    }

    public String saveBook(Book book,Model model){
        if(BookValidation.checkAllFields(book)){
            bookService.saveBook(book);
            return "redirect:/bookStore/books";
        }
        checkBookErrorFields(book,model);
        List<Category> names = categoryService.getAllCategoriesNames();
        model.addAttribute("categoriesNames", names);
       return showAddBookPage(model,book);

    }
    public String update(Book book,Model model){
        if(BookValidation.checkAllFields(book)){
            bookService.saveBook(book);
            return "redirect:/bookStore/books";
        }
        checkBookErrorFields(book,model);
        return "Admin_view/edit-book";

    }

    public void checkBookErrorFields(Book book, Model model) {

        if (!BookValidation.isValidQuantity(book.getQuantity())) {
            model.addAttribute("notValidQuantity", "not valid quantity, must greater than 0");

        }

        if (!BookValidation.isValidPrice(book.getPrice())) {
            model.addAttribute("notValidPrice", "not valid price");
        }

        if (!BookValidation.isValidBookAuthorName(book.getAuthor())) {
            model.addAttribute("notValidAuthor", "not valid author name");
        }

    }



    // for customers
    @GetMapping("bookDetails/{id}")
    public String bookDetails(@PathVariable int id, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Book book = bookService.getBook(id).get();
        model.addAttribute("book", book);
        model.addAttribute("rate", new Rate());
        model.addAttribute("rates", getRates(book, user));

        return "Customer_view/shop-detail";
    }


    public List<Rate> getRates(Book book, User user) {
        List<Rate> rates = new ArrayList<>();
        for (Rate rate : book.getRates()) {
            if (rate.getUser().getId() != user.getId()) {
                rates.add(rate);
            }
        }
        return rates;
    }


    @GetMapping("/search")
    public String searchBooks(Model model, @RequestParam(value= "keyword",required = false) String keyword) {
        List<Book> searchResult = bookService.searchBooks(keyword);
        model.addAttribute("searchResult", searchResult);
        return "Customer_view/search-books";
    }

}
