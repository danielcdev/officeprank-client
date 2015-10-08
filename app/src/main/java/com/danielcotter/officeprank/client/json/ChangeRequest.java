package com.danielcotter.officeprank.client.json;

public class ChangeRequest {

    Boolean state;
    String timestamp;
    String hash;

    /**
     * @param state
     * @param timestamp
     * @param hash
     */
    public ChangeRequest(Boolean state, String timestamp, String hash) {
        this.state = state;
        this.timestamp = timestamp;
        this.hash = hash;
    }

    /**
     * @return the state
     */
    public Boolean getState() {
        return state;
    }

    /**
     * @param state
     *            the state to set
     */
    public void setState(Boolean state) {
        this.state = state;
    }

    /**
     * @return the timestamp
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp
     *            the timestamp to set
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * @return the hash
     */
    public String getHash() {
        return hash;
    }

    /**
     * @param hash
     *            the hash to set
     */
    public void setHash(String hash) {
        this.hash = hash;
    }
}
