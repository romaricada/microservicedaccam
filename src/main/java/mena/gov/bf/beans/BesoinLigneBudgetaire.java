package mena.gov.bf.beans;

public class BesoinLigneBudgetaire {
    private Long id;

    private Boolean deleted;

    private Long ligneBudgetId;

    private String budgetLigneBudget;

    private String ligneCreditLigneBudget;

    private String aecpLigneBudget;

    private Double montantEstimeLigneBudget;

    private Long besoinId;

    private String besoinLibelle;

    private Long activiteId;

    private Long montantEstime;

    private String activiteLibelle;

    private String ligneCredit;

    public Long getMontantEstime() {
        return montantEstime;
    }

    public void setMontantEstime(Long montantEstime) {
        this.montantEstime = montantEstime;
    }

    public String getLigneCredit() {
        return ligneCredit;
    }

    public void setLigneCredit(String ligneCredit) {
        this.ligneCredit = ligneCredit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Long getLigneBudgetId() {
        return ligneBudgetId;
    }

    public void setLigneBudgetId(Long ligneBudgetId) {
        this.ligneBudgetId = ligneBudgetId;
    }

    public String getBudgetLigneBudget() {
        return budgetLigneBudget;
    }

    public void setBudgetLigneBudget(String budgetLigneBudget) {
        this.budgetLigneBudget = budgetLigneBudget;
    }

    public String getLigneCreditLigneBudget() {
        return ligneCreditLigneBudget;
    }

    public void setLigneCreditLigneBudget(String ligneCreditLigneBudget) {
        this.ligneCreditLigneBudget = ligneCreditLigneBudget;
    }

    public String getAecpLigneBudget() {
        return aecpLigneBudget;
    }

    public void setAecpLigneBudget(String aecpLigneBudget) {
        this.aecpLigneBudget = aecpLigneBudget;
    }

    public Double getMontantEstimeLigneBudget() {
        return montantEstimeLigneBudget;
    }

    public void setMontantEstimeLigneBudget(Double montantEstimeLigneBudget) {
        this.montantEstimeLigneBudget = montantEstimeLigneBudget;
    }

    public Long getBesoinId() {
        return besoinId;
    }

    public void setBesoinId(Long besoinId) {
        this.besoinId = besoinId;
    }

    public String getBesoinLibelle() {
        return besoinLibelle;
    }

    public void setBesoinLibelle(String besoinLibelle) {
        this.besoinLibelle = besoinLibelle;
    }

    public Long getActiviteId() {
        return activiteId;
    }

    public void setActiviteId(Long activiteId) {
        this.activiteId = activiteId;
    }

    public String getActiviteLibelle() {
        return activiteLibelle;
    }

    public void setActiviteLibelle(String activiteLibelle) {
        this.activiteLibelle = activiteLibelle;
    }
}
