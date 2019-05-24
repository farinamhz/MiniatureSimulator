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
public class Mux {
    private int signal=0;
    private int data1=0;
    private int data0=0;
    
    public int getOutput()
    {
        if(signal==0)
            return data0;
        else
            return data1;
    }

    public Mux setSignal(int signal) {
        this.signal = signal;
        return this;
    }

    public Mux setData1(int data1) {
        this.data1 = data1;
        return this;
    }

    public Mux setData0(int data0) {
        this.data0 = data0;
        return this;
    }
    
    
}
