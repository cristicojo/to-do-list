package todolist.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ToDoDto {

    private int id;

    @NotNull
    private String title;

    private String description;

    @NotNull
    private LocalDate dueDate;

    @Pattern(regexp = "To Do|In Progress|Done", message = "must match: To Do, In Progress or Done ")
    @NotNull
    private String status;
}
