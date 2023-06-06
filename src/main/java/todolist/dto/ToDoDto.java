package todolist.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;

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
