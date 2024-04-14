package fitch.product.card.service.repo;

import fitch.product.card.service.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    @Query("select card from Card card left join fetch card.tags")
    List<Card> getCards();
    @Modifying
    @Query("delete Card where id = :id")
    int deleteCard(@Param("id") Long id);
}
