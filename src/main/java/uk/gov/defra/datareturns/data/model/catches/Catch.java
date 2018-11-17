package uk.gov.defra.datareturns.data.model.catches;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;
import uk.gov.defra.datareturns.data.model.AbstractBaseEntity;
import uk.gov.defra.datareturns.data.model.submissions.HasSubmission;
import uk.gov.defra.datareturns.data.model.activities.Activity;
import uk.gov.defra.datareturns.data.model.method.Method;
import uk.gov.defra.datareturns.data.model.species.Species;
import uk.gov.defra.datareturns.data.model.submissions.Submission;
import uk.gov.defra.datareturns.validation.catches.ValidCatch;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import java.util.Date;

/**
 * Records an anglers catches against an given {@link Submission} and {@link Activity}
 *
 * @author Sam Gardner-Dell
 */
@Entity(name = "rcr_catch")
@Audited
@Getter
@Setter
@ValidCatch
public class Catch extends AbstractBaseEntity<Long> implements HasSubmission {
    /**
     * Database sequence name for this entity
     */
    public static final String SEQUENCE = "rcr_catch_id_seq";

    /**
     * Primary key
     */
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = SEQUENCE, sequenceName = SEQUENCE)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE)
    @ApiModelProperty(readOnly = true)
    private Long id;

    /**
     * The parent submission
     */
    @ManyToOne(optional = false)
    private Submission submission;

    /**
     * The activity associated with this catch
     */
    @ManyToOne(optional = false)
    private Activity activity;

    /**
     * The date of the catch
     */
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    @SuppressFBWarnings("EI_EXPOSE_REP")
    private Date dateCaught;

    /**
     * The species of catch (Salmon, Sea Trout)
     */
    @ManyToOne(optional = false)
    private Species species;

    /**
     * The mass of the catch
     */
    @Embedded
    @Valid
    private CatchMass mass = new CatchMass();

    /**
     * The method used (Fly, Spinner, Bait)
     */
    @ManyToOne(optional = false)
    private Method method;

    /**
     * Was the catch released?
     */
    @Column
    private boolean released;

    /**
     * Is this entry excluded from reporting
     */
    @Column
    private boolean reportingExclude = false;
}
