
package model;

import com.fasterxml.jackson.annotation.JsonView;


public class Moves {
    @JsonView(Views.StatusOnly.class)
    private int row;
    @JsonView(Views.StatusOnly.class)
    private int column;
    @JsonView(Views.StatusOnly.class)
    private String value;
    
    public Moves(int row,int column, String value){
        this.row=row;
        this.column=column;
        this.value=value;
    }
    
    public int getRow(){
        return row;
    }
    public int getColumn(){
        return column;
    }
    public String getValue(){
        return value;
    }
    
    public void setRow(int row){
        this.row=row;
    }
    public void setColumn(int column){
        this.column=column;
    }
    public void setValue(String value){
        this.value=value;
    }
    
}
