/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniaturesimulator.AlU;

/**
 *
 * @author FARINAM
 */
public class AlU_Ultimate implements ALU {
    boolean isZero =false ;
    int output =0;
    @Override
    public int calculate(int data1, int data2, int control) {
        switch(control){
           case 0:
               return add(data1,data2);

           case 1:
               return sub(data1,data2);

           case 2:
               return or(data1,data2);

           case 6:
               return and(data1,data2);

           case 7:
               return slt(data1,data2);
           default:
               return 0;
        }
    }

    @Override
    public int isZero() {
         
        if (isZero == false ){
                return 0;
        }
        else{
                return 1;
        }        
    }

    public int add(int data1, int data2) {
    
        return data1+data2;
    }

    public int sub(int data1, int data2) {
        if (data1-data2 == 0)
            isZero=true;
        return data1-data2;
    }

    public int or(int data1, int data2) {
        
        return (data1 | data2);
    }

    public int and(int data1, int data2) {
    
        return (data1 & data2);
    }

    public int slt(int data1, int data2) {
            if(data1-data2 < 0) 
                return 1;
            else 
                return 0;
    }
    
}