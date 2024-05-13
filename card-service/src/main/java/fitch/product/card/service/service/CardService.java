package fitch.product.card.service.service;

import fitch.product.card.service.exceptions.CardNotFoundException;
import fitch.product.card.service.model.Card;
import fitch.product.card.service.model.Tag;
import fitch.product.card.service.model.dto.CardInfoDto;
import fitch.product.card.service.model.dto.CreateCardDto;
import fitch.product.card.service.repo.CardRepository;
import fitch.product.card.service.repo.TagsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;
    private final TagsRepository tagsRepository;

    @Transactional
    public CardInfoDto createCard(CreateCardDto cardDto) {

        var card = Card.builder()
                .title(cardDto.title())
                .description(cardDto.description())
                .imageBase64(cardDto.image_base64())
                .build();

        var tags = cardDto.tags().stream().map(tagName -> {
            return Tag.builder().name(tagName).card(card).build();
        }).toList();

        card.setTags(null);

        Card savedCard = cardRepository.save(card);
        tagsRepository.saveAll(tags);

        return CardInfoDto.builder()
                .id(savedCard.getId())
                .title(savedCard.getTitle())
                .description(savedCard.getDescription())
                .tags(cardDto.tags())
                .image(card.getImageBase64())
                .build();
    }

    public List<CardInfoDto> getCards() {
        var cards = cardRepository.getCards();

        return cards.stream().map(card -> {
            return CardInfoDto.builder()
                    .id(card.getId())
                    .title(card.getTitle())
                    .description(card.getDescription())
                    .tags(card.getTags().stream().map(Tag::getName).toList())
                    .build();
        }).toList();
    }

    public CardInfoDto getCardById(Long cardId) {
        var card = cardRepository.findById(cardId);

        if (card.isPresent()) {
            return CardInfoDto.builder()
                    .id(card.get().getId())
                    .title(card.get().getTitle())
                    .description(card.get().getDescription())
                    .tags(card.get().getTags().stream().map(Tag::getName).toList())
                    .image(card.get().getImageBase64())
                    .build();
        } else {
            throw new CardNotFoundException("Card with id " + cardId + " not found");
        }

    }
    @Transactional
    public void deleteCard(Long cardId) {
        if (cardRepository.deleteCard(cardId) == 0) {
            throw new CardNotFoundException("Card with id " + cardId + " not found");
        }
    }
    @Transactional
    public CardInfoDto updateCard(Long cardId, CreateCardDto cardDto) {
        var card = cardRepository.findById(cardId);

        if (card.isPresent()) {

            tagsRepository.deleteAll(card.get().getTags());

            var tags = cardDto.tags().stream().map(tagName -> {
                return Tag.builder().name(tagName).card(card.get()).build();
            }).toList();

            tagsRepository.saveAll(tags);

            var newCardVersion = Card.builder()
                    .id(card.get().getId())
                    .title(cardDto.title())
                    .description(cardDto.description())
                    .tags(tags)
                    .build();

            var savedCard = cardRepository.save(newCardVersion);

            return CardInfoDto.builder()
                    .id(savedCard.getId())
                    .title(savedCard.getTitle())
                    .description(savedCard.getDescription())
                    .tags(cardDto.tags())
                    .image(savedCard.getImageBase64())
                    .build();
        } else {
            throw new CardNotFoundException("Card with id " + cardId + " not found");
        }
    }

}
