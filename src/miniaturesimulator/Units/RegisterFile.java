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
            this.registers[writeAddress]=writeData;
        
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
   
}
