package com.example.bookStore.controller;

import com.example.bookStore.model.entities.Book;
import com.example.bookStore.model.entities.Offer;
import com.example.bookStore.model.entities.User;
import com.example.bookStore.service.BookService;
import com.example.bookStore.service.OfferService;
import com.example.bookStore.service.OrderService;
import com.example.bookStore.service.UserService;
import com.example.bookStore.validation.BookValidation;
import com.example.bookStore.validation.OfferValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("bookStore")
public class OfferController {
    @Autowired
    private OfferService offerService;

    @Autowired
    private BookService bookService;

    @GetMapping("/offers")
    public String showOffersPage(Model model) {
        List<Offer> offers = offerService.getAllOffers();
        model.addAttribute("offers", offers);
        return "Admin_view/Offers";
    }

    @GetMapping("/deleteOffer/{id}")
    private String deleteOffer(@PathVariable int id) {
        offerService.deleteOffer(id);
        return "redirect:/bookStore/offers";
    }

    @GetMapping("/showAddOfferPage")
    private String showAddOfferPage(Model model) {
        model.addAttribute("offer", new Offer());
        return "Admin_view/add-offer";
    }

    @GetMapping("/addOffer")
    private String addOffer(@ModelAttribute("offer") Offer offer, Model model) {
        Book book= bookService.getBook(offer.getBook().getId()).get();
        if (book != null) {
            Offer existingOffer = book.getOffer();
            if (existingOffer != null) {
                existingOffer.setDiscount(offer.getDiscount());
                return saveOffer(existingOffer,model);
            } else {
                offer.setBook(book);
                return saveOffer(offer,model);
            }
        }

        model.addAttribute("error", true);
        return "Admin_view/add-offer";

    }
    public String saveOffer(Offer offer, Model model){
        if(OfferValidation.checkAllFields(offer)){
            offerService.saveOffer(offer);
            return "redirect:/bookStore/offers";
        }
        checkOfferErrorFields(offer,model);
        return "Admin_view/add-offer";

    }

    public String updateOffer(Offer offer, Model model){
        if(OfferValidation.checkAllFields(offer)){
            offerService.saveOffer(offer);
            return "redirect:/bookStore/offers";
        }
        checkOfferErrorFields(offer,model);
        return "Admin_view/edit-offer";

    }

    public void checkOfferErrorFields(Offer offer, Model model) {

        if (!OfferValidation.isValidDiscount(offer.getDiscount())) {
            model.addAttribute("notValidDiscount", "not valid discount,must grater than 0");

        }

        if (!OfferValidation.isValidBookId(offer.getBook().getId())) {
            model.addAttribute("notValidId", "not valid book Id");
        }


    }



    // edit

    @GetMapping("/showEditOfferPage/{id}")
    public String showEditOfferPage(Model model,@PathVariable int id) {
        Offer offer= offerService.getOffer(id).get();
        model.addAttribute("offer", offer);
        return "Admin_view/edit-offer";
    }

    @GetMapping("/editOffer")
    public String editOffer(@ModelAttribute("offer") Offer offer,Model model) {
       return updateOffer(offer,model);
    }

}

