/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import app.controller.RoleJpaController;
import app.methods.GetConFactory;
import app.model.Role;
import java.util.List;

/**
 *
 * @author Raimundo Jose
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        System.out.println("Teste do Sistema ... Lista de Roles");
        List<Role> roles = new RoleJpaController(new GetConFactory().conect_to_db()).findRoleEntities();
        
        System.out.println("=====================================================");
        
        for (Role role : roles) {
            System.out.println("["+role.getIdrole()+"] - " + role.getDescription());
        }
        
        System.out.println("=====================================================");
        
    }
    
}
