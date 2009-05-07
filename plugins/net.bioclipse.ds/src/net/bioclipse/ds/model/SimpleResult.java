/*******************************************************************************
 * Copyright (c) 2009 Ola Spjuth.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Ola Spjuth - initial API and implementation
 ******************************************************************************/
package net.bioclipse.ds.model;

public class SimpleResult implements ITestResult{

    private TestRun testRun;
    private String name;
    
    public TestRun getTestRun() {
        return testRun;
    }
    
    public void setTestRun( TestRun testRun ) {
        this.testRun = testRun;
    }

    public String getName() {
        return name;
    }

    
    public void setName( String name ) {
        this.name = name;
    }

    

}