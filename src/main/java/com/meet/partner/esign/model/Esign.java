package com.meet.partner.esign.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Esign")
public class Esign {
    private String ver;
    private String sc;
    private String ts;
    private String txn;
    private String ekycId;
    private String ekycIdType;
    private String aspId;
    private String AuthMode;
    private String responseSigType;
    private String responseUrl;
    private Docs docs;
    private String signature;

    @XmlAttribute(name = "ver")
    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    @XmlAttribute(name = "sc")
    public String getSc() {
        return sc;
    }

    public void setSc(String sc) {
        this.sc = sc;
    }

    @XmlAttribute(name = "ts")
    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    @XmlAttribute(name = "txn")
    public String getTxn() {
        return txn;
    }

    public void setTxn(String txn) {
        this.txn = txn;
    }

    @XmlAttribute(name = "ekycId")
    public String getEkycId() {
        return ekycId;
    }

    public void setEkycId(String ekycId) {
        this.ekycId = ekycId;
    }

    @XmlAttribute(name = "ekycIdType")
    public String getEkycIdType() {
        return ekycIdType;
    }

    public void setEkycIdType(String ekycIdType) {
        this.ekycIdType = ekycIdType;
    }

    @XmlAttribute(name = "aspId")
    public String getAspId() {
        return aspId;
    }

    public void setAspId(String aspId) {
        this.aspId = aspId;
    }

    @XmlAttribute(name = "AuthMode")
    public String getAuthMode() {
        return AuthMode;
    }

    public void setAuthMode(String authMode) {
        AuthMode = authMode;
    }

    @XmlAttribute(name = "responseSigType")
    public String getResponseSigType() {
        return responseSigType;
    }

    public void setResponseSigType(String responseSigType) {
        this.responseSigType = responseSigType;
    }

    @XmlAttribute(name = "responseUrl")
    public String getResponseUrl() {
        return responseUrl;
    }

    public void setResponseUrl(String responseUrl) {
        this.responseUrl = responseUrl;
    }

    @XmlElement(name = "Docs")
    public Docs getDocs() {
        return docs;
    }

    public void setDocs(Docs docs) {
        this.docs = docs;
    }

    @XmlElement(name = "Signature")
    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
