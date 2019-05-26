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
public class Instruction {
    private int machineCode=0;
    private int opcode;
    private int readAddress1;//rs
    private int readAddress2;//rt
    private int writeAddress;//rd
    private int immidiate;
    
    public Instruction(int machineCode)
    {
        this.machineCode=machineCode;
        
        this.opcode=machineCode>>>24;
        this.readAddress1=(machineCode>>>20)&0xf;
        this.readAddress2=(machineCode>>>16)&0xf;
        this.writeAddress=(machineCode>>>12)&0xf;
        this.immidiate=(machineCode)&0xffff;
    }

    public int getMachineCode() {
        return machineCode;
    }

    public int getOpcode() {
        return opcode;
    }

    public int getReadAddress1() {
        return readAddress1;
    }

    public int getReadAddress2() {
        return readAddress2;
    }

    public int getWriteAddress() {
        return writeAddress;
    }

    public int getImmidiate() {
        return immidiate;
    }
    
    
}
