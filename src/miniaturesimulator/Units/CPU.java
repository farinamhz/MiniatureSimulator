/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniaturesimulator.Units;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import miniaturesimulator.AlU.ALU;
import miniaturesimulator.AlU.AlU_Ultimate;
import miniaturesimulator.CodeFileReader;
import miniaturesimulator.Instruction;
import miniaturesimulator.Parameters;

/**
 *
 * @author Moses
 */
public class CPU {
    private RegisterFile registerFile;
    private DataMemory dataMemory;
    private ControlUnit controlUnit;
    private ALU alu;
    private int pc=0;
    private Mux regDestMux;
    private Mux aluSrcMux;
    private Mux memToRegMux;
    private Mux jalrMux;
    private Mux branchMux;
    private Mux jMux;
    private Mux jalrAddressMux;
    private Mux luishiftMux;
    
    private int instructionCount=0;
    private int runnedInstruction=0;
    private boolean finish=false;
    
    public void initialize(String filepath) throws IOException
    {
        
        this.registerFile=new RegisterFile();
        this.dataMemory=new DataMemory();
        this.alu=new AlU_Ultimate();
        this.controlUnit=new ControlUnitPython();
        this.regDestMux=new Mux();
        this.aluSrcMux=new Mux();
        this.memToRegMux=new Mux();
        this.jalrMux=new Mux();
        this.jMux=new Mux();
        this.branchMux=new Mux();
        this.jalrAddressMux=new Mux();
        this.luishiftMux=new Mux();
        
        LinkedList<Integer> program=new CodeFileReader(filepath).read();
        
        this.dataMemory.loadProgram(program);
        this.instructionCount=program.size();
    }
    
    public void run1Step()
    {
        Instruction instruction=new Instruction(this.dataMemory.readInstruction(pc));
        this.controlUnit.calculate(instruction.getOpcode());// control unit creates signals
        if(controlUnit.getHalt()==1)
        {
            finish=true;
            return;
        }
        //mux before reg file
        this.regDestMux.setData0(instruction.getReadAddress2())
                .setData1(instruction.getWriteAddress())
                .setSignal(controlUnit.getRegDest());
        
        this.registerFile.setReadAddress1(instruction.getReadAddress1())
                .setReadAddress2(instruction.getReadAddress2())
                .setWriteAddress(this.regDestMux.getOutput())
                .setRegWrite(controlUnit.getRegWrite());
        
        int extendedImiidiate=extendImmidiate(instruction.getImmidiate(), controlUnit.getExtOp());
        this.aluSrcMux.setData0(this.registerFile.getReadData_2())
                .setData1(extendedImiidiate)
                .setSignal(controlUnit.getALUSrc());
        
        int aluOutput=this.alu.calculate(this.registerFile.getReadData_1(), aluSrcMux.getOutput(),controlUnit.getALUCtr());
        
        this.dataMemory
                .setMemRead(controlUnit.getMemRead())
                .setMemWrite(controlUnit.getMemWrite())
                .setAddress(aluOutput)
                .setWriteData(this.registerFile.getReadData_2());
        
        this.memToRegMux
                .setData0(aluOutput)
                .setData1(this.dataMemory.readData())
                .setSignal(controlUnit.getMemToReg());
        
        int pcPlus4=pc+1;
        
        this.jalrMux
                .setData0(memToRegMux.getOutput())
                .setData1(pcPlus4)
                .setSignal(controlUnit.getJalr());
        
        this.luishiftMux
                .setData0(jalrMux.getOutput())
                .setData1(jalrMux.getOutput()<<16)
                .setSignal(controlUnit.getLui());
        
        this.registerFile.setWriteData(luishiftMux.getOutput());
        
        int branchTarget=pcPlus4+(extendedImiidiate);
        
        int branchSignal=(controlUnit.getBranch() & alu.isZero());
        
        this.branchMux
                .setData0(pcPlus4)
                .setData1(branchTarget)
                .setSignal(branchSignal);
        
        this.jMux
                .setData0(branchMux.getOutput())
                .setData1(instruction.getImmidiate())
                .setSignal(controlUnit.getJ());
        
        this.jalrAddressMux
                .setData0(jMux.getOutput())
                .setData1(this.registerFile.getReadData_1())
                .setSignal(controlUnit.getJalr());
        
        this.pc=jalrAddressMux.getOutput();
        runnedInstruction++;
        
    }
    
    public void runCompletely()
    {
        while(!finish)
        {
            run1Step();
        }
    }
    
    public int registersUsed()
    {
        int count=0;
        for(boolean bool:this.registerFile.getRegistersUsed())
        {
            if(bool)
                count++;
        }
        return count;
    }
    
    public int memortyUsed()
    {
        int count=0;
        for(boolean bool:this.dataMemory.getMemoryUsed())
        {
            if(bool)
                count++;
        }
        return count;
    }

    public boolean isFinished() {
        return finish;
    }
    
    
   
    private int extendImmidiate(int immidiate,int ExtOp)
    {
        if(ExtOp==0)
            return immidiate;
        
        if((immidiate>>15)==0)
            return immidiate;
        
        return immidiate+(65535<<16);
    }
    
    public int[] getRegs()
    {
        return this.registerFile.getRegisters();
    }

    public int getInstructionCount() {
        return instructionCount;
    }

    public int getRunnedInstruction() {
        return runnedInstruction;
    }

    
    
    
}
