package com.e2.wfm.gurobidemo;

import com.gurobi.gurobi.GRB;
import com.gurobi.gurobi.GRBEnv;
import com.gurobi.gurobi.GRBException;

public class GurobiMain {

	public static void main(String[] args) throws GRBException {
		GRBEnv env = new GRBEnv(true);
		env.set("logFile", "mip1.log");
		env.set(GRB.StringParam.CloudAccessID, "NICE-DCM");
        env.set(GRB.StringParam.CloudSecretKey, "D4SNC4DY");
		env.start();
	}

}
