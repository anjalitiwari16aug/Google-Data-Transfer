package fr.docjyJ.googleTransfer.api.Services.gmail;

import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Filter;
import com.google.api.services.gmail.model.Label;
import fr.docjyJ.googleTransfer.api.Utils.GoogleTransfer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GmailElement extends GoogleTransfer {
    //ELEMENT
    protected transient Gmail service;
    protected List<Filter> filters;
    protected List<Label> labels;
    protected Map<String,String> labelCorrection;

    //CONSTRUCTOR
    public GmailElement(Gmail service) {
        this.service = service;
    }

    //READ
    public GmailElement readAll() throws IOException {
        return this.readFilters().readLabels();
    }
    public GmailElement readFilters() throws IOException {
        this.filters = new ArrayList<>();
        this.filters.addAll(this.service
                .users().settings().filters()
                .list("me")
                .execute()
                .getFilter());
        return this;
    }
    public GmailElement readLabels() throws IOException {
        this.labels = new ArrayList<>();
        for (Label label : this.service.users().labels()
                .list("me")
                .execute()
                .getLabels()) {
            if(label.getType().equals("user"))
                this.labels.add(label);
        }
        return this;
    }

    //PUT
    public GmailElement putAll(GmailElement data) throws IOException {
        return this;
    }
    public GmailElement putLabels(List<Label> data) throws IOException {
        for (Label label: data) {
            this.labelCorrection.put(
                    label.getId(),
                    service.users().labels()
                            .create("me", new Label()
                                    .setName(label.getName())
                                    .setLabelListVisibility(label.getLabelListVisibility())
                                    .setMessageListVisibility(label.getMessageListVisibility())
                                    .setColor(label.getColor()))
                            .execute()
                            .getId()
            );
        }
        return this;
    }



    //GET
    public Gmail getService() {
        return service;
    }
    public List<Filter> getFilters() {
        return filters;
    }
    public List<Label> getLabels() {
        return labels;
    }
}
