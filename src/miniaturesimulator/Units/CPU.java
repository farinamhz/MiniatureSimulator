/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniaturesimulator.Units;

import java.util.LinkedList;
import miniaturesimulator.AlU.ALU;
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
    
    private int instructionCount=0;
    private int runnedInstruction=0;
    private boolean[] registersUsed=new boolean[16];
    private boolean[] memoryUsed=new boolean[Parameters.memorySize];
    
    public void initialize(LinkedList<Integer> program)
    {
        this.registerFile=new RegisterFile();
        this.dataMemory=new DataMemory();
        this.regDestMux=new Mux();
        this.aluSrcMux=new Mux();
        this.memToRegMux=new Mux();
        this.jalrMux=new Mux();
        this.jMux=new Mux();
        this.branchMux=new Mux();
        this.jalrAddressMux=new Mux();
        
        this.dataMemory.loadProgram(program);
        this.instructionCount=program.size();
    }
    
    public void run1Step()
    {
        Instruction instruction=new Instruction(this.dataMemory.readInstruction(pc));
        this.controlUnit.calculate(instruction.getOpcode());// control unit creates signals
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
                .setData0(this.dataMemory.readData())
                .setData1(aluOutput)
                .setSignal(controlUnit.getMemToReg());
        
        int pcPlus4=pc+4;
        
        this.jalrMux
                .setData0(memToRegMux.getOutput())
                .setData1(pcPlus4)
                .setSignal(controlUnit.getJalr());
        
        this.registerFile.setWriteData(jalrMux.getOutput());
        
        int branchTarget=pcPlus4+(extendedImiidiate<<2);
        
        int branchSignal=(controlUnit.getBranch() & alu.isZero());
        
        this.branchMux
                .setData0(pcPlus4)
                .setData1(branchTarget)
                .setSignal(branchSignal);
        
        this.jMux
                .setData0(branchMux.getOutput())
                .setData1(instruction.getImmidiate()<<2)
                .setSignal(controlUnit.getJ());
        
        this.jalrAddressMux
                .setData0(jMux.getOutput())
                .setData1(this.registerFile.getReadData_1())
                .setSignal(controlUnit.getJ());
        
        this.pc=jalrAddressMux.getOutput();
        
        
        
    }
    
    //todo
    private int extendImmidiate(int immidiate,int ExtOp)
    {
        return 0;
    }
    
    
}
