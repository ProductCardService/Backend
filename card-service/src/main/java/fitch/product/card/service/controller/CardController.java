package fitch.product.card.service.controller;

import fitch.product.card.service.model.dto.CardInfoDto;
import fitch.product.card.service.model.dto.CreateCardDto;
import fitch.product.card.service.model.dto.ImageDto;
import fitch.product.card.service.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;

    @PostMapping(path = "/cards")
    public CardInfoDto createCard(@RequestBody CreateCardDto cardDto) {
        return cardService.createCard(cardDto);
    }

    @GetMapping(path = "/cards")
    public List<CardInfoDto> getCards() {
        return cardService.getCards();
    }

    @GetMapping(path = "/cards/{cardId}")
    public CardInfoDto getCardById(@PathVariable Long cardId) {
        return cardService.getCardById(cardId);
    }

    @GetMapping(path = "/cards/{cardId}/image")
    public ImageDto getCardImage(@PathVariable Long cardId) {
        return new ImageDto(cardService.getCardById(cardId).image());
    }

    @DeleteMapping(path = "/cards/{cardId}")
    private void deleteCard(@PathVariable Long cardId) {
        cardService.deleteCard(cardId);
    }

    @PutMapping(path = "/cards/{cardId}")
    private CardInfoDto updateCard(@PathVariable Long cardId, @RequestBody CreateCardDto cardDto) {
        return cardService.updateCard(cardId, cardDto);
    }

}
