/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniaturesimulator.AlU;

/**
 *
 * @author Moses
 */
public interface ALU {
    public int calculate(int data1,int data2,int control);
    public boolean isZero();
}
