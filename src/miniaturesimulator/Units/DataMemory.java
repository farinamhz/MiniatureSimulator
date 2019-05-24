/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniaturesimulator.Units;

import java.util.LinkedList;
import miniaturesimulator.Parameters;

/**
 *
 * @author Moses
 */
public class DataMemory {
    private int[] memory=new int[Parameters.memorySize];
    private int memRead=0;
    private int memWrite=0;
    private int address=0;
    private int writeData=0;
    private boolean[] memoryUsed=new boolean[Parameters.memorySize]; 
    
    public void loadProgram(LinkedList<Integer> program)
    {
        int index=0;
        for(int line:program)
        {
            this.memory[index]=line;
            this.memoryUsed[index]=true;
        }
    }
    
    public int readInstruction(int pc)
    {
        return this.memory[pc];
    }
    
    public int readData()
    {
        if(memRead==1)
            return this.memory[address];
        else if(memWrite==1)
        {
            this.memory[address]=writeData;
            this.memoryUsed[address]=true;
            return 0;
        }else
            return 0;
    }

    public DataMemory setWriteData(int writeData) {
        this.writeData = writeData;
        return this;
    }
    

    public DataMemory setMemRead(int memRead) {
        this.memRead = memRead;
        return this;
    }

    public DataMemory setMemWrite(int memWrite) {
        this.memWrite = memWrite;
        return this;
    }
    
    
    public DataMemory setAddress(int address) {
        this.address = address;
        return this;
    }

    public boolean[] getMemoryUsed() {
        return memoryUsed;
    }
    
    
    
}
