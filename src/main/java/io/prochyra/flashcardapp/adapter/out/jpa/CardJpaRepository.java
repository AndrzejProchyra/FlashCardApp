package io.prochyra.flashcardapp.adapter.out.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CardJpaRepository extends JpaRepository<CardDbo, Long> {
}
