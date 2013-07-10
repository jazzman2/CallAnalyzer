package sk.jazzman.callanalyzer.domain;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class CAUser {

    /**
     */
    @NotNull
    @Column(unique = true)
    private Long id;

    /**
     */
    private String name;
}
