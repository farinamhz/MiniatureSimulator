/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniaturesimulator.Units;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Moses
 */
public class ControlUnitPython implements ControlUnit{

    private String [] signals;
    
    @Override
    public void calculate(int opcode) {
        try {
            ProcessBuilder pb=new ProcessBuilder("ControlUnitPython.exe", opcode+"");
            Process p=pb.start();
            while(p.isAlive());
            
            BufferedReader buffread=new BufferedReader(new InputStreamReader(p.getInputStream()));
            String output="";
            int i=0;
            while((i=buffread.read())!=-1)
            {
                output+=(char)i;
            }
            
            signals=output.split(",");
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        
    }

    @Override
    public int getBranch() {
        return Integer.parseInt(this.signals[0]);
    }

    @Override
    public int getMemRead() {
        return Integer.parseInt(this.signals[1]);
    }

    @Override
    public int getMemToReg() {
        return Integer.parseInt(this.signals[2]);
    }

    @Override
    public int getMemWrite() {
        return Integer.parseInt(this.signals[3]);
    }

    @Override
    public int getALUSrc() {
        return Integer.parseInt(this.signals[4]);
    }

    @Override
    public int getRegWrite() {
        return Integer.parseInt(this.signals[5]);
    }

    @Override
    public int getALUCtr() {
        return Integer.parseInt(this.signals[11]);
    }

    @Override
    public int getRegDest() {
        return Integer.parseInt(this.signals[6]);
    }

    @Override
    public int getExtOp() {
        return Integer.parseInt(this.signals[7]);
    }

    @Override
    public int getJalr() {
        return Integer.parseInt(this.signals[8]);
    }

    @Override
    public int getJ() {
        return Integer.parseInt(this.signals[9]);
    }

    @Override
    public int getHalt() {
        return Integer.parseInt(this.signals[10]);
    }

    @Override
    public int getLui() {
        return Integer.parseInt(this.signals[12].trim());
    }
    
    
    
}
