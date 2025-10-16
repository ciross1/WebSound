package com.webSounds.WebSounds.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SoundRequest {
    private String title;
    private String description;
    private int durationSeconds;
    private BigDecimal price;
    private String fileUrl;
    private List<String> tags;
    private List<Long> genreIds;
}
