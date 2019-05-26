/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniaturesimulator.Units;

/**
 *
 * @author Moses
 */
public class RegisterFile {
    private int[] registers=new int[16];
    private int writeAddress=0;
    private int readAddress1=0;
    private int readAddress2=0;
    private int regWrite=0;
    
    private boolean[] registersUsed=new boolean[16];

    public RegisterFile()
    {
        
    }
    
    public int getReadData_1()
    {
        return this.registers[readAddress1];
    }
    
    public int getReadData_2()
    {
        return this.registers[readAddress2];
    }
    

    public RegisterFile setWriteAddress(int writeAddress) {
        this.writeAddress = writeAddress;
        return this;
    }

    public RegisterFile setRegWrite(int regWrite) {
        this.regWrite = regWrite;
        return this;
    }
    

    public RegisterFile setWriteData(int writeData) 
    {
        if(this.regWrite==1)
        {
            if(writeAddress==0)
                return this;
            
            this.registers[writeAddress]=writeData;
            this.registersUsed[writeAddress]=true;
        }
        return this;
        
    }

    public RegisterFile setReadAddress1(int readAddress1) {
        this.readAddress1 = readAddress1;
        return this;
    }

    public RegisterFile setReadAddress2(int readAddress2) {
        this.readAddress2 = readAddress2;
        
        return this;
    }

    public boolean[] getRegistersUsed() {
        return registersUsed;
    }

    public int[] getRegisters() {
        return registers;
    }
    
    
   
}
