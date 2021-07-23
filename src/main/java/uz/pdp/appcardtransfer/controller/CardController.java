package uz.pdp.appcardtransfer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcardtransfer.entity.Card;
import uz.pdp.appcardtransfer.entity.Income;
import uz.pdp.appcardtransfer.entity.Outcome;
import uz.pdp.appcardtransfer.payload.ApiResponse;
import uz.pdp.appcardtransfer.service.CardService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/card")
public class CardController {

    private CardService cardService;

    @Autowired
    public void setCardService(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping("/addCard")
    public ResponseEntity<?> addCard(@RequestBody Card card) {
        ApiResponse apiResponse = cardService.addCard(card);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }

    @GetMapping("/incomeHistory")
    public ResponseEntity<?> getIncomeHistory(@RequestParam Integer cardId) {
        List<Income> incomes = cardService.getIncomeHistory(cardId);
        return ResponseEntity.ok(incomes);
    }

    @GetMapping("/outcomeHistory")
    public ResponseEntity<?> getOutcomeHistory(@RequestParam Integer cardId) {
        List<Outcome> outcomes = cardService.getOutcomeHistory(cardId);
        return ResponseEntity.ok(outcomes);
    }
}
