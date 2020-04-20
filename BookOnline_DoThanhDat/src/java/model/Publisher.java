/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Administrator
 */
public class Publisher {
    private String publicationId;
    private String publicationName;
    private String publicationAddress;

    public Publisher() {
    }

    public Publisher(String publicationId, String publicationName, String publicationAddress) {
        this.publicationId = publicationId;
        this.publicationName = publicationName;
        this.publicationAddress = publicationAddress;
    }

    public String getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(String publicationId) {
        this.publicationId = publicationId;
    }

    public String getPublicationName() {
        return publicationName;
    }

    public void setPublicationName(String publicationName) {
        this.publicationName = publicationName;
    }

    public String getPublicationAddress() {
        return publicationAddress;
    }

    public void setPublicationAddress(String publicationAddress) {
        this.publicationAddress = publicationAddress;
    }
    
    
}
