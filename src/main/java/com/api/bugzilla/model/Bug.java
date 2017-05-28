package com.api.bugzilla.model;

import com.api.bugzilla.utils.DateUtils;
import com.j2bugzilla.base.HttpBug;
import org.apache.http.protocol.HTTP;
import org.springframework.data.annotation.Id;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

/**
 * Created by springfield-home on 5/28/17.
 */
public class Bug {

    private enum Priority {

    }

    private enum Status {

    }

    @Id
    private String id;

    private Date creationTs;
    private Date deltaTs;
    private String bugSeverity;
    private Priority priority;
    private Status bugStatus;
    private Boolean reopened;
    private Integer caseCount;
    private String assignedTo;
    private String needinfo;
    private String reporter;
    private String engEscalation_mgr;
    private String category;
    private String component;
    private String foundIn;
    private String fixBy;
    private String type;
    private Boolean security;
    private String branch;
    private Boolean regression;
    private Date eta;
    private Float viss;
    private String escalation;
    private String shortDesc;

    public Bug() {
    }

    public void buildBug(HttpBug bug) {
        for (Map.Entry<String, Object> entry : bug.getInternalState().entrySet()) {
            try {
                String key = entry.getKey();
                Object value = entry.getValue();
                String sValue = (String) value;
                switch (key) {
                    case "DeltaTs":
                    case "CreationTS":
                    case "Eta":
                        value = "".equals(sValue) ? null : DateUtils.convertToDate(sValue);
                        break;
                    case "Reopened":
                    case "Regression":
                    case "Security":
                        //TODO: check how boolean get to here
                        value = new Boolean(false);
                        break;
                    case "Viss":
                        value = "".equals(sValue) ? new Float(0) : new Float(sValue);
                        break;
                    case "CaseCount":
                        value = "".equals(sValue) ? new Integer(0) : new Integer(sValue);
                        break;
                    default:
                        value = new String((String) value);
                }

                Method getter = this.getClass().getMethod("set" + key, value.getClass());
                getter.invoke(this, value);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }


    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreationTs() {
        return creationTs;
    }

    public void setCreationTs(Date creationTs) {
        this.creationTs = creationTs;
    }

    public Date getDeltaTs() {
        return deltaTs;
    }

    public void setDeltaTs(Date deltaTs) {
        this.deltaTs = deltaTs;
    }

    public String getBugSeverity() {
        return bugSeverity;
    }

    public void setBugSeverity(String bugSeverity) {
        this.bugSeverity = bugSeverity;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Status getBugStatus() {
        return bugStatus;
    }

    public void setBugStatus(Status bugStatus) {
        this.bugStatus = bugStatus;
    }

    public Boolean getReopened() {
        return reopened;
    }

    public void setReopened(Boolean reopened) {
        this.reopened = reopened;
    }

    public Integer getCaseCount() {
        return caseCount;
    }

    public void setCaseCount(Integer caseCount) {
        this.caseCount = caseCount;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getNeedinfo() {
        return needinfo;
    }

    public void setNeedinfo(String needinfo) {
        this.needinfo = needinfo;
    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public String getEngEscalation_mgr() {
        return engEscalation_mgr;
    }

    public void setEngEscalation_mgr(String engEscalation_mgr) {
        this.engEscalation_mgr = engEscalation_mgr;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getFoundIn() {
        return foundIn;
    }

    public void setFoundIn(String foundIn) {
        this.foundIn = foundIn;
    }

    public String getFixBy() {
        return fixBy;
    }

    public void setFixBy(String fixBy) {
        this.fixBy = fixBy;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getSecurity() {
        return security;
    }

    public void setSecurity(Boolean security) {
        this.security = security;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public Boolean getRegression() {
        return regression;
    }

    public void setRegression(Boolean regression) {
        this.regression = regression;
    }

    public Date getEta() {
        return eta;
    }

    public void setEta(Date eta) {
        this.eta = eta;
    }

    public Float getViss() {
        return viss;
    }

    public void setViss(Float viss) {
        this.viss = viss;
    }

    public String getEscalation() {
        return escalation;
    }

    public void setEscalation(String escalation) {
        this.escalation = escalation;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {
            if (o instanceof HttpBug) {
                HttpBug bug = (HttpBug) o;
                return this.getId().equals(bug.getId());
            }
            return false;
        }

        Bug bug = (Bug) o;
        return this.getId().equals(bug.getId());


    }

}
