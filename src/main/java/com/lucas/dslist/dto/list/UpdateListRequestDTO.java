package com.lucas.dslist.dto.list;

import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateListRequestDTO {

    @Size(min = 2, max = 50, message = "O nome deve conter entre 2 e 50 caracteres.")
    private String name;

    @Size(min = 10, max = 150, message = "A descrição deve conter entre 10 e 150 caracteres.")
    private String description;
}
