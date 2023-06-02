package todolist.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

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

    @Column(name="Due_date")
    private LocalDateTime dueDate;

    @Column(name="Status")
    private String status;

}
