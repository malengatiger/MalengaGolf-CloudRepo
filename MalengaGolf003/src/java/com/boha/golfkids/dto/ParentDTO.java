/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.golfkids.dto;

import com.boha.golfkids.data.Parent;
import java.util.List;

/**
 *
 * @author Aubrey Malabie
 */
public class ParentDTO {

    private int parentID;
    private String cellphone;
    private String email;
    private String firstName;
    private String lastName;
    private String middleName;
    private int parentType;
    private String pin;
    private List<PlayerDTO> players;

    public ParentDTO(Parent a) {
        parentID = a.getParentID();
        cellphone = a.getCellphone();
        email = a.getEmail();
        firstName = a.getFirstName();
        lastName = a.getLastName();
        middleName = a.getMiddleName();
        parentType = a.getParentType();
        pin = a.getPin();
    }

    public int getParentID() {
        return parentID;
    }

    public void setParentID(int parentID) {
        this.parentID = parentID;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public int getParentType() {
        return parentType;
    }

    public void setParentType(int parentType) {
        this.parentType = parentType;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public List<PlayerDTO> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerDTO> players) {
        this.players = players;
    }
}
