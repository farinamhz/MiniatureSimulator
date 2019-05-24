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
public interface ControlUnit {
    public void calculate(int opcode);
    public int getBranch();// 1 for branch, 0 for other
    public int getMemRead();// 1 for lw, 0 for others
    public int getMemToReg();//1 for lw, 0 for others
    public int getMemWrite();//1 for sw, 0 for others
    public int getALUSrc();// 0 for readdata2 , 1 for immidiate
    public int getRegWrite();//1 if write back to register, 0 for not 
    public int getALUCtr();// alu function
    public int getRegDest();// 0 for rt , 1 for rd
    public int getExtOp();// 0 for unsigned and 1 for signed
    public int getJalr();//1 for jalr and 0 for others
    public int getJ();//1 for j and 0 for others
    public int getHalt();// 1 for halt and 0 for others
    
}
