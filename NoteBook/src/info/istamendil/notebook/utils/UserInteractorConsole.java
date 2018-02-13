/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.istamendil.notebook.utils;

import java.util.Scanner;

/**
 *
 * @author danis_zam
 */
public class UserInteractorConsole implements UserInteractor {
    private Scanner sc = new Scanner(System.in);
    
    public String readCommand() throws UserInteractorReadException {
        String command = sc.nextLine();
        return command;
    }
    
    public void print(String output) throws UserInteractorWriteException {
        System.out.println(output);
    }
    
}
