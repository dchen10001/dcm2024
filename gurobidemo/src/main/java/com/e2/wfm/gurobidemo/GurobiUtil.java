package com.e2.wfm.gurobidemo;

import java.util.Map;

import com.gurobi.gurobi.GRB;
import com.gurobi.gurobi.GRBEnv;
import com.gurobi.gurobi.GRBModel;

public class GurobiUtil {
    public static final String GUROBI_KEY_11 = "0MXJN1TW";
    public static final String GUROBI_KEY_12 = "D4SNC4DY";
    public static final String GUROBI_ISV_NAME = "Nice";
    public static final String APPLICATION_NAME = "DCM";
    public static final String GUROBI_ISV_EXPIRATION_DATE = "20260101";

    public static GRBEnv getGurobiEnvironment(String logfile) throws Exception {
	    
	// don't set log to file, it may hit max limitation of file handler in windows
	GRBEnv env = new GRBEnv(true);
	
	int a = GRB.VERSION_MAJOR;
	int b = GRB.VERSION_MINOR;
	int c = GRB.VERSION_TECHNICAL;
	
	String version = String.format("%d.%d.%d", a, b, c);
	env.set(GRB.IntParam.OutputFlag, 0);
	env.set("GURO_PAR_ISVNAME", GUROBI_ISV_NAME);
	env.set("GURO_PAR_ISVAPPNAME", APPLICATION_NAME);
	env.set("GURO_PAR_ISVEXPIRATION", GUROBI_ISV_EXPIRATION_DATE);
	env.set("GURO_PAR_ISVKEY", GUROBI_KEY_11);
	
	env.set(GRB.IntParam.Threads, Runtime.getRuntime().availableProcessors());
	env.set("logFile", logfile);
	
	env.start();

	return env;
    }

    public static void dispose(GRBEnv env) {
	try {
	    if(env != null) {
		env.dispose();
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public static void dispose(GRBModel model) {
	try {
	    if(model != null) {
		model.dispose();
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }    
}
