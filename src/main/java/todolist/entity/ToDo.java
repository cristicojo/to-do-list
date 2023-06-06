package todolist.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name="todo")
public class ToDo {

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native")
    private int id;


    @Column(name="Title")
    private String title;

    @Column(name="Description")
    private String description;

    @Column(name="Due_date")
    private LocalDate dueDate;

    @Column(name="Status")
    private String status;

}
