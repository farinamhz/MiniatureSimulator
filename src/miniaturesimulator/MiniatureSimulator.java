/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniaturesimulator;

/**
 *
 * @author Moses
 */
public class MiniatureSimulator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Instruction ins=new Instruction(218103811);
        System.out.println(ins.getImmidiate());
        System.out.println(ins.getOpcode());
        System.out.println(ins.getReadAddress1());
        System.out.println(ins.getReadAddress2());
        System.out.println(ins.getWriteAddress());
        
        int immidiate=ins.getImmidiate();
        if((immidiate>>15)==1)
            immidiate+=(65535<<16);
        
        System.out.println(immidiate);
    }
    
}
