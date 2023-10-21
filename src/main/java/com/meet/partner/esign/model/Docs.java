package com.meet.partner.esign.model;

import javax.xml.bind.annotation.XmlElement;

public class Docs {
    private InputHash inputHash;

    @XmlElement(name = "InputHash")
    public InputHash getInputHash() {
        return inputHash;
    }

    public void setInputHash(InputHash inputHash) {
        this.inputHash = inputHash;
    }
}
