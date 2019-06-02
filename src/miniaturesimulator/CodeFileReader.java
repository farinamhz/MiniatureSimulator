/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniaturesimulator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Moses
 */
public class CodeFileReader {
    private String filePath;

    public CodeFileReader(String filePath) {
        this.filePath = filePath;
    }
    
    public LinkedList<Integer> read() throws  IOException
    {
        FileInputStream fin=new FileInputStream(filePath);
        int i=0;
        LinkedList<Integer> lines=new LinkedList<>();
        StringBuilder line=new StringBuilder();
        while((i=fin.read())!=-1)
        {
            if(i=='\n' || i=='\r')
            {
                if(line.toString().trim().length()!=0)
                {
                   lines.add(Integer.valueOf(line.toString()));
                    line.setLength(0); 
                }
                
            }else
            {
                line.append((char)i);
            }
        }
        
        if(line.toString().trim().length()!=0)
            lines.add(Integer.valueOf(line.toString()));
        
        return lines;
    }
}
