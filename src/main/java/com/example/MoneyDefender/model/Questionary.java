package com.example.MoneyDefender.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
// ?????????????????????????????????????????????????????????
// No se como componerlo. Awantame en lo que resuelvo la demas logica :'x
public class Questionary {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long questionaryId;
    @NotBlank(message = "Question cannot be empty or Null")
    private String question;
}
