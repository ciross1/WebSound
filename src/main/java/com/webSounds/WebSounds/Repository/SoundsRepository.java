package com.webSounds.WebSounds.Repository;

import com.webSounds.WebSounds.Entity.Sounds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoundsRepository extends JpaRepository<Sounds, Long> {
}
