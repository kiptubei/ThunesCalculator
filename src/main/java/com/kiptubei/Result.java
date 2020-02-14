package com.kiptubei;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="result")
public class Result {
	private String inputOne;
	private String inputTwo;
	private String output;
    private String description;
    
    
    public Result(){}

    public Result(String description) {
        this.description = description;
    }
    
   
    public String getInputOne() {
        return inputOne;
    }
    public void setInputOne(String inputOne) {
        this.inputOne = inputOne;
    }
    
    public String getInputTwo() {
        return inputTwo;
    }
    public void setInputTwo(String inputTwo) {
        this.inputTwo = inputTwo;
    }
    
    public String getOutput() {
        return output;
    }
    public void setOutput(String output) {
        this.output = output;
    }
    
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

}
