package sk.jazzman.callanalyzer.domain;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.validation.constraints.Min;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.ManyToOne;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Log {

    /**
     */
    @NotNull
    @Column(unique = true)
    private Long id;

    /**
     */
    @NotNull
    private String callNumber;

    /**
     */
    @NotNull
    @Min(0L)
    private Integer duration;

    /**
     */
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date startCall;

    /**
     */
    @NotNull
    @ManyToOne
    private CallType callType;

    /**
     */
    @NotNull
    @ManyToOne
    private CAUser owner;

    /**
     */
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date importTime;
}
