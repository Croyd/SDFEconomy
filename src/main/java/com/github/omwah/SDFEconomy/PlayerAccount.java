/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.omwah.SDFEconomy;

/**
 *
 * @author mcduffie
 */
public class PlayerAccount extends Account {
    private final String name;
    
    public PlayerAccount(String name, String location) {
        this.name = name;
        this.location = location;
    }
    
    public String getName() {
        return name;
    }
}
