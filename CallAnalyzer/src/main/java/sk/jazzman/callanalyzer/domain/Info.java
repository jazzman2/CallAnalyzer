package sk.jazzman.callanalyzer.domain;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Info {

    /**
     */
    @NotNull
    private Long id;

    /**
     */
    @NotNull
    private String infoValue;

    /**
     */
    @NotNull
    @ManyToOne
    private CAUser owner;

    /**
     */
    @NotNull
    @ManyToOne
    private InfoType type;
}
