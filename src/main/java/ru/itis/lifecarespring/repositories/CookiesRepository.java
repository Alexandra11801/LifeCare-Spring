package ru.itis.lifecarespring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.lifecarespring.models.CookieValue;

public interface CookiesRepository extends JpaRepository<CookieValue, Long> {
}
