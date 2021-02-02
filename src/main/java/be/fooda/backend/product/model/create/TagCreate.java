package be.fooda.backend.product.model.create;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.Field;

import javax.persistence.*;
import java.util.UUID;

@Data
@NoArgsConstructor
public class TagCreate {

    private String value;

}
