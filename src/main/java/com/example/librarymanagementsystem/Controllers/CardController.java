package com.example.librarymanagementsystem.Controllers;

import com.example.librarymanagementsystem.Services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
public class CardController {
@Autowired

private CardService cardService;
    @PostMapping("generateACard")

    public String AddCard(){
        String result = cardService.getFreshCard();

        return result;

    }
    @PutMapping("/associateCardAndStudent")


    public RequestEntity associateCardAndStudent(@RequestParam("studentId") Integer studentId,
                                                 @RequestParam("cardId") Integer cardId){

            try {

            }catch(Exception e){
               return new  RequestEntity(e.getMessage(),HttpStatus.BAD_REQUEST);

            }



    }

}
