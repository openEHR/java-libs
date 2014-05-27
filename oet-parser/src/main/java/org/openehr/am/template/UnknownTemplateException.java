package org.openehr.am.template;

/**
 * User: Iago.Corbal
 * Date: 2014-05-27
 * Time: 14:40
 */
public class UnknownTemplateException extends FlatteningException {

    private String templateId;

    public UnknownTemplateException(String templateId) {
        super("Unknown template: "+templateId);
        this.templateId = templateId;
    }

    public String getTemplateId() {
        return templateId;
    }

}
