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
        Instruction ins=new Instruction(101964016);
        System.out.println(ins.getImmidiate());
        System.out.println(ins.getOpcode());
        System.out.println(ins.getReadAddress1());
        System.out.println(ins.getReadAddress2());
        System.out.println(ins.getWriteAddress());
        
        
    }
    
}
